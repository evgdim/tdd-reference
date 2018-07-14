package com.github.evgdim.tdd;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.tuple;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AssertJReferenceTests {
	private List<Person> people;
	private Person aaa;
	@Before
	public void init () {
		aaa = new Person(1L, "AAA", 1);
		this.people = Arrays.asList(aaa, new Person(2L, "BBB", 2),new Person(3L, "CCC", 3), new Person(4L, "DDD", 4));
	}
	
	@Test
	public void add_shouldReturn3_whenPass1And2() {
		assertThat(Calculator.add(1, 2)).isEqualTo(3);
	}
	
	@Test
	public void collection_shouldCointainOnlyAAA() {
		assertThat(people).filteredOn(p -> p.getAge() < 2).contains(aaa);
	}
	
	@Test
	public void collection_shouldCointainNameAAA() {
		List<Person> filteretPeople = this.people.stream().filter(p -> p.getAge() < 2).collect(Collectors.toList());
		assertThat(filteretPeople).extracting("name").contains("AAA").doesNotContain("BBB", "CCC", "DDD");
	}
	
	@Test
	public void collection_shouldCointainNameAAAAndAge1() {
		List<Person> filteretPeople = this.people.stream().filter(p -> p.getAge() < 2).collect(Collectors.toList());
		assertThat(filteretPeople).extracting("name", "age").contains(tuple("AAA", 1));
	}
	
	@Test
	public void lambda_shouldThrowException() {
	   assertThatThrownBy(() -> { throw new Exception("boom!"); }).isInstanceOf(Exception.class)
	                                                             .hasMessageContaining("boom");
	}
}
