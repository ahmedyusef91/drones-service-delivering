package com.musalasoft.dronesServiceDelivering.controller;

import java.io.IOException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.dronesServiceDelivering.dto.request.MedicationRequest;
import com.musalasoft.dronesServiceDelivering.mapper.MedicationMapper;
import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.service.MedicationService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 14, 2022 1:39:45 PM
 */
@RestController
@RequestMapping
@Validated
public class MedicationController {

	@Autowired
	private MedicationService medicationSerivce;

	@Autowired
	private MedicationMapper medicationMapper;

	@PostMapping(value = "/v1/medication/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> registerMedication(@Valid @RequestPart("request") MedicationRequest medicationRequest,
			@RequestPart("image") MultipartFile image) throws IOException {
		Medication medication = medicationMapper.unmap(medicationRequest);
		this.medicationSerivce.registerMedication(medication, image);
		return ResponseEntity.status(HttpStatus.CREATED).body(medication);
	}

	@GetMapping(value = "/v1/medication/checkLoadedItemsForDrone/{droneSerialNumber}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getAllLoadedItems(@Valid @PathVariable String droneSerialNumber) {
		List<Medication> medications = medicationSerivce.checkLoadedMedicationItem(droneSerialNumber);
		return ResponseEntity.status(HttpStatus.OK).body(medications);
	}
}
