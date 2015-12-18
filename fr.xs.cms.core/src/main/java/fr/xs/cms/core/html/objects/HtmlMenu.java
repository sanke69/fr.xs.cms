package fr.xs.cms.core.html.objects;

import java.util.LinkedHashMap;

import fr.xs.cms.core.html.HtmlObject;

public class HtmlMenu extends HtmlDiv {
	LinkedHashMap<String, String> entries;
	HtmlList asAnchorList;

	public HtmlMenu() {
		super();

		entries = new LinkedHashMap<String, String>();
	}
	
	public HtmlMenu addEntry(String _url, String _label) {
		entries.put(_url, _label);
		return this;
	}

	@Override
	public String toHtml() {
		if(asAnchorList == null) {
			asAnchorList = new HtmlList();
			for(String link : entries.keySet()) {
//				HtmlListItem item = new HtmlListItem();
				HtmlAnchor   anch = new HtmlAnchor();
				
				anch.setHRef(link);
			}
		}

		String toHtmlBalisa = "<ul>";
		for(String link : entries.keySet())
			toHtmlBalisa += "<li><a href='" + link + "'>" + entries.get(link) + "</a></li>";
		toHtmlBalisa += "</ul>";

		setInnerContent(toHtmlBalisa);

		String html = "<div" + (id != null ? " id='" + id + "' " : " ") + (cssClass != null ? " class='" + cssClass + "' " : " ") + (style != null ? style.toHtml() : "") + ">";
		html += (getInnerContent() != null ? getInnerContent() : "");
		for(HtmlObject c : children) html += c.toHtml();
			html += "</div>";
		return html;
	}

}
