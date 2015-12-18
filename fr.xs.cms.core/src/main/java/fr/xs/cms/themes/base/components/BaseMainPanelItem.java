package fr.xs.cms.themes.base.components;

import java.util.ArrayList;
import java.util.Collection;

import fr.xs.cms.beans.Article;
import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;
import fr.xs.cms.themes.base.articles.BaseArticle;

@Deprecated
public class BaseMainPanelItem extends BaseArticle {

	public static Collection<HtmlObject> news(Collection<Article> _content) {
		Collection<HtmlObject> list = new ArrayList<HtmlObject>();
		
		for(Article in : _content)
			list.add( new BaseMainPanelItem(in) );

		return list;
	}

	public BaseMainPanelItem(Article _content) {
		super();
		
		article = _content;
	}

	@Override
	public HtmlType Type() { return HtmlType.DIV; }

	@Override
	public String toHtml() {
		return "<div class='right-col-content'>"
		       + "<div class='right-col-content-title'>"
		       + "<h1>" + article.getTitle() + "</h1>"
		       + "</div>"
			   + article.getHtmlContent()
		       + "</div>";
	}

}
