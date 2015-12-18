package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlAnchor extends HtmlObject {
	String label, href;

	public HtmlAnchor() { super(HtmlType.A); label = null; href = null; }
	
	public HtmlAnchor setLabel(String _label) {
		label = _label;
		return this;
	}
	public HtmlAnchor setHRef(String _url) {
		href = _url;
		return this;
	}

	@Override public String toHtml() {
		String html = "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (href != null ? " href='" + href + "'" : "")
					+ (style != null ? style.toHtml() : "") + ">";
		if(label != null) html += label;
		for(HtmlObject c : children) if(c != null) html += c.toHtml();
		html += "</" + Type().value() + ">";
       	return html;
	}

}
