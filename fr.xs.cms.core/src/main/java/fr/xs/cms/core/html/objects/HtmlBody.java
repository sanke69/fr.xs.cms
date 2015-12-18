package fr.xs.cms.core.html.objects;

import java.util.List;
import java.util.Vector;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.properties.HtmlStyle;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlBody extends HtmlObject {
	Html5Header header;
	Html5Footer footer;
	protected List<HtmlScript> scripts;

	public HtmlBody() {
		super(HtmlType.BODY);
		scripts = new Vector<HtmlScript>();
	}

	public HtmlStyle getStyle() { if(style == null) style = new HtmlStyle(true); return style; }

	public HtmlBody addScript(HtmlScript _script) { scripts.add(_script); return this; }
	public HtmlBody addScripts(HtmlScript... _scripts) { for(HtmlScript s : _scripts) scripts.add(s); return this; }

	public String toHtml() {
		String html = "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (style != null ? style.toHtml() : "") + ">";
		if(header != null) html += header.toHtml();
		for(HtmlScript s : scripts)  if(s != null) html += s.toHtml();
		for(HtmlObject c : children) if(c != null) html += c.toHtml();
		if(footer != null) html += footer.toHtml();
		html += "</" + Type().value() + ">";
       	return html;
	}

	public Html5Header getHeader() {
		if(header == null)
			header = new Html5Header();
		return header;
	}
	public Html5Footer getFooter() {
		if(footer == null)
			footer = new Html5Footer();
		return footer;
	}
	
	
	
}
