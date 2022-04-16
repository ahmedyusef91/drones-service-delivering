package com.musalasoft.dronesServiceDelivering.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.model.exception.BusinessException;
import com.musalasoft.dronesServiceDelivering.model.type.State;
import com.musalasoft.dronesServiceDelivering.repository.DroneRepository;
import com.musalasoft.dronesServiceDelivering.service.DroneService;
import com.musalasoft.dronesServiceDelivering.validation.ValidationUtil;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 6:23:28 PM
 */
@Service
public class DroneServiceImpl implements DroneService {

	@Autowired
	private DroneRepository droneRepository;

	@Override
	public Drone registerDrone(Drone drone) {
		if (!ValidationUtil.validateDroneBatteryCapacity(drone.getBatteryCapacity()))
			throw new BusinessException("invalid drone battery capacity");

		if (!ValidationUtil.validateDroneWeightLimit(drone.getWeightLimit()))
			throw new BusinessException("invalid drone weight limit");

		Drone droneInDb = findBySerialNumber(drone.getSerialNumber())
				.orElseThrow(() -> new BusinessException("Drone serial number not found"));

		if (!droneInDb.getState().equals(State.LOADING))
			throw new BusinessException("Update drone state to Loading before loading any items");

		Drone newDrone = Drone.builder().serialNumber(drone.getSerialNumber())
				.batteryCapacity(drone.getBatteryCapacity()).model(drone.getModel()).state(drone.getState()).build();

		return droneRepository.save(newDrone);
	}

	@Override
	public Drone loadingDroneWithMedicationItem(Drone drone) {

		Drone dronedb = findBySerialNumber(drone.getSerialNumber()).orElseThrow(
				() -> new BusinessException("Drone with serial number " + drone.getSerialNumber() + " not found"));

		List<Double> itemWeights = drone.getMedications().stream().map(Medication::getWeight)
				.collect(Collectors.toList());

		double allItemsWeightSum = itemWeights.stream().mapToDouble(Double::doubleValue).sum();

		if (allItemsWeightSum > dronedb.getWeightLimit())
			throw new BusinessException("Medication weight exceeds drone limit");

		List<Medication> medications = drone.getMedications().stream()
				.map(m -> new Medication(m.getCode(), m.getName(), m.getWeight(), m.getImage()))
				.collect(Collectors.toList());

		if (allItemsWeightSum == dronedb.getWeightLimit())
			dronedb.setState(State.LOADED);

		dronedb.setMedications(medications);
		Drone droneInDb = droneRepository.save(dronedb);

		return droneInDb;
	}

	@Override
	public List<Drone> checkAvailableDrones() {
		return droneRepository.findAllByState(State.IDLE);
	}

	@Override
	public Optional<Drone> findBySerialNumber(String number) {
		return droneRepository.findBySerialNumber(number);
	}

	@Override
	public List<Drone> findAll() {
		return (List<Drone>) droneRepository.findAll();
	}

}
