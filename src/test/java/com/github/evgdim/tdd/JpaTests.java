package com.github.evgdim.tdd;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@DataJpaTest
public class JpaTests {
	@Autowired
	private TestEntityManager em;
	
	@Test
	public void testPersonMapping() {
		Person person = new Person(null, "Evgeni", 29);
		em.persistFlushFind(person );
	}
}
