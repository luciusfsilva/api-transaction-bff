package br.com.coffeeandit.apilimitesvc.events;

import java.io.IOException;
import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.coffeeandit.apilimitesvc.dto.TransactionDTO;
import br.com.coffeeandit.apilimitesvc.service.LimiteDiarioService;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LimiteConsumer {
	
	private LimiteDiarioService limiteDiarioService;
	private ObjectMapper objectMapper;
	
	public LimiteConsumer(LimiteDiarioService limiteDiarioService, ObjectMapper objectMapper) {
		this.limiteDiarioService = limiteDiarioService;
		this.objectMapper = objectMapper;
	}
	
	@KafkaListener(topics = "${app.topic}")
	public void onConsume(final String message) {
		try {
			var transactionDTO = getTransaction(message);
			limiteDiarioService.validarLimiteDiario(transactionDTO);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
		
	}
	
	//Consome o tópico
	private TransactionDTO getTransaction(String message) throws IOException {
		var transactionDTO = objectMapper.readValue(message, TransactionDTO.class);
		transactionDTO.setData(LocalDateTime.now());
		return transactionDTO;
		
	}

}
