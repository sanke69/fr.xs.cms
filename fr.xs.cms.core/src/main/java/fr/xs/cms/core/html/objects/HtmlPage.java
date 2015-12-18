package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.interfaces.HtmlPageInterface;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlPage extends HtmlObject implements HtmlPageInterface {
	public static final String
		DocType_XHTML1_0_Strict =
				"<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">",
		DocType_HTML4 =
				"<!DOCTYPE html PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN' 'http://www.w3.org/TR/html4/loose.dtd'>",
		DocType_HTML5 =
				"<!DOCTYPE html>";

	protected String   format;
	protected HtmlHead head;
	protected HtmlBody body;

	public HtmlPage() {
		this(DocType_HTML5);
	}
	public HtmlPage(String _format) {
		super(HtmlType.HTML);
		format = _format;
		head = new HtmlHead();
		body = new HtmlBody();
		super.addChildren(head, body);
	}

	@Override
	public HtmlHead getHead() { return head; }
	public HtmlPage setHead(HtmlHead _head) { head = _head; return this; }
	@Override
	public HtmlBody getBody() { return body; }
	public HtmlPage setBody(HtmlBody _body) { body = _body; return this; }

	@Override public HtmlObject addChild(HtmlObject _obj) { body.addChild(_obj); return this; }
	@Override public HtmlObject addChildren(HtmlObject... _obj) { body.addChildren(_obj); return this; }

	@Override public String toHtml() {
		String page = super.toHtml();
		return format + page;
	}

}
