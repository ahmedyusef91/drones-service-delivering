package com.musalasoft.dronesServiceDelivering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.musalasoft.dronesServiceDelivering.dto.request.DroneRequest;
import com.musalasoft.dronesServiceDelivering.dto.request.LoadDroneRequest;
import com.musalasoft.dronesServiceDelivering.dto.request.StateRequest;
import com.musalasoft.dronesServiceDelivering.mapper.DroneMapper;
import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.exception.BusinessException;
import com.musalasoft.dronesServiceDelivering.model.type.State;
import com.musalasoft.dronesServiceDelivering.service.BatteryService;
import com.musalasoft.dronesServiceDelivering.service.DroneService;

import lombok.RequiredArgsConstructor;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 14, 2022 1:23:20 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping
public class DroneController {
	@Autowired
	private DroneService droneService;

	@Autowired
	private DroneMapper droneMapper;

	@Autowired
	private BatteryService batteryService;

	@PostMapping(path = "/v1/drone/registration", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneRequest request) {
		Drone drone = droneMapper.unmap(request);
		Drone newDrone = droneService.registerDrone(drone);
		return ResponseEntity.status(HttpStatus.CREATED).body(newDrone);
	}

	@GetMapping(value = "/v1/drone/getAll", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllRegisteredDrone() {

		List<Drone> response = droneService.findAll();

		return new ResponseEntity<List<Drone>>(response, HttpStatus.OK);
	}

	@PostMapping(value = "/v1/drone/loadDroneWithItems", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> loadDroneWithMedicationItems(@Valid @RequestBody LoadDroneRequest request) {

		Drone response = droneService.loadingDroneWithMedicationItem(request);

		return new ResponseEntity<Drone>(response, HttpStatus.OK);
	}

	@GetMapping(value = "/v1/drone/checkForAvailableDrones", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> checkAvailableDrones() {
		List<Drone> drones = droneService.checkAvailableDrones();
		return new ResponseEntity<>(drones, HttpStatus.OK);
	}

	@PutMapping(value = "/v1/drone/updateDroneState", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> updateDroneState(@Valid @RequestBody StateRequest request) {

		Drone droneInDb = droneService.findBySerialNumber(request.getSerialNumber())
				.orElseThrow(() -> new BusinessException("Drone serial number not found"));

		if (request.getDroneState().equals(State.LOADING)
				&& batteryService.checkBatteryLevel(droneInDb.getSerialNumber()) < 25)
			throw new BusinessException("Drone battery level too low");

		droneInDb.setState(request.getDroneState());
		Drone updatedDrone = droneService.save(droneInDb);

		return ResponseEntity.status(HttpStatus.OK).body(updatedDrone);
	}

}
