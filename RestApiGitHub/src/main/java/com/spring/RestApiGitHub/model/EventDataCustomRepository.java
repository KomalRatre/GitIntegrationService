package com.spring.RestApiGitHub.model;

public interface EventDataCustomRepository {
	void save(EventData eventData, String topic);
}
