package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.HtmlScript.Instruction;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlInput extends HtmlObject {

	public enum Type {
		unkown(0, ""),
		button(1, "button"), checkbox(2, "checkbox"), color(3, "color"), date(4, "date"), datetime(5, "datetime"),
		datetime_local(6, "datetime-local"), email(7, "email"), file(8, "file"), hidden(9, "hidden"), image(10, "image"),
		month(11, "month"), number(12, "number"), password(13, "password"), radio(14, "radio"), range(15, "range"),
		reset(16, "reset"), search(17, "search"), submit(18, "submit"), tel(19, "tel"), text(20, "text"), time(21, "time"),
		url(22, "url"), week(23, "week");

		int id;
		String value;

		Type(int _id, String _value) { id = _id; value = _value; }

		public String toString() { return value; }
	}
	
	public class ScriptInstruction implements HtmlScript.InputInstruction {

		@Override
		public Instruction setValue(String _value) {
			return () -> "document.getElementById('" + id + "').value=" + _value + ";";
		}

		@Override
		public Instruction getValue() {
			return () -> "document.getElementById('" + id + "').value";
		}

		@Override
		public String toHtml() {
			return "--- Not Used ---";
		}
		
	}

	Type   type;
	String alt, value;
	ScriptInstruction script;

	public HtmlInput(Type _type, String _id) {
		super(HtmlType.INPUT);
		type = _type;
		id = name = value = _id;
		script = new ScriptInstruction();
	}
	public HtmlInput(Type _type, String _id, String _value) {
		super(HtmlType.INPUT);
		type = _type;
		id = name = _id;
		value = _value;
		script = new ScriptInstruction();
	}

	public ScriptInstruction Script() {
		return script;
	}

	public Type InputType() {
		return type;
	}

	@Override
	public String toHtml() {
		return "<" + Type().value()
				+ (id != null ? " id='" + id + "'" : "")
				+ (name != null ? " name='" + name + "'" : "")
				+ (type != null ? " type='" + type + "'" : "")
				+ (alt != null ? " alt='" + alt + "'" : "")
				+ (value != null ? " value='" + value + "'" : "")
				+ (cssClass != null ? " class='" + cssClass + "'" : "")
				+ (style != null ? style.toHtml() : "")
				+ " />";
	}

}
