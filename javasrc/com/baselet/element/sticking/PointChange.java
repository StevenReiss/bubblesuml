package com.baselet.element.sticking;

public class PointChange {
	private final Integer index;
	private final int diffX;
	private final int diffY;

	public PointChange(Integer i, int dx, int dy) {
		super();
		this.index = i;
		this.diffX = dx;
		this.diffY = dy;
	}

	public Integer getIndex() {
		return index;
	}

	public int getDiffX() {
		return diffX;
	}

	public int getDiffY() {
		return diffY;
	}

	@Override
	public String toString() {
		return "PointChange [index=" + index + ", diffX=" + diffX + ", diffY=" + diffY + "]";
	}

}
