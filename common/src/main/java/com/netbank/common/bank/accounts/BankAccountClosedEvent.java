package com.netbank.common.bank.accounts;

import com.netbank.common.events.BaseEvent;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class BankAccountClosedEvent extends BaseEvent {}
