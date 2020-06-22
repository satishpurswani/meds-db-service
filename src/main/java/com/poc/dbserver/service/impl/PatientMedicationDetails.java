package com.poc.dbserver.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.function.ToLongFunction;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.dbserver.model.MedsPatient;
import com.poc.dbserver.service.meds.repo.IMedsPatientsRepository;

@Service
public class PatientMedicationDetails {

	@Autowired
	private IMedsPatientsRepository iMedsPatientsRepository;

	@PostConstruct
	public void myMethod() {
		System.out.println(">>>>>>. myMethod Called....");
		iMedsPatientsRepository.saveAll(Arrays.asList(new MedsPatient(1, 1),new MedsPatient(1, 2),new MedsPatient(1, 3),new MedsPatient(2, 1),new MedsPatient(2, 2)));
	}
	
	public boolean saveMedsPatientInformation(List<MedsPatient> list) {
		return (iMedsPatientsRepository.saveAll(list)==list) ? true : false;
	}
	
	@Transactional
	public void deletePatientMeds(Long patientId) {
		iMedsPatientsRepository.deleteByPatientId(patientId);
	}

	public List<MedsPatient> getPatientInformation(Long patientId) {
		return iMedsPatientsRepository.findByPatientId(patientId);
	}

	public List<MedsPatient> getPatientInformation(List<MedsPatient> medsPatient) {
		return iMedsPatientsRepository.findByPatientIdIn(medsPatient.stream().map( t -> t.getPatientId()).collect(Collectors.toList()));
	}
}
