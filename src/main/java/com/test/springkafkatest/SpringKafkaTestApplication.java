package com.test.springkafkatest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@SpringBootApplication(scanBasePackages={
"com.test.service", "com.test.springkafkatest", "com.test.library", "org.apache.kafka.clients.producer"})
@EnableJpaRepositories
@Component
@Service
public class SpringKafkaTestApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaTestApplication.class, args);
	}

}
