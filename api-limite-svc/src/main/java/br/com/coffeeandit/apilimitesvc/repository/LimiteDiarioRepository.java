package br.com.coffeeandit.apilimitesvc.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;

@Repository
public interface LimiteDiarioRepository extends CrudRepository<LimiteDiario, Long>{
	
	Optional<LimiteDiario> findByAgenciaAndConta(final Long agencia, final Long conta);
	
	LimiteDiario findByAgenciaAndContaAndData(final Long agencia, final Long conta, final LocalDateTime data);

}
