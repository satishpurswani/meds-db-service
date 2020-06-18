package com.poc.dbserver.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Medication {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String name;
	
	private String batchNo;
	
	private LocalDate expiryDate;

	public Medication() {
		super();
	}

	public Medication(String name, String batchNo, LocalDate expiryDate) {
		super();
		this.name = name;
		this.batchNo = batchNo;
		this.expiryDate = expiryDate;
	}

	public Medication(Medication medsDetails) {
		this.name = medsDetails.getName();
		this.batchNo = medsDetails.getBatchNo();
		this.expiryDate = medsDetails.getExpiryDate();
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

	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public LocalDate getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(LocalDate expiryDate) {
		this.expiryDate = expiryDate;
	}
}
