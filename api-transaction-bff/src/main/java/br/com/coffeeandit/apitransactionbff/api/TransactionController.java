package br.com.coffeeandit.apitransactionbff.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coffeeandit.apitransactionbff.dto.RequestTransactionDTO;
import br.com.coffeeandit.apitransactionbff.dto.TransactionDTO;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TransactionDTO> enviarTransacao(@RequestBody final RequestTransactionDTO requestTransactionDto) {
		return Mono.empty();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TransactionDTO> buscarTransacao(@PathVariable("id") final String uuid){
		return Mono.empty();
	}
	
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TransactionDTO> removerTransacao(@PathVariable("id") final String uuid){
		return Mono.empty();
	}
	
	@PatchMapping(value = "/{id}/confirmar")
	public Mono<TransactionDTO> confirmarTransacao(@PathVariable("id") final String uuid) {
		return Mono.empty();
	}

}
