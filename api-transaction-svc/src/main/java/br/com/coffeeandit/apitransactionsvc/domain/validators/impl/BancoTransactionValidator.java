package br.com.coffeeandit.apitransactionsvc.domain.validators.impl;

import java.util.Objects;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.domain.validators.TransactionValidator;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;

@Component
@ConditionalOnProperty(value = "{transaction.validation.banco}",
						havingValue = "true",
						matchIfMissing = false)
public class BancoTransactionValidator implements TransactionValidator {
	
	public static final int CODIGO_BANCO = 785;

	@Override
	public void validate(RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException {
		if (Objects.isNull(requestTransactionDTO.getBeneficiario())) {
			throw new DomainBusinessException("Inválido banco do beneficiário.");
		} else if (Objects.isNull(requestTransactionDTO.getBeneficiario().getCodigoBanco()) || 
				requestTransactionDTO.getBeneficiario().getCodigoBanco().compareTo((long) CODIGO_BANCO) != 0) {
			throw new DomainBusinessException("Inválido banco do beneficiário.");
		}
	}

}
