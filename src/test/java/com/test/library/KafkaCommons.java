package com.test.library;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.test.utils.KafkaTestUtils;


public class KafkaCommons {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaCommons.class);

	private KafkaCommons() {
		
	}

	public static <K,V, S, T> KafkaConsumer<K,V> createConsumer(String kafkaServer, String registryUrl, String topic, Class<K> key, Class<V> value, Class<S> keyDeser, Class<T> valueDeser) {
		Map<String, Object> consumerProps = KafkaTestUtils.consumerProps(
				kafkaServer,
				RandomData.getRandomUUID(),
				"true");
		consumerProps.put(ConsumerConfig.CLIENT_ID_CONFIG, "test-cons-"+RandomData.getRandomId());
		consumerProps.put(ConsumerConfig.GROUP_ID_CONFIG, "test-group-"+RandomData.getRandomId());
		consumerProps.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
		consumerProps.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, keyDeser);
		consumerProps.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, valueDeser);
		
		KafkaConsumer<K,V> consumer =  new KafkaConsumer<>(consumerProps);
		consumer.subscribe(Arrays.asList(topic));
		
		return consumer;
	}
	
	public static <K,V> Map<K,V> consumeMessages(KafkaConsumer<K,V> consumer) {
		Map<K,V> records = new HashMap<>();
		
		final int maxRetryCount = 5;
		for(int i=0; i<maxRetryCount; i++) {
			LOGGER.debug("[Test]runConsumer() Consuming...");
			ConsumerRecords<K, V> consumerRecords = KafkaTestUtils.getRecords(consumer, 2000);
			
			consumerRecords.forEach(record -> {
				LOGGER.debug("[Test]Consumer Record: Topic={}, Partition={}, Offset={}, Key = {} , Value={}" , record.topic(), record.partition(), record.offset() , record.key(), record.value());
				records.put(record.key(), record.value());
			});
			
		}
		return records;
	}
	
	public static <K,V> Optional<V> consumeMessageWithKey(KafkaConsumer<K,V> consumer, K key) {
		Map<K,V> records = new HashMap<>();
		
		final int maxRetryCount = 5;
		for(int i=0; i<maxRetryCount; i++) {
			LOGGER.debug("[Test]Consuming from partitions:{}",consumer.assignment());
			ConsumerRecords<K, V> consumerRecords = KafkaTestUtils.getRecords(consumer, 2000);
			
			consumerRecords.forEach(record -> {
				LOGGER.debug("[Test]Consumer Record: Topic={}, Partition={}, Offset={}, Key = {} , Value={}" , record.topic(), record.partition(), record.offset() , record.key(), record.value());
				records.put(record.key(), record.value());
			});
			
			if(records.containsKey(key)) {
				return Optional.ofNullable(records.get(key));
			}
		}
		return Optional.empty();
	}
	
	public static <K,V> void closeConsumer(KafkaConsumer<K,V> consumer) {
		if(consumer != null) {
			LOGGER.info("[Test]Closing consumer={} with assignment={}",consumer, consumer.assignment());
			consumer.close();
			LOGGER.info("[Test]Closed consumer={}", consumer);
		}
	}
	
	public static <K,V, S, T> Producer<K, V> createProducer(String kafkaServer, String registryUrl, Class<K> key, Class<V> value, Class<S> keySer, Class<T> valueSer) {
		Map<String, Object> producerProps = KafkaTestUtils.producerProps(kafkaServer);
		producerProps.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, keySer);
		producerProps.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSer);
		producerProps.put(ProducerConfig.CLIENT_ID_CONFIG, "test-prod-"+RandomData.getRandomId());
		
		return new KafkaProducer<K, V>(producerProps, null, null);
	}
	
	public static <K,V> void produceMessage(Producer<K,V> producer, String topic, K key, V value) {
		Future<RecordMetadata> record = producer.send(new ProducerRecord<>(topic, key, value));
		producer.flush();
		try {
			RecordMetadata metadata = record.get(2, TimeUnit.SECONDS);
			LOGGER.info("[Test]Produced message to Topic={} with Partition={}, Offset={}, key={}, value={} to topic ={}",metadata.topic(),metadata.partition(),metadata.offset(),key,value);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			LOGGER.error(e.getMessage(),e);
		}

	}
	
	public static <K,V> void closeProducer(Producer<K,V> producer) {
		if(producer != null) {
			LOGGER.info("[Test]Closing producer={}", producer.toString());
			producer.close();
			LOGGER.info("[Test]Closed producer={}", producer.toString());
		}
		
	}

}
