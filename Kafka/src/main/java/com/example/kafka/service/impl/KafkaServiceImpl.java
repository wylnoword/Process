package com.example.kafka.service.impl;

import com.example.kafka.service.KafkaService;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.common.protocol.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;

@Service
@Slf4j
public class KafkaServiceImpl implements KafkaService {

	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;

	@Override
	public void sendMessage(String topic, String messages) {
		ListenableFuture<SendResult<String, String>> send = kafkaTemplate.send(topic, messages);
	}

	@Override
	@KafkaListener(topics = "test")
	public String receive(String message) {
		log.info("receive a message {} ", message);
		return message;
	}

	@Override
	public void createTopic(String topicName) {
		kafkaTemplate.setDefaultTopic(topicName);
	}
}
