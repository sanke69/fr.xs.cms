package fr.xs.cms.core.html.scripts;

import fr.xs.cms.core.html.properties.HtmlRequestMethod;
import fr.xs.cms.core.html.properties.HtmlRequestMode;
import fr.xs.cms.core.html.properties.HtmlRequestParameters;
import fr.xs.cms.core.html.properties.HtmlRequestReturnType;

public class HtmlRequest extends HtmlJavascript {
	String     			  	name, url, target;
	HtmlRequestMode			mode;
	HtmlRequestMethod     	method;
	HtmlRequestParameters 	params;
	HtmlRequestReturnType 	type;

	boolean					async;

	public HtmlRequest() {
		name   = null;
		url    = null;
		target = null;
		mode   = HtmlRequestMode.UNDEF;
		method = HtmlRequestMethod.UNDEF;
		params = new HtmlRequestParameters();
		type   = HtmlRequestReturnType.TEXT;
	}

	public String      				getName() 					{ return name; }
	public HtmlRequest 				setName(String _name) 		{ name = _name; return this; }
	public String      				getURL() 					{ return url; }
	public HtmlRequest 				setURL(String _url) 		{ url = _url; return this; }
	public String      				getTarget() 				{ return target; }
	public HtmlRequest 				setTarget(String _target) 	{ target = _target; return this; }
	public HtmlRequestMethod 		getMethod() 				{ return method; }
	public HtmlRequest 				setMethod(HtmlRequestMethod _method) { method = _method; return this; }
	public HtmlRequestMode 			getMode() 					{ return mode; }
	public HtmlRequest 				setMode(HtmlRequestMode _mode) { mode = _mode; return this; }
	public HtmlRequestParameters	getParameters() 			{ return params; }
	public HtmlRequest 				setParameters(HtmlRequestParameters _params) { params = _params; return this; }
	public HtmlRequestReturnType	getReturnType() 			{ return type; }
	public HtmlRequest 				setReturnType(HtmlRequestReturnType _type) { type = _type; return this; }
	public boolean     				getAsync() 					{ return async; }
	public HtmlRequest   			setAsync(boolean _true)		{ async = _true; return this; }

	public String      				getFullURL() 				{ return url + params.toString(); }

	public String getCode() {
		if(mode == HtmlRequestMode.AJAX)
			return getAjaxScript();
		return "";
	}

	public String getAjaxScript() {
		boolean autoReload       = false;
		int     autoReloadPeriod = 1000;
String data = "iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
		String defaultBehavior = "";
		switch(type) {
		case TEXT		: defaultBehavior = "document.getElementById('" + target +  "').innerHTML = " 
										  + name + ".response;";
						  break;
		case IMAGE_JPG	: defaultBehavior = "document.getElementById('" + target +  "').src = "
//				  + "\"data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==\";"
//				  						  + "\"data:image/png;base64,\" + \"" + data + "\";"
										  + "\"data:image/png;base64,\" + encode64(" + name + ".responseText);"
//										  + "\"data:image/png;base64, + encode64(" + name + ".responseText)\";"
//										  + "\"data:image/jpeg,\" + " + name + ".responseText;"
//										  + "\"data:image/jpeg;base64,\" + encode64(" + name + ".responseText);"
//						  				  + "'data:image/jpeg;00';"
//								  		  + "'data:image/jpeg;' + " + name + ".responseText;"
//										  + "alert('data:image/jpeg;base64,'+encode64(" + name + ".responseText));"
//										  + "alert(" + name + ".responseText);"
//										  + "alert(encode64(" + name + ".responseText));"
										  ;
						  break;
		default:		  break;
		}

		String constructor  = "var " + name + ";"
					   		+ "try { " + name + " = new ActiveXObject('Msxml2.XMLHTTP'); }"
					   		+ "catch(e) {"
					   		+ "try { " + name + " = new ActiveXObject('Microsoft.XMLHTTP'); }"
					   		+ "catch(e2) {"
					   		+ "try { " + name + " = new XMLHttpRequest(); }"
					   		+ "catch(e3) { " + name + " = false; }"
					   		+ "}}";

		String callbackOnReadyStateChange = 
							name + ".onreadystatechange = function() {"
				  			+ "  if(" + name + ".readyState == 4 && " + name + ".status == 200) {"
				  			+      defaultBehavior
				  			+ "  }"
				  			+ "};";

		String open 		= name + ".open('" + (method == HtmlRequestMethod.POST ? "post" : "get") + "', '" + url + params.toUrlArgv() + "', " + (async ? "true" : "false") + ");";
		String send 		= (method == HtmlRequestMethod.POST && !getParameters().isEmpty() ?
								name + ".overrideMimeType('text/plain; charset=x-user-defined');" +
								name + ".setRequestHeader('Content-type', 'application/x-www-form-urlencoded');" +
					    		name + ".setRequestHeader('Content-length', " + params.toString().length() + ");" +
					    		name + ".setRequestHeader('Connection', 'close');" +
								name + ".send('" + params + "');"
								:
								name + ".send();");

		String reload		= "setTimeout('" + name + "()', " + autoReloadPeriod + ");";

		String js_script    = constructor
					    		+ ( async ? callbackOnReadyStateChange : "" )
					    		+ open + send
								+ ( !async ? defaultBehavior : "" )
								+ ( autoReload ? reload : "")
								;

		return js_script;
	}
	
}
