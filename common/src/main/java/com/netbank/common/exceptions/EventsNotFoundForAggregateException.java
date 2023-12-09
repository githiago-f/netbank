package com.netbank.common.exceptions;

public class EventsNotFoundForAggregateException extends RuntimeException {
    public EventsNotFoundForAggregateException() {
        super("Events not found for this aggregate");
    }
}
