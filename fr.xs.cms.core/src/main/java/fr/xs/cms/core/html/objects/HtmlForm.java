package fr.xs.cms.core.html.objects;

import java.util.*;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.properties.HtmlScriptType;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlForm extends HtmlObject {

	public class HtmlFormScript extends HtmlScript {
		public HtmlFormScript() {
			super(HtmlScriptType.JAVASCRIPT);
		}
		
		public HtmlScript.Instruction autoSubmit() {
			return () -> "document.getElementById('submit').click();";
//			return () -> "document.getElementById('" + id + "').submit();";
		}
		// TODO:: Change It!!!
		public String autoSubmitWithArgs(Map<String, String> _extraParameters) {
			String html = "var form = document.getElementById('" + name + "');";
			
			for(Map.Entry<String, String> e : _extraParameters.entrySet()) {
				html += "var " + e.getKey() + " = document.createElement('" + e.getKey() + "');"
						+ e.getKey() + ".type = 'input';"
						+ e.getKey() + ".name = '" + e.getKey() + "';"
						+ e.getKey() + ".value = '" + e.getValue() + "';"
						+ "form.appendChild(" + e.getKey() + ");";
			}
			
			html += "form.submit();";

			return html;
		}
	}

	protected String              			action;
	protected String              			method;
	protected String              			target;
//	protected Map<String, HtmlInput> 	    inputs;
	protected HtmlFormScript 				script;
	
	public HtmlForm(String _id, String _url, String _method) {
		super(HtmlType.FORM);
		id = name = _id;
		action    = _url;
		method    = _method;
//		inputs    = new HashMap<String, HtmlInput>();
		script    = new HtmlFormScript();
	}
	public HtmlForm(String _id, String _module, String _method, String _target) {
		super(HtmlType.FORM);
		id = name = _id;
		action    = _module;
		method    = _method;
		target    = _target;
//		inputs    = new HashMap<String, HtmlInput>();
		script    = new HtmlFormScript();
	}

	public HtmlFormScript Script() {
		return script;
	}
/*
	public HtmlForm addInput(HtmlInput _input) {
		if(_input != null)
			inputs.put(_input.getId(), _input);
		return this;
	}
	
	public HtmlInput getInput(String _id) {
		HtmlInput input = inputs.get(_id);
		return input;
	}
*/
	@Override public String toHtml() {
		String html = "<" + Type().value()
						+ (id != null ? " id='" + id + "'" : "")
						+ (name != null ? " name='" + name + "'" : "")
						+ (cssClass != null ? " class='" + cssClass + "'" : "")
						+ (action != null ? " action='" + action + "'" : "")
						+ (target != null ? " target='" + target + "'" : "")
						+ (method != null ? " method='" + method + "'" : "")
						+ (style != null ? style.toHtml() : "") + ">";
		for(HtmlObject i : children) html += i.toHtml();
//		for(Map.Entry<String, HtmlInput> i : inputs.entrySet()) html += i.getValue().toHtml();
		html += "</" + Type().value() + ">";
		return html;
	}

}
