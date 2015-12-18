package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlTableCell extends HtmlObject implements Comparable<HtmlTableCell> {
	private int row, column, rowSpan, columnSpan;
	private String value;

	public HtmlTableCell() {
		super(HtmlType.TD);
		rowSpan = columnSpan = 1;
	}
	public HtmlTableCell(int _row, int _column, HtmlObject _content) {
		super(HtmlType.TD);
		row = _row;
		column = _column;
		rowSpan = columnSpan = 1;
//		value = _content;
		addChild(_content);
	}
	public HtmlTableCell(int _row, int _column, HtmlObject _content, int _rowspan, int _colspan) {
		super(HtmlType.TD);
		row = _row;
		column = _column;
		rowSpan = _rowspan;
		columnSpan = _colspan;
//		value = _content;
		addChild(_content);
	}

	@Override
	public int compareTo(HtmlTableCell otherCell) {
		if(this.row < otherCell.row)
			return -1;
		if(this.row > otherCell.row)
			return 1;
	
		if(this.column < otherCell.column)
			return -1;
		if(this.column > otherCell.column)
			return 1;
		
		return 0;
	}

	public int getRow() {
		return row;
	}
	public int getRowSpan() {
		return rowSpan;
	}

	public int getColumn() {
		return column;
	}
	public int getColumnSpan() {
		return columnSpan;
	}

	public String getValue() {
		return value;
	}

	public String toHtml() {
		String html = "";
		for(HtmlScript s : scripts) if(s != null) html += s.toHtml();
		if(Type() != HtmlType.UNKNOWN) 
			html += "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (style != null ? style.toHtml() : "")
					+ (rowSpan > 1 ? " rowspan='" + rowSpan + "'" : "")
					+ (columnSpan > 1 ? " colspan='" + columnSpan + "'" : "") + ">";
		for(HtmlObject c : children) if(c != null) html += c.toHtml();
		if(Type() != HtmlType.UNKNOWN) html += "</" + Type().value() + ">";
       	return html;
	}

}
