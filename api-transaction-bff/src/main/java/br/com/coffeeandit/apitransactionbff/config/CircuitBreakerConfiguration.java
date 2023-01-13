package br.com.coffeeandit.apitransactionbff.config;

import java.time.Duration;

import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class CircuitBreakerConfiguration {
	
	//Posso configurar via anotação e as propriedades no yml

	@Bean
	public CircuitBreaker countCircuitBreaker() {
	//config
	CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
			.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.COUNT_BASED)
			.slidingWindowSize(5)
			.slowCallRateThreshold(65.0f)
			.slowCallDurationThreshold(Duration.ofSeconds(2))
			.build();
	//registro
	CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
	
	//nomeação
	CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("limitsSearchBasedCount");
	
		return cb;
	}
	
	@Bean
	public CircuitBreaker timeCircuitBreaker() {
		//config
		CircuitBreakerConfig circuitBreakerConfig = CircuitBreakerConfig.custom()
				.slidingWindowType(CircuitBreakerConfig.SlidingWindowType.TIME_BASED)
				.minimumNumberOfCalls(3)
				.slidingWindowSize(5)
				.failureRateThreshold(70.0f)
				.waitDurationInOpenState(Duration.ofDays(10))
				.writableStackTraceEnabled(false)
				.build();
		//registro
		CircuitBreakerRegistry circuitBreakerRegistry = CircuitBreakerRegistry.of(circuitBreakerConfig);
		
		//nomeação
		CircuitBreaker cb = circuitBreakerRegistry.circuitBreaker("limitsSearchBasedOnTime");
		
			return cb;
	}
}
