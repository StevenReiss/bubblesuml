package com.baselet.control.config;

public class SharedConfig {
	private static final SharedConfig instance = new SharedConfig();

	public static SharedConfig getInstance() {
		return instance;
	}

	private boolean show_stickingpolygon = true;
	private boolean stickingEnabled = true;
	private boolean dev_mode = false; // TODO should be moved to a shared config class

	private SharedConfig() {}

	public boolean isShow_stickingpolygon() {
		return show_stickingpolygon;
	}

	public void setShow_stickingpolygon(boolean stickingpolygon) {
		this.show_stickingpolygon = stickingpolygon;
	}

	public boolean isStickingEnabled() {
		return stickingEnabled;
	}

	public void setStickingEnabled(boolean fg) {
		this.stickingEnabled = fg;
	}

	public boolean isDev_mode() {
		return dev_mode;
	}

	public void setDev_mode(boolean devmode) {
		this.dev_mode = devmode;
	}
}
