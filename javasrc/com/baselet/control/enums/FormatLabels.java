package com.baselet.control.enums;

public enum FormatLabels {
	UNDERLINE("_"),
	BOLD("*"),
	ITALIC("/");

	private String value;

	private FormatLabels(String v) {
		this.value = v;
	}

	public String getValue() {
		return value;
	}

}
