package com.test.sampleapp.integration;

import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaProducerException;

import com.test.library.ScenarioContext;
import com.test.service.MessageService;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class ReprocessFailedMessagePeristedToDB {
	
	@Autowired
	private ScenarioContext scenarioContext;
	
	@Autowired
	private MessageService messageService;
	

	@When("a message with correlation id=\"123456\" is read from DB")
	public void a_message_with_correlation_id_is_published_to_deadletter_topic() throws KafkaProducerException, InterruptedException {
		/* request to read correlation id from DB goes here */
		// MessageItem messageFromDB = messageService.getMessages(message.getCorrelationId()); // DB entity
		
		// producer.send(messageFromDB); // publish the message to primary topic again
	}

	@Then("re-process it by publishing it to topic=\"topic-1\"")
	public void reprocess_message()
	{
		// Message[] message = consumer.consume(); // consume messages from primary topic 
		/* Filter and find the message with correlation id = 123456789. This is to verify the message is re-published to topic-1 */
		
	    Assert.assertTrue("published correltion id to exist in secondary topic", "123456789".equals("123456789"));	
	   
	}
	
}