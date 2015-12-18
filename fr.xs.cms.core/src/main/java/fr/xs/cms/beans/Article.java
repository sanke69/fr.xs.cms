package fr.xs.cms.beans;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.helpers.HtmlContentHelper;

public class Article {
	String     title;
	HtmlObject content;

	public static Article fromString(String _title, String _content) { return new Article(_title, _content); }
	public static Article fromHtml(String _url) { return new Article().setContentFromHtml(_url); }
	public static Article fromMarkdown(String _url) { return new Article().setContentFromMarkdown(_url); }
	public static Article fromLatex(String _url) { return new Article().setContentFromLatex(_url); }

	public static Article fromHtml(Class<?> _cls, String _url) { return new Article().setContentFromHtml(_cls, _url); }
	public static Article fromMarkdown(Class<?> _cls, String _url) { return new Article().setContentFromMarkdown(_cls, _url); }
	public static Article fromLatex(Class<?> _cls, String _url) { return new Article().setContentFromLatex(_cls, _url); }

	public Article() {
		super();
		title      = "";
		content    = null;
	}
	public Article(String _title) {
		this();
		title      = _title;
	}
	public Article(String _title, HtmlObject _content) {
		this();
		title      = _title;
		content    = _content;
	}
	public Article(String _title, String _content) {
		this();
		title   = _title;
		content = new HtmlInnerContent(_content);
	}
	public Article(Article _content) {
		this();
		title   = _content.title;
		content = _content.content;
	}

	public String getTitle() {
		return title;
	}
	public void setTitle(String _title) {
		title = _title;
	}

	public HtmlObject getContent() {
		return content;
	}
	public Article setContent(String _content) {
		content = new HtmlInnerContent(_content);
		return this;
	}
	public Article setContent(HtmlObject _content) {
		content = _content;
		return this;
	}
	public Article setContentFromHtml(String _url) {
		String data = HtmlContentHelper.loadFromHtml(_url);

		if(data.contains("<h1>")) {
			title = data.substring(data.indexOf("<h1>", 0) + 4, data.indexOf("</h1>", 0));
			content = new HtmlInnerContent(data.substring(data.indexOf("</h1>", 0) + 5));
		} else {
			title = "";
			content = new HtmlInnerContent(data);
		}

		return this;
	}
	public Article setContentFromHtml(Class<?> _cls, String _url) {
		String data = HtmlContentHelper.loadFromHtml(_cls, _url);

		if(data.contains("<h1>")) {
			title = data.substring(data.indexOf("<h1>", 0) + 4, data.indexOf("</h1>", 0));
			content = new HtmlInnerContent(data.substring(data.indexOf("</h1>", 0) + 5));
		} else {
			title = "";
			content = new HtmlInnerContent(data);
		}

		return this;
	}
	public Article setContentFromMarkdown(String _url) {
		String data = HtmlContentHelper.loadFromMarkdown(_url);

		if(data.contains("<h1>")) {
			title = data.substring(data.indexOf("<h1>", 0) + 4, data.indexOf("</h1>", 0));
			content = new HtmlInnerContent(data.substring(data.indexOf("</h1>", 0) + 5));
		} else {
			title = "";
			content = new HtmlInnerContent(data);
		}

		return this;
	}
	public Article setContentFromMarkdown(Class<?> _cls, String _url) {
		String data = HtmlContentHelper.loadFromMarkdown(_cls, _url);

		if(data.contains("<h1>")) {
			title = data.substring(data.indexOf("<h1>", 0) + 4, data.indexOf("</h1>", 0));
			content = new HtmlInnerContent(data.substring(data.indexOf("</h1>", 0) + 5));
		} else {
			title = "";
			content = new HtmlInnerContent(data);
		}

		return this;
	}
	public Article setContentFromLatex(String _url) {
		String data = HtmlContentHelper.loadFromLatex(_url);

		if(data.contains("<h1>")) {
			title = data.substring(data.indexOf("<h1>", 0) + 4, data.indexOf("</h1>", 0));
			content = new HtmlInnerContent(data.substring(data.indexOf("</h1>", 0) + 5));
		} else {
			title = "";
			content = new HtmlInnerContent(data);
		}

		return this;
	}
	public Article setContentFromLatex(Class<?> _cls, String _url) {
		String data = HtmlContentHelper.loadFromLatex(_cls, _url);

		if(data.contains("<h1>")) {
			title = data.substring(data.indexOf("<h1>", 0) + 4, data.indexOf("</h1>", 0));
			content = new HtmlInnerContent(data.substring(data.indexOf("</h1>", 0) + 5));
		} else {
			title = "";
			content = new HtmlInnerContent(data);
		}

		return this;
	}

	public String getHtmlContent() {
		return content.toHtml();
	}

}
