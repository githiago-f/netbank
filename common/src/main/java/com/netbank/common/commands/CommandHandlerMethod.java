package com.netbank.common.commands;

@FunctionalInterface
public interface CommandHandlerMethod<T extends BaseCommand> {
    void handle(BaseCommand command);
}
