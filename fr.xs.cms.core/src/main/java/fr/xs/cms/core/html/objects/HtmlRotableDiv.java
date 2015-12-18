package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.jtk.helpers.MediaHelper;

public class HtmlRotableDiv extends HtmlDiv {

	String script = null;
	
	public HtmlRotableDiv(String _id) {
		super();
		setId(_id);
		getStyle()	. setPerspective(300)
					. setWebkitPerspective(300);
	}
	
	@Override
	public String toHtml() {
		if(script == null)
			script = MediaHelper.getContentAsString(HtmlRotableDiv.class, "/javascript/rotator/DeviceOrientation.js").replaceAll("__DIV_ID__", "\""+ getId() + "\"");

		String html = "<div" + (id != null ? " id='" + id + "'" : "") + (cssClass != null ? " class='" + cssClass + "'" : "") + (style != null ? style.toHtml() : "") + ">";
		html += "<center>";
		for(HtmlObject c : children) html += c.toHtml();
		html += (innerHtml != null ? innerHtml : "");
		html += "</center>";
		html += script != null ? "<script>" + script + "</script>": "";
		html += "</div>";
		return html;
	}

}
