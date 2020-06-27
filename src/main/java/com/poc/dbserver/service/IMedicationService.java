package com.poc.dbserver.service;

import java.util.List;
import java.util.Optional;

import com.poc.dbserver.model.Medication;

public interface IMedicationService {

	boolean saveMedsInformation(Medication medsDetails);

	List<Medication> saveAll(List<Medication> medsDetails);

	Optional<Medication> getByMedicineId(Long id);

	void deleteByID(Long id);

	boolean updateMedication(Long id, Medication medsDetails);

	List<Medication> searchMedicationByIdAndNameAndAgeAndGenderAndAccessionNumberAndDOB(Medication medsDetails);
	
	public boolean isMedicineAvailable(List<Medication> medsDetails);

	List<Medication> getMedicinesInformation(List<Medication> medsDetails);

}
