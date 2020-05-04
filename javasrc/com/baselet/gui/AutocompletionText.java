package com.baselet.gui;

public class AutocompletionText {
	private String text;
	private final String info;
	private String base64Img;

	public AutocompletionText(String t, String i) {
		super();
		this.text = t;
		this.info = i;
	}

	public AutocompletionText(String t, String i, String b64) {
		super();
		this.text = t;
		this.info = i;
		this.base64Img = b64;
	}

	public String getText() {
		return text;
	}

	public void setText(String t) {
		this.text = t;
	}

	public String getInfo() {
		return info;
	}

	public String getHtmlInfo() {
		String baseText = getText() + " <span style='font-style:italic;color:gray'>" + getInfo() + "</span>";
		if (base64Img != null) {
			baseText += " <img src='data:image/gif;base64," + base64Img + "'>";
		}
		return baseText;
	}

}
