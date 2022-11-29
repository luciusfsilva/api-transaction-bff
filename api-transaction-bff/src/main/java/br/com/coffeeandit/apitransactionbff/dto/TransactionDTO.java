package br.com.coffeeandit.apitransactionbff.dto;

import java.util.UUID;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class TransactionDTO {
	
	private UUID uuid;

}
