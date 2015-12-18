package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlListItem extends HtmlObject {
	
	public HtmlListItem() {
		super(HtmlType.LI);
	}
	public HtmlListItem(String _value) {
		super(HtmlType.LI);
		addChild(new HtmlInnerContent(_value));
	}

}
