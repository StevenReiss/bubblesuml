package com.baselet.element.facet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import com.baselet.control.enums.FormatLabels;
import com.baselet.diagram.draw.helper.StyleException;
import com.baselet.gui.AutocompletionText;

public abstract class KeyValueFacet extends Facet {

	public static class KeyValue {
		private final String key;
		private final boolean allValuesListed;
		private final List<ValueInfo> valueInfos;
    
		public KeyValue(String k, boolean avl, String value, String info) {
			super();
			this.key = k.toLowerCase(Locale.ENGLISH);
			this.allValuesListed = avl;
			valueInfos = Arrays.asList(new ValueInfo(value, info));
		}
    
		public KeyValue(String k, List<ValueInfo> vi) {
			super();
			this.key = k;
			allValuesListed = true;
			this.valueInfos = vi;
		}
    
		public KeyValue(String k, ValueInfo... vi) {
			this(k, Arrays.asList(vi));
		}
    
		public String getKey() {
			return key;
		}
    
		public List<ValueInfo> getValueInfos() {
			return valueInfos;
		}
    
		public String getValueString() {
			StringBuilder sb = new StringBuilder();
			if (allValuesListed) {
				sb.append("Valid are: ");
				for (ValueInfo vi : valueInfos) {
					sb.append(vi.value.toString().toLowerCase(Locale.ENGLISH)).append(',');
				}
				sb.deleteCharAt(sb.length() - 1);
			}
			else {
				for (ValueInfo vi : valueInfos) {
					sb.append(vi.info);
				}
			}
			return sb.toString();
		}
	}

	public static class ValueInfo {
		private final Object value;
		private final String info;
		private final String base64Img;
    
		public ValueInfo(Object f, String i) {
			this(f, i, null);
		}
    
		public ValueInfo(Object v, String i, String img) {
			super();
			this.value = v;
			this.info = i;
			this.base64Img = img;
		}
    
		public Object getValue() {
			return value;
		}
    
		private String getInfo() {
			return info;
		}
    
		private String getBase64Img() {
			return base64Img;
		}
    
	}

	public static final String SEP = "=";

	public abstract KeyValue getKeyValue();

	public abstract void handleValue(String value, PropertiesParserState state);

	@Override
	public boolean checkStart(String line, PropertiesParserState state) {
		return line.startsWith(getKeyWithSep());
	}

	@Override
	public void handleLine(String line, PropertiesParserState state) {
		String value = extractValue(line);
		try {
			handleValue(value, state);
		} catch (Exception e) {
			String errorMessage = getKeyValue().getValueString();
			if (e instanceof StyleException) { // self defined exceptions overwrite the default message
				errorMessage = e.getMessage();
			}
			throw new RuntimeException(FormatLabels.BOLD.getValue() + "Invalid value:" + FormatLabels.BOLD.getValue() + "\n" + getKeyWithSep() + value + "\n" + errorMessage);
		}
	}

	protected String extractValue(String line) {
		return line.substring(getKeyWithSep().length());
	}

	@Override
	public List<AutocompletionText> getAutocompletionStrings() {
		List<AutocompletionText> returnList = new ArrayList<AutocompletionText>();
		for (ValueInfo valueInfo : getKeyValue().getValueInfos()) {
			returnList.add(new AutocompletionText(getKeyWithSep() + valueInfo.getValue().toString().toLowerCase(Locale.ENGLISH), valueInfo.getInfo(), valueInfo.getBase64Img()));
		}
		return returnList;
	}

	public String getKeyWithSep() {
		return getKeyValue().getKey() + KeyValueFacet.SEP;
	}
}
