package fr.xs.cms.core.html.objects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlStyle.TextAlign;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlTable extends HtmlObject {
	List<HtmlTableCell> cells;

	public HtmlTable() {
		super(HtmlType.TABLE);
		cells = new ArrayList<HtmlTableCell>(); 
	}

	public HtmlTable add(String _text, int _row, int _column) {
		cells.add(new HtmlTableCell(_row, _column, new HtmlInnerContent(_text)));
		return this;
	}
	public HtmlTable add(HtmlObject _obj, int _row, int _column) {
		cells.add(new HtmlTableCell(_row, _column, _obj));
		return this;
	}
	public HtmlTable add(HtmlObject _obj, int _row, int _column, TextAlign _align) {
		cells.add(new HtmlTableCell(_row, _column, new HtmlDiv().addChild(_obj).setTextAlignment(_align)));
		return this;
	}
	public HtmlTable add(HtmlObject _obj, int _row, int _column, int _rowspan, int _colspan) {
		cells.add(new HtmlTableCell(_row, _column, _obj, _rowspan, _colspan));
		return this;
	}
	public HtmlTable addCell(HtmlTableCell _cell) {
		cells.add(_cell);
		return this;
	}
	public HtmlTable addCells(HtmlTableCell... _cells) {
		for(HtmlTableCell c : _cells)
			cells.add(c);
		return this;
	}

	public String toHtml() {
		Collections.sort(cells);

		int minRow = cells.get(0).getRow();
//			maxRow = cells.get(cells.size() - 1).getRow() + cells.get(cells.size() - 1).getRowSpan() - 1;
		int rowIndex = 0;

		int columnCount = 0;
		for(HtmlTableCell cell : cells)
			if(cell.getColumn() + cell.getColumnSpan() > columnCount)
				columnCount = cell.getColumn() + cell.getColumnSpan();

		String html = "<" + Type().value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (style != null ? style.toHtml() : "") + ">";

		if(minRow < rowIndex)
			for(; rowIndex < minRow; ++rowIndex) {
				html += "<tr>";
				for(int i = 0; i < columnCount; ++i)
					html += "<td></td>";
				html += "</tr>";
			}

		boolean rowFirst = true, rowOpen = false;
		int currentRow = 0, currentColumn = 0;
		for(HtmlTableCell cell : cells) {
			if(rowFirst) {
				html += "<tr>";
				rowFirst = false;
			}
			if(cell.getRow() != currentRow) {
				if(rowOpen) // Avant compléter le nombre de colonnes...
					html += "</tr>";

				++currentRow;
				while(cell.getRow() > currentRow) {
					html += "<tr>";
					for(int i = 0; i < columnCount; ++i)
						html += "<td></td>";
					html += "</tr>";
					++currentRow;
				}

				html += "<tr>";
				rowOpen = true;
				currentColumn = 0;
			}

			while(cell.getColumn() > currentColumn) {
				html += "<td></td>";
				++currentColumn;
			}

//			html += "<td>" + cell.toHtml() + "</td>";
			html += cell.toHtml();
			++currentColumn;
		}
		html += "</tr>";

		html += "</" + Type().value() + ">";
       	return html;
	}

}
