package com.poc.dbserver.service;

import java.util.List;

import com.poc.dbserver.model.MedsPatient;

public interface IPatientMedicationService {

	public boolean saveMedsPatientInformation(List<MedsPatient> list);
	
	public void deletePatientMeds(Long patientId);
	
	public List<MedsPatient> getPatientInformation(Long patientId);
	
	public List<MedsPatient> getPatientInformation(List<MedsPatient> medsPatient);
}
