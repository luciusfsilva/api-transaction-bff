package br.com.coffeeandit.apilimitesvc.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;
import br.com.coffeeandit.apilimitesvc.repository.LimiteDiarioRepository;
import lombok.Getter;
import lombok.Setter;

@Service
public class LimiteDiarioService {
	
	@Getter
	@Setter
	private LimiteDiarioRepository limiteDiarioRepository;

	public LimiteDiarioService(LimiteDiarioRepository limiteDiarioRepository) {
		this.limiteDiarioRepository = limiteDiarioRepository;
	}
	
	public Optional<LimiteDiario> findById(Long id) {
		return limiteDiarioRepository.findById(id);
	}

}
