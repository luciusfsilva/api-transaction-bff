package br.com.coffeeandit.apitransactionbff.redis;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.coffeeandit.apitransactionbff.dto.TransactionDTO;

@Repository
public interface TransactionRedisRepository extends CrudRepository<TransactionDTO, String>{

}
