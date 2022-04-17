package com.musalasoft.dronesServiceDelivering.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.musalasoft.dronesServiceDelivering.model.entity.AuditLog;
import com.musalasoft.dronesServiceDelivering.repository.AuditLogRepository;
import com.musalasoft.dronesServiceDelivering.service.AuditLogService;
import com.musalasoft.dronesServiceDelivering.service.BatteryService;
import com.musalasoft.dronesServiceDelivering.service.DroneService;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:41:06 PM
 */
@Service
public class AuditLogServiceImpl implements AuditLogService {

	@Autowired
	private DroneService droneService;
	@Autowired
	private AuditLogRepository logRepository;
	@Autowired
	private BatteryService batteryService;

	public AuditLog save(AuditLog log) {
		return logRepository.save(log);
	}

	public void checkDroneBatteryLevel() {

		droneService.findAll().forEach(m -> {
			double capacity = batteryService.checkBatteryLevel(m.getSerialNumber());
			AuditLog newAuditLog = new AuditLog();
			newAuditLog.setDroneBatteryLevel(capacity);
			newAuditLog.setSerialNumber(m.getSerialNumber());
			System.out.println("Serial Number:   " + m.getSerialNumber() + "      battery capacity     " + m.getBatteryCapacity());
			save(newAuditLog);
		});
	}
}
