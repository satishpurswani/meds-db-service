package com.poc.dbserver.service.impl;

import java.io.UnsupportedEncodingException;
import java.net.InetSocketAddress;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.poc.dbserver.model.Medication;
import com.poc.dbserver.model.MedsPatient;
import com.poc.dbserver.model.Patient;
import com.poc.dbserver.service.IMedicationService;
import com.poc.dbserver.service.IPatientMedicationService;
import com.poc.dbserver.service.meds.repo.IMedsPatientsRepository;

@Service
public class PatientMedicationDetails implements IPatientMedicationService{

	private final Logger LOGGER = LoggerFactory.getLogger(PatientMedicationDetails.class);
	
	@Autowired
	private IMedsPatientsRepository iMedsPatientsRepository;

	@Autowired
	private IMedicationService iMedicationService;
	
	@Autowired
	private RestTemplate restTemplate;

	@PostConstruct
	public void myMethod() {
		System.out.println(">>>>>>. myMethod Called....");
		iMedsPatientsRepository.saveAll(Arrays.asList(new MedsPatient(1, 1),new MedsPatient(1, 2),new MedsPatient(1, 3),new MedsPatient(2, 1),new MedsPatient(2, 2)));
	}
	
	public boolean saveMedsPatientInformation(List<MedsPatient> list) {
		LOGGER.info("Inside saveMedsPatientInformation...");
		List<Patient> patientDetails = null;
		HttpEntity<?> entity = null;
		String composedJson = null;
		List<Medication> medsDetails = list.stream().map(t -> t.getMedids()).distinct().map(m -> {
			Medication med = new Medication();
			med.setId(m);
			return med;
		}).collect(Collectors.toList());
		LOGGER.info("medsDetails : {} ", medsDetails);
		if (iMedicationService.isMedicineAvailable(medsDetails)) {
			patientDetails = list.stream().map(t -> t.getPatientId()).distinct().map(t -> {
				Patient patient = new Patient();
				patient.setId(t);
				return patient;
			}).collect(Collectors.toList());
			try {
				composedJson = new ObjectMapper().writeValueAsString(patientDetails);
				LOGGER.info(" -----------========= medsDetails : {} ",
						new ObjectMapper().writeValueAsString(medsDetails));
				LOGGER.info("patientDetails : {} ", composedJson);
			} catch (Exception e) {
				e.printStackTrace();
			}
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.set("Content-Type", "application/json");
			headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
			headers.setHost(InetSocketAddress.createUnresolved("127.0.0.1", 8081));
			try {
				headers.setContentLength(composedJson.getBytes("UTF-8").length);
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			entity = new HttpEntity<>(composedJson, headers);
			LOGGER.info(">>>>>>>>>>>>> BODY : {}, Headers {} ", entity.getBody(), entity.getHeaders());
			ResponseEntity<?> response = restTemplate.exchange("http://patient-db-server/patient/checkPatientAvailability",
					HttpMethod.POST, entity, String.class);
			LOGGER.info(">>>>>>>>>>>>>>>>>>>>>>> response {}", response.getBody());
			if (response.getStatusCode().equals(HttpStatus.OK)
					&& !response.getBody().toString().equalsIgnoreCase("true")) {
				return false;
			}
		} else {
			return false;
		}

		return (iMedsPatientsRepository.saveAll(list).equals(list)) ? true : false;
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
