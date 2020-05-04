package com.baselet.gui.command;

import java.util.Collection;
import java.util.Vector;

import com.baselet.control.HandlerElementMap;
import com.baselet.control.basics.geom.Point;
import com.baselet.control.basics.geom.PointDouble;
import com.baselet.control.basics.geom.Rectangle;
import com.baselet.control.config.SharedConfig;
import com.baselet.diagram.CurrentDiagram;
import com.baselet.diagram.DiagramHandler;
import com.baselet.element.interfaces.GridElement;
import com.baselet.element.old.element.Relation;
import com.baselet.element.sticking.StickingPolygon;

/**
 * resizing has been merged with Move command and only remains for old grid elements which will not be migrated but removed from the code after some time
 */
@Deprecated
public class OldResize extends Command {
	private int current_id = 0;

	private int id;
	private final int diffx, diffy, diffw, diffh;
	private Vector<OldRelationLinePoint> linepoints;
	private final Vector<OldMoveLinePoint> move_commands;
	private final GridElement entity;

	private int getDiffx() {
		return diffx * HandlerElementMap.getHandlerForElement(entity).getGridSize();
	}

	private int getDiffy() {
		return diffy * HandlerElementMap.getHandlerForElement(entity).getGridSize();
	}

	private int getDiffw() {
		return diffw * HandlerElementMap.getHandlerForElement(entity).getGridSize();
	}

	private int getDiffh() {
		return diffh * HandlerElementMap.getHandlerForElement(entity).getGridSize();
	}

	public OldResize(GridElement e, int dx, int dy, int dw, int dh) {
		this(e, dx, dy, dw, dh, null);
	}

	// resize for merge
	private OldResize(GridElement e, int idv, int dx, int dy, int dw, int dh,
			Vector<OldMoveLinePoint> mc, Vector<OldMoveLinePoint> move_commands2) {
		this.entity = e;
		this.id = idv;
		this.move_commands = mc;
		this.move_commands.addAll(move_commands2);
		this.diffx = dx / HandlerElementMap.getHandlerForElement(entity).getGridSize();
		this.diffy = dy / HandlerElementMap.getHandlerForElement(entity).getGridSize();
		this.diffw = dw / HandlerElementMap.getHandlerForElement(entity).getGridSize();
		this.diffh = dh / HandlerElementMap.getHandlerForElement(entity).getGridSize();
	}

	public OldResize(GridElement e, int dx, int dy, int dw, int dh, OldResize first) {
		this.entity = e;
		move_commands = new Vector<OldMoveLinePoint>();
		this.diffx = dx / HandlerElementMap.getHandlerForElement(entity).getGridSize();
		this.diffy = dy / HandlerElementMap.getHandlerForElement(entity).getGridSize();
		this.diffw = (dw - diffx) / HandlerElementMap.getHandlerForElement(entity).getGridSize();
		this.diffh = (dh - diffy) / HandlerElementMap.getHandlerForElement(entity).getGridSize();

		Rectangle entityRect = this.entity.getRectangle();
		StickingPolygon from = this.entity.generateStickingBorder(entityRect);

		// AB: FIXED: Use this.diffw/this.diffh instead of diffw/diffh as calculation base
		Rectangle newRect = new Rectangle(entityRect.x + diffx, entityRect.y + diffy, entityRect.width + getDiffw(), entityRect.height + getDiffh());
		StickingPolygon to = this.entity.generateStickingBorder(newRect);

		if (first != null) {
			id = first.id;
			linepoints = first.linepoints;
		}
		else {
			id = current_id;
			current_id++;
			linepoints = getStickingRelationLinePoints(HandlerElementMap.getHandlerForElement(this.entity), from);
		}

		PointDouble diff;
		Point p;
		Relation r;
		for (OldRelationLinePoint lp : linepoints) {
			r = lp.getRelation();
			p = r.getLinePoints().get(lp.getLinePointId());

			diff = from.getLine(lp.getStickingLineId()).diffToLine(to.getLine(lp.getStickingLineId()), p.x_loc + r.getRectangle().x, p.y_loc + r.getRectangle().y);

			DiagramHandler handler = HandlerElementMap.getHandlerForElement(entity);
			move_commands.add(new OldMoveLinePoint(lp.getRelation(), lp.getLinePointId(), handler.realignToGrid(diff.x_loc), handler.realignToGrid(diff.y_loc)));
		}

	}

	@Override
	public void execute(DiagramHandler handler) {
		super.execute(handler);

		entity.setLocationDifference(getDiffx(), getDiffy());
		entity.setSize(entity.getRectangle().width + getDiffw(), entity.getRectangle().height + getDiffh());
		if (SharedConfig.getInstance().isStickingEnabled()) {
			for (OldMoveLinePoint c : move_commands) {
				c.execute(handler);
			}
		}
	}

	@Override
	public void undo(DiagramHandler handler) {
		super.undo(handler);
		entity.setLocationDifference(-getDiffx(), -getDiffy());
		entity.setSize(entity.getRectangle().width + -getDiffw(), entity.getRectangle().height + -getDiffh());
		for (OldMoveLinePoint c : move_commands) {
			c.undo(handler);
		}
		CurrentDiagram.getInstance().getDiagramHandler().getDrawPanel().updatePanelAndScrollbars();
	}

	@Override
	public boolean isMergeableTo(Command c) {
		if (!(c instanceof OldResize)) {
			return false;
		}
		OldResize r = (OldResize) c;
		if (id == r.id) {
			return true;
		}
		return false;
	}

	@Override
	public Command mergeTo(Command c) {
		OldResize tmp = (OldResize) c;
		return new OldResize(entity, Math.max(id, tmp.id), getDiffx() + tmp.getDiffx(), getDiffy() + tmp.getDiffy(),
				getDiffw() + tmp.getDiffw(), getDiffh() + tmp.getDiffh(), move_commands, tmp.move_commands);
	}

	public static Vector<OldRelationLinePoint> getStickingRelationLinePoints(DiagramHandler handler, StickingPolygon stickingPolygon) {
		Vector<OldRelationLinePoint> lpts = new Vector<OldRelationLinePoint>();
		Collection<Relation> rels = handler.getDrawPanel().getOldRelations();
		for (Relation r : rels) {
			PointDouble l1 = r.getAbsoluteCoorStart();
			PointDouble l2 = r.getAbsoluteCoorEnd();
			int c1 = stickingPolygon.isConnected(l1, handler.getGridSize());
			int c2 = stickingPolygon.isConnected(l2, handler.getGridSize());
			if (c1 >= 0) {
				lpts.add(new OldRelationLinePoint(r, 0, c1));
			}
			if (c2 >= 0) {
				lpts.add(new OldRelationLinePoint(r, r.getLinePoints().size() - 1, c2));
			}
		}
		return lpts;
	}
}
