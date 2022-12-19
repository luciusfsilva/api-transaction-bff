package br.com.coffeeandit.apitransactionsvc.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BeneficiarioDTO implements Serializable{

	private static final long serialVersionUID = -2340124217524964306L;
	
	@NotNull(message = "Informar o CPF.")
	private Long cpf;
	
	@NotNull(message = "Informar o código do banco de destino.")
	private Long codigoBanco;
	
	@NotNull(message = "Informar a agência de destino.")
	private String agencia;
	
	@NotNull(message = "Informar a conta de destino.")
	private String conta;
	
	@NotNull(message = "Informar nome do favorecido.")
	private String nomeFavorecido;
	

}
