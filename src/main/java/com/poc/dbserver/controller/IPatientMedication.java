package com.poc.dbserver.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.poc.dbserver.model.MedsPatient;

public interface IPatientMedication {

	@PostMapping("/addPatientMedsInfo")
	public boolean addPatientMeds(@RequestBody Optional<List<MedsPatient>> medsPatient );
	
	@DeleteMapping("/deletePatientMedsInfo/{patientId}")
	public boolean deletePatientMeds(@PathVariable("patientId") Optional<Long> id );
	
	@GetMapping("/getPatientMedsInfo/{patientId}")
	public List<MedsPatient> getPatientMedsInformation(@PathVariable("patientId") Optional<Long> id );
	
	@GetMapping("/getPatientsMedsInfo")
	public List<MedsPatient> getPatientsMedsInformation(@RequestBody List<MedsPatient> medsPatient );
}
