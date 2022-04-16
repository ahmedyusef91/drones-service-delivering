package com.musalasoft.dronesServiceDelivering.service;

import java.util.List;
import java.util.Optional;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:46:24 PM
 */
public interface DroneService {

	// registring a drone
	Drone registerDrone(Drone drone);

	// loading a drone with modification item
	Drone loadingDroneWithMedicationItem(Drone drone);

	// check available drones for loading
	List<Drone> checkAvailableDrones();

	// findBySerialNumber
	Optional<Drone> findBySerialNumber(String serialNumber);

	List<Drone> findAll();

}
