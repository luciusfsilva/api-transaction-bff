package br.com.coffeeandit.apitransactionsvc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.com.coffeeandit.apitransactionsvc.dto.Conta;
import br.com.coffeeandit.apitransactionsvc.dto.TransactionDTO;

@Repository
public interface TransactionRepository extends MongoRepository<TransactionDTO, UUID>{
	
	List<TransactionDTO> findByConta(final Conta conta);

}
