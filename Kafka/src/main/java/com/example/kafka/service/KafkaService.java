package com.example.kafka.service;

import org.springframework.kafka.annotation.KafkaListener;

public interface KafkaService {

	void sendMessage(String topic, String messages);

	String receive(String message);

	void createTopic(String topicName);
}
