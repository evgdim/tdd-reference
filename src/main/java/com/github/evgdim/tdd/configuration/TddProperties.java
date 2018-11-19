package com.github.evgdim.tdd.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Component
@ConfigurationProperties
@Getter @Setter
public class TddProperties {
	private AccountProperties account;
	
	@Getter @Setter
	public static class AccountProperties {
		private String url;
	}
}
