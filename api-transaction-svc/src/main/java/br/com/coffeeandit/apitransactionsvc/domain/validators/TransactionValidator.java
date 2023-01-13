package br.com.coffeeandit.apitransactionsvc.domain.validators;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;

@FunctionalInterface
public interface TransactionValidator {
	
	void validate(final RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException;

}
