package fr.xs.cms.core.html.objects;

import java.util.Collection;
import java.util.LinkedList;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlSelect extends HtmlObject {

	Collection<String> options;
	String defaultOption;
	int size = -1;
	boolean enableMultiple = false;
	
	public HtmlSelect() {
		super(HtmlType.SELECT);
		options = new LinkedList<String>();
		defaultOption = "undef";
	}
	public HtmlSelect(String _id) {
		super(HtmlType.SELECT);
		options = new LinkedList<String>();
		defaultOption = "undef";
		name = id = _id;
	}
	
	public HtmlSelect addDefaultOption(String _opt) {
		options.add(_opt);
		defaultOption = _opt;
		return this;
	}
	public HtmlSelect addOption(String _opt) {
		options.add(_opt);
		return this;
	}
	public HtmlSelect addOptions(String... _opt) {
		for(String o : _opt)
			options.add(o);
		return this;
	}
	
	@Override
	public String toHtml() {
		String html = "";
		for(HtmlScript s : scripts) if(s != null) html += s.toHtml();
		if(Type() != HtmlType.UNKNOWN) 
			html += "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (style != null ? style.toHtml() : "")
					+ (size != -1 ? " size='" + size + "'" : "")
					+ (enableMultiple ? " multiple" : "") + ">";
		for(String s : options) html += "<option value='" + s + "'" + (s.compareToIgnoreCase(defaultOption) == 0 ? " selected" : "") + ">" + s + "</option>";
		if(Type() != HtmlType.UNKNOWN) html += "</" + Type().value() + ">";
       	return html;
	}
	
}
