package com.spring.RestApiGitHub.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class EventDataCustomRepositoryImpl implements EventDataCustomRepository {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public void save(EventData eventData, String topic) {
        mongoTemplate.save(eventData, topic);
    }
}
