package fr.xs.cms.core.html.scripts;

public class HtmlCallBack extends HtmlJavascript {

	String name;

	public HtmlCallBack() {
		;
	}

	@Override public String getName() {
		return name;
	}
	public HtmlCallBack setName(String _name) {
		name = _name; return this;
	}
	
	@Override public String getCode() {
		return "";
	}

}
