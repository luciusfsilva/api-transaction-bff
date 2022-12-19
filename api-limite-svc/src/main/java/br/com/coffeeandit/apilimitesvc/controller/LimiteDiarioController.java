package br.com.coffeeandit.apilimitesvc.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.coffeeandit.apilimitesvc.entity.LimiteDiario;
import br.com.coffeeandit.apilimitesvc.service.LimiteDiarioService;
import lombok.Getter;
import lombok.Setter;

@RestController
@RequestMapping("/limite-diario")
public class LimiteDiarioController {
	
	@Getter
	@Setter
	private LimiteDiarioService limiteDiarioService;

	public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
		this.limiteDiarioService = limiteDiarioService;
	}
	
	@GetMapping("/{id}")
	public LimiteDiario findById(@PathVariable("id") Long id) {
		Optional<LimiteDiario> limiteDiario = limiteDiarioService.findById(id);
		
		if (limiteDiario.isPresent()) {
			return limiteDiarioService.findById(id).get();
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
		
	}
	

}
