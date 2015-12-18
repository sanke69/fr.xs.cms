package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlDiv extends HtmlObject {
	String innerHtml;
	String align = null;
	
	public HtmlDiv() {
		super(HtmlType.DIV);
	}

	public String  getAlign() { return align; }
	public HtmlDiv setAlign(String _align) { align = _align; return this; }
	
	public String  getInnerContent() { return innerHtml; }
	public HtmlDiv setInnerContent(String _content) { innerHtml = _content; return this; }

	@Override public HtmlType Type() { return HtmlType.DIV; }
	@Override public String toHtml() {
		String html = "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (align != null ? " align='" + align + "'" : "")
					+ (style != null ? style.toHtml() : "") + ">";
		html += (innerHtml != null ? innerHtml : "");
		for(HtmlObject c : children) html += c.toHtml();
		html += "</" + Type().value() + ">";
		return html;
	}

}
