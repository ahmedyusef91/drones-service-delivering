package com.musalasoft.dronesServiceDelivering.service;

import java.math.BigDecimal;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 5:49:02 PM
 */
public interface BatteryService {
	BigDecimal checkBatteryLevel(String serialNumber);
}
