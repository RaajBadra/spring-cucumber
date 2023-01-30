package com.test.library;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.awaitility.Durations;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.support.serializer.JsonSerializer;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;
import org.springframework.test.annotation.DirtiesContext;

import com.test.springkafkatest.dto.Message;

import java.util.Map;
import java.util.concurrent.ExecutionException;

import static org.awaitility.Awaitility.await;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DirtiesContext
@EmbeddedKafka(topics = {"TOPIC_EXAMPLE", "TOPIC_EXAMPLE_EXTERNE"})
public class ConsumeAndProduce {
    Logger log = LoggerFactory.getLogger(ConsumeAndProduce.class);

    private static final String TOPIC_EXAMPLE = "TOPIC_EXAMPLE";

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    public void produce() throws ExecutionException, InterruptedException {
        // GIVEN
        Message exampleDTO = new Message("123456789", "test", "test");
        // simulation consumer
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafkaBroker.getBrokersAsString());
        log.info("props {}", producerProps);
        Producer<String, Message> producerTest = new KafkaProducer(producerProps, new StringSerializer(), new JsonSerializer<Message>());
        producerTest.send(new ProducerRecord(TOPIC_EXAMPLE, 0, "", exampleDTO));
    }
}