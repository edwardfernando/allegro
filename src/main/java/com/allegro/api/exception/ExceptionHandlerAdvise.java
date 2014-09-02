package com.allegro.api.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * http://springinpractice.com/2013/10/09/generating-json-error-object-responses
 * -with-spring-web-mvc
 * 
 */

@ControllerAdvice
public class ExceptionHandlerAdvise extends ResponseEntityExceptionHandler {

	@ExceptionHandler({ AllegroException.class, Throwable.class })
	protected ResponseEntity<Object> handleInvalidRequest(RuntimeException exception,
	        WebRequest request) {

		AllegroException allegroException = (AllegroException) exception;

		List<FieldErrorResource> fieldErrorResources = new ArrayList<>();

		ErrorResource error = new ErrorResource(allegroException.getErrorCode().toString(), allegroException.getMessage());

		if (allegroException.getErrors() != null) {
			List<FieldError> fieldErrors = allegroException.getErrors().getFieldErrors();
			for (FieldError fieldError : fieldErrors) {

				FieldErrorResource fieldErrorResource = new FieldErrorResource();
				fieldErrorResource.setResource(fieldError.getObjectName());
				fieldErrorResource.setField(fieldError.getField());
				fieldErrorResource.setCode(fieldError.getCode());
				fieldErrorResource.setMessage(fieldError.getDefaultMessage());

				fieldErrorResources.add(fieldErrorResource);
			}

		}

		error.setFieldErrors(fieldErrorResources);

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);

		return handleExceptionInternal(
		    exception,
		    error,
		    headers,
		    HttpStatus.UNPROCESSABLE_ENTITY,
		    request);
	}

}
