package com.musalasoft.dronesServiceDelivering.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.dronesServiceDelivering.model.entity.Drone;
import com.musalasoft.dronesServiceDelivering.model.entity.Medication;
import com.musalasoft.dronesServiceDelivering.model.exception.BusinessException;
import com.musalasoft.dronesServiceDelivering.service.BatteryService;
import com.musalasoft.dronesServiceDelivering.service.DroneService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:49:13 PM
 */
@Service
public class BatteryServiceImpl implements BatteryService {
	@Autowired
	private DroneService droneService;

	@Override
	public double checkBatteryLevel(String serialNumber) {
		Drone droneInDb = droneService.findBySerialNumber(serialNumber)
				.orElseThrow(() -> new BusinessException("Drone serial number not found", null));

		List<Double> itemWeights = droneInDb.getMedications().stream().map(Medication::getWeight)
				.collect(Collectors.toList());

		Double medicationSum = droneInDb.getMedications().size() == 0 ? 0
				: itemWeights.stream().mapToDouble(Double::doubleValue).sum();

		return ((droneInDb.getWeightLimit() - medicationSum) / 5);
	}
}
