package com.musalasoft.dronesServiceDelivering.service.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.entity.DroneMedicationLoad;
import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.model.exception.BusinessException;
import com.musalasoft.dronesServiceDelivering.model.type.State;
import com.musalasoft.dronesServiceDelivering.repository.DroneMedicationLoadRepository;
import com.musalasoft.dronesServiceDelivering.repository.DroneRepository;
import com.musalasoft.dronesServiceDelivering.repository.MedicationRepository;
import com.musalasoft.dronesServiceDelivering.service.DroneService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 6:23:28 PM
 */
@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;
	@Autowired
	private MedicationRepository medicationRepository;
	@Autowired
	private DroneMedicationLoadRepository droneLoadMedicationRepository;

	@Override
	public Drone registerDrone(Drone drone) {
		drone.setState(State.IDLE);
		return droneRepository.save(drone);
	}

	@Override
	public DroneMedicationLoad loadingDroneWithMedicationItem(DroneMedicationLoad dronMedicatioLoad) {

		Drone drone = droneRepository.findBySerialNumber(dronMedicatioLoad.getSerialNumber());
		Medication medication = medicationRepository.findByCode(dronMedicatioLoad.getCode());

		if (drone == null) {
			throw new BusinessException("validation.drone.notFound", new Object[] { dronMedicatioLoad.getSerialNumber() });
		}

		if (medication == null) {
			throw new BusinessException("validation.medication.notexist", new Object[] { dronMedicatioLoad.getCode() });
		}

		DroneMedicationLoad droneMedicationLoad = new DroneMedicationLoad();
		droneMedicationLoad.setSerialNumber(dronMedicatioLoad.getSerialNumber());
		droneMedicationLoad.setCode(dronMedicatioLoad.getCode());
		droneMedicationLoad.setCreationDate(LocalDateTime.now());
		droneMedicationLoad.setSource(dronMedicatioLoad.getSource());
		droneMedicationLoad.setDestination(dronMedicatioLoad.getDestination());
		droneLoadMedicationRepository.save(droneMedicationLoad);

		this.droneRepository.updateState(drone, State.LOADING);

		return droneMedicationLoad;
	}

	@Override
	public boolean checkLoadedMedicationItem(String droneSerialNumber) throws BusinessException {
		Drone drone = droneRepository.findBySerialNumber(droneSerialNumber);
		DroneMedicationLoad droneMedicationLoad = droneLoadMedicationRepository.findBySerialNumber(droneSerialNumber);
		if (drone == null) {
			throw new BusinessException("validation.drone.notFound", new Object[] { droneSerialNumber });
		}

		if (droneMedicationLoad == null) {
			throw new BusinessException("validation.droneMedicationLoad.notloaded", new Object[] { droneSerialNumber });
		}

		Medication medication = medicationRepository.findByCode(droneMedicationLoad.getCode());

		if (medication == null) {
			throw new BusinessException("validation.medication.notexist", null);
		}

		if (drone.getWeightLimit() <= medication.getWeight()) {
			droneLoadMedicationRepository.delete(droneMedicationLoad);
			throw new BusinessException("validation.drone.exceedLimit",
					new Object[] { droneSerialNumber, drone.getWeightLimit() });
		}
		if (drone.getBatteryCapacity().compareTo(BigDecimal.valueOf(0.25)) < 0) {
			droneLoadMedicationRepository.delete(droneMedicationLoad);
			throw new BusinessException("validation.drone.batteryLow", new Object[] { droneSerialNumber });
		}

		if (droneMedicationLoad.getSource() == null) {
			droneLoadMedicationRepository.delete(droneMedicationLoad);
			throw new BusinessException("validation.droneMedicationLoad.source.notNull", null);
		}

		if (droneMedicationLoad.getDestination() == null) {
			droneLoadMedicationRepository.delete(droneMedicationLoad);
			throw new BusinessException("validation.droneMedicationLoad.destination.notNull", null);
		}

		this.droneRepository.updateState(drone, State.LOADED);

		return true;
	}

	@Override
	public List<Drone> checkAvailableDrones() {
		return droneRepository.findAllByState(State.IDLE);
	}

	@Override
	public BigDecimal checkBatteryLevelForGivenDrone(String droneSerialNumber) throws BusinessException {
		Drone drone = droneRepository.findBySerialNumber(droneSerialNumber);
		if (drone == null) {
			throw new BusinessException("validation.drone.notfound", new Object[] { droneSerialNumber });
		}
		BigDecimal batteryLevel = drone.getBatteryCapacity();

		return batteryLevel;
	}

}
