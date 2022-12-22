package br.com.coffeeandit.apitransactionbff.exception;

public class NotFoundException extends RuntimeException {

	private static final long serialVersionUID = 6552851081139509668L;
	
	public NotFoundException(String message) {
		super(message);
	}

}
