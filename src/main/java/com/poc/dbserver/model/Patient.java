package com.poc.dbserver.model;

import java.time.LocalDate;
public class Patient {

	private Long id;

	private String name;
	
	public Patient() {
		super();
	}

	public Patient(String name, int age, String gender, Long accessionNumber, LocalDate dob) {
		super();
		this.name = name;
		this.age = age;
		this.gender = gender;
		this.accessionNumber = accessionNumber;
		this.dob = dob;
	}

	public Patient(Patient patientDetails) {
		this.name = patientDetails.getName();
		this.age = patientDetails.getAge();
		this.gender = patientDetails.getGender();
		this.accessionNumber = patientDetails.getAccessionNumber();
		this.dob = patientDetails.getDob();
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Long getAccessionNumber() {
		return accessionNumber;
	}

	public void setAccessionNumber(Long accessionNumber) {
		this.accessionNumber = accessionNumber;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	private int age;
	
	private String gender;
	
	private Long accessionNumber;
	
	private LocalDate dob;
}
