package com.mobiliya.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.mobiliya.bean.ErrorResponse;
import com.mobiliya.bean.MissingHeaderInfoException;
import com.mobiliya.bean.RecordNotFoundException;
import com.mobiliya.config.AppConfig;

//This class is used to handle HTTP status code related exception
@ControllerAdvice
@ResponseBody
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

	private static Logger logger = LogManager.getLogger(CustomExceptionHandler.class);

	// If URL contains pathvariable is not found then it will return 404 HTTP Status
	// code
	@ExceptionHandler(RecordNotFoundException.class)
	public final ResponseEntity<ErrorResponse> handleUserNotFoundException(RecordNotFoundException ex, WebRequest req) {

		logger.error("Record Not Found Exception ");
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getLocalizedMessage());

		ErrorResponse error = new ErrorResponse("INCORRECT_REQUEST", errors);
		return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);

	}

	// If Request missing any header then it will return 400 HTTP Status code
	@ExceptionHandler(MissingHeaderInfoException.class)
	public final ResponseEntity<ErrorResponse> handleInvalidTraceIdException(MissingHeaderInfoException ex,
			WebRequest req) {
		logger.error("Missing Header Info Exception");
		List<String> errors = new ArrayList<String>();
		errors.add(ex.getLocalizedMessage());

		ErrorResponse error = new ErrorResponse("INCORRECT_REQUEST", errors);
		return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);

	}

	// If Request missing any body parameter then it will return 400 HTTP Status
	// code (Bad Request)
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		logger.error("Method Argument Not Valid Exception");
		List<String> messages = ex.getBindingResult().getFieldErrors().stream().map(x -> x.getDefaultMessage())
				.collect(Collectors.toList());

		return new ResponseEntity<>(new ErrorResponse(messages), HttpStatus.BAD_REQUEST);
	}
}
