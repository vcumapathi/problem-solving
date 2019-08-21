package com.skillenza.parkinglotjava;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR) // 500
	@ExceptionHandler(DataIntegrityViolationException.class)
	@ResponseBody
	public ErrorDetails conflict(HttpServletRequest req, DataIntegrityViolationException e) {
		return new ErrorDetails(new Date(), "unique constraint", "vehicle already parked");
	}
}
