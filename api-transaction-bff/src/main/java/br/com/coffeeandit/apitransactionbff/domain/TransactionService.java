package br.com.coffeeandit.apitransactionbff.domain;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.coffeeandit.apitransactionbff.dto.RequestTransactionDTO;
import br.com.coffeeandit.apitransactionbff.dto.TransactionDTO;
import br.com.coffeeandit.apitransactionbff.redis.TransactionRedisRepository;

@Service
public class TransactionService {
	
	private TransactionRedisRepository transactionRedisRepository;

	public TransactionService(TransactionRedisRepository transactionRedisRepository) {
		this.transactionRedisRepository = transactionRedisRepository;
	}
	
	@Transactional
	public Optional<TransactionDTO> save (final RequestTransactionDTO requestTransactionDTO) {
		requestTransactionDTO.setData(LocalDateTime.now());
		return Optional.of(transactionRedisRepository.save(requestTransactionDTO));
	}
	
	public Optional<TransactionDTO> findById(String id) {
		return transactionRedisRepository.findById(id);
	}
	

}
