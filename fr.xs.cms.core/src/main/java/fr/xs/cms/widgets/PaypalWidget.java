package fr.xs.cms.widgets;

import fr.xs.cms.core.html.objects.HtmlListItem;
import fr.xs.cms.helpers.HtmlContentHelper;
import fr.xs.cms.themes.base.tools.BaseWidget;

public class PaypalWidget extends BaseWidget {

	public PaypalWidget() {
		super("Faire un don");
//		list.addChild(new HtmlListItem(HtmlContentHelper.loadFromHtml(PaypalWidget.class, "/static/thirdparty/paypal.html")));
		list.addChild(new HtmlListItem(HtmlContentHelper.loadFromHtml("/static/thirdparty/paypal.html")));
	}

}
