package com.baselet.control.basics.geom;

public class Point {

	public int x_loc;
	public int y_loc;

	public Point() {}

	public Point(int x, int y) {
		super();
		this.x_loc = x;
		this.y_loc = y;
	}

	public int getX() {
		return x_loc;
	}

	public void setX(int x) {
		this.x_loc = x;
	}

	public int getY() {
		return y_loc;
	}

	public void setY(int y) {
		this.y_loc = y;
	}

	public Point move(int diffX, int diffY) {
		x_loc += diffX;
		y_loc += diffY;
		return this;
	}

	public double distance(Point o) {
		double distX = o.getX() - getX();
		double distY = o.getY() - getY();
		return Math.sqrt(distX * distX + distY * distY);
	}

	public Point copy() {
		return new Point(x_loc, y_loc);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + x_loc;
		result = prime * result + y_loc;
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
		Point other = (Point) obj;
		if (x_loc != other.x_loc) {
			return false;
		}
		if (y_loc != other.y_loc) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "p(x=" + x_loc + ", y=" + y_loc + ")";
	}

	public PointDouble toPointDouble() {
		return new PointDouble(x_loc, y_loc);
	}

}
