package com.musalasoft.dronesServiceDelivering.controller;

import java.io.IOException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.service.MedicationService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 14, 2022 1:39:45 PM
 */
@RestController
@RequestMapping(path = "/medication")
@Validated
public class MedicationController {

	@Autowired
	private MedicationService medicationSerivce;

	@PostMapping(value = "/register", consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
	public ResponseEntity<?> registerMedication(@Valid @RequestPart("request") Medication medication,
			@RequestPart("image") MultipartFile image) throws IOException {
		this.medicationSerivce.registerMedication(medication, image);
		return ResponseEntity.status(HttpStatus.CREATED).body(medication);
	}
}
