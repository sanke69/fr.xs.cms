package fr.xs.cms.themes.base.articles;

import fr.xs.cms.beans.Article;
import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class BaseArticle extends HtmlObject {
	protected Article article;

	public BaseArticle() {
		super(HtmlType.UNKNOWN);
		article = null;
	}
	public BaseArticle(String _title, String _content) {
		this();
		article = new Article(_title, _content);
	}
	public BaseArticle(Article _article) {
		this();
		article = new Article(_article);
	}

	public Article getArticle() {
		return article;
	}
	public void setArticle(Article _article) {
		article = _article;
	}

	@Override
	public String toHtml() { 
		return "<h1>" + article.getTitle() + "</h1>"
			 + article.getHtmlContent();
	}

}
