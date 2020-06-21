package com.poc.dbserver.controller;

import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.poc.dbserver.model.Medication;

public interface IMedicationDataController {

	@PostMapping("/add")
	public boolean savePatientData(@RequestBody Medication medsDetails);

	@PostMapping("/addMeds/")
	public List<Medication> saveAllPatients(@RequestBody List<Medication> medsDetails);

	@GetMapping("/{medicationId}")
	public Medication getPatientDetails(@PathVariable("medicationId") Long id) ;

	@DeleteMapping("/remove/{medicineId}")
	public void removePatientDetails(@PathVariable("medicineId") Long id);

	@PutMapping("/update/{medicineId}")
	public boolean updateExistingPatient(@PathVariable("medicineId") Long id, @RequestBody Medication medsDetails) ;

	@GetMapping("/search") 
	public List<Medication> searchPatient(@RequestBody Medication medsDetails);
}
