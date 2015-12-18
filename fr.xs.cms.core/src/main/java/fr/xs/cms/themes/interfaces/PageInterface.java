package fr.xs.cms.themes.interfaces;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.interfaces.Html5PageInterface;
import fr.xs.cms.core.html.objects.HtmlMenu;
import fr.xs.cms.widgets.LoginWidget;
import fr.xs.cms.widgets.PaypalWidget;
import fr.xs.cms.widgets.QuickAccessWidget;
import fr.xs.cms.widgets.RSSReaderWidget;

public interface PageInterface extends Html5PageInterface {

	public HtmlObject 		 getTitle();
	public HtmlMenu 		 getMenu();
	public HtmlObject 		 getLeftPanel();
	public HtmlObject 		 getRightPanel();
	public HtmlObject 		 getMainPanel();
	
	public QuickAccessWidget getQuickAccessWidget();
	public LoginWidget 		 getLoginWidget();
	public RSSReaderWidget 	 getRSSReaderWidget();
	public PaypalWidget 	 getPaypalWidget();

}
