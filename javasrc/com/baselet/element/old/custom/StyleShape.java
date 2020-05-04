package com.baselet.element.old.custom;

import java.awt.Color;
import java.awt.Shape;

import com.baselet.control.enums.LineType;

/**
 * Extended shape which supports the stroketype and the line thickness
 */
public class StyleShape {

	private Shape shape;
	private LineType lineType;
	private int lineThickness;
	private Color fgColor;
	private Color bgColor;
	private float alpha;

	public StyleShape(Shape s, LineType lt, int thk, Color fg, Color bg, float a) {
		super();
		this.shape = s;
		this.lineType = lt;
		this.lineThickness = thk;
		this.fgColor = fg;
		this.bgColor = bg;
		this.alpha = a;
	}

	public Shape getShape() {
		return shape;
	}

	public LineType getLineType() {
		return lineType;
	}

	public int getLineThickness() {
		return lineThickness;
	}

	public Color getFgColor() {
		return fgColor;
	}

	public Color getBgColor() {
		return bgColor;
	}

	public float getAlpha() {
		return alpha;
	}

	public void setShape(Shape s) {
		this.shape = s;
	}

	public void setLineType(LineType lt) {
		this.lineType = lt;
	}

	public void setLineThickness(int lt) {
		this.lineThickness = lt;
	}

	public void setFgColor(Color fg) {
		this.fgColor = fg;
	}

	public void setBgColor(Color bg) {
		this.bgColor = bg;
	}

	public void setAlpha(float a) {
		this.alpha = a;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(alpha);
		result = prime * result + (bgColor == null ? 0 : bgColor.hashCode());
		result = prime * result + (fgColor == null ? 0 : fgColor.hashCode());
		result = prime * result + lineThickness;
		result = prime * result + (shape == null ? 0 : shape.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		StyleShape other = (StyleShape) obj;
		if (Float.floatToIntBits(alpha) != Float.floatToIntBits(other.alpha)) {
			return false;
		}
		if (bgColor == null) {
			if (other.bgColor != null) {
				return false;
			}
		}
		else if (!bgColor.equals(other.bgColor)) {
			return false;
		}
		if (fgColor == null) {
			if (other.fgColor != null) {
				return false;
			}
		}
		else if (!fgColor.equals(other.fgColor)) {
			return false;
		}
		if (lineThickness != other.lineThickness) {
			return false;
		}
		if (lineType != other.lineType) {
			return false;
		}
		if (shape == null) {
			if (other.shape != null) {
				return false;
			}
		}
		else if (!shape.equals(other.shape)) {
			return false;
		}
		return true;
	}

}
