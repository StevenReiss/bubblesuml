package com.baselet.element.sticking;

import com.baselet.element.sticking.StickingPolygon.StickLine;

public class StickLineChange {
	StickLine oldLine;
	StickLine newLine;

	public StickLineChange(StickLine ol, StickLine nl) {
		super();
		this.oldLine = ol;
		this.newLine = nl;
	}

	public StickLine getNew() {
		return newLine;
	}

	public StickLine getOld() {
		return oldLine;
	}

	@Override
	public String toString() {
		return "StickLineChange [oldLine=" + oldLine + ", newLine=" + newLine + "]";
	}

}
