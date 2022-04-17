package com.musalasoft.dronesServiceDelivering.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.dronesServiceDelivering.dto.request.LoadDroneRequest;
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
			throw new BusinessException("validation.drone.batteryCapacity.invalid");

		if (!ValidationUtil.validateDroneWeightLimit(drone.getWeightLimit()))
			throw new BusinessException("validation.drone.invalidLimit");


		if (!drone.getState().equals(State.LOADING))
			throw new BusinessException("validation.droneMedicationLoad.notloading");
		if (drone.getBatteryCapacity() < 25) {
			throw new BusinessException("validation.drone.batteryCapacity.low");
		}
		Drone newDrone = Drone.builder().serialNumber(drone.getSerialNumber()).weightLimit(drone.getWeightLimit())
				.batteryCapacity(drone.getBatteryCapacity()).model(drone.getModel()).state(drone.getState()).build();

		return droneRepository.save(newDrone);
	}

	@Override
	public Drone loadingDroneWithMedicationItem(LoadDroneRequest drone) {

		Drone dronedb = findBySerialNumber(drone.getSerialNumber()).orElseThrow(
				() -> new BusinessException("validation.drone.notfound", new Object[] { drone.getSerialNumber() }));
		List<Double> itemWeights = drone.getItems().stream().map(Medication::getWeight).collect(Collectors.toList());

		double allItemsWeightSum = itemWeights.stream().mapToDouble(Double::doubleValue).sum();

		if (allItemsWeightSum > dronedb.getWeightLimit())
			throw new BusinessException("validation.drone.exceedLimit",
					new Object[] { drone.getSerialNumber(), dronedb.getWeightLimit() });
		if (dronedb.getBatteryCapacity() < 25) {
			throw new BusinessException("validation.drone.batteryCapacity.low");
		}
		dronedb.setState(State.LOADING);

		List<Medication> medications = drone.getItems().stream()
				.map(m -> new Medication(m.getCode(), m.getName(), m.getWeight(), m.getImage()))
				.collect(Collectors.toList());

		if (allItemsWeightSum == dronedb.getWeightLimit())
			dronedb.setState(State.LOADED);

		dronedb.setMedications(medications);
		Drone droneInDb = save(dronedb);

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

	@Override
	public Drone save(Drone drone) {
		return droneRepository.save(drone);
	}

}
