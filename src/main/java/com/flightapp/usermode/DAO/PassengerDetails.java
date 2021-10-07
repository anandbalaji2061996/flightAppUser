package com.flightapp.usermode.DAO;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "passengerDetails")
public class PassengerDetails {
	
	@Id
	@NotEmpty(message = "Id should not be empty")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	@NotEmpty(message = "Name should not be empty")
	private String name;
	@NotEmpty(message = "Gender should not be empty")
	private String gender;
	@NotEmpty(message = "Age should not be empty")
	private int age;
	
	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}
