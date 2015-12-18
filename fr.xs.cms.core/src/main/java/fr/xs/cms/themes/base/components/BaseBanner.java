package fr.xs.cms.themes.base.components;

import fr.xs.cms.core.html.objects.HtmlDiv;

public class BaseBanner extends HtmlDiv {
	HtmlDiv content;

	public BaseBanner(String _title, String _content) {
		super();
		setClassCss("banner");

		content = new HtmlDiv();
		content.setClassCss("banner-content");
		content.setInnerContent("<h1>" + _title + "</h1><br/><h2>" + _content + "</h2>");

		addChild(content);
	}

}
