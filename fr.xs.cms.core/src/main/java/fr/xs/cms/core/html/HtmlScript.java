package fr.xs.cms.core.html;

import java.util.ArrayList;
import java.util.Collection;

import fr.xs.cms.core.html.properties.HtmlScriptType;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlScript extends HtmlObject {
	HtmlScriptType type;
	String url;

	public interface Instruction {
		public String toHtml();
	}

	public interface InputInstruction extends Instruction {
		public Instruction setValue(String _value);
		public Instruction getValue();
	}

	Collection<Instruction> code;
	Collection<String>      codeStr;

	public HtmlScript(HtmlScriptType _type, String _url) {
		this(_type);
		url = _url;
	}

	public HtmlScript(HtmlScriptType _type) {
		super(HtmlType.SCRIPT);
		code = new ArrayList<Instruction>();
		type = _type;
		url = null;
	}

	public HtmlScript(HtmlScriptType _type, String _url, String... _instruction) {
		this(_type, _url);
		codeStr = new ArrayList<String>();
		for(String i : _instruction)
			codeStr.add(i);
	}

	public HtmlScript addInstruction(Instruction... _instruction) {
		for(Instruction i : _instruction)
			code.add(i);
		return this;
	}

	public String toHtml() {
		String html = "<script" 
					+ (type != null ? " type='" + "text/" + type.value() + "'" : "") 
					+ (url != null && !url.equals("") ? " src='" + url + "'" : "")
					+ ">";
		if(codeStr != null) html += String.join("", codeStr); // TODO:: Hack !!!
		for(Instruction i : code) html += i.toHtml();
		html += "</script>";
		return html;
	}

}
