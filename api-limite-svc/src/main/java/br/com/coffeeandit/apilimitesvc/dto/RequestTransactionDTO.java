package br.com.coffeeandit.apilimitesvc.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RequestTransactionDTO extends TransactionDTO {
	private LocalDateTime data;

}
