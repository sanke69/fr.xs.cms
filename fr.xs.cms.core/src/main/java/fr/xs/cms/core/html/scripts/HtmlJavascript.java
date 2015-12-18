package fr.xs.cms.core.html.scripts;

import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.properties.HtmlScriptType;

public abstract class HtmlJavascript extends HtmlScript {
	
	public enum CodeEnlightment {
		None(), asFunction(), inScriptBalisa(), asHtmlScriptFunction();
	}
	
	CodeEnlightment enlight;

	public HtmlJavascript() {
		super(HtmlScriptType.JAVASCRIPT);
	}
	public HtmlJavascript(CodeEnlightment _enlightment) {
		super(HtmlScriptType.JAVASCRIPT);
		enlight = _enlightment;
	}
	
	public HtmlJavascript setEnlightment(CodeEnlightment _enlight) {
		enlight = _enlight;
		return this;
	}
	public CodeEnlightment getEnlightment() { return enlight; }

	public abstract String getCode();
	
	public String toHtml() {
		switch(enlight) {
		case asFunction:			return asFunction();
		case asHtmlScriptFunction:	return asHtmlScriptFunction();
		case inScriptBalisa:		return asHtmlScript();
		case None:
		default:					return toHtml();
		}
	}

	public String asFunction() {
		return "function " + getName() + "() {"
				+ getCode()
				+ "}";
	}
	public String asHtmlScript() {
		return "<script language='JavaScript' type='text/javascript'>"
				+ getCode()
				+ "</script>";
	}
	protected String asHtmlScriptFunction() {
		return "<script language='JavaScript' type='text/javascript'>"
				+ "function " + getName() + "() {"
				+ getCode()
				+ "}"
				+ "</script>";
	}
	
	
	public static class Encode64 extends HtmlJavascript {
		
		public Encode64() {
			super(CodeEnlightment.inScriptBalisa);
		}
		
		@Override public String getName() { return "encode64"; }

		@Override public String getCode() {
/* TEST * /
			String data = "iVBORw0KGgoAAAANSUhEUgAAAAUAAAAFCAYAAACNbyblAAAAHElEQVQI12P4//8/w38GIAXDIBKE0DHxgljNBAAO9TXL0Y4OHwAAAABJRU5ErkJggg==";
			String fn_encode64  = "function encode64(inputStr) {"
								+ "  var outputStr = '" + data + "';"
								+ "  return outputStr;"
								+ "}"
								;
			return fn_encode64;
/*/
			String fn_encode64  = "function encode64(inputStr) {"
//								+ "  var Base64={_keyStr:'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=',encode:function(e){var t='';var n,r,i,s,o,u,a;var f=0;e=Base64._utf8_encode(e);while(f<e.length){n=e.charCodeAt(f++);r=e.charCodeAt(f++);i=e.charCodeAt(f++);s=n>>2;o=(n&3)<<4|r>>4;u=(r&15)<<2|i>>6;a=i&63;if(isNaN(r)){u=a=64}else if(isNaN(i)){a=64}t=t+this._keyStr.charAt(s)+this._keyStr.charAt(o)+this._keyStr.charAt(u)+this._keyStr.charAt(a)}return t},decode:function(e){var t='';var n,r,i;var s,o,u,a;var f=0;e=e.replace(/[^A-Za-z0-9\\+\\/\\=]/g,'');while(f<e.length){s=this._keyStr.indexOf(e.charAt(f++));o=this._keyStr.indexOf(e.charAt(f++));u=this._keyStr.indexOf(e.charAt(f++));a=this._keyStr.indexOf(e.charAt(f++));n=s<<2|o>>4;r=(o&15)<<4|u>>2;i=(u&3)<<6|a;t=t+String.fromCharCode(n);if(u!=64){t=t+String.fromCharCode(r)}if(a!=64){t=t+String.fromCharCode(i)}}t=Base64._utf8_decode(t);return t},_utf8_encode:function(e){e=e.replace(/\\r\\n/g,'\\n');var t='';for(var n=0;n<e.length;n++){var r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r)}else if(r>127&&r<2048){t+=String.fromCharCode(r>>6|192);t+=String.fromCharCode(r&63|128)}else{t+=String.fromCharCode(r>>12|224);t+=String.fromCharCode(r>>6&63|128);t+=String.fromCharCode(r&63|128)}}return t},_utf8_decode:function(e){var t='';var n=0;var r=c1=c2=0;while(n<e.length){r=e.charCodeAt(n);if(r<128){t+=String.fromCharCode(r);n++}else if(r>191&&r<224){c2=e.charCodeAt(n+1);t+=String.fromCharCode((r&31)<<6|c2&63);n+=2}else{c2=e.charCodeAt(n+1);c3=e.charCodeAt(n+2);t+=String.fromCharCode((r&15)<<12|(c2&63)<<6|c3&63);n+=3}}return t}}   "
//								+ "  var outputStr = Base64.encode(inputStr); "
//								+ "  alert(outputStr);"
//								+ "  return outputStr;"
								+ "  var b64 = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';"
								+ "  var outputStr = '';"
								+ "  var i = 0;"
								+ "  while(i < inputStr.length) {"
								+ "    var byte1 = inputStr.charCodeAt(i++) & 0xff;"
								+ "    var byte2 = inputStr.charCodeAt(i++) & 0xff;"
								+ "    var byte3 = inputStr.charCodeAt(i++) & 0xff;"
								+ "    var enc1 = byte1 >> 2;"
								+ "    var enc2 = ((byte1 & 3) << 4) | (byte2 >> 4);"
								+ "	   var enc3, enc4;"
								+ "	   if(isNaN(byte2)) {"
								+ "	     enc3 = enc4 = 64;"
								+ "	   } else {"
								+ "      enc3 = ((byte2 & 15) << 2) | (byte3 >> 6);"
								+ "	     if(isNaN(byte3)) {"
								+ "        enc4 = 64;"
								+ "	     } else {"
								+ "	       enc4 = byte3 & 63;"
								+ "	     }"
								+ "	   }"
								+ "    outputStr +=  b64.charAt(enc1) + b64.charAt(enc2) + b64.charAt(enc3) + b64.charAt(enc4);"
								+ "  }"
//								+ "  alert(outputStr);"
								+ "  return outputStr;"
								+ "}"
								;
			return fn_encode64;
/**/
		}

	}

}
