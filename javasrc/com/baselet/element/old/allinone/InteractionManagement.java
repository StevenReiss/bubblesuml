package com.baselet.element.old.allinone;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;



// Contains all interactions entered by the user and
// offers various comfort-functions for finding and
// working with interactions
class InteractionManagement {
	private final Set<Interaction>[] level;

	// private Set[] level;

	@SuppressWarnings("unchecked")
      InteractionManagement(int numLevels) {
		level = new Set[numLevels];
		for (int i = 0; i < numLevels; i++) {
			level[i] = new HashSet<Interaction>();
		}
	}

	public boolean controlBoxExists(int levelNum, int objNum) {
		Iterator<Interaction> it = level[levelNum - 1].iterator();
		while (it.hasNext()) {
			Interaction ia = it.next();
			if (ia.hasControl(objNum)) {
				return true;
			}
		}
		return false;
	}

	public void add(int numLevel, Interaction i) {
		level[numLevel - 1].add(i);
	}

	public Set<Interaction> getInteractionsInLevel(int levelNum) {
		return level[levelNum - 1];
	}								

	public int getNumLevels() {
		return level.length;
	}
}


