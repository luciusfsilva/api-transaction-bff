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

@RestController
@RequestMapping("/limite-diario")
public class LimiteDiarioController {
	
	private final LimiteDiarioService limiteDiarioService;

	public LimiteDiarioController(LimiteDiarioService limiteDiarioService) {
		this.limiteDiarioService = limiteDiarioService;
	}
	
	@GetMapping("/{agencia}/{conta}")
	public LimiteDiario buscarLimiteDiario(@PathVariable("agencia") Long agencia, @PathVariable("conta") Long conta) {
		final Optional<LimiteDiario> limiteDiario = limiteDiarioService.buscarLimiteDiario(agencia, conta);
		
		if (limiteDiario.isPresent()) {
			return limiteDiario.get();
		}
		
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Recurso não encontrado");
		
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
