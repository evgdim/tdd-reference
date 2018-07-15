package com.github.evgdim.tdd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

@Entity
@Table(
		indexes = { @Index(name = "person_name_ix",  columnList="name", unique = true) })
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	private Integer age;
	
	public Person() {}
	
	public Person(Long id, String name, Integer age) {
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	
	
}
