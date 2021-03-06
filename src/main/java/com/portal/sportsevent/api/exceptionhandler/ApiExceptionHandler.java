package com.portal.sportsevent.api.exceptionhandler;

import java.time.LocalDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.portal.sportsevent.exception.InformationAlreadyExists;
import com.portal.sportsevent.exception.ResourceNotFound;

import lombok.var;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler{

	@Autowired
	private MessageSource messageSource;
	
	/**
	 * Method responsible for handling exception of ResourceNotFound
	 * @param ex the exception
	 * @param request the request
	 * @return ResponseEntity<Object> custom answer
	 */
	@ExceptionHandler(ResourceNotFound.class)
	public ResponseEntity<Object> handleNegocio(ResourceNotFound ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setDateTime(LocalDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
	
	/**
	 * Method responsible for handling exception of InformationAlreadyExists
	 * @param ex the exception
	 * @param request the request
	 * @return ResponseEntity<Object> custom answer
	 */
	@ExceptionHandler(InformationAlreadyExists.class)
	public ResponseEntity<Object> handleNegocio(InformationAlreadyExists ex, WebRequest request) {
		var status = HttpStatus.UNPROCESSABLE_ENTITY;
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle(ex.getMessage());
		problem.setDateTime(LocalDateTime.now());
		
		return handleExceptionInternal(ex, problem, new HttpHeaders(), status, request);
	}
		
	/**
	 * {@inheritDoc}
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		var fields = new ArrayList<Problem.Field>();
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Problem.Field(name, message));
		}
		
		var problem = new Problem();
		problem.setStatus(status.value());
		problem.setTitle("Um ou mais campos est??o inv??lidos. "
				+ "Fa??a o preenchimento correto e tente novamente.");
		problem.setDateTime(LocalDateTime.now());
		problem.setFields(fields);
		
		return super.handleExceptionInternal(ex, problem, headers, status, request);
	}
}
