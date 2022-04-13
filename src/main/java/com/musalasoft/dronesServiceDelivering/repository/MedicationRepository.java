package com.musalasoft.dronesServiceDelivering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musalasoft.dronesServiceDelivering.model.entity.Medication;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:21:26 PM
 */
public interface MedicationRepository extends JpaRepository<Medication, String> {

	Medication findByCode(String code);

}
