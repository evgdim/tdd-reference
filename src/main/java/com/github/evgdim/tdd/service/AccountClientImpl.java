package com.github.evgdim.tdd.service;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.github.evgdim.tdd.configuration.TddProperties;
import com.github.evgdim.tdd.entity.Account;

@Component
public class AccountClientImpl implements AccountClient {
	private final RestTemplate restTemplate; 
	
	public AccountClientImpl(TddProperties props) {
		this.restTemplate = new RestTemplateBuilder().rootUri(props.getAccount().getUrl()).build();
	}

	@Override
	public List<Account> findAccounts(Long id) {
		return restTemplate.exchange("/accounts/{id}", HttpMethod.GET, null, new ParameterizedTypeReference<List<Account>>(){})
			.getBody();
	}

}
