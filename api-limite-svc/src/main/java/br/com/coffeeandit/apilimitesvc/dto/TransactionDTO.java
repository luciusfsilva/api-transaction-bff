package br.com.coffeeandit.apilimitesvc.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = "uuid")
@ToString(of = {"uuid", "situacao"})
public class TransactionDTO {
	
	private UUID uuid;
	
	private BigDecimal valor;
	
	@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
	private LocalDateTime data;
	
	private Conta conta;
	
	private BeneficiarioDTO beneficiario;
	
	private TipoTransacao tipoTransacao;
	
	private SituacaoEnum situacao;
	
	public void suspeitaFraude() {
		situacao = SituacaoEnum.EM_SUSPEITA_FRAUDE;
	}
	
	public void analisada() {
		situacao = SituacaoEnum.ANALISADA;
	}
	
	public void analiseHumana() {
		situacao = SituacaoEnum.EM_ANALISE_HUMANA;
	}

}
