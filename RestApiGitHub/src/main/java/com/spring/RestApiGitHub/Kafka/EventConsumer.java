package com.spring.RestApiGitHub.Kafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.spring.RestApiGitHub.model.EventData;
import com.spring.RestApiGitHub.model.EventDataRepository;

@Component
public class EventConsumer {

    private final EventDataRepository eventDataRepository;

    @Autowired
    public EventConsumer(EventDataRepository eventDataRepository) {
        this.eventDataRepository = eventDataRepository;
    }

    @KafkaListener(topics = "organizations", groupId = "1")
    public void consumeOrganizationEvent(String message) {
        saveEventData("organizations", message);
        System.out.println("Received organization event: " + message);
    }

    @KafkaListener(topics = "members", groupId = "1")
    public void consumeOrganizationMembersEvent(String message) {
        saveEventData("members", message);
        System.out.println("Received organization members event: " + message);
    }

    @KafkaListener(topics = "repos", groupId = "1")
    public void consumeOrganizationReposEvent(String message) {
        saveEventData("repos", message);
        System.out.println("Received organization repositories event: " + message);
    }

    @KafkaListener(topics = "events", groupId = "1")
    public void consumeRepositoryEventsEvent(String message) {
        saveEventData("events", message);
        System.out.println("Received repository events event: " + message);
    }

    @KafkaListener(topics = "contributors", groupId = "1")
    public void consumeRepositoryContributorsEvent(String message) {
        saveEventData("contributors", message);
        System.out.println("Received repository contributors event: " + message);
    }

    @KafkaListener(topics = "pulls", groupId = "1")
    public void consumeRepositoryPullsEvent(String message) {
        saveEventData("pulls", message);
        System.out.println("Received repository pulls event: " + message);
    }

    @KafkaListener(topics = "commits", groupId = "1")
    public void consumeRepositoryCommitsEvent(String message) {
        saveEventData("commits", message);
        System.out.println("Received repository commits event: " + message);
    }

    private void saveEventData(String topic, String message) {
        EventData eventData = new EventData();
        eventData.setTopic(topic);
        eventData.setData(message);
        eventDataRepository.save(eventData, topic); // Save with different collection name
    }
}

