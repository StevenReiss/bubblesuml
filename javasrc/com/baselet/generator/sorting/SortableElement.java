package com.baselet.generator.sorting;

import com.baselet.element.interfaces.GridElement;
import com.baselet.generator.java.JavaClass;

public class SortableElement implements Comparable<SortableElement> {

	private final GridElement element;
	private JavaClass parsedClass;
	private final String name;

	public SortableElement(GridElement elt, String n) {
		this.element = elt;
		this.name = n;
	}

	public SortableElement(GridElement elt, JavaClass pcls) {
		this(elt, pcls.getPackage());
		this.parsedClass = pcls;
	}

	public GridElement getElement() {
		return element;
	}

	public JavaClass getParsedClass() {
		return parsedClass;
	}

	public String getName() {
		return name;
	}

	@Override
	public int compareTo(SortableElement o) {
		return name.compareTo(o.name);
	}
}
