package br.com.coffeeandit.apitransactionbff.exception;

import java.time.LocalDateTime;

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
		log.error("Erro não autorizado tratado.", exception);
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
				.body(new ErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED.toString(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public ResponseEntity<ErrorResponse> internal(Exception exception) {
		log.error("Erro interno tratado.", exception);
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
				.body(new ErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR.toString(), LocalDateTime.now()));
	}
	
	@ExceptionHandler(NotFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public ResponseEntity<ErrorResponse> notFound(NotFoundException exception) {
		log.error("Recurso não encontrado tratado.", exception);
		return ResponseEntity.status(HttpStatus.NOT_FOUND)
				.body(new ErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND.toString(), LocalDateTime.now()));
	}
	
}
