package com.baselet.element;

import java.util.ArrayList;
import java.util.List;

public class UndoHistory {

	private final List<UndoInformation> history = new ArrayList<UndoInformation>();
	private int currentIndex = -1;

	public void add(UndoInformation undoInformation) {
		while (history.size() > currentIndex + 1) {
			history.remove(history.size() - 1);
		}
		history.add(undoInformation);
		currentIndex++;
	}

	public UndoInformation remove() {
		UndoInformation undoInformation = history.remove(currentIndex);
		currentIndex = Math.min(currentIndex, history.size() - 1); // stay at current index except if it was pointing to the last element
		return undoInformation;
	}

	public UndoInformation get(boolean undo) {
		if (history.isEmpty()) {
			return null;
		}
		if (!undo) {
			currentIndex++;
		}
		UndoInformation undoInformation = history.get(currentIndex);
		if (undo) {
			currentIndex--;
		}
		return undoInformation;
	}

}
