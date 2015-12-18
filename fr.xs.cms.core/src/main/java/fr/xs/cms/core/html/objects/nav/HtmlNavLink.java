package fr.xs.cms.core.html.objects.nav;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

@Deprecated
public class HtmlNavLink extends HtmlObject {
	boolean vertical;
	
	String module, action,label, args, href;

	public HtmlNavLink() {
		super(HtmlType.NAV);
	}

	@Override public String toHtml() { 
		String html = ""; 
		
	    html += vertical ? "<div class='submenu'>" : "<li>";
	    html += "<a href='index.php?module=" + module + "&amp;action=" + action + "'>" + label + "</a>";
	    html += vertical ? "</div>" : "</li>";

	    html += vertical ? "<div class='submenu'>" : "<li>";
	    html += "<a href='index.php?module=" + module + "&amp;action=" + action + "&amp;" + args + "'>" + label + "</a>";
	    html += vertical ? "</div>" : "</li>";

	    html += vertical ? "<div class='submenu'>" : "<li>";
	    html += "<a href='index.php?module=articles&amp;action=reader&amp;path=" + href + "'>" + label + "</a>";
	    html += vertical ? "</div>" : "</li>";
	    
	    html = "<li><a href='" + href + "' target='MainContent'>" + label + "</a></li>";
		
		return html;
	}

}
