package br.com.coffeeandit.apilimitesvc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;

@Repository
public interface LimiteDiarioRepository extends CrudRepository<LimiteDiario, Long>{
	
	LimiteDiario findByAgenciaAndConta(Long agencia, Long conta);

}
