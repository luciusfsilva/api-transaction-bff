package br.com.coffeeandit.apitransactionbff.domain;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.retry.support.RetryTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.coffeeandit.apitransactionbff.dto.RequestTransactionDTO;
import br.com.coffeeandit.apitransactionbff.dto.SituacaoEnum;
import br.com.coffeeandit.apitransactionbff.dto.TransactionDTO;
import br.com.coffeeandit.apitransactionbff.exception.NotFoundException;
import br.com.coffeeandit.apitransactionbff.feign.TransactionClient;
import br.com.coffeeandit.apitransactionbff.redis.TransactionRedisRepository;
import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.SignalType;

@Service
@Slf4j
public class TransactionService {
	
	@Value("${app.topic}")
	private String topic;
	
	private final TransactionRedisRepository transactionRedisRepository;
	private final RetryTemplate retryTemplate;
	private final ReactiveKafkaProducerTemplate<String, RequestTransactionDTO> reactiveKafkaProducerTemplate;
	private final TransactionClient transactionClient;
	
	public TransactionService(TransactionRedisRepository transactionRedisRepository, RetryTemplate retryTemplate, 
			ReactiveKafkaProducerTemplate<String, RequestTransactionDTO> reactiveKafkaProducerTemplate,
			TransactionClient transactionClient) {
		this.transactionRedisRepository = transactionRedisRepository;
		this.retryTemplate = retryTemplate;
		this.reactiveKafkaProducerTemplate = reactiveKafkaProducerTemplate;
		this.transactionClient = transactionClient;
	}
	
	//Uma forma de implementar o retry
	@Transactional
	@Retryable(value = QueryTimeoutException.class, maxAttempts = 5, backoff = @Backoff(delay = 100))
	public Mono<RequestTransactionDTO> save (final RequestTransactionDTO requestTransactionDTO) {
		return Mono.fromCallable(() -> {
			requestTransactionDTO.setData(LocalDateTime.now());
			requestTransactionDTO.setSituacao(SituacaoEnum.NAO_ANALISADA);
			return transactionRedisRepository.save(requestTransactionDTO);
			
		}).doOnError(throwable -> {
			log.error(throwable.getMessage(), throwable);
			throw new NotFoundException("Unable to find resource");
			
		}).doOnSuccess(requestTransactionDTO01 -> {
					log.info("Transação persistida com sucesso - ", requestTransactionDTO01);
					reactiveKafkaProducerTemplate.send(topic, requestTransactionDTO)
						.doOnSuccess(voidSendResult -> log.info(voidSendResult.toString()))
						.subscribe();
					
		}).doFinally(signalType -> { 
					if (signalType.compareTo(SignalType.ON_COMPLETE) == 0) {
						log.info("Mensagem enviada para o Kafka com sucesso - " + requestTransactionDTO);
					}
				});
	}
	
	//Segunda forma de implementar o retry
	public Optional<TransactionDTO> findById(String id) {
		return retryTemplate.execute(ret -> {
			log.info("Consultando Redis");
			return transactionRedisRepository.findById(id);
		});
	}
	
	public Flux<List<TransactionDTO>> findByAgenciaAndContaFlux(final Long agencia, final Long conta) {
		var transactionDTOList = findByAgenciaAndConta(agencia, conta);
		return Flux.fromIterable(transactionDTOList).cache(Duration.ofSeconds(2))
				.limitRate(200).defaultIfEmpty(new TransactionDTO())
				.buffer(200);
	}
	
	public List<TransactionDTO> findByAgenciaAndConta(final Long agencia, final Long conta) {
		return transactionClient.buscarTransacoes(agencia, conta);
	}

}
