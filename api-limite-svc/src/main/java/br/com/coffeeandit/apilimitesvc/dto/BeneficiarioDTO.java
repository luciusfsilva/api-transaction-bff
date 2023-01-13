package br.com.coffeeandit.apilimitesvc.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BeneficiarioDTO implements Serializable {

	private static final long serialVersionUID = -2340124217524964306L;
	
	private Long cpf;
	private Long codigoBanco;
	private String agencia;
	private String conta;
	private String nomeFavorecido;
	

}
