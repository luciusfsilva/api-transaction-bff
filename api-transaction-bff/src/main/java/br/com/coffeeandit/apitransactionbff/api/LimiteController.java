package br.com.coffeeandit.apitransactionbff.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coffeeandit.apitransactionbff.domain.LimiteService;
import br.com.coffeeandit.apitransactionbff.dto.LimiteDiario;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/limites")
public class LimiteController {
	
	private LimiteService limiteService;
	
	public LimiteController(LimiteService limiteService) {
		this.limiteService = limiteService;
	}

	@GetMapping("/{agencia}/{conta}")
	public Mono<LimiteDiario> buscarLimiteDiario(@PathVariable("agencia") Long agencia, @PathVariable("conta") Long conta) {
		return limiteService.buscarLimiteDiario(agencia, conta);
		
	}

}
