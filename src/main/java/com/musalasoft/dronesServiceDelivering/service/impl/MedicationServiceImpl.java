package com.musalasoft.dronesServiceDelivering.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.repository.MedicationRepository;
import com.musalasoft.dronesServiceDelivering.service.MedicationService;
import com.musalasoft.dronesServiceDelivering.util.ImageUtil;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 14, 2022 1:18:05 PM
 */
@Service
public class MedicationServiceImpl implements MedicationService {

	@Autowired
	private MedicationRepository medicationRepository;

	@Override
	public Medication registerMedication(Medication medication, MultipartFile file) throws IOException {
		byte[] imagebytes = ImageUtil.processImage(file);
		medication.setImage(imagebytes);
		return this.medicationRepository.save(medication);
	}

	@Override
	public List<Medication> checkLoadedMedicationItem(String name) {

		List<Medication> medications = medicationRepository.findByName(name);
		
		return medications;
	}
}
