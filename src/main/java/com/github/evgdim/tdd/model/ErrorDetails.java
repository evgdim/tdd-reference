package com.github.evgdim.tdd.model;

import lombok.Data;

@Data
public class ErrorDetails {
	private final String errorCode;
	private final String errorMessage;
}
