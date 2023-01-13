package br.com.coffeeandit.apilimitesvc.service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.coffeeandit.apilimitesvc.dto.TransactionDTO;
import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;
import br.com.coffeeandit.apilimitesvc.events.LimiteProducer;
import br.com.coffeeandit.apilimitesvc.repository.LimiteDiarioRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class LimiteDiarioService {
	
	private final LimiteDiarioRepository limiteDiarioRepository;
	private final LimiteProducer limiteProducer;
	
	@Value("${limite.valor}")
	private BigDecimal valorDiario;

	public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository, LimiteProducer limiteProducer) {
		this.limiteDiarioRepository = limiteDiarioRepository;
		this.limiteProducer = limiteProducer;
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
	
	public void validarLimiteDiario(TransactionDTO transactionDTO) {
		var limiteDiario = limiteDiarioRepository.findByAgenciaAndContaAndData(transactionDTO.getConta().getCodigoAgencia(), transactionDTO.getConta().getCodigoConta(), 
				LocalDateTime.now());
		
		if (Objects.isNull(limiteDiario)) {
			limiteDiario = new LimiteDiario();
			limiteDiario.setAgencia(transactionDTO.getConta().getCodigoAgencia());
			limiteDiario.setConta(transactionDTO.getConta().getCodigoConta());
			limiteDiario.setValor(valorDiario);
			limiteDiario.setData(transactionDTO.getData());
			limiteDiario = limiteDiarioRepository.save(limiteDiario);
		}
		
		if (limiteDiario.getValor().compareTo(transactionDTO.getValor()) < 0) {
			transactionDTO.suspeitaFraude();
			log.info("Transação excede valor diário: {}", transactionDTO);
		}
		else if (limiteDiario.getValor().compareTo(BigDecimal.valueOf(1000000l)) > 0) {
			transactionDTO.analiseHumana();;
			log.info("Transação está em análise humana: {}", transactionDTO);
			
		}
		else {
			transactionDTO.analisada();
			log.info("Transação analisada.");
			limiteDiario.setValor(limiteDiario.getValor().subtract(transactionDTO.getValor()));
			limiteDiarioRepository.save(limiteDiario);
		}
		
		limiteProducer.enviar(transactionDTO);
		
	}

}
