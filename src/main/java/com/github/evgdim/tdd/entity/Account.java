package com.github.evgdim.tdd.entity;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class Account {
	private String number;
	private BigDecimal amount;
}
