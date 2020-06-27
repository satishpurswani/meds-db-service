package com.poc.dbserver.controller;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.poc.dbserver.model.Medication;

public interface IMedicationDataController {

	@PostMapping("/add")
	boolean savePatientData(@RequestBody Medication medsDetails);

	@PostMapping("/addMeds")
	List<Medication> saveAllMedicines(@RequestBody List<Medication> medsDetails);

	@GetMapping("/{medicationId}")
	Medication getPatientDetails(@PathVariable("medicationId") Long id) ;

	@DeleteMapping("/remove/{medicineId}")
	void removePatientDetails(@PathVariable("medicineId") Long id);

	@PutMapping("/update/{medicineId}")
	boolean updateExistingPatient(@PathVariable("medicineId") Long id, @RequestBody Medication medsDetails) ;

	@GetMapping("/search") 
	List<Medication> searchPatient(@RequestBody Medication medsDetails);
	
	@PostMapping("/checkMedicineAvailability")
	boolean checkMedicineAvailability(@RequestBody List<Medication> medsDetails);
	
	@PostMapping("/MedsInfo")
	List<Medication> fetchMedicinesInformation(@RequestBody List<Medication> medsDetails);
}
