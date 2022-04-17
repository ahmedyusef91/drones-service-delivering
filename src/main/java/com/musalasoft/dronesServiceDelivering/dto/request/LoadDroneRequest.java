package com.musalasoft.dronesServiceDelivering.dto.request;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.musalasoft.dronesServiceDelivering.model.entity.Medication;

import lombok.Data;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 6:18:17 PM
 */
@Data
public class LoadDroneRequest {
	@NotNull(message = "Drone id is required")
	private String serialNumber;
	private List<Medication> items;
}
