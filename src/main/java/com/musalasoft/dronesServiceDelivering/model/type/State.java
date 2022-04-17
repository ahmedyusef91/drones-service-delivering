package com.musalasoft.dronesServiceDelivering.model.type;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 4:15:25 PM
 */
public enum State {
	IDLE("IDLE"), LOADING("LOADING"), LOADED("LOADED"), DELIVERING("DELIVERING"), DELIVERED("DELIVERED"),
	RETURNING("RETURNING");

	final String value;

	private State(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static State fromValue(String value) {
		for (State e : State.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException(value);
	}
}
