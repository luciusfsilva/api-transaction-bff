package br.com.coffeeandit.apilimitesvc.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Conta implements Serializable{

	private static final long serialVersionUID = -4725839536373039612L;
	
	private Long codigoAgencia;
	private Long codigoConta;
	
}
