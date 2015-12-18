package fr.xs.cms.core.html.objects;

import java.util.List;
import java.util.Vector;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlHead extends HtmlObject {
	protected String 		title;
	protected String 		author;
	protected String 		favicon, icon;
	protected String 		copyright;
	protected String 		charset;
	protected String 		description;
	protected String 		keywords;
	protected String 		content_type;
	protected String 		language;
	protected String 		robot_order;
	protected boolean 		autorefresh;
	protected long 			autorefresh_ms;
	protected String 		autorefresh_target;
	protected String        css_listing;
	protected List<String>  css_import;
	protected List<String>  js_import;
	
	public HtmlHead() {
		super(HtmlType.HEAD);
		css_import = new Vector<String>();
		js_import  = new Vector<String>();
	}

	public HtmlHead setAuthor(String _author) 						{ author = _author; return this; }
	public HtmlHead setTitle(String _title) 						{ title = _title; return this; }
	public HtmlHead setFavicon(String _favicon) 					{ favicon = _favicon; return this; }
	public HtmlHead setIcon(String _icon) 							{ icon = _icon; return this; }
	public HtmlHead setCopyright(String _copyright)	 				{ copyright = _copyright; return this; }
	public HtmlHead setCharset(String _charset)	 					{ charset = _charset; return this; }
	public HtmlHead setDescription(String _description) 			{ description = _description; return this; }
	public HtmlHead setKeywords(String _keywords) 					{ keywords = _keywords; return this; }
	public HtmlHead setContentType(String _content_type)	 		{ content_type = _content_type; return this; }
	public HtmlHead setLanguage(String _language) 					{ language = _language; return this; }
	public HtmlHead setRobotDefaultBehavior(String _order)			{ robot_order = _order; return this; }
	public HtmlHead setAutoRefresh(boolean _true, long _period_ms)	{ autorefresh = _true; autorefresh_ms = _period_ms; return this; }
	public HtmlHead setAutoRefreshTarget(String _autorefresh_target){ autorefresh_target = _autorefresh_target; return this; }

	public HtmlHead includeCss(String _css_content)	{ if(css_listing == null) css_listing = ""; css_listing += _css_content; return this; }
	public HtmlHead addCssInclude(String _css_path)	{ css_import.add(_css_path); return this; }
	public HtmlHead addCssIncludes(String... _css_pathes)	{ for(String css : _css_pathes) css_import.add(css); return this; }
	public HtmlHead addJsInclude(String _js_path)	{ js_import.add(_js_path); return this; }
	public HtmlHead addJsIncludes(String... _js_pathes)	{ for(String js : _js_pathes) js_import.add(js); return this; }
	

	@Override public HtmlType Type() { return null; }

	@Override public String toHtml() {
		String html = "<head>"
				+ (charset != null ? "<meta charset='" + charset + "' />" : "")

				+ "<meta http-equiv='X-UA-Compatible' content='IE=edge'>"
			    + "<meta name='viewport' content='width=device-width, initial-scale=1'>"

				+ (author       != null ? "<meta name='Author' content='" + author + "' />" : "")
				+ (copyright    != null ? "<meta name='Copyright' content='" + copyright + "' />" : "")
				+ (description  != null ? "<meta name='Description' content='" + description + "' />" : "")
				+ (keywords     != null ? "<meta name='Keywords' content='" + keywords + "' />" : "")
				+ (robot_order  != null ? "<meta name='Robots' content='" + robot_order + "' />" : "")
				+ (content_type != null ? "<meta http-equiv='Content-type' content='text/html; charset=UTF-8' />" : "")
				+ (language     != null ? "<meta http-equiv='Content-Language' content='fr' />" + "<meta name='language' content='Fr' />" : "")
				+ (autorefresh          ? "<meta http-equiv='Refresh' content='"+ autorefresh_ms + "; url=" + autorefresh_target + "'>" : "")
				+ (icon         != null ? "<link rel='Icon' type='image/png' href='" + icon + "' />" : "")
				+ (favicon      != null ? "<link rel='shortcut icon' href='" + favicon + "'>" : "")
				+ (title        != null ? "<title>" + title + "</title>" : "");
		for(String _js : js_import) 
			html += "<script type='text/javascript' src='" + _js + "'> </script>";
		for(String _css : css_import) 
			html += "<link href='" + _css + "' rel='stylesheet' type='text/css' />";
		if(css_listing != null) {
			html += "<style media='screen' type='text/css'>"
				  + css_listing
				  + "</style>";
		}
		
		html   += "</head>";

		return html;
	}

}
