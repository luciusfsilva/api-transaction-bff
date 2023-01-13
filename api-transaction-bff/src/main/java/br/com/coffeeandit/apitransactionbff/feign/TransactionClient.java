package br.com.coffeeandit.apitransactionbff.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.coffeeandit.apitransactionbff.dto.TransactionDTO;

@FeignClient(value = "transaction", url = "${transaction.url}")
public interface TransactionClient {
	
	@GetMapping("/{agencia}/{conta}")
	List<TransactionDTO> buscarTransacoes(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta);

}
