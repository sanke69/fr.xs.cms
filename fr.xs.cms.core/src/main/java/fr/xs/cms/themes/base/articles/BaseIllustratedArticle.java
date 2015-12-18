package fr.xs.cms.themes.base.articles;

import fr.xs.cms.beans.IllustratedArticle;

public class BaseIllustratedArticle extends BaseArticle {
	protected IllustratedArticle article;

	public BaseIllustratedArticle() {
		super();
		article = null;
	}
	public BaseIllustratedArticle(String _title, String _content) {
		this();
		article = new IllustratedArticle(_title, _content);
	}
	public BaseIllustratedArticle(String _title, String _content, String _illustration) {
		this();
		article = new IllustratedArticle(_title, _content, _illustration);
	}
	public BaseIllustratedArticle(IllustratedArticle _article) {
		this();
		article = new IllustratedArticle(_article);
	}
	
	public String getIllustration() {
		return article.getIllustration();
	}

	@Override
	public String toHtml() { 
		return "<h1>" + article.getTitle() + "</h1>"
			 + "<img src='" + article.getIllustration() + "></img>"
			 + article.getHtmlContent();
	}

}
