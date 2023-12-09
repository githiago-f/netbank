package com.netbank.account.infrastructure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.netbank.common.commands.BaseCommand;
import com.netbank.common.commands.CommandHandlerMethod;
import com.netbank.common.infraestructure.CommandDispatcher;

@Component
public class BankAccountCommandDispatcher implements CommandDispatcher {
    private Map<Class<? extends BaseCommand>, List<CommandHandlerMethod>> routes = new HashMap<>();

    @Override
    public <T extends BaseCommand> void registerHandler(Class<T> type, 
        CommandHandlerMethod<T> handler) {
        List<CommandHandlerMethod> handlers = 
            routes.computeIfAbsent(type, c -> new LinkedList<>());
        handlers.add(handler);
    }

    @Override
    public void send(BaseCommand command) {
        List<CommandHandlerMethod> handlers = routes.get(command.getClass());
        if(handlers == null || handlers.size() == 0) {
            throw new RuntimeException("Cannot find any command handlers");
        }
        if(handlers.size() > 1) {
            throw new RuntimeException("Cannot have two command handlers for the same command");
        }

        handlers.get(0).handle(command);
    }
    
}
