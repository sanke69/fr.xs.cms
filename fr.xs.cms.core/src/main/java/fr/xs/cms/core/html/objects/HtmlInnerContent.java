package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlInnerContent extends HtmlObject {
	String 	value;

	public HtmlInnerContent() {
		super(HtmlType.UNKNOWN);
	}
	public HtmlInnerContent(String _content) {
		super(HtmlType.UNKNOWN);
		value = _content;
	}
	
	public HtmlInnerContent setValue(String _content) {
		value = _content;
		return this;
	}

	@Override public String toHtml() {
		return value;
	}

}
