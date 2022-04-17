package com.musalasoft.dronesServiceDelivering.service.impl;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.model.exception.BusinessException;
import com.musalasoft.dronesServiceDelivering.repository.MedicationRepository;
import com.musalasoft.dronesServiceDelivering.service.DroneService;
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

	@Autowired
	private DroneService droneService;

	@Override
	public Medication registerMedication(Medication medication, MultipartFile file) throws IOException {
		byte[] imagebytes = ImageUtil.processImage(file);
		medication.setImage(imagebytes);
		return this.medicationRepository.save(medication);
	}

	@Override
	public List<Medication> checkLoadedMedicationItem(String droneSerialNumber) {
		Drone dronedb = droneService.findBySerialNumber(droneSerialNumber).orElseThrow(
				() -> new BusinessException("validation.drone.notfound", new Object[] { droneSerialNumber }));
		List<Medication> medicationList = dronedb.getMedications();
		return medicationList;
	}
}
