package com.musalasoft.dronesServiceDelivering.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musalasoft.dronesServiceDelivering.model.entity.DroneMedicationLoad;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:36:08 PM
 */
public interface DroneMedicationLoadRepository extends JpaRepository<DroneMedicationLoad, String> {

	DroneMedicationLoad findBySerialNumber(String serialno);

	DroneMedicationLoad findByCode(String code);
}
