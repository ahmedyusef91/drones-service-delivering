package com.musalasoft.dronesServiceDelivering.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.musalasoft.dronesServiceDelivering.model.type.ResponseStatus;

import lombok.Data;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 6:16:50 PM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class GenericResponse<T> {
	private String responseDescription;
	private String responseCode;
	private ResponseStatus responseStatus;
	private T responsePayload;

	public GenericResponse() {
	}

	public GenericResponse(String responseDescription) {
		this.responseDescription = responseDescription;
	}

	public GenericResponse(String responseCode, ResponseStatus responseStatus, String responseDescription,
			T responsePayload) {
		this.responseDescription = responseDescription;
		this.responseStatus = responseStatus;
		this.responseCode = responseCode;
		this.responsePayload = responsePayload;
	}

}
