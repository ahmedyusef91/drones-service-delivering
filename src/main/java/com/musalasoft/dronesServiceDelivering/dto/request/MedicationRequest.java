package com.musalasoft.dronesServiceDelivering.dto.request;

import javax.validation.constraints.NotNull;

import lombok.Data;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 6:18:53 PM
 */
@Data
public class MedicationRequest {

	@NotNull(message = "Name is required")
	private String name;
	@NotNull(message = "Weight is required")
	private Integer weight;
	private String image;
	@NotNull(message = "Code is required")
	private String code;
}
