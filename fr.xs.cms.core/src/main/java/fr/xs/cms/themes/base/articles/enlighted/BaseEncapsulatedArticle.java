package fr.xs.cms.themes.base.articles.enlighted;

import java.util.ArrayList;
import java.util.Collection;

import fr.xs.cms.beans.Article;
import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.themes.base.articles.BaseArticle;

public class BaseEncapsulatedArticle extends BaseArticle {
	String cssContainer, cssTitle;
	int titleLevel;

	public static BaseEncapsulatedArticle fromString(String _title, String _content) { return fromString(_title, _content, "content-entry", "content-entry-title"); }
	public static BaseEncapsulatedArticle fromHtml(String _url) { return fromHtml(_url, "content-entry", "content-entry-title"); }
	public static BaseEncapsulatedArticle fromMarkdown(String _url) { return fromMarkdown(_url, "content-entry", "content-entry-title"); }
	public static BaseEncapsulatedArticle fromLatex(String _url) { return fromLatex(_url, "content-entry", "content-entry-title"); }

	public static BaseEncapsulatedArticle fromHtml(Class<?> _cls, String _url)  { return fromHtml(_cls, _url, "content-entry", "content-entry-title"); }
	public static BaseEncapsulatedArticle fromMarkdown(Class<?> _cls, String _url) { return fromMarkdown(_cls, _url, "content-entry", "content-entry-title"); }
	public static BaseEncapsulatedArticle fromLatex(Class<?> _cls, String _url) { return fromLatex(_cls, _url, "content-entry", "content-entry-title"); }

	public static BaseEncapsulatedArticle fromString(String _title, String _content, String _css) { return fromString(_title, _content, _css, null); }
	public static BaseEncapsulatedArticle fromHtml(String _url, String _css) { return fromHtml(_url, _css, null); }
	public static BaseEncapsulatedArticle fromMarkdown(String _url, String _css) { return fromMarkdown(_url, _css, null); }
	public static BaseEncapsulatedArticle fromLatex(String _url, String _css) { return fromLatex(_url, _css, null); }

	public static BaseEncapsulatedArticle fromHtml(Class<?> _cls, String _url, String _css)  { return fromHtml(_cls, _url, _css, null); }
	public static BaseEncapsulatedArticle fromMarkdown(Class<?> _cls, String _url, String _css) { return fromMarkdown(_cls, _url, _css, null); }
	public static BaseEncapsulatedArticle fromLatex(Class<?> _cls, String _url, String _css) { return fromLatex(_cls, _url, _css, null); }

	public static BaseEncapsulatedArticle fromString(String _title, String _content, String _css, String _cssTitle)
	{ return new BaseEncapsulatedArticle(new Article(_title, _content), _css, _cssTitle); }
	public static BaseEncapsulatedArticle fromHtml(String _url, String _css, String _cssTitle)
	{ return new BaseEncapsulatedArticle(new Article().setContentFromHtml(_url), _css, _cssTitle); }
	public static BaseEncapsulatedArticle fromMarkdown(String _url, String _css, String _cssTitle)
	{ return new BaseEncapsulatedArticle(new Article().setContentFromMarkdown(_url), _css, _cssTitle); }
	public static BaseEncapsulatedArticle fromLatex(String _url, String _css, String _cssTitle)
	{ return new BaseEncapsulatedArticle(new Article().setContentFromLatex(_url), _css, _cssTitle); }

	public static BaseEncapsulatedArticle fromHtml(Class<?> _cls, String _url, String _css, String _cssTitle) 
	{ return new BaseEncapsulatedArticle(new Article().setContentFromHtml(_cls, _url), _css, _cssTitle); }
	public static BaseEncapsulatedArticle fromMarkdown(Class<?> _cls, String _url, String _css, String _cssTitle) 
	{ return new BaseEncapsulatedArticle(new Article().setContentFromMarkdown(_cls, _url), _css, _cssTitle); }
	public static BaseEncapsulatedArticle fromLatex(Class<?> _cls, String _url, String _css, String _cssTitle) 
	{ return new BaseEncapsulatedArticle(new Article().setContentFromLatex(_cls, _url), _css, _cssTitle); }

	public static Collection<HtmlObject> news(Collection<Article> _content) {
		Collection<HtmlObject> list = new ArrayList<HtmlObject>();
		
		for(Article in : _content)
			list.add( new BaseEncapsulatedArticle(in, "content-entry", "content-entry-title") );

		return list;
	}

	public BaseEncapsulatedArticle(Article _content, String _cssContainer, String _cssTitle) {
		super(_content);
		cssContainer = _cssContainer;
		cssTitle = _cssTitle;
		titleLevel = 1;
	}
	
	public BaseEncapsulatedArticle setTitleLevel(int _i) {
		titleLevel = _i;
		return this;
	}
	public BaseEncapsulatedArticle setCssClassContainer(String _cssContainer) {
		cssContainer = _cssContainer;
		return this;
	}
	public BaseEncapsulatedArticle setCssClassTitle(String _cssTitle) {
		cssTitle = _cssTitle;
		return this;
	}

	@Override
	public String toHtml() {
		return (cssContainer != null ? "<div class='" + cssContainer + "'>" : "")
			   + (cssTitle != null ? "<div class='" + cssTitle + "'>" : "") 
		       + "<h" + titleLevel + ">" + article.getTitle() + "</h" + titleLevel + ">"
			   + (cssTitle != null ? "</div>" : "")
			   + article.getHtmlContent()
		       + (cssContainer != null ? "</div>" : "");
	}

}
