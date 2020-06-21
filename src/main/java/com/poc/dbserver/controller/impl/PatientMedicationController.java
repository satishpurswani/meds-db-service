package com.poc.dbserver.controller.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.dbserver.model.MedsPatient;
import com.poc.dbserver.service.impl.PatientMedicationDetails;
@RestController
@RequestMapping("/patientmedication")
public class PatientMedicationController {

	@Autowired
	private PatientMedicationDetails medsPatientService;
	
	@PostMapping("/addPatientMedsInfo")
	public boolean addPatientMeds(@RequestBody Optional<List<MedsPatient>> medsPatient ) {
		if(null==medsPatient) {
			return false;
		}
		if(medsPatient.isPresent()) {
			return medsPatientService.saveMedsPatientInformation(medsPatient.get());
		}
		return false;
	}
	
	@DeleteMapping("/deletePatientMedsInfo/{patientId}")
	public boolean deletePatientMeds(@PathVariable("patientId") Optional<Long> id ) {
		if(id.isPresent()) {
			medsPatientService.deletePatientMeds(id.get());
			return true;
		}
		return false;
	}
	
	@GetMapping("/getPatientMedsInfo/{patientId}")
	public List<MedsPatient> getPatientMedsInformation(@PathVariable("patientId") Optional<Long> id ){
		return id.isPresent() ? medsPatientService.getPatientInformation(id.get()) : null;
	}
	
	@GetMapping("/getPatientsMedsInfo")
	public List<MedsPatient> getPatientsMedsInformation(@RequestBody List<MedsPatient> medsPatient ){
		if(null==medsPatient) {
			return null;
		}
		return medsPatientService.getPatientInformation(medsPatient);
	}
}
