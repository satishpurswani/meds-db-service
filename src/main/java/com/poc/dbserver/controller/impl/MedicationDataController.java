package com.poc.dbserver.controller.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.dbserver.controller.IMedicationDataController;
import com.poc.dbserver.model.Medication;
import com.poc.dbserver.service.IMedicationService;

@RestController
@RequestMapping("/meds")
public class MedicationDataController implements IMedicationDataController{

	@Autowired
	private IMedicationService imedicationService;
	
	public boolean savePatientData(@RequestBody Medication medsDetails) {
		boolean status = imedicationService.saveMedsInformation(medsDetails);
		return status;
	}

	public List<Medication> saveAllMedicines(@RequestBody List<Medication> medsDetails) {
		return imedicationService.saveAll(medsDetails);
	}

	public Medication getPatientDetails(@PathVariable("medicationId") Long id) {
		return imedicationService.getByMedicineId(id).orElse(null);
	}

	public void removePatientDetails(@PathVariable("medicineId") Long id) {
		try {
			imedicationService.deleteByID(id);
		} catch (EmptyResultDataAccessException ex) {
			ex.printStackTrace();
		}
	}

	public boolean updateExistingPatient(@PathVariable("medicineId") Long id, @RequestBody Medication medsDetails) {
		return imedicationService.updateMedication(id, medsDetails);
	}

	public List<Medication> searchPatient(@RequestBody Medication medsDetails){
		return (medsDetails==null) ?  null :  imedicationService.searchMedicationByIdAndNameAndAgeAndGenderAndAccessionNumberAndDOB(medsDetails);
	}

	public boolean checkMedicineAvailability(@RequestBody List<Medication> medsDetails) {
		return imedicationService.isMedicineAvailable(medsDetails);
	}
	
	public List<Medication> fetchMedicinesInformation(@RequestBody List<Medication> medsDetails){
		return (medsDetails==null) ? null : imedicationService.getMedicinesInformation(medsDetails);
	}
}
