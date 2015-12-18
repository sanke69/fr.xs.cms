package fr.xs.cms.core.html.properties;

public enum HtmlRequestReturnType {
	TEXT		(0x00000001, "text"),
	IMAGE_JPG	(0xFFFFFFFF, "image");

	private int    id;
	private String value;

	HtmlRequestReturnType(int _i, String _s) { id = _i; value = _s; }	
	
	public int id()       { return id; }
	public String value() { return value; }

}
