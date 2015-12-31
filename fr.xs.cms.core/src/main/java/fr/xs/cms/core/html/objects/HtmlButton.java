package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlEventListener;
import fr.xs.cms.core.html.properties.HtmlRequestMethod;
import fr.xs.cms.core.html.properties.HtmlType;
import fr.xs.cms.core.html.scripts.HtmlJavascript;
import fr.xs.cms.core.html.scripts.HtmlRequest;

public class HtmlButton extends HtmlObject {

	public enum ButtonType {
		UNDEF(0, ""), BUTTON(1, "button"), RESET(2, "reset"), SUBMIT(3, "submit");
		
		int id;
		String value;
		
		private ButtonType(int _id, String _value) {
			id = _id;
			value = _value;
		}
	} 

	String      label;
	ButtonType  type;

	HtmlRequest	request;

	public HtmlButton() {
		super(HtmlType.BUTTON);
		label   = null;
		
		type    = ButtonType.UNDEF;

		request = new HtmlRequest();
		request.setMethod(HtmlRequestMethod.UNDEF);
	}
	public HtmlButton(String _label) {
		super(HtmlType.BUTTON);
		label   = _label;

		type    = ButtonType.UNDEF;

		request = null;
	}

	public HtmlButton setRequest(HtmlRequest _request) {
		request = _request;
		request.setName(id + (int) (1e9 * Math.random()));
		request.setEnlightment(HtmlJavascript.CodeEnlightment.asHtmlScriptFunction);

		return this;
	}

	@Override public HtmlType Type() { return HtmlType.BUTTON; }

	@Override public String toHtml() {
		switch(request.getMode()) {
		case CSS        :
		case HTML       : return setAsHtmlButton();
		case JAVASCRIPT :
		case AJAX       : return setAsAjaxButton();
		case UNDEF      :
		default         : return "";
		}
		
	}

	private String setAsHtmlButton() {
		String html = "<form";
		switch(request.getMethod()) {
		case GET   : html += " method='get'"; break;
		case POST  : html += " method='post'"; break;
		case UNDEF : 
		default    : break;
		}
		html += " action='" + request.getURL() + "'";
		if(request.getTarget() != null)
			html += " target='" + request.getTarget() + "'";
		html += " >";

		html += "<button type='submit'" + (style != null ? " " + style.toHtml() : "") + ">" + label + "</button>";

		html += "</form>";
		return html;
	}
	private String setAsAjaxButton() {
		String html = "";

		html   += request.toHtml();

		html   += "<button " 
						+ (id != null ? "id='" + id + "' " : "") 
						+ (event != null ? event.toHtmlExcept(HtmlEventListener.Type.Click) : "") 
						+ "onClick='" + request.getName() + "();' "
						+ (style != null ? style.toHtml() : "") + ">"
						+ (name != null ? "name='" + name + "' " : "") ;
		html += label;
		for(HtmlObject c : children) html += c.toHtml();
		html   += "</button>";

		return html;
	}

}
