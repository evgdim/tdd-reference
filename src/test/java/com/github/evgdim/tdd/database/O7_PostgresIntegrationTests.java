package com.github.evgdim.tdd.database;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;

import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.util.TestPropertyValues;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import com.github.evgdim.tdd.TddReferenceApplication;
import com.github.evgdim.tdd.repository.PersonRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { TddReferenceApplication.class })
@ContextConfiguration(initializers = {O7_PostgresIntegrationTests.Initializer.class })
public class O7_PostgresIntegrationTests {
	@ClassRule
	public static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:10.4").withDatabaseName("sampledb")
			.withUsername("sampleuser").withPassword("samplepwd").withStartupTimeout(Duration.ofSeconds(600));

	static class Initializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
		public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
			TestPropertyValues
					.of("spring.datasource.url=" + postgreSQLContainer.getJdbcUrl(),
							"spring.datasource.username=" + postgreSQLContainer.getUsername(),
							"spring.datasource.password=" + postgreSQLContainer.getPassword(),
							"spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect", 
							"spring.jpa.show-sql=true", 
							"spring.jpa.hibernate.ddl-auto=create")
					.applyTo(configurableApplicationContext.getEnvironment());
		}
	}

	@Autowired
	private PersonRepository personRepo;

	@Test
	public void personRepoCount_shouldReturn0() {
		assertThat(personRepo.count()).isEqualTo(0);
	}
}
