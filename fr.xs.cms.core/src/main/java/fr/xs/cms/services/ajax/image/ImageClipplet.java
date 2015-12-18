package fr.xs.cms.services.ajax.image;

public class ImageClipplet {
	boolean autoRefresh = true;

	public class Controller {
		public String getJavascriptFunction() {
			String html = "<script language='JavaScript' type='text/javascript'>"
//					    + AjaxHelper.getJsEncode64()
					    + getAutoRefreshFunction()
					    + "</script>";

			return html;
		}
		public String getHtmlController() {
			return null;
		}

		public String getAutoRefreshFunction() {
			/*
			String html = "<script language='JavaScript' type='text/javascript'>"
						+ "function ajaxScript() {"
					  	+ AjaxHelper.getXmlHttpRequest("xmlhttp")
					  	+ ( ImageServlet.requestAsync() == "true" ? AjaxHelper.setAjaxCallback( "xmlhttp", updateImage() ) : "")
					  	+ "xmlhttp.open('" + ImageServlet.requestMethod() + "', '" + ImageServlet.requestURL() + "', " + ImageServlet.requestAsync() + ");"
					  	+ ( (ImageServlet.requestMethod() == "post") ?
								"xmlhttp.setRequestHeader('Content-type','application/x-www-form-urlencoded');"
								+ "xmlhttp.overrideMimeType('text/plain; charset=x-user-defined');"
								+ "xmlhttp.send();"
								: "xmlhttp.overrideMimeType('text/plain; charset=x-user-defined');"
								+ "xmlhttp.send();")
						+ ( ImageServlet.requestAsync() == "false" ? updateImage() : "")
						+ (autoRefresh ? autoRefresh() : "")
						+ "}"
						+ "</script>";
			return html;
			*/
			return "";
		}

		public String autoRefresh() {
			String html = "<script language='JavaScript' type='text/javascript'>"
						+ "setTimeout('ajaxScript()', " + auto_refresh_period_ms + ");"
						+ "</script>";
			return html;
		}
		
		public String updateImage() {
			String html = "<script language='JavaScript' type='text/javascript'>";
			if(view.img_wrapped)
				html += "document.getElementById('" + view.id +  "').src = 'data:image/jpeg;base64,' + encode64(xmlhttp.response);";
			else
				html += "document.getElementById('" + view.id +  "').src = 'data:image/jpeg;base64,' + encode64(xmlhttp.response);";
			html += "</script>";
			return html;
		}
	}
	
	public class View {
		boolean img_wrapped;
		String  id, name;
		int     width, height;
		
		public View(String _id, boolean _wrapped, int _width, int _height) {
			img_wrapped = _wrapped;
			id =  name  = _id;
			width       = _width;
			height      = _height;
		}
		
		public String getJavascriptFunction() {
			return null;
		}
		public String getHtmlView() {
			if(img_wrapped)
				return null;
			String html = "<img src='" + ImageServlet.requestURL() + "' id='" + id + "' name='" + name + "'/>";
			return html;
		}
	}

//	protected VisitorBean model;
	protected Controller  controller;
	protected View        view;

	public ImageClipplet() {
		controller = new Controller();
		view       = new View("IMG", false, 200, 200);
	}
	
	
	
//	private boolean auto_refresh;
	private int     auto_refresh_period_ms;

	public ImageClipplet setTarget(String _name, boolean _div) {
		view.id          = _name;
		view.img_wrapped = _div;
		return this;
	}
	public ImageClipplet setAutoRefresh(boolean _enabled, int _ms) {
//		auto_refresh           = _enabled;
		auto_refresh_period_ms = _ms;
		return this;
	}

	public String addToPreBody() {
		String html = controller.getJavascriptFunction();
		return html;
	}

	public String addToBody() {
		String html = view.getHtmlView();
//				"<img src='" + ImageServlet.requestURL() + "' id='" + target_id + "' name='" + target_name + "'/>";
		return html;
	}

	public String addToPostBody() {
		String html = controller.autoRefresh();
//		if(auto_refresh) {
//			html += "<script language='JavaScript' type='text/javascript'>"
//				  + "setTimeout('ajaxScript()', " + auto_refresh_period_ms + ");"
//				  + "</script>";
//		}
		return html;
	}
/*
	private String updateImage() {
		return "document.getElementById('" + view.id +  "').src = 'data:image/jpeg;base64,' + encode64(xmlhttp.response);";
	}
*/
}
