package com.github.evgdim.tdd.service;

import org.springframework.http.ResponseEntity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class AccountsServiceException extends RuntimeException {
	private final ResponseEntity<?> entity;
}
