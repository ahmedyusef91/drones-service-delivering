package com.musalasoft.dronesServiceDelivering.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.musalasoft.dronesServiceDelivering.service.BatteryService;
import com.musalasoft.dronesServiceDelivering.service.impl.BatteryServiceImpl;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 6:06:35 PM
 */
@Configuration
@ComponentScan(basePackages = "com.musalasoft.dronesServiceDelivering")
public class AnnotationConfig {
	@Bean
	public BatteryService batteryService() {
		return new BatteryServiceImpl();
	}
}
