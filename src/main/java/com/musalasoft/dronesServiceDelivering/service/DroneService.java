package com.musalasoft.dronesServiceDelivering.service;

import java.math.BigDecimal;
import java.util.List;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.entity.DroneMedicationLoad;
import com.musalasoft.dronesServiceDelivering.model.exception.BusinessException;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:46:24 PM
 */
public interface DroneService {

	// registring a drone
	Drone registerDrone(Drone drone);

	// loading a drone with modification item
	DroneMedicationLoad loadingDroneWithMedicationItem(String droneSerialNumber, String medicationCode,
			String source, String destination);

	// check loaded modification items for given drones
	boolean checkLoadedMedicationItem(String droneSerialNumber) throws BusinessException;

	// check available drones for loading
	List<Drone> checkAvailableDrones();

	// check drone battery level for a given drone
	BigDecimal checkBatteryLevelForGivenDrone(String droneSerialNumber) throws BusinessException;

}
