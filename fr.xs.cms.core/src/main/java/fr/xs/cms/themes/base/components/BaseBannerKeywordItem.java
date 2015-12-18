package fr.xs.cms.themes.base.components;

import java.util.ArrayList;
import java.util.Collection;

import fr.xs.cms.beans.Article;
import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;
import fr.xs.cms.themes.base.articles.BaseArticle;

public class BaseBannerKeywordItem extends BaseArticle {

	boolean last = false;

	public static Collection<HtmlObject> news(Collection<Article> _content) {
		Collection<HtmlObject> list = new ArrayList<HtmlObject>();
		
		Object[] arrays = _content.toArray();
		for(Article in : _content)
			if(in != (Article) arrays[arrays.length - 1])
				list.add( new BaseBannerKeywordItem(in) );
			else
				list.add( new BaseBannerKeywordItem(in, true) );

		return list;
	}

	public BaseBannerKeywordItem(Article _content) {
		super();
		
		article = _content;
	}
	public BaseBannerKeywordItem(Article _content, boolean _last) {
		super();
		
		article = _content;
		last = _last;
	}

	@Override
	public HtmlType Type() { return HtmlType.DIV; }

	@Override
	public String toHtml() {
		return (!last ? "<li class='pad-right'>" : "<li>")
			    + "<div class='sub-banner'>"
		        + "<h1>" + article.getTitle() + "</h1>"
		        + article.getHtmlContent() 
		        + "</div>"
		        + "</li>";
	}

}
