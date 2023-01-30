package com.test.springkafkatest.dto;

import java.io.Serializable;
import lombok.*;
import javax.persistence.*;


public class Message {
    private String correlationId;
    private String topicName;
    private String message;
	public Message(String correlationId, String topicName, String message) {
		super();
		this.correlationId = correlationId;
		this.topicName = topicName;
		this.message = message;
	}
	public String getCorrelationId() {
		return correlationId;
	}
	public void setCorrelationId(String correlationId) {
		this.correlationId = correlationId;
	}
	public String getTopicName() {
		return topicName;
	}
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

    
}