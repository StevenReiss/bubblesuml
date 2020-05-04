package com.baselet.control.basics.geom;

/**
 * an immutable point with double coordinates
 * must be immutable because equals and hashcode is overwritten and sets and maps can contain PointDouble as keys
 */
public class PointDouble {

	public final Double x_loc;
	public final Double y_loc;

	public PointDouble(double x, double y) {
		super();
		this.x_loc = x;
		this.y_loc = y;
	}

	public Double getX() {
		return x_loc;
	}

	public Double getY() {
		return y_loc;
	}

	public double distance(PointDouble o) {
		double distX = o.getX() - getX();
		double distY = o.getY() - getY();
		return Math.sqrt(distX * distX + distY * distY);
	}

	public PointDouble copy() {
		return new PointDouble(x_loc, y_loc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (x_loc == null ? 0 : x_loc.hashCode());
		result = prime * result + (y_loc == null ? 0 : y_loc.hashCode());
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
		PointDouble other = (PointDouble) obj;
		if (x_loc == null) {
			if (other.x_loc != null) {
				return false;
			}
		}
		else if (!x_loc.equals(other.x_loc)) {
			return false;
		}
		if (y_loc == null) {
			if (other.y_loc != null) {
				return false;
			}
		}
		else if (!y_loc.equals(other.y_loc)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "p(x=" + x_loc + " y=" + y_loc + ")";
	}

}
