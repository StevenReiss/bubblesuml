package com.baselet.command;

import java.util.List;

import com.baselet.element.interfaces.GridElement;

public class AddGridElementCommand extends Command {

	private CommandTarget target;
	private List<GridElement> elements;

	public AddGridElementCommand(CommandTarget tgt, List<GridElement> elts) {
		this.target = tgt;
		this.elements = elts;
	}

	@Override
	public void execute() {
		target.addGridElements(elements);
	}

	@Override
	public void undo() {
		target.removeGridElements(elements);
	}

}
