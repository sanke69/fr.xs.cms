package fr.xs.cms.core.html.objects;

import java.util.Map;
import java.util.Map.Entry;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

/** HTML5 Object **/
public class HtmlNav extends HtmlObject {
	protected String title;
	protected Map<String, HtmlObject> children;

	@Override public HtmlType Type() { return HtmlType.NAV; }
	@Override public String toHtml() {
		String html = "";
		html += "<nav id='" + id + "'>";
	    html += "<h1>" + title + "</h1>";
	    html += "<ul>";
	    
		for(Entry<String, HtmlObject> c : children.entrySet()) html += c.getValue().toHtml();

		html += "</ul></nav>";
		return html;
	}
	

	public HtmlNav(String _id, String _title) {
		super(HtmlType.NAV);
	}

}
