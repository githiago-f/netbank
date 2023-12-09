package com.netbank.common.commands;

import com.netbank.common.messages.Message;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public abstract class BaseCommand extends Message {
    public BaseCommand(String id) {
        super(id);
    }
}
