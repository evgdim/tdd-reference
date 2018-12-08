package com.github.evgdim.tdd.repository;

import java.sql.Types;
import java.util.List;
import java.util.Optional;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.github.evgdim.tdd.entity.Account;

import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class JdbcAccountRepository implements AccountRepository{
	private final NamedParameterJdbcTemplate jdbc;
	@Override
	public Optional<Account> findById(Long id) {
		MapSqlParameterSource paramMap = new MapSqlParameterSource();
		paramMap.addValue("id", id, Types.NUMERIC);
		List<Account> accounts = jdbc.query("select number, amount from account where id = :id", paramMap, 
				(RowMapper<Account>) (rs, rowNum) -> new Account(id, rs.getString("number"), rs.getBigDecimal("amount")));
		return accounts != null && accounts.size() > 0 ? Optional.of(accounts.get(0)) : Optional.empty();
	}

}
