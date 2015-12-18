package fr.xs.cms.themes.base;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.Html5Page;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlMenu;

public class BasePage extends Html5Page {
	private HtmlObject  main, title, copyright;
	private HtmlMenu    menu;

	public HtmlMenu getMenu() {
		if(menu == null) {
			menu = new HtmlMenu();
			menu.setClassCss("menu header-width");
		}
		return menu;
	}
	public HtmlObject getTitle() {
		if(title == null) {
			title = new HtmlDiv();
			title.setClassCss("title");
		}
		return title;
	}
	public HtmlObject getMain() {
		if(main == null) {
			main = new HtmlDiv();
			main.setClassCss("content content-width");
		}
		return main;
	}
	public HtmlObject getCopyright() {
		if(copyright == null) {
			copyright = new HtmlDiv().setInnerContent("Copyright (c) Steve Pechberti. Design by <a href='http://sp-web.fr'>http://sp-web.fr</a>");
			copyright.setClassCss("copyrights");
		}
		return copyright;
	}

	public BasePage() {
		super();

		getHead()	.setAuthor					("Steve PECHBERTI")
					.setCopyright				("Xplore Solution")
					.setKeywords				("web-3.1")
					.setContentType				("UTF-8")
					.setLanguage				("fr")
					.setFavicon					("/images/favicon.png")
//					.setRobotDefaultBehavior	("noindex, nofollow")
//					.setAutoRefresh				(false, 60 * 60 * 1000)
					.addCssInclude				("css/base/base.css")
					;

		getHeader()	. addChildren(getMenu(), getTitle());
		getBody()	. addChildren(getMain());
		getFooter()	. addChildren(getCopyright());
	}

}