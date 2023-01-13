package br.com.coffeeandit.apitransactionbff.domain;

import java.math.BigDecimal;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.coffeeandit.apitransactionbff.dto.LimiteDiario;
import br.com.coffeeandit.apitransactionbff.feign.LimiteClient;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import io.github.resilience4j.decorators.Decorators;
import reactor.core.publisher.Mono;

@Service
public class LimiteService {

	private final LimiteClient limiteClient;
	
	@Autowired
	private CircuitBreaker countCircuitBreaker;  

	public LimiteService(LimiteClient limiteClient) {
		this.limiteClient = limiteClient;
	}
	
	public Mono<LimiteDiario> buscarLimiteDiario(final Long agencia, final Long conta) {
		return buscarLimiteSupplier(agencia, conta);
	}
	
	private Mono<LimiteDiario> buscarLimiteSupplier(final Long agencia, final Long conta) {
		var limiteDiarioSup = countCircuitBreaker.decorateSupplier(() -> limiteClient.buscarLimiteDiario(agencia, conta)
				);
		
		return Mono.fromSupplier(
				Decorators.ofSupplier(limiteDiarioSup)
				.withCircuitBreaker(countCircuitBreaker)
				.withFallback(Arrays.asList(CallNotPermittedException.class),
						e -> this.getStaticLimit())
				.decorate());
				
	}
	
	private LimiteDiario getStaticLimit() {
		LimiteDiario limiteDiario = new LimiteDiario();
		limiteDiario.setValor(BigDecimal.ZERO);
		return limiteDiario;
	}
}
