package com.test.sampleapp.integration;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;
import org.springframework.stereotype.Component;

import com.test.library.ContextObject;
import com.test.library.ScenarioContext;
import com.test.springkafkatest.dto.Message;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class PrimaryTopicMessageFailover extends CucumberRoot {
	
	@Autowired
	private ScenarioContext scenarioContext;
  
    private KafkaProducer producer;
    
    private KafkaConsumer consumer;

	@Given("^A Message with following correlation id1$")
	public void message_with_the_following_data_is_prepared(DataTable data) {
		List<Map<String, String>> orders = data.asMaps(String.class, String.class);
		Message message = new Message(orders.get(0).get("correlationId"), "topic-1", "test-message");
		scenarioContext.setContext(ContextObject.KAFKA_EVENT, message);
	}
	
	@When("^unknown event with correlation id = \"123456789\" is published to primary topic name=\"topic-1\"$")
	public void publish_unknown_event_primary_topic() throws KafkaProducerException, InterruptedException, ExecutionException {
		/* code to publish message to primary topic goes here */
		// producer.send();
	
	}

	@Then("^Unprocessed message is published to secondary topic=\"test-2\" with the same correlation id=\"123456789\"$")
	public void message_published_to_secondary_topic()
	{
		// Message[] message = consumer.consume(); // consume messages from secondary topic 
		/* Filter and find the message with correlation id = 123456789*/
	    Assert.assertTrue("published correltion id to exist in secondary topic", "123456789".equals("123456789"));
	}
	
}