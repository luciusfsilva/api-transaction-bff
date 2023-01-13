package br.com.coffeeandit.apitransactionsvc.domain.validators.impl;

import java.time.LocalDateTime;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import br.com.coffeeandit.apitransactionsvc.domain.exception.DomainBusinessException;
import br.com.coffeeandit.apitransactionsvc.domain.validators.TransactionValidator;
import br.com.coffeeandit.apitransactionsvc.dto.RequestTransactionDTO;

@Component
@ConditionalOnProperty(value = "{transaction.validation.horario}",
						havingValue = "true",
						matchIfMissing = false)
public class HorarioTransactionValidator implements TransactionValidator {
	
	public static final int HOUR_END = 18;

	@Override
	public void validate(RequestTransactionDTO requestTransactionDTO) throws DomainBusinessException {
		
		if (LocalDateTime.now().getHour() > HOUR_END || requestTransactionDTO.getData().getHour() > HOUR_END) {
			throw new DomainBusinessException("Horário após as 18.");
		}
	}
}
