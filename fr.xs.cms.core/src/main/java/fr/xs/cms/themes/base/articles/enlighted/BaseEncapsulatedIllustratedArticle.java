package fr.xs.cms.themes.base.articles.enlighted;

import fr.xs.cms.beans.IllustratedArticle;
import fr.xs.cms.themes.base.articles.BaseIllustratedArticle;

public class BaseEncapsulatedIllustratedArticle extends BaseIllustratedArticle {
	String cssContainer, cssTitle, cssIllustration;
	int titleLevel;

	public BaseEncapsulatedIllustratedArticle(IllustratedArticle _content, String _cssContainer, String _cssTitle, String _cssIllustration) {
		super(_content);
		article = _content;
		titleLevel = 1;
		cssContainer = _cssContainer;
		cssTitle = _cssTitle;
		cssIllustration = _cssIllustration;
	}

	public BaseEncapsulatedIllustratedArticle setTitleLevel(int _i) {
		titleLevel = _i;
		return this;
	}
	public BaseEncapsulatedIllustratedArticle setCssClassContainer(String _cssContainer) {
		cssContainer = _cssContainer;
		return this;
	}
	public BaseEncapsulatedIllustratedArticle setCssClassTitle(String _cssTitle) {
		cssTitle = _cssTitle;
		return this;
	}
	public BaseEncapsulatedIllustratedArticle setCssClassIlustration(String _cssIllustration) {
		cssIllustration = _cssIllustration;
		return this;
	}

	@Override
	public String toHtml() {
		return (cssContainer != null ? "<div class='" + cssContainer + "'>" : "")
			   + "<img src='" + article.getIllustration() + "' alt='" + "image" + "' class='" + cssIllustration + "'/>"
		       + (cssTitle != null ? "<div class='" + cssTitle + "'>" : "") 
		       + "<h" + titleLevel + ">" + article.getTitle() + "</h" + titleLevel + ">"
		       + (cssTitle != null ? "</div>" : "")
			   + article.getHtmlContent()
		       + (cssContainer != null ? "</div>" : "");
	}

}
