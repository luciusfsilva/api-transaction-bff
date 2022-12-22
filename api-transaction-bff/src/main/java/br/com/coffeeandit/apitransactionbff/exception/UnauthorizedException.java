package br.com.coffeeandit.apitransactionbff.exception;

public class UnauthorizedException extends RuntimeException{

	private static final long serialVersionUID = -1871516306524587293L;

	public UnauthorizedException(String message) {
		super(message);
	}

}
