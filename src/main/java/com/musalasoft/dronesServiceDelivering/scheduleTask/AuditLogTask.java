package com.musalasoft.dronesServiceDelivering.scheduleTask;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.musalasoft.dronesServiceDelivering.service.AuditLogService;

import lombok.RequiredArgsConstructor;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:39:54 PM
 */
@Component
@RequiredArgsConstructor
public class AuditLogTask {
	private final AuditLogService logService;

	@Scheduled(cron = "${droneBatteryLevelTiming}")
	public void logBatteryCapacity() {

		System.out.println("running audit log");
		logService.checkDroneBatteryLevel();

	}

}
