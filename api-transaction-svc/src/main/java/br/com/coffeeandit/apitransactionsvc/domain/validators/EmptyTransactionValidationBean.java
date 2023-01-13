package br.com.coffeeandit.apitransactionsvc.domain.validators;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;

public class EmptyTransactionValidationBean implements TransactionValidation {

	@Override
	public void validate(final RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException {
	}

}
