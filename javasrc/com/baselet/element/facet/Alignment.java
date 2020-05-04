package com.baselet.element.facet;

import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.AlignVertical;

public class Alignment {

	private final AlignHorizontal horizontalDefault;
	private final AlignVertical verticalDefault;

	private AlignHorizontal horizontal;
	private boolean horizontalGloballySet;
	private AlignVertical vertical;
	private boolean verticalGloballySet;

	public Alignment(Settings settings) {
		horizontalDefault = settings.getHAlign();
		verticalDefault = settings.getVAlign();
		horizontal = horizontalDefault;
		vertical = verticalDefault;
		horizontalGloballySet = false;
		verticalGloballySet = false;
	}

	public AlignHorizontal getHorizontal() {
		return horizontal;
	}

	public void setHorizontal(boolean setGlobal, AlignHorizontal h) {
		if (setGlobal) {
			horizontalGloballySet = true;
			this.horizontal = h;
		}
		else if (!horizontalGloballySet) {
			this.horizontal = h;
		}
	}

	public boolean isHorizontalGloballySet() {
		return horizontalGloballySet;
	}

	public AlignVertical getVertical() {
		return vertical;
	}

	public void setVertical(boolean setGlobal, AlignVertical v) {
		if (setGlobal) {
			verticalGloballySet = true;
			this.vertical = v;
		}
		else if (!verticalGloballySet) {
			this.vertical = v;
		}
	}

	public boolean isVerticalGloballySet() {
		return verticalGloballySet;
	}

	public void reset() {
		setHorizontal(false, horizontalDefault);
		setVertical(false, verticalDefault);
	}

}
