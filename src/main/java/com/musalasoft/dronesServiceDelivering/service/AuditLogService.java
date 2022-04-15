package com.musalasoft.dronesServiceDelivering.service;

import com.musalasoft.dronesServiceDelivering.model.entity.AuditLog;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:40:56 PM
 */
public interface AuditLogService {

	AuditLog save(AuditLog log);

	void checkDroneBatteryLevel();
}
