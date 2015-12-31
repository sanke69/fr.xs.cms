package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlSourceFormat;
import fr.xs.cms.core.html.properties.HtmlType;

public class Html5Source extends HtmlObject {
	String url;
	HtmlSourceFormat format;

	public Html5Source(String _url, HtmlSourceFormat _format) {
		super(HtmlType.SOURCE);
		url    = _url;
		format = _format;
	}

	public String toHtml() {
		return "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (url != null ? " src='" + url + "'" : "")
					+ (format != null ? " type='" + format.toHtml() + "'" : "") + " />";
	}

}
