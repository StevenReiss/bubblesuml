package com.baselet.diagram.draw.swing;

import com.baselet.control.StringStyle;
import com.baselet.control.enums.AlignHorizontal;

public class Text {
	private final StringStyle[] text;
	private final double x;
	private final double y;
	private final AlignHorizontal horizontalAlignment;

	public Text(StringStyle[] t, double xv, double yv, AlignHorizontal al) {
		this.text = t;
		this.x = xv;
		this.y = yv;
		horizontalAlignment = al;
	}

	public StringStyle[] getText() {
		return text;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public AlignHorizontal getHorizontalAlignment() {
		return horizontalAlignment;
	}
}