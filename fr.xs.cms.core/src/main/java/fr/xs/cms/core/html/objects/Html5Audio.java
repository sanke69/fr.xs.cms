package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class Html5Audio extends HtmlObject {
/*
	public class Html5EventListener extends HtmlEventListener {
		
	}
*/
	String  id, source, alt;
	boolean autoplay, loop, muted;
	
	public Html5Audio() {
		super(HtmlType.AUDIO);
		source = "";
		alt    = "";
	}

	public String    getId() { return id; }
	public Html5Audio setId(String _id) { id = _id; return this; } 

	public String    getSource() { return source; }
	public Html5Audio setSource(String _src) { source = _src; return this; } 

	public String    getAlternate() { return alt; }
	public Html5Audio setAlternative(String _alt) { alt = _alt; return this; } 

	public boolean   getAutoplay() { return autoplay; }
	public Html5Audio setAutoplay(boolean _b) { autoplay = _b; return this; }
	
	public boolean   getLoop() { return loop; }
	public Html5Audio setLoop(boolean _b) { loop = _b; return this; } 

	public boolean   getMuted() { return muted; }
	public Html5Audio setMuted(boolean _b) { muted = _b; return this; } 
	
	@Override public HtmlType Type() { return HtmlType.AUDIO; }

	@Override public String toHtml() { 
		return "<audio"
				+ (id       != null  ? " id='" + id + "' " : "")
				+ (autoplay != false ? " autoplay" : "")
				+ (loop     != false ? " loop" : "")
				+ (muted    != false ? " Nmuted" : "")
				+ " controls=''"
				+ " preload='auto'"
				+ " tabindex='0'"
//				+ " onended='function() { alert(\"Tada\"); }'"
				+ " onended='alert(\"Tada\")'"
				+ ">"
				+ "<source id='primary' src='" + source + "'>"
				+ alt
				+ "</audio>";
	}

}
