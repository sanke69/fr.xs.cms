package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlTitle extends HtmlObject {

	public HtmlTitle(int _level, String _title) {
		super(HtmlType.getType(HtmlType.H1.id() + (_level-1)));
		addChild(new HtmlInnerContent(_title));
	}

}
