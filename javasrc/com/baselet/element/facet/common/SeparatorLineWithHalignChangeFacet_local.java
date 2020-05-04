package com.baselet.element.facet.common;

import com.baselet.control.enums.AlignHorizontal;
import com.baselet.element.facet.PropertiesParserState;

public class SeparatorLineWithHalignChangeFacet_local extends SeparatorLineFacet {

	public static final SeparatorLineWithHalignChangeFacet_local CHANGE_INSTANCE = new SeparatorLineWithHalignChangeFacet_local();

	private SeparatorLineWithHalignChangeFacet_local() {}

	@Override
	public void handleLine(String line, PropertiesParserState state) {
		state.getAlignment().setHorizontal(false, AlignHorizontal.LEFT);
		super.handleLine(line, state);
	}

}
