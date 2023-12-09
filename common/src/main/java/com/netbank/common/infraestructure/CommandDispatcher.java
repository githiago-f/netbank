package com.netbank.common.infraestructure;

import com.netbank.common.commands.BaseCommand;
import com.netbank.common.commands.CommandHandlerMethod;

public interface CommandDispatcher {
    <T extends BaseCommand> 
    void registerHandler(Class<T> type, CommandHandlerMethod<T> handler);
    void send(BaseCommand command);
}
