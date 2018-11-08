package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import javax.sql.DataSource;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import com.github.evgdim.tdd.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes= {TddReferenceApplication.class})
public class PostgresIntegrationTests {
	@ClassRule
	public static PostgreSQLContainer postgres = new PostgreSQLContainer<>("postgres:10.4")
			.withDatabaseName("sampledb")
            .withUsername("sampleuser")
            .withPassword("samplepwd")
            .withStartupTimeout(Duration.ofSeconds(600));
	
	@Configuration
	public static class TestConfig {
		@Bean
		public DataSource containerDataSource() { 
			return DataSourceBuilder.create()
			.url(postgres.getJdbcUrl())
			.username(postgres.getUsername())
			.password(postgres.getPassword())
			.driverClassName("org.postgresql.Driver")
			.build();
		}
		
	}
	@Autowired
	private PersonRepository personRepo;
	@Test
	public void personRepoCount_shouldReturn0() {
		assertThat( personRepo.count()).isEqualTo(0);
	}
}
