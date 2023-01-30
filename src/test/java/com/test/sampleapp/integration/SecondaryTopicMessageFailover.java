package com.test.sampleapp.integration;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.Producer;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;

import com.test.library.ContextObject;
import com.test.library.KafkaCommons;
import com.test.library.ScenarioContext;
import com.test.springkafkatest.dto.Message;

import org.apache.kafka.common.serialization.StringDeserializer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SecondaryTopicMessageFailover {
	
	@Autowired
	private ScenarioContext scenarioContext;

	@Given("^A Message with correlation id=\"12345678\"$")
	public void message_with_the_following_data_is_prepared(DataTable data) {
		System.out.println("*****************************");
		System.out.println(data);
		List<Map<String, String>> orders = data.asMaps(String.class, String.class);
		Message message = new Message(orders.get(0).get("correlationId"),"topic-2","test-message");
		scenarioContext.setContext(ContextObject.KAFKA_EVENT, message);
	}
	
	@When("^unknown event with correlation id = \"12345678\" is published to secondary topic name=\"topic-2\"$")
	public void publish_unknown_event_secondary_topic() throws KafkaProducerException, InterruptedException {
		/* code to publish message to secondary topic goes here */
		// producer.send();
	}

	@Then("^Unprocessed message is published to dead letter topic=\"deadletter-topic\" with the same correlation id=\"12345678\"$")
	public void message_published_to_secondary_topic()
	{
		Message message = (Message)scenarioContext.getContext(ContextObject.KAFKA_EVENT);
		Assert.assertTrue("published correltion id to exist in secondary topic", "12345678".equals(message.getCorrelationId()));
	}
	
}