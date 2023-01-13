package br.com.coffeeandit.apitransactionbff.config;

import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.reactive.ReactiveKafkaProducerTemplate;

import br.com.coffeeandit.apitransactionbff.dto.RequestTransactionDTO;
import reactor.kafka.sender.SenderOptions;

@Configuration
public class ReactiveKafkaProducerConfig {

	@Bean
	public ReactiveKafkaProducerTemplate<String, RequestTransactionDTO> reactiveKafkaProducerTemplate (
			final KafkaProperties kafkaProperties) {
		var props = kafkaProperties.buildProducerProperties();
		return new ReactiveKafkaProducerTemplate<String, RequestTransactionDTO>(SenderOptions.create(props));
	}
}
