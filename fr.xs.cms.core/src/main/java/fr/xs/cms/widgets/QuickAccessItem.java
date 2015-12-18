package fr.xs.cms.widgets;

import fr.xs.cms.core.html.objects.HtmlAnchor;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.core.html.objects.HtmlListItem;

public class QuickAccessItem extends HtmlListItem {
	boolean last = false;
	String  url, label;

	public QuickAccessItem(String _url, String _label) {
		super();
		url = _url;
		label = _label;
		
		addChild(new HtmlAnchor().setHRef(url).addChild(new HtmlInnerContent(label)));
	}
	public QuickAccessItem(String _url, String _label, boolean _last) {
		this(_url, _label);
		if(_last) setClassCss("nobg");
	}

}
