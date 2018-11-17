package com.github.evgdim.tdd.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.Data;

@RestController
@RequestMapping("/health")
public class HealthController {
	@GetMapping
	public Health health() {
		return new Health("OK");
	}
	
	@Data
	@AllArgsConstructor
	public static class Health {
		private String status;
	}
}
