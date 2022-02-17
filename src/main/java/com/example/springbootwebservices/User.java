package com.example.springbootwebservices;

import java.util.Date;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;


@Entity
public class User {
	
	@Size(min = 5 ,message = "Name should have 5 chars")
	private String name;
	
	@GeneratedValue
	@Id
	private Integer id;
	
	@Past
	private Date birthdate;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public User(String name, Integer id, Date birthdate) {
		super();
		this.name = name;
		this.id = id;
		this.birthdate = birthdate;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}
	
	
	
	

}
