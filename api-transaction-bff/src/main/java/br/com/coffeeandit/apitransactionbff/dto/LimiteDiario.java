package br.com.coffeeandit.apitransactionbff.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class LimiteDiario {

	private Long id;
	private Long agencia;
	private Long conta;
	private BigDecimal valor;
}
