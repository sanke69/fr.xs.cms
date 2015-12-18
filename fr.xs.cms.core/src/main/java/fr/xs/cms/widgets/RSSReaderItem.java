package fr.xs.cms.widgets;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class RSSReaderItem extends HtmlObject {
	boolean last = false;
	String  portrait, username, timestamp;
	String  content;

	public RSSReaderItem(String _username, String _timestamp, String _content) {
		super(HtmlType.UNKNOWN);
		username = _username;
		timestamp = _timestamp;
		content = _content;
	}
	public RSSReaderItem(String _username, String _timestamp, String _content, boolean _last) {
		this(_username, _timestamp,_content);
		last = _last;
	}

	@Override
	public HtmlType Type() { return HtmlType.LI; }
	@Override
	public String toHtml() {
		return "<li" + (last ? " class='noline'" : " class='under-line'") + ">"
				+ "<div class='user'><img src='" + (portrait != null ? portrait : "images/common/avatar_default.svg") + "' alt='sp-web.fr' /></div>"
				+ "<div class='user-comments'>"
				+ "<h1>@" + username + "</h1>"
				+ "<h2>" + timestamp + "</h2>"
				+ content + "</div>"
				+ "</li>";
	}

}
