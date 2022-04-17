package com.musalasoft.dronesServiceDelivering.model.type;

/**
 * @author Ahmed.Yusef
 *
 * @Date Apr 13, 2022 4:25:40 PM
 */
public enum Model {
	LIGHTWEIGHT("LIGHTWEIGHT"), MIDDLEWEIGHT("MIDDLEWEIGHT"), CRUISERWEIGHT("CRUISERWEIGHT"), HEAVYWEIGHT("HEAVYWEIGHT");

	final String value;

	private Model(String value) {
		this.value = value;
	}

	public String value() {
		return this.value;
	}

	public static Model fromValue(String value) {
		for (Model e : Model.values()) {
			if (e.value.equals(value)) {
				return e;
			}
		}
		throw new IllegalArgumentException(value);
	}

}
