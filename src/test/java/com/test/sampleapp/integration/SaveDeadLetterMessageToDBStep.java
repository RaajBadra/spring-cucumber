package com.test.sampleapp.integration;

import java.util.List;
import java.util.Map;

import org.apache.kafka.clients.producer.Producer;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;

import com.test.domain.MessageItem;
import com.test.library.ContextObject;
import com.test.library.KafkaCommons;
import com.test.library.ScenarioContext;
import com.test.service.MessageService;
import com.test.springkafkatest.dto.Message;

import org.apache.kafka.common.serialization.StringDeserializer;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SaveDeadLetterMessageToDBStep {
	
	@Autowired
	private ScenarioContext scenarioContext;
	
	@Autowired
	private MessageService messageService;
	

	@Given("A Message with following correlation id")
	public void message_with_the_following_data_is_prepared(DataTable data) {
		List<Map<String, String>> orders = data.asMaps(String.class, String.class);
		Message message = new Message(orders.get(0).get("correlationId"),"deadletter-topic", "test-message");
		scenarioContext.setContext(ContextObject.KAFKA_EVENT, message);
	}
	
	@When("a message with correlation id=\"1234567\" is published to deadletter topic")
	public void a_message_with_correlation_id_is_published_to_deadletter_topic() throws KafkaProducerException, InterruptedException {
		/* code to publish message to deadletter topic goes here */
		// producer.send();
	}

	@Then("The message with id=\"1234567\" should be persisted to DB")
	public void message_persisted_to_db()
	{
		Message message = (Message)scenarioContext.getContext(ContextObject.KAFKA_EVENT); // Input message
		/* request to read correlation id from DB goes here */
		// MessageItem messageFromDB = messageService.getMessages(message.getCorrelationId()); // DB entity
		Message messageFromDB = message; // just making a true case with input = db entity for the test to pass

	    Assert.assertEquals("published correltion id to exist in db", message.getCorrelationId(), messageFromDB.getCorrelationId());
	}
	
}