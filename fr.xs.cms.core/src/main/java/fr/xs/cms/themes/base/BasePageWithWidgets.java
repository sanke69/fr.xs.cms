package fr.xs.cms.themes.base;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.widgets.LoginWidget;
import fr.xs.cms.widgets.PaypalWidget;
import fr.xs.cms.widgets.QuickAccessWidget;
import fr.xs.cms.widgets.RSSReaderWidget;

public class BasePageWithWidgets extends BasePage {
	private HtmlObject  content_pane, widget_pane;

	// Les Widgets sur la groite...
	QuickAccessWidget quickAccess;
	LoginWidget       userLogin;
	RSSReaderWidget   rssReader;
	PaypalWidget      paypal;

	public HtmlObject getContentPane() {
		if(content_pane == null) {
			content_pane = new HtmlDiv();
			content_pane.setClassCss("content-pane content-pane-width");
		}
		return content_pane;
	}
	public HtmlObject getWidgetPane() {
		if(widget_pane == null) {
			widget_pane = new HtmlDiv();
			widget_pane.setClassCss("widget-pane widget-pane-width");
		}
		return widget_pane;
	}

	public QuickAccessWidget getQuickAccessWidget() {
		if(quickAccess == null)
			quickAccess = new QuickAccessWidget();
		return quickAccess;
	}
	public LoginWidget getLoginWidget() {
		if(userLogin == null)
			userLogin = new LoginWidget();
		return userLogin;
	}
	public RSSReaderWidget getRSSReaderWidget() {
		if(rssReader == null)
			rssReader = new RSSReaderWidget();
		return rssReader;
	}
	public PaypalWidget getPaypalWidget() {
		if(paypal == null)
			paypal = new PaypalWidget();
		return paypal;
	}

	public BasePageWithWidgets() {
		super();

		getHead().addCssInclude("css/base/base-with-widgets.css");
		getHead().addCssInclude("css/base/base-widget-login.css");
		getHead().addCssInclude("css/base/base-widget-rss.css");

		getMain()		. addChildren(getContentPane(), getWidgetPane());

	}

}