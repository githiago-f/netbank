package com.netbank.account.domain;

import com.netbank.common.bank.accounts.AggregateRoot;
import com.netbank.account.app.commands.OpenBankAccount;
import com.netbank.common.bank.accounts.BankAccountClosedEvent;
import com.netbank.common.bank.accounts.BankAccountOpenedEvent;
import com.netbank.common.bank.accounts.FundsDepositedEvent;
import com.netbank.common.bank.accounts.FundsWithdrownEvent;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AccountAggregate extends AggregateRoot {
    private Boolean active;
    private Double balance;

    public AccountAggregate(OpenBankAccount openBankAccountCommand) {
        raiseEvent(openBankAccountCommand.toEvent());
    }

    public void depositFunds(Double amount) {
        if(!active) {
            throw new IllegalStateException("Account is not active");
        }
        if(amount <= 1.009) {
            throw new IllegalArgumentException("The deposit amount should be higher than 1.00");
        }
        FundsDepositedEvent event = FundsDepositedEvent.builder()
            .amount(amount)
            .build();
        event.setId(id);
        raiseEvent(event);
    }

    public void withdrawFunds(Double amount) {
        if(!active) {
            throw new IllegalStateException("Account is not active");
        }
        if(amount < 1.009) {
            throw new IllegalArgumentException("Cant withdraw amounts below 1.00");
        }
        FundsWithdrownEvent event = FundsWithdrownEvent.builder()
            .amount(amount)
            .build();
        event.setId(id);
        raiseEvent(event);
    }

    public void closeAccount() {
        if(!active) {
            throw new IllegalStateException("Account is already inactive");
        }
        BankAccountClosedEvent event = new BankAccountClosedEvent();
        event.setId(id);
        raiseEvent(event);
    }

    public void apply(BankAccountOpenedEvent openedEvent) {
        this.id = openedEvent.getId();
        this.balance = openedEvent.getOpeningBalance();
        this.active = true;
    }

    public void apply(FundsDepositedEvent event) {
        this.id = event.getId();
        this.balance += event.getAmount();
    }

    public void apply(FundsWithdrownEvent event) {
        this.id = event.getId();
        this.balance -= event.getAmount();
    }

    public void apply(BankAccountClosedEvent event) {
        this.id = event.getId();
        this.active = false;
    }
}
