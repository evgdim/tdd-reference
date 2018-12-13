package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.evgdim.tdd.entity.Account;
import com.github.evgdim.tdd.repository.AccountRepository;
import com.github.evgdim.tdd.repository.JdbcAccountRepository;

@RunWith(SpringRunner.class)
@JdbcTest
@Import(JdbcAccountRepository.class)
@Sql(scripts = {"drop_account_table.sql","create_account_table.sql"})
public class O4_JdbcTests {
	@Autowired
	private AccountRepository accRepo;
	
	@Test
	public void getById_shouldReturnEmptyOptional_forInvalidId() {
		Optional<Account> acc = accRepo.findById(1L);
		assertThat(acc).isEmpty();
	}
	
	@Test
	@Sql("insert_account_table.sql")
	public void getById_shouldReturnAccount_forExistingEntry() {
		Optional<Account> acc = accRepo.findById(1L);
		assertThat(acc.get().getAmount()).isEqualByComparingTo(BigDecimal.valueOf(200));
		assertThat(acc.get().getNumber()).isEqualTo("615/1231234");
	}
}
