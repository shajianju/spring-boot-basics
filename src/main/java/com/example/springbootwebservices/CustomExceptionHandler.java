package com.example.springbootwebservices;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestController
@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler{

	@ExceptionHandler(Exception.class)
public final   ResponseEntity<Object> handleAllException(Exception e, WebRequest request) {
ExceptionResponse exceptionResponse=	new ExceptionResponse(new Date(),e.getMessage(), request.getDescription(false));
return new ResponseEntity<>(exceptionResponse,HttpStatus.INTERNAL_SERVER_ERROR);


}
	@ExceptionHandler(UserNotFoundException.class)
	public final   ResponseEntity<Object> handleAllUserNotFoundException(Exception e, WebRequest request) {
	ExceptionResponse exceptionResponse=	new ExceptionResponse(new Date(),e.getMessage(), request.getDescription(false));
	return new ResponseEntity<>(exceptionResponse,HttpStatus.NOT_FOUND);


	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		ExceptionResponse exceptionResponse=	new ExceptionResponse(new Date(),ex.getMessage(), request.getDescription(false));

		return new ResponseEntity<>(exceptionResponse,HttpStatus.BAD_REQUEST);
	}

}
