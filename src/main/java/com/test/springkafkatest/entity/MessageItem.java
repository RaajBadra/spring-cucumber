package com.test.springkafkatest.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Messages")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
	@Column(name = "correlationId", nullable = false)
    private String correlationId;
    @Column(name = "topic_name", nullable = false)
    private String topicName;
    @Column(name = "message", nullable = false)
    private String message;

}

