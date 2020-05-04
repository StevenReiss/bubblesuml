package com.baselet.element.sticking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;

import com.baselet.control.basics.geom.Line;
import com.baselet.control.basics.geom.PointDouble;
import com.baselet.control.basics.geom.Rectangle;

public class StickingPolygon {

	public static class StickLine extends Line {

		StickLine(PointDouble p1, PointDouble p2) {
			super(p1, p2);
		}

		// calculates the difference between this line and the other line at the specified x or y coordinate (whichever fits better)
		public PointDouble diffToLine(StickLine s, int inX, int inY) {
			double x = 0;
			double y = 0;
			if (getEnd().x_loc.equals(getStart().x_loc)) {
				// AB: Fixed: use s.getStart().x instead of getStart().x
				x = s.getStart().x_loc - (s.getEnd().x_loc - s.getStart().x_loc) - inX; // mitte der neuen linie

				if (s.getEnd().x_loc.equals(s.getStart().x_loc)) {
					// vertical lines - no y difference except the line is at an end
					y = 0;
					if (s.getStart().y_loc > s.getEnd().y_loc) {
						if (s.getStart().y_loc < inY) {
							y = s.getStart().y_loc - inY;
						}
						else if (s.getEnd().y_loc > inY) {
							y = s.getEnd().y_loc - inY;
						}
					}
					else {
						if (s.getEnd().y_loc < inY) {
							y = s.getEnd().y_loc - inY;
						}
						else if (s.getStart().y_loc > inY) {
							y = s.getStart().y_loc - inY;
						}
					}
					return new PointDouble(x, y);
				}
			}
			else {
				x = (inX - getStart().x_loc) * (s.getEnd().x_loc - s.getStart().x_loc) / (getEnd().x_loc - getStart().x_loc) + s.getStart().x_loc - inX;
			}

			if (getEnd().y_loc.equals(getStart().y_loc)) {
				// AB: Fixed: use s.getStart().x instead of getStart().x
				y = s.getStart().y_loc - (s.getEnd().y_loc - s.getStart().y_loc) - inY;

				if (s.getEnd().y_loc.equals(s.getStart().y_loc)) {
					// horizontal lines - no x difference except the line is at an end
					x = 0;
					if (s.getStart().x_loc > s.getEnd().x_loc) {
						if (s.getStart().x_loc < inX) {
							x = s.getStart().x_loc - inX;
						}
						else if (s.getEnd().x_loc > inX) {
							x = s.getEnd().x_loc - inX;
						}
					}
					else {
						if (s.getEnd().x_loc < inX) {
							x = s.getEnd().x_loc - inX;
						}
						else if (s.getStart().x_loc > inX) {
							x = s.getStart().x_loc - inX;
						}
					}
				}
			}
			else {
				y = (inY - getStart().y_loc) * (s.getEnd().y_loc - s.getStart().y_loc) / (getEnd().y_loc - getStart().y_loc) + s.getStart().y_loc - inY;
			}

			return new PointDouble(x, y);
		}

		public boolean isConnected(PointDouble p, int maxDistance) {
			double distance = getDistanceToPoint(p);
			return distance < maxDistance;
		}
	}

	private final Vector<StickLine> stick = new Vector<StickLine>();
	private PointDouble lastpoint = null;
	private PointDouble firstpoint = null;
	private final int elementX;
	private final int elementY;

	// store all points for the copyZoomed() method
	private final List<PointDouble> allPoints = new ArrayList<PointDouble>();

	public StickingPolygon(int x, int y) {
		this.elementX = x;
		this.elementY = y;
	}

	public void addPoint(List<PointDouble> points) {
		for (PointDouble p : points) {
			addPoint(p.getX(), p.getY());
		}
	}

	public void addPoint(double x, double y) {
		PointDouble p = new PointDouble(elementX + x, elementY + y);
		allPoints.add(p);
		if (firstpoint == null) {
			firstpoint = p;
		}
		else {
			stick.add(new StickLine(lastpoint, p));
		}
		lastpoint = p;
	}

	public void addPoint(int x, int y, boolean connectToFirst) {
		this.addPoint(x, y);
		if (connectToFirst) {
			allPoints.add(firstpoint);
			stick.add(new StickLine(lastpoint, firstpoint));
		}
	}

	public void addRectangle(int x, int y, int width, int height) {
		addPoint(x, y);
		addPoint(x + width, y);
		addPoint(x + width, y + height);
		addPoint(x, y + height, true);
	}

	public void addRectangle(Rectangle rect) {
		addRectangle(rect.getX(), rect.getY(), rect.getWidth(), rect.getHeight());
	}

	public StickLine getLine(int index) {
		return stick.get(index);
	}

	public Vector<StickLine> getStickLines() {
		return stick;
	}

	public int isConnected(PointDouble p, int gridSize) {
		int con = -1;
		for (int i = 0; i < stick.size(); i++) {
			if (stick.get(i).isConnected(p, gridSize)) {
				return i;
			}
		}

		return con;
	}

	@Override
	public String toString() {
		return "StickingPolygon [stick=" + Arrays.toString(stick.toArray(new StickLine[stick.size()])) + "]";
	}

}
