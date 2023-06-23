package com.spring.RestApiGitHub.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Document(collection = "events")
public class EventData {

    @Id
    private String id;
    private String topic;
    private String data;
    
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTopic() {
		return topic;
	}
	public void setTopic(String topic) {
		this.topic = topic;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}

    
}

