package br.com.coffeeandit.apitransactionsvc.api;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.coffeeandit.apitransactionsvc.domain.TransactionBusiness;
import br.com.coffeeandit.apitransactionsvc.dto.TransactionDTO;

@RestController
@RequestMapping("/transaction")
public class TransactionController {
	
	private TransactionBusiness transactionBusiness;
	
	public TransactionController(TransactionBusiness transactionBusiness) {
		this.transactionBusiness = transactionBusiness;
	}
	
	@GetMapping("/{agencia}/{conta}")
	public List<TransactionDTO> buscarLimiteDiario(@PathVariable("agencia") final Long agencia, @PathVariable("conta") final Long conta){
		return transactionBusiness.findByConta(agencia, conta);
	}
}
