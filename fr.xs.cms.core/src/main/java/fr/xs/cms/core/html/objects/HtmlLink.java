package fr.xs.cms.core.html.objects;

import java.util.Date;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlRequestMethod;
import fr.xs.cms.core.html.properties.HtmlType;
import fr.xs.cms.core.html.scripts.HtmlJavascript;
import fr.xs.cms.core.html.scripts.HtmlRequest;

public class HtmlLink extends HtmlObject {
	HtmlRequest	request;
	
	public HtmlLink() {
		super(HtmlType.A);
		request = new HtmlRequest();
	}

	@Override public HtmlType Type() { return HtmlType.A; }
	@Override public String toHtml() {
		String html = "";
		if(request.getMethod() == HtmlRequestMethod.POST || request.getAsync()) {
			request. setName(id + new Date().getTime());

			request.setEnlightment(HtmlJavascript.CodeEnlightment.asHtmlScriptFunction);
			html   += request.toHtml();
			html   += "<a " 
							+ (id != null ? "id='" + id + "' " : "") 
							+ "href='javascript: " + request.getName() + "();' "
							+ "target='" + request.getURL() + "' " 
							+ (style != null ? style.toHtml() : "") + ">";
			for(HtmlObject c : children) html += c.toHtml();
			html   += "</a>";
		} else {
			html   += "<a " 
							+ (id != null ? "id='" + id + "' " : "") 
							+ "href='" + request.getURL() + "' "  
							+ "target='" + request.getURL() + "' " 
							+ (style != null ? style.toHtml() : "") + ">";
			for(HtmlObject c : children) html += c.toHtml();
			html   += "</a>";
		}
		return html;
	}


	public HtmlLink setConnection(String _url, boolean _post, boolean _async) {
//		request.setConnection(_url, _post, _async);
		return this;
	}

	public HtmlLink setDestination(String _destination) {
		request.setTarget(_destination);
		return this;
	}

}
