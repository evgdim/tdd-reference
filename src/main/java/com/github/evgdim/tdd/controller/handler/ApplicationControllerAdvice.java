package com.github.evgdim.tdd.controller.handler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.github.evgdim.tdd.model.ErrorDetails;

import lombok.extern.slf4j.Slf4j;

@ControllerAdvice
@Slf4j
public class ApplicationControllerAdvice {
	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorDetails> handleException(Throwable t) {
		log.error("[handleException]", t);
		return ResponseEntity.status(500).body(new ErrorDetails("1", t.getMessage()));
	}
}
