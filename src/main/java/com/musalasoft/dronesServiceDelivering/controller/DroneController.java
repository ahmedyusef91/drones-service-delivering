package com.musalasoft.dronesServiceDelivering.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 14, 2022 1:23:20 PM
 */
@RestController
@RequiredArgsConstructor
@RequestMapping(name = "api")
public class DroneController {
//	@Autowired
//	private DroneService droneService;
//
//	  @PostMapping(value = "/v1/drone/registration", produces = MediaType.APPLICATION_JSON_VALUE)
//	    public ResponseEntity<?> registerDrone(@Valid @RequestBody DroneRequest request) {
//
//	        GenericResponse<?> response = droneService.droneRegistration(request);
//
//	        if(!response.getResponseCode().equals(ResponseCode.SUCCESS.getCode()))
//	            return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.PRECONDITION_FAILED);
//
//	        return new ResponseEntity<GenericResponse<?>>(response, HttpStatus.OK);
//	    }
//	@GetMapping(path = "/available")
//	public ResponseEntity<?> checkAvailableDrones() {
//		List<Drone> drones = droneService.checkAvailableDrones();
//		return new ResponseEntity<>(drones, HttpStatus.OK);
//	}
//
//	@PostMapping(path = "/battery/{serialNumber}")
//	public ResponseEntity<?> checkBatteryLevelForGivenDrone(@PathVariable("serialNumber") String serialNumber) {
//		BigDecimal batteryLevel = droneService.checkBatteryLevelForGivenDrone(serialNumber);
//		return new ResponseEntity<>(batteryLevel, HttpStatus.CREATED);
//	}
//
//	@PostMapping(path = "/load")
//	public ResponseEntity<?> loadingDroneWithMedicationItem(
//			@Valid @NotNull @RequestBody DroneMedicationLoad dronMedicatioLoad) {
//		DroneMedicationLoad dronMedicatioLoading = droneService.loadingDroneWithMedicationItem(dronMedicatioLoad);
//		return new ResponseEntity<>(dronMedicatioLoading, HttpStatus.CREATED);
//	}
//
//	@GetMapping(path = "items/{serialNumber}")
//	public ResponseEntity<?> checkLoadedMedicationItem(@PathVariable("serialNumber") String serialNumber) {
//		boolean isLoaded = droneService.checkLoadedMedicationItem(serialNumber);
//		return new ResponseEntity<>(isLoaded, HttpStatus.OK);
//	}

}
