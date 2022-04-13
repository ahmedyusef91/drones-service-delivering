package com.musalasoft.dronesServiceDelivering.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.dronesServiceDelivering.model.entity.Medication;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 5:46:42 PM
 */
public interface MedicationService {
	// registering medication
	Medication registerMedication(Medication medication, MultipartFile file) throws IOException;
}
