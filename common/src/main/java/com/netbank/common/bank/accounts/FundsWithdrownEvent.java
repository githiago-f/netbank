package com.netbank.common.bank.accounts;

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
public class FundsWithdrownEvent extends BaseEvent {
    private Double amount;
}
