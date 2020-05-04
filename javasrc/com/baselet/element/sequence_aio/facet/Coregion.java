package com.baselet.element.sequence_aio.facet;

import com.baselet.control.basics.Line1D;
import com.baselet.control.basics.geom.PointDouble;
import com.baselet.diagram.draw.DrawHandler;

/**
 * <pre>
 * getLifelineLeftPartWidth
 *        +--+
 *            getLifelineRightPartWidth
 *           +------+
 *
 *           |
 *        +--+--+
 *        |     |
 *        |   +-+---+
 *        |   |     |
 * +-------------------+
 * |      |   |     |  |  height
 * |      |   |     |  |
 * +      |   +-+---+  +
 *        |     |
 *        +--+--+
 *           |
 * +--+             +--+
 * gap               gap
 *
 *</pre>
 */
public class Coregion implements LifelineOccurrence {

	private static final double COREGION_GAP_LIFELINE = 10;
	private static final double COREGION_HEIGHT = 10;

	private final Lifeline correspondingLifeline;
	private final int tick;
	private final boolean start;

	/**
	 * @param lifeline on which the Coregion is specified
	 * @param start if true then it is the beginning of a Coregion, otherwise it is the end of a Coregion
	 */
	public Coregion(Lifeline lifeline, int t, boolean s) {
		correspondingLifeline = lifeline;
		this.tick = t;
		this.start = s;
	}

	@Override
	public Line1D draw(DrawHandler drawHandler, PointDouble topLeft, PointDouble size) {
		PointDouble topLeftCoregion = new PointDouble(topLeft.x_loc + size.x_loc / 2.0 - getWidth() / 2.0, topLeft.y_loc + size.y_loc / 2.0 - COREGION_HEIGHT / 2.0);
		if (isStart()) {
			drawHandler.drawLine(topLeftCoregion.x_loc, topLeftCoregion.y_loc, topLeftCoregion.x_loc + getWidth(), topLeftCoregion.y_loc); // horizontal line
		}
		else {
			drawHandler.drawLine(topLeftCoregion.x_loc, topLeftCoregion.y_loc + COREGION_HEIGHT, topLeftCoregion.x_loc + getWidth(), topLeftCoregion.y_loc + COREGION_HEIGHT); // horizontal line
		}
		drawHandler.drawLine(topLeftCoregion.x_loc, topLeftCoregion.y_loc, topLeftCoregion.x_loc, topLeftCoregion.y_loc + COREGION_HEIGHT); // left vertical line
		drawHandler.drawLine(topLeftCoregion.x_loc + getWidth(), topLeftCoregion.y_loc, topLeftCoregion.x_loc + getWidth(), topLeftCoregion.y_loc + COREGION_HEIGHT); // right vertical line
		return null;
	}

	@Override
	public double getMinWidth(DrawHandler drawHandler) {
		return getWidth();
	}

	public double getWidth() {
		return correspondingLifeline.getLifelineRightPartWidth(tick) * 2 + COREGION_GAP_LIFELINE * 2;
	}

	@Override
	public double getAdditionalYHeight(DrawHandler drawHandler, PointDouble size) {
		return COREGION_HEIGHT - size.y_loc;
	}

	public boolean isStart() {
		return start;
	}
}
