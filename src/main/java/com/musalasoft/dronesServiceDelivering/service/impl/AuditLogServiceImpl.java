package com.musalasoft.dronesServiceDelivering.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;

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
			BigDecimal capacity = batteryService.checkBatteryLevel(m.getSerialNumber());
			AuditLog newAuditLog = new AuditLog();
			newAuditLog.setDroneBatteryLevel(capacity);
			newAuditLog.setSerialNumber(m.getSerialNumber());

			save(newAuditLog);
		});
	}
}
