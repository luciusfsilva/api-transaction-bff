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
public class BeneficiarioDTO implements Serializable{

	private static final long serialVersionUID = -2340124217524964306L;
	
	@Schema(description = "CPF do Beneficiário")
	@NotNull(message = "Informar o CPF.")
	private Long cpf;
	
	@Schema(description = "Código do banco de destino")
	@NotNull(message = "Informar o código do banco de destino.")
	private Long codigoBanco;
	
	@Schema(description = "Agência de destino")
	@NotNull(message = "Informar a agência de destino.")
	private String agencia;
	
	@Schema(description = "Cónta de destino")
	@NotNull(message = "Informar a conta de destino.")
	private String conta;
	
	@Schema(description = "Nome do favorecido")
	@NotNull(message = "Informar nome do favorecido.")
	private String nomeFavorecido;
	

}
