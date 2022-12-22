package br.com.coffeeandit.apitransactionbff.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.coffeeandit.apitransactionbff.dto.LimiteDiario;

@FeignClient(value = "limites", url = "${limites.url}")
public interface LimiteClient {
	
	@GetMapping(path = "/{agencia}/{conta}", produces = MediaType.APPLICATION_JSON_VALUE)
	LimiteDiario buscarLimiteDiario(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta);

}
