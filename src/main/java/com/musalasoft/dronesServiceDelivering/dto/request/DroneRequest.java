package com.musalasoft.dronesServiceDelivering.dto.request;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.musalasoft.dronesServiceDelivering.model.type.Model;
import com.musalasoft.dronesServiceDelivering.model.type.State;

import lombok.Data;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 6:14:39 PM
 */
@Data
public class DroneRequest {
	@Size(min = 5, max = 100, message = "Serial Number must be between 10 and 100")
	@NotNull(message = "Serial Number is required")
	private String serialNumber;
	@NotNull(message = "Weight is required")
	private Integer weight;
	@NotNull(message = "Battery Capacity is required")
	private Integer batteryCapacity;
	@NotNull(message = "Drone Model is required")
	private Model droneModel;
	@NotNull(message = "Drone State is required")
	private State droneState;
}
