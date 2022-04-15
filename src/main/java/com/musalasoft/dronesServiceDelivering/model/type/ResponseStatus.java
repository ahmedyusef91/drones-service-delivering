package com.musalasoft.dronesServiceDelivering.model.type;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 15, 2022 6:20:11 PM
 */
public enum ResponseStatus {
	SUCCESS("SUCCESSFUL"), FAILURE("FAILED"), UNKNOWN("UNKNOWN"), TIMEOUT("TIMEOUT");

	final String value;

	private ResponseStatus(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static ResponseStatus fromValue(String value) {
		for (ResponseStatus e : ResponseStatus.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
