package com.poc.dbserver.service.impl;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.dbserver.model.Medication;
import com.poc.dbserver.service.IMedicationService;
import com.poc.dbserver.service.meds.repo.IMedicationRepository;

@Service
public class MedicationService implements IMedicationService{
	
	@Autowired
	private IMedicationRepository iMedicationRepository;
	
	@PostConstruct
	public void testDataInset() {
		List<Medication> medsLst = Arrays.asList(
				new Medication("HQC", "123", LocalDate.now()),
				new Medication("Cetrizine", "1234", LocalDate.now()),
				new Medication("Crocin", "12345", LocalDate.now()),
				new Medication("Burffen", "123456", LocalDate.now()),
				new Medication("alprax", "1234567", LocalDate.now())
				);
		saveAll(medsLst);
	}

	@Override
	public boolean saveMedsInformation(Medication medsDetails) {
		return (iMedicationRepository.save(medsDetails).getId()==medsDetails.getId()) ? true : false;
	}

	@Override
	public List<Medication> saveAll(List<Medication> medsDetails) {
		return iMedicationRepository.saveAll(medsDetails);
	}

	@Override
	public Optional<Medication> getByMedicineId(Long id) {
		return iMedicationRepository.findById(id);
	}
	
	@Override
	public boolean isMedicineAvailable(List<Medication> medsDetails) {
		List<Long> medids = medsDetails.stream().map(t->t.getId()).sorted().collect(Collectors.toList());
		List<Long> reponseids=iMedicationRepository.findAllById(medids).stream().map(t->t.getId()).sorted().collect(Collectors.toList());
		return medids.equals(reponseids)? true: false;
	}

	@Override
	public void deleteByID(Long id) {
		iMedicationRepository.deleteById(id);
	}

	@Override
	public boolean updateMedication(Long id, Medication medsDetails) {
		medsDetails.setId(id);
		iMedicationRepository.save(medsDetails);
		return false;
	}

	@Override
	public List<Medication> searchMedicationByIdAndNameAndAgeAndGenderAndAccessionNumberAndDOB(Medication medsDetails) {
		Medication meds = new Medication(medsDetails);
		if(meds.getName()!=null && meds.getBatchNo()!=null && meds.getExpiryDate()!=null ) {
			return iMedicationRepository.findMedicationByNameAndBatchNoAndExpiryDate(meds.getName(), meds.getBatchNo(), meds.getExpiryDate());	
		}else if(meds.getName()!=null && meds.getBatchNo()!=null) {
			return iMedicationRepository.findMedicationByNameAndBatchNo(meds.getName(), meds.getBatchNo());	
		}else if(meds.getName()!=null && meds.getExpiryDate()!=null ) {
			return iMedicationRepository.findMedicationByNameAndExpiryDate(meds.getName(), meds.getExpiryDate());	
		}else if(meds.getBatchNo()!=null && meds.getExpiryDate()!=null ) {
			return iMedicationRepository.findMedicationByBatchNoAndExpiryDate(meds.getBatchNo(), meds.getExpiryDate());	
		}else if(meds.getName()!=null ) {
			return iMedicationRepository.findMedicationByName(meds.getName());	
		}else if(meds.getBatchNo()!=null) {
			return iMedicationRepository.findMedicationByBatchNo(meds.getBatchNo());	
		}else if(meds.getExpiryDate()!=null) {
			return iMedicationRepository.findMedicationByExpiryDate(meds.getExpiryDate());	
		}
		return null;
	}

	@Override
	public List<Medication> getMedicinesInformation(List<Medication> medsDetails) {
		return iMedicationRepository.findAllById(medsDetails.stream().mapToLong(m->m.getId()).boxed().collect(Collectors.toList()));
	}
	

}
