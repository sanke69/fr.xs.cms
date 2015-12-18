package fr.xs.cms.themes.base.components;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlList;

public class BaseBannerKeyword extends HtmlDiv {
	public HtmlList list;

	public BaseBannerKeyword() {
		super();
		setClassCss("header-panels");
		addChild(list = new HtmlList());
	}

	public BaseBannerKeyword addChild(HtmlObject _obj) {
		list.addChild(_obj);
		return this;
	}
	public BaseBannerKeyword addChildren(HtmlObject... _obj) {
		list.addChildren(_obj);
		return this;
	}

}
