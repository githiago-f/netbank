package com.netbank.common.bank.accounts;

import java.util.Date;

import com.netbank.common.dto.BankAccountType;
import com.netbank.common.events.BaseEvent;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankAccountOpenedEvent extends BaseEvent {
    private String accountHolder;
    private BankAccountType accountType;
    private Double openingBalance;
    private Date createdAt;
}
