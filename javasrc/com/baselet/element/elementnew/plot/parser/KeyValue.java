package com.baselet.element.elementnew.plot.parser;

public class KeyValue {

	private String key;
	private String value;
	private int line;
	private boolean used;

	public KeyValue(String k, String v, int l) {
		super();
		this.key = k;
		this.value = v;
		this.line = l;
		used = false;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String k) {
		this.key = k;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String v) {
		this.value = v;
	}

	public int getLine() {
		return line;
	}

	public void setLine(int l) {
		this.line = l;
	}

	public boolean isUsed() {
		return used;
	}

	public void setUsed(boolean u) {
		this.used = u;
	}

	@Override
	public String toString() {
		return key + "\t-> " + value + " (line " + line + ")";
	}

}
