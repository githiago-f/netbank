package com.netbank.account.app.commands;

import com.netbank.common.commands.BaseCommand;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DepositFunds extends BaseCommand {
    private Double amount;
}
