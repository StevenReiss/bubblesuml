package com.baselet.element.elementnew.plot.drawer;

import com.baselet.control.basics.geom.Dimension;

public class PlotGridDrawConfig {

	private final Dimension realSize;
	private final Dimension size;
	private final Double minValue;
	private final Double maxValue;

	public PlotGridDrawConfig(Dimension rs, Dimension s, Double minv, Double maxv) {
		super();
		this.realSize = rs;
		this.size = s;
		this.minValue = minv;
		this.maxValue = maxv;
	}

	public Dimension getRealSize() {
		return realSize;
	}

	public Dimension getSize() {
		return size;
	}

	public Double getMinValue() {
		return minValue;
	}

	public Double getMaxValue() {
		return maxValue;
	}
}