package br.com.coffeeandit.apitransactionsvc.events;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Objects;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.coffeeandit.apitransactionsvc.domain.TransactionBusiness;
import br.com.coffeeandit.apitransactionsvc.dto.SituacaoEnum;
import br.com.coffeeandit.apitransactionsvc.dto.TransactionDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionConsumer {
	
	private final ObjectMapper objectMapper;
	private final TransactionBusiness transactionBusiness;

	public TransactionConsumer(ObjectMapper objectMapper, TransactionBusiness transactionBusiness) {
		this.objectMapper = objectMapper;
		this.transactionBusiness = transactionBusiness;
	}
	
	@KafkaListener(topics = "${events.consumeTopic}")
	public void consumeTransaction(String message) {
		try {
			var transaction = getTransaction(message);
			transactionBusiness.atualizarTransacao(transaction);
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	@KafkaListener(topics = "${events.returnTopic}")
	public void consumeTransactionExtorno(String message) {
		try {
			var transaction = getTransaction(message);
			log.info("Transação recebida para analise: {}", transaction);
			if (!transaction.isAnalisada()) {
				return;
			} else {
				log.info("Transação analisada: {}", transaction);
				transactionBusiness.aprovarTransacao(transaction);
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
	
	
	
	
	private TransactionDTO getTransaction(String message) throws IOException {
		var transactionDTO = objectMapper.readValue(message, TransactionDTO.class);
		
		if (Objects.isNull(transactionDTO.getSituacao())) {
			transactionDTO.setSituacao(SituacaoEnum.NAO_ANALISADA);
		}
		
		transactionDTO.setData(LocalDateTime.now());
		return transactionDTO;
		
	}
	
	
	

}
