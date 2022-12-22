package br.com.coffeeandit.apilimitesvc.service;

import java.math.BigDecimal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;
import br.com.coffeeandit.apilimitesvc.repository.LimiteDiarioRepository;

@Service
public class LimiteDiarioService {
	
	private final LimiteDiarioRepository limiteDiarioRepository;
	
	@Value("${limite.valor}")
	private BigDecimal valorDiario;

	public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
		this.limiteDiarioRepository = limiteDiarioRepository;
	}
	
	public Optional<LimiteDiario> buscarLimiteDiario(final Long agencia, final Long conta) {
		var byAgenciaAndConta = limiteDiarioRepository.findByAgenciaAndConta(agencia, conta);
	
		if (byAgenciaAndConta.isEmpty()) {
			var limiteDiario = new LimiteDiario();
			limiteDiario.setAgencia(agencia);
			limiteDiario.setConta(conta);
			limiteDiario.setValor(valorDiario);
			return Optional.of(limiteDiarioRepository.save(limiteDiario));
		}
		
		return byAgenciaAndConta;
	
	}
	
	public Optional<LimiteDiario> findById(Long id) {
		return limiteDiarioRepository.findById(id);
	}

}
