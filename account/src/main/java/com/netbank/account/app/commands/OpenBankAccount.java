package com.netbank.account.app.commands;

import java.util.Date;

import com.netbank.common.bank.accounts.BankAccountOpenedEvent;
import com.netbank.common.commands.BaseCommand;
import com.netbank.common.dto.BankAccountType;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class OpenBankAccount extends BaseCommand {
    private String holder;
    private BankAccountType bankAccountType;
    private Double openingBalance;

    public BankAccountOpenedEvent toEvent() {
        BankAccountOpenedEvent event = BankAccountOpenedEvent.builder()
            .accountHolder(getHolder())
            .accountType(getBankAccountType())
            .openingBalance(getOpeningBalance())
            .createdAt(new Date())
            .build();
        event.setId(this.id);
        return event;
    }
}
