package br.com.coffeeandit.apitransactionbff.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString(of = {"uuid", "situacao"})
@Schema(description = "Objeto de transporte para o envio de uma promessa de transação")
public class RequestTransactionDTO extends TransactionDTO {
	
	@JsonIgnore
	private SituacaoEnum situacao;
	
	@JsonIgnore
	private LocalDateTime data;

}
