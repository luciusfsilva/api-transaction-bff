package br.com.coffeeandit.apitransactionbff.api;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.coffeeandit.apitransactionbff.dto.RequestTransactionDTO;
import br.com.coffeeandit.apitransactionbff.dto.TransactionDTO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	@Operation(description = "API para criar uma transação financeira")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(responseCode="201", description = "Retorno OK com a transação criada."),
			@ApiResponse(responseCode="401", description = "Erro de autenticação dessa API."),
			@ApiResponse(responseCode="403", description = "Erro de autorização dessa API."),
			@ApiResponse(responseCode="404", description = "Recurso não encontrado.")
	})
	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TransactionDTO> enviarTransacao(@RequestBody final RequestTransactionDTO requestTransactionDto) {
		return Mono.empty();
	}
	
	@Operation(description = "API para buscar as transações persistidas por agência e conta")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(responseCode="200", description = "Retorno OK com a transação criada."),
			@ApiResponse(responseCode="401", description = "Erro de autenticação dessa API."),
			@ApiResponse(responseCode="403", description = "Erro de autorização dessa API."),
			@ApiResponse(responseCode="404", description = "Recurso não encontrado.")
	})
	@Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TransactionDTO> buscarTransacao(@PathVariable("id") final String uuid){
		return Mono.empty();
	}
	
	@Operation(description = "API para remover as transações persistidas")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(responseCode="204", description = "Retorno OK com a transação criada."),
			@ApiResponse(responseCode="401", description = "Erro de autenticação dessa API."),
			@ApiResponse(responseCode="403", description = "Erro de autorização dessa API."),
			@ApiResponse(responseCode="404", description = "Recurso não encontrado.")
	})
	@Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Mono<TransactionDTO> removerTransacao(@PathVariable("id") final String uuid){
		return Mono.empty();
	}
	
	@Operation(description = "API para autorizar a transação financeira")
	@ResponseBody
	@ApiResponses(value = {@ApiResponse(responseCode="201", description = "Retorno OK com a transação criada."),
			@ApiResponse(responseCode="401", description = "Erro de autenticação dessa API."),
			@ApiResponse(responseCode="403", description = "Erro de autorização dessa API."),
			@ApiResponse(responseCode="404", description = "Recurso não encontrado.")
	})
	@Parameters(value = {@Parameter(name = "id", in = ParameterIn.PATH)})
	@PatchMapping(value = "/{id}/confirmar")
	public Mono<TransactionDTO> confirmarTransacao(@PathVariable("id") final String uuid) {
		return Mono.empty();
	}

}
