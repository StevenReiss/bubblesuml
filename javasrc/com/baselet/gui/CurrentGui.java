package com.baselet.gui;

public class CurrentGui {

	private final static CurrentGui instance = new CurrentGui();

	public static CurrentGui getInstance() {
		return instance;
	}

	private BaseGUI gui;

	public void setGui(BaseGUI g) {
		this.gui = g;
	}

	public BaseGUI getGui() {
		return gui;
	}

}
