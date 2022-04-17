package com.musalasoft.dronesServiceDelivering.dto.request;

import com.musalasoft.dronesServiceDelivering.model.type.State;

import lombok.Data;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 17, 2022 12:46:50 AM
 */
@Data
public class StateRequest {
	private String serialNumber;
	private State droneState;
}
