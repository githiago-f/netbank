package com.netbank.common.events;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@Document(collection = "eventStore")
public class EventModel {
    @Id
    public String id;
    public Date timestamp;
    public String aggregateIdentifier;
    public String aggregateType;
    public Integer version;
    public String eventType;
    public BaseEvent eventData;
}
