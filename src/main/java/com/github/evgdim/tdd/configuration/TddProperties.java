package com.github.evgdim.tdd.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@ConfigurationProperties(prefix="tddref")
@Data
public class TddProperties {
	private AccountProperties account;
	private String random1;
	private String random2;
	private String random3;
	
	@Data
	public static class AccountProperties {
		private String url;
	}
}
