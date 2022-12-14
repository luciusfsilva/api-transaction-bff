package br.com.coffeeandit.apitransactionbff.exception;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;



@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class ApplicationExceptionHandler {

	@ExceptionHandler(UnauthorizedException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public ResponseEntity<ErrorResponse> unauthorized(UnauthorizedException exception) {
		log.error("Erro n?o autorizado tratado.", exception);
		return response(exception.getMessage(), HttpStatus.UNAUTHORIZED);
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> internal(Exception exception) {
		log.error("Erro interno tratado.", exception);
		return response(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> notFound(NotFoundException exception) {
		log.error("Recurso n?o encontrado tratado.", exception);
		return response(exception.getMessage(), HttpStatus.NOT_FOUND);
	}
	
	public ResponseEntity<ErrorResponse> response(String message, HttpStatus httpStatus) {
		return ResponseEntity.status(httpStatus).body(new ErrorResponse(message, httpStatus.toString()));
	}
}
