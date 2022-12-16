package br.com.coffeeandit.apitransactionbff.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestTransactionDTO extends TransactionDTO {
	private LocalDateTime data;

}
