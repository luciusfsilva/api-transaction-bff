package br.com.coffeeandit.apitransactionsvc.domain.validators;

import org.springframework.stereotype.Component;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;

@Component
public interface TransactionValidation {
	
	public void validate(final RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException;

}
