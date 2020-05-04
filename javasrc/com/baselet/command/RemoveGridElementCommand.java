package com.baselet.command;

import java.util.List;

import com.baselet.element.interfaces.GridElement;

public class RemoveGridElementCommand extends Command {

	private CommandTarget target;
	private List<GridElement> elements;

	public RemoveGridElementCommand(CommandTarget tgt, List<GridElement> elts) {
		this.target = tgt;
		this.elements = elts;
	}

	@Override
	public void execute() {
		target.removeGridElements(elements);
	}

	@Override
	public void undo() {
		target.addGridElements(elements);
	}

}
