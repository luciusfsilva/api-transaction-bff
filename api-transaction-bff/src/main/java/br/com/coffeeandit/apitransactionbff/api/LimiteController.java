package br.com.coffeeandit.apitransactionbff.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coffeeandit.apitransactionbff.dto.LimiteDiario;
import br.com.coffeeandit.apitransactionbff.feign.LimiteClient;

@RestController
@RequestMapping("/limites")
public class LimiteController {
	
	private LimiteClient limiteClient;
	
	public LimiteController(LimiteClient limiteClient) {
		this.limiteClient = limiteClient;
	}

	@GetMapping("/{agencia}/{conta}")
	public LimiteDiario buscarLimiteDiario(@PathVariable("agencia") Long agencia, @PathVariable("conta") Long conta) {
		return limiteClient.buscarLimiteDiario(agencia, conta);
		
	}

}
