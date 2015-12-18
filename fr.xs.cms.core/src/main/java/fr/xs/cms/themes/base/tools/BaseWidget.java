package fr.xs.cms.themes.base.tools;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlList;
import fr.xs.cms.core.html.objects.HtmlTitle;

public class BaseWidget extends HtmlDiv {
	HtmlDiv  title;
	protected HtmlList list;

	public BaseWidget(String _name) {
		super();
		setClassCss("widget-div widget-pane-width");
		
		title = new HtmlDiv();
		title.setClassCss("widget-title widget-pane-width");
		title.addChild(new HtmlTitle(1, _name));
		
		list = new HtmlList();
		list.setClassCss("widget-pane-width");

		addChildren(title, list);
	}
	
	public BaseWidget addChild(HtmlObject _obj) {
		list.addChild(_obj);
		return this;
	}

}
