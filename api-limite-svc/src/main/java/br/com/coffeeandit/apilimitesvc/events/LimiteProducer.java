package br.com.coffeeandit.apilimitesvc.events;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.coffeeandit.apilimitesvc.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LimiteProducer {
	
	@Value("${app.returnTopic}")
	private String topic;
	
	private KafkaTemplate<String, String> kafkaTemplate;
	private ObjectMapper objectMapper;
	
	public LimiteProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
		this.kafkaTemplate = kafkaTemplate;
		this.objectMapper = objectMapper;
	}
	
	public void enviar(final TransactionDTO transactionDTO) {
		try {
			String payload = objectMapper.writeValueAsString(transactionDTO);
			Message<String> message = org.springframework.messaging.support.MessageBuilder
				.withPayload(payload)
				.setHeader(KafkaHeaders.TOPIC, topic)
				.setHeader(KafkaHeaders.PARTITION_ID, 0)
				.build();
		
			kafkaTemplate.send(message);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	
	

}
