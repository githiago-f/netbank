package com.netbank.common.events;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface EventStoreRepository extends MongoRepository<EventModel, String> {
    List<EventModel> findByAggregateIdentifier(String aggregateIdentifier);
    
    @Aggregation(pipeline = {
        "{'$match': { 'aggregateIdentifier': ?0 }}",
        "{'$sort': {'version': -1 }}",
        "{'$limit': 1}"
    })
    List<EventModel> findLastAggregateVersion(String aggregateIdentifier);
}
