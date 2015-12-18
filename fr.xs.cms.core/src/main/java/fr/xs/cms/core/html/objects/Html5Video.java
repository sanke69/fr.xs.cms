package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlEventListener;
import fr.xs.cms.core.html.properties.HtmlType;

public class Html5Video extends HtmlObject {

	public class Html5EventListener extends HtmlEventListener {
		
	}

	String  id, source, alt;
	int     width, height;
	boolean autoplay, loop, muted;
	
	public Html5Video() {
		super(HtmlType.VIDEO);
		source = "";
		alt    = "";
		width  = -1;
		height = -1;
	}

	public String    getId() { return id; }
	public Html5Video setId(String _id) { id = _id; return this; } 

	public String    getSource() { return source; }
	public Html5Video setSource(String _src) { source = _src; return this; } 

	public String    getAlternate() { return alt; }
	public Html5Video setAlternative(String _alt) { alt = _alt; return this; } 

	public int       getWidth() { return width; }
	public Html5Video setWidth(int _w) { width = _w; return this; }
	
	public int       getHeight() { return height; }
	public Html5Video setHeight(int _h) { height = _h; return this; } 

	public Html5Video setDimension(int _w, int _h) { width = _w; height = _h; return this; }
		
	public boolean   getAutoplay() { return autoplay; }
	public Html5Video setAutoplay(boolean _b) { autoplay = _b; return this; }
	
	public boolean   getLoop() { return loop; }
	public Html5Video setLoop(boolean _b) { loop = _b; return this; } 

	public boolean   getMuted() { return muted; }
	public Html5Video setMuted(boolean _b) { muted = _b; return this; } 

	@Override public String toHtml() { 
		return "<video "
				+ (id       != null  ? "id='" + id + "' " : "")
				+ (width  > 0 ? "width=" + width : "")
				+ (height > 0 ? "height=" + height : "")
				+ (autoplay != false ? "autoplay" : "")
				+ (loop     != false ? "loop" : "")
				+ (muted    != false ? "Nmuted" : "")
				+ "controls='controls'"
				+ "preload='auto'"
				+ "tabindex='0'"
				+ " />"
				+ "<source id='primary' src='" + source + "'>"
//				+ "<source src='/spwebsite/WEB-RES/rc/video/small.mp4' type='video/mp4' />"
//				+ "<source src='/spwebsite/WEB-RES/rc/video/small.webm' type='video/webm' />"
//				+ "<source src='/spwebsite/WEB-RES/rc/video/small.ogv' type='video/ogg' />"
//				+ "<source src='/spwebsite/WEB-RES/rc/video/small.3gp' type='video/3gp' />"
				+ alt
				+ "</video>";
	}

}
