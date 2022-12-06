package br.com.coffeeandit.apitransactionbff.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Conta implements Serializable{

	private static final long serialVersionUID = -4725839536373039612L;
	
	@Schema(description = "C�digo da Ag�ncia")
	@NotNull(message = "Informar o c�digo da Ag�ncia.")
	private Long codigoAgencia;
	
	@Schema(description = "C�digo da Conta")
	@NotNull(message = "Informar o c�digo da Conta.")
	private Long codigoConta;

	
	
}
