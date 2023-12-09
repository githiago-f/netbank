package com.netbank.account.infrastructure.data;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Component;

import com.netbank.account.domain.AccountAggregate;
import com.netbank.common.events.BaseEvent;
import com.netbank.common.events.EventModel;
import com.netbank.common.events.EventStoreRepository;
import com.netbank.common.exceptions.ConcurrencyException;
import com.netbank.common.exceptions.EventsNotFoundForAggregateException;
import com.netbank.common.infraestructure.EventStore;

@Component
public class AccountEventStore implements EventStore {
    private EventStoreRepository repository;

    public AccountEventStore(EventStoreRepository repository) {
        this.repository = repository;
    }

    @Override
    public void saveEvent(String aggId, Iterable<BaseEvent> events, int expectedVersion) {
        List<EventModel> eventModels = repository.findLastAggregateVersion(aggId);
        EventModel model = eventModels.get(eventModels.size());
        if(expectedVersion != -1 && model.getVersion() != expectedVersion) {
            throw new ConcurrencyException();
        }
        int version = expectedVersion;
        for(BaseEvent event : events) {
            version++;
            event.setVersion(version);
            EventModel eventModel = EventModel.builder()
                .aggregateIdentifier(aggId)
                .eventType(event.getClass().getTypeName())
                .aggregateType(AccountAggregate.class.getName())
                .eventData(event)
                .version(version)
                .timestamp(new Date())
                .build();
            EventModel persistedEvent = repository.save(eventModel);
            if(persistedEvent != null) {
                // TODO producer kafka
            }
        }
    }

    @Override
    public List<BaseEvent> getEvents(String aggId) {
        List<EventModel> events = repository.findByAggregateIdentifier(aggId);
        if(events == null || events.isEmpty()) {
            throw new EventsNotFoundForAggregateException();
        }
        return events.stream().map(i -> i.eventData).toList();
    }
}
