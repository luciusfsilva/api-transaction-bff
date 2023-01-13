package br.com.coffeeandit.apitransactionsvc.domain.exception;

public class DomainBusinessException extends Exception {

	private static final long serialVersionUID = 6621912032765096754L;
	
	public DomainBusinessException(String message) {
		super(message);
	}

}
