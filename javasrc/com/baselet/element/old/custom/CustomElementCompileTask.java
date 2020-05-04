package com.baselet.element.old.custom;

import java.util.TimerTask;

public class CustomElementCompileTask extends TimerTask {

	private CustomElementHandler handler;

	public CustomElementCompileTask(CustomElementHandler h) {
		this.handler = h;
	}

	@Override
	public void run() {
		handler.runCompilation();
	}

}
