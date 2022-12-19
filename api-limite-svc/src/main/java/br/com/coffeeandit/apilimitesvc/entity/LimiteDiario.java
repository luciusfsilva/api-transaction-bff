package br.com.coffeeandit.apilimitesvc.entity;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
@Builder
@EqualsAndHashCode(of = "id")
public class LimiteDiario {

	@Id
	private Long id;
	
	private Long agencia;
	
	private Long conta;
	
	private BigDecimal valor;
}
