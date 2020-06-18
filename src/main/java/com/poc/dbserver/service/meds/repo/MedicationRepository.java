package com.poc.dbserver.service.meds.repo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.poc.dbserver.model.Medication;

@Repository
public interface MedicationRepository extends JpaRepository<Medication, Long>{

	List<Medication> findMedicationByName(String name);

	List<Medication> findMedicationByExpiryDate(LocalDate expiryDate);

	List<Medication> findMedicationByBatchNo(String batchNo);

	List<Medication> findMedicationByNameAndBatchNoAndExpiryDate(String name, String batchNo, LocalDate expiryDate);

	List<Medication> findMedicationByNameAndBatchNo(String name, String batchNo);

	List<Medication> findMedicationByNameAndExpiryDate(String name, LocalDate expiryDate);

	List<Medication> findMedicationByBatchNoAndExpiryDate(String batchNo, LocalDate expiryDate);


}
