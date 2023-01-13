package br.com.coffeeandit.apitransactionsvc.domain.validators;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Service;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.domain.validators.impl.BancoTransactionValidator;
import br.com.coffeeandit.apitransactionsvc.domain.validators.impl.HorarioTransactionValidator;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@ConditionalOnBean(value = {BancoTransactionValidator.class, HorarioTransactionValidator.class})
@ConditionalOnExpression("${transaction.validation.enabled:true}")
@Slf4j
public class TransactionValidationBean implements TransactionValidation {
	
	private List<TransactionValidator> transactionValidatorList = new ArrayList<>();
	
	@PostConstruct
	public void addBeans() {
		transactionValidatorList.add(new BancoTransactionValidator());
		transactionValidatorList.add(new HorarioTransactionValidator());
	}

	@Override
	public void validate(final RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException {
		transactionValidatorList.stream().forEach(tv -> {
			try {
				tv.validate(requestTransactionDTO);
			} catch (DomainBusinessException e) {
				log.error(e.getMessage(), e);
			}
		});
	}

}
