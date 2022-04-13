package com.musalasoft.dronesServiceDelivering.model.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 4:39:21 PM
 */
@Setter
@Getter
public class EntityNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private String code;
	private Object[] params;

	public EntityNotFoundException(String code, Object[] params) {
		this.code = code;
		this.params = params;
	}

}