package com.github.evgdim.tdd.repository;

import java.util.Optional;

import com.github.evgdim.tdd.entity.Account;

public interface AccountRepository {
	Optional<Account> findById(Long id);
}
