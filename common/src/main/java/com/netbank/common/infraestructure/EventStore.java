package com.netbank.common.infraestructure;

import java.util.List;

import com.netbank.common.events.BaseEvent;

public interface EventStore {
    void saveEvent(String aggId, Iterable<BaseEvent> events, int expectedVersion);
    /**
     * @param aggId stands for aggregate identifyier
     */
    List<BaseEvent> getEvents(String aggId);
}
