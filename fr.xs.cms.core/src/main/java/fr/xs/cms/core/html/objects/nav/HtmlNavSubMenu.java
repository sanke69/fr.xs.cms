package fr.xs.cms.core.html.objects.nav;

import java.util.Map;
import java.util.Map.Entry;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

@Deprecated
public class HtmlNavSubMenu extends HtmlObject {
	String href;
	String label;
	boolean vertical;
	protected Map<String, HtmlObject> children;

	public HtmlNavSubMenu() {
		super(HtmlType.NAV);
	}

	@Override public String toHtml() { 		
		String html = "";
		html += "<li";
	    html += " onmouseover = 'toggle(" + name + ",true);'";
	    html += " onmouseout  = 'toggle(" + name + ",false);'";
	    html += " onclick     = 'toggle(" + name + ");'";

		if( href == null ){
		    html += "><a href=#>" + label + "</a>";
		} else {
		    html += "><a href='" + href + "'>" + label + "</a>";
		}
		html += vertical ? "<ul id='" + name + "'>" : "<ul class='submenuClosed' id='" + id + "'>";

		for(Entry<String, HtmlObject> c : children.entrySet()) html += c.getValue().toHtml();

		html += "</ul></li>";
		return html;
	}

}
