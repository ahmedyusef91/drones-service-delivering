package com.musalasoft.dronesServiceDelivering.controller;

import java.math.BigDecimal;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.entity.DroneMedicationLoad;
import com.musalasoft.dronesServiceDelivering.service.DroneService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 14, 2022 1:23:20 PM
 */
@RestController
@RequestMapping(path = "/drone")
@Validated
public class DroneController {
	@Autowired
	private DroneService droneService;

	@PostMapping(path = "/register")
	public ResponseEntity<?> registerDrone(@Valid @NotNull @RequestBody Drone drone) {
		Drone newDrone = droneService.registerDrone(drone);
		return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
	}

	@GetMapping(path = "/available")
	public ResponseEntity<?> checkAvailableDrones() {
		List<Drone> drones = droneService.checkAvailableDrones();
		return new ResponseEntity<>(drones, HttpStatus.OK);
	}

	@PostMapping(path = "/battery/{serialNumber}")
	public ResponseEntity<?> checkBatteryLevelForGivenDrone(@PathVariable("serialNumber") String serialNumber) {
		BigDecimal batteryLevel = droneService.checkBatteryLevelForGivenDrone(serialNumber);
		return new ResponseEntity<>(batteryLevel, HttpStatus.CREATED);
	}

	@PostMapping(path = "/load")
	public ResponseEntity<?> loadingDroneWithMedicationItem(
			@Valid @NotNull @RequestBody DroneMedicationLoad dronMedicatioLoad) {
		DroneMedicationLoad dronMedicatioLoading = droneService.loadingDroneWithMedicationItem(dronMedicatioLoad);
		return new ResponseEntity<>(dronMedicatioLoading, HttpStatus.CREATED);
	}

	@GetMapping(path = "items/{serialNumber}")
	public ResponseEntity<?> checkLoadedMedicationItem(@PathVariable("serialNumber") String serialNumber) {
		boolean isLoaded = droneService.checkLoadedMedicationItem(serialNumber);
		return new ResponseEntity<>(isLoaded, HttpStatus.OK);
	}

}
