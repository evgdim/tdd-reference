package com.github.evgdim.tdd.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.evgdim.tdd.entity.Account;
import com.github.evgdim.tdd.repository.AccountRepository;
import com.github.evgdim.tdd.repository.JdbcAccountRepository;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcAccountRepository.class)
public class JdbcTests {
	@Autowired
	private AccountRepository accRepo;
	
	@Test
	public void getById_shouldReturnEmptyOptional_forInvalidId() {
		Optional<Account> acc = accRepo.findById(-1L);
		assertThat(acc).isEmpty();
	}
}
