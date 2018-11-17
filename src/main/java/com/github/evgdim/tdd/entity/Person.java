package com.github.evgdim.tdd.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(indexes = { @Index(name = "person_name_ix",  columnList="name", unique = true) })

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {
	@Id
	@GeneratedValue
	private Long id;
	@Column(nullable = false)
	private String name;
	private Integer age;
}
