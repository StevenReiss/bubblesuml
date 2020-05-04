package com.baselet.element.old.allinone;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.font.TextLayout;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.sourceforge.jlibeps.epsgraphics.EpsGraphics2D;

import com.baselet.control.HandlerElementMap;
import com.baselet.control.enums.AlignHorizontal;
import com.baselet.control.enums.Direction;
import com.baselet.control.enums.FormatLabels;
import com.baselet.control.enums.LineType;
import com.baselet.control.util.Utils;
import com.baselet.diagram.FontHandler;
import com.baselet.element.interfaces.GridElementDeprecatedAddons;
import com.baselet.element.old.OldGridElement;


// An interaction represents a synchronous/asynchronous message
// that is sent between two objects.
class Interaction {

	private final int srcObj;
	private final boolean srcObjHasControl;
	private final int arrowKind; // 1=SYNC, 2= ASYNC, 3=EDGE, 4=FILLED
	private final int lineKind; // 1=SOLID, 2=DOTTED
	private final int destObj;
	private final boolean destObjHasControl;
	private final String methodName;

	public Interaction(int so, boolean cfg, int ak, int lk,
			int dov, boolean dfg, String mnm) {
		this.srcObj = so;
		this.srcObjHasControl = cfg;
		this.arrowKind = ak;
		this.lineKind = lk;
		this.destObj = dov;
		this.destObjHasControl = dfg;
		this.methodName = mnm;
	}

	public boolean hasControl(int objNum) {
		return srcObjHasControl && srcObj == objNum ||
				destObjHasControl && destObj == objNum;
	}

	@Override
	public boolean equals(Object o) {
		if (!(o instanceof Interaction)) {
			return false;
		}
		Interaction i = (Interaction) o;

		return srcObj == i.srcObj &&srcObjHasControl == i.srcObjHasControl &&
				arrowKind == i.arrowKind && destObj == i.destObj &&
				destObjHasControl == i.destObjHasControl &&
				methodName == null || methodName.equals(i.methodName);
	}

	@Override
	public int hashCode() {
		return (methodName != null ? methodName.hashCode() : 1) +srcObj +
				(srcObjHasControl ? 1 : 0) + arrowKind + destObj + (destObjHasControl ? 1 : 0);
	}

	public int getSrcObj() {
		return srcObj;
	}

	public boolean getSrcObjHasControl() {
		return srcObjHasControl;
	}

	public int getArrowKind() {
		return arrowKind;
	}

	public int getLineKind() {
		return lineKind;
	}

	public int getDestObj() {
		return destObj;
	}

	public boolean getDestObjHasControl() {
		return destObjHasControl;
	}

	public String getMethodName() {
		return methodName;
	}
}


