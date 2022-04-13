package com.musalasoft.dronesServiceDelivering.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.type.State;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 4:48:05 PM
 */
public interface DroneRepository extends JpaRepository<Drone, String> {

	List<Drone> findAllByState(State state);

	Drone findBySerialNumber(String serialNumber);

}