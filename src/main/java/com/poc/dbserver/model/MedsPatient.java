package com.poc.dbserver.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class MedsPatient {

	public MedsPatient() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private long patientId;

	private long medids;

	public long getPatientId() {
		return patientId;
	}

	public void setPatientId(long patientId) {
		this.patientId = patientId;
	}

	public long getMedids() {
		return medids;
	}

	public void setMedids(long medids) {
		this.medids = medids;
	}

	public MedsPatient(long patientId, long medids) {
		super();
		this.patientId = patientId;
		this.medids = medids;
	}

}
