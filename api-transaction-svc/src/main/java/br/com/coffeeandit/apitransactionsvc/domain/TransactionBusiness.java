package br.com.coffeeandit.apitransactionsvc.domain;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.domain.validators.TransactionValidation;
import br.com.coffeeandit.apitransactionsvc.dto.Conta;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;
import br.com.coffeeandit.apitransactionsvc.dto.TransactionDTO;
import br.com.coffeeandit.apitransactionsvc.repository.TransactionRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class TransactionBusiness {
	
	public TransactionBusiness(TransactionRepository transactionRepository, TransactionValidation transactionValidation) {
		this.transactionRepository = transactionRepository;
		this.transactionValidation = transactionValidation;
	}
	
	private TransactionRepository transactionRepository;
	private TransactionValidation transactionValidation;
	
	public void save(final RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException {
		if (Objects.isNull(requestTransactionDTO.getData()))
			requestTransactionDTO.setData(LocalDateTime.now());
		
		transactionValidation.validate(requestTransactionDTO);
		transactionRepository.save(requestTransactionDTO);
	}
	
	public void atualizarTransacao(TransactionDTO transactionDTO) {
		log.info("Atualizando transação: {}", transactionDTO);
		transactionRepository.save(transactionDTO);
	}
	
	public void aprovarTransacao(TransactionDTO transEvent) {
		var transacao = buscarTransacao(transEvent);
		
		if (transacao.isPresent()) {
			var transDB = transacao.get();
			if (!transDB.isAnalisada() && transEvent.isAnalisada()) {
				transDB.aprovar();
				atualizarTransacao(transDB);
				log.info("Transação aprovada: {}", transDB);
			}
		}
		
	}
	
	public Optional<TransactionDTO> buscarTransacao(TransactionDTO transactionDTO) {
		return transactionRepository.findById(transactionDTO.getUuid());
	}
	
	public List<TransactionDTO> findByConta(final Long codigoAgencia, final Long codigoConta) {
		var conta = new Conta();
		conta.setCodigoConta(codigoConta);
		conta.setCodigoAgencia(codigoAgencia);
		return transactionRepository.findByConta(conta);
	}

}
