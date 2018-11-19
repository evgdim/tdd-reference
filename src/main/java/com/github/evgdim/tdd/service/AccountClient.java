package com.github.evgdim.tdd.service;

import java.util.List;

import com.github.evgdim.tdd.entity.Account;

public interface AccountClient {
	public List<Account> findAccounts(Long id);
}
