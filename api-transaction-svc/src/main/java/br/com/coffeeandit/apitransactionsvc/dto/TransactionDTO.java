package br.com.coffeeandit.apitransactionsvc.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class TransactionDTO {
	
	@Id
	private UUID uuid;
	
	@NotNull(message = "Informar o valor da transa��o")
	private BigDecimal valor;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime data;
	
	@NotNull(message = "Informar a conta de origem da transa��o")
	@Valid
	private Conta conta;
	
	@NotNull(message = "Informar o benefici�rio de origem da transa��o")
	@Valid
	private BeneficiarioDTO beneficiario;
	
	@NotNull(message = "Informar o tipo da transa��o")
	private TipoTransacao tipoTransacao;
	
	private SituacaoEnum situacao;

}
