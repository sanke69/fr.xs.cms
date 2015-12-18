package fr.xs.cms.core.html.properties;

public enum HtmlScriptType {
	JAVASCRIPT(0x00000001, "javascript"),
	UNKNOWN(0xFFFFFFFF, "UNKNOW");

	private int    id;
	private String value;

	HtmlScriptType(int _i, String _s) { id = _i; value = _s; }	
	
	public int id()       { return id; }
	public String value() { return value; }

}
