package com.baselet.control.basics.geom;

public class DimensionDouble {

	private double height;
	private double width;

	public DimensionDouble(double w, double h) {
		super();
		this.width = w;
		this.height = h;
	}

	public double getWidth() {
		return width;
	}

	public double getHeight() {
		return height;
	}

	@Override
	public String toString() {
		return "Dimension [height=" + height + ", width=" + width + "]";
	}

}
