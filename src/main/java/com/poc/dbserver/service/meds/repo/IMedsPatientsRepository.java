package com.poc.dbserver.service.meds.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poc.dbserver.model.MedsPatient;

public interface IMedsPatientsRepository extends JpaRepository<MedsPatient, Long> {

	void deleteByPatientId(Long id);

	List<MedsPatient> findByPatientId(Long patientId);

	List<MedsPatient> findByPatientIdIn(List<Long> patientmeds);

}
