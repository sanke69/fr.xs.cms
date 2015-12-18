package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlImage extends HtmlObject {
	String source, alt;
	int    width, height;
	
	public HtmlImage() {
		super(HtmlType.IMG);
		source = "";
		alt    = "";
		width  = -1;
		height = -1;
	}
	public HtmlImage(String _src) {
		super(HtmlType.IMG);
		source = _src;
		alt    = "";
		width  = -1;
		height = -1;
	}
	
	
	public String    getSource() { return source; }
	public HtmlImage setSource(String _src) { source = _src; return this; } 

	public String    getAlternate() { return alt; }
	public HtmlImage setAlternative(String _alt) { alt = _alt; return this; } 

	public int       getWidth() { return width; }
	public HtmlImage setWidth(int _w) { width = _w; return this; }
	
	public int       getHeight() { return height; }
	public HtmlImage setHeight(int _h) { height = _h; return this; } 

	public HtmlImage setDimension(int _w, int _h) { width = _w; height = _h; return this; }

	@Override public String toHtml() {
		return "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (source != null ? " src='" + source + "' " : "")
					+ (alt    != null ? " alt='" + alt + "' " : "")
					+ (width  > 0 ? " width=" + width : "")
					+ (height > 0 ? " height=" + height : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (style != null ? style.toHtml() : "")
					+ " />";
	}

}
