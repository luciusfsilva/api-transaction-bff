package br.com.coffeeandit.apitransactionbff.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
public class TransactionDTO {
	
	@Schema(description = "C�digo de identifica��o da transa��o")
	private UUID uuid;
	
	@Schema(description = "Valor da transa��o")
	@NotNull(message = "Informar o valor da transa��o")
	private BigDecimal valor;
	
	@Schema(description = "Data/hora/minuto e segundo da transa��o")
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime data;
	
	@Schema(description = "Conta de origem da transa��o")
	@NotNull(message = "Informar a conta de origem da transa��o")
	@Valid
	private Conta conta;
	
	@Schema(description = "Benefici�rio da transa��o")
	@Valid
	private BeneficiarioDTO beneficiario;
	
	@Schema(description = "Tipo da transa��o")
	@NotNull(message = "Informar o tipo da transa��o")
	private TipoTransacao tipoTransacao;
	
	@Schema(description = "Situa��o da transa��o")
	private SituacaoEnum situacao;

}
