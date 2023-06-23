package com.spring.RestApiGitHub.model;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface EventDataRepository extends MongoRepository<EventData, String> , EventDataCustomRepository{
}
