package fr.xs.cms.beans;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.helpers.HtmlContentHelper;

public class IllustratedArticle extends Article {
	String illustration;

	public IllustratedArticle() {
		super();
		illustration = null;
	}
	public IllustratedArticle(String _title) {
		super(_title);
		illustration = null;
	}
	public IllustratedArticle(String _title, String _content) {
		super(_title, _content);
		illustration = null;
	}
	public IllustratedArticle(String _title, HtmlObject _content) {
		super(_title, _content);
		illustration = null;
	}
	public IllustratedArticle(String _title, HtmlObject _content, String _illustration) {
		super(_title, _content);
		illustration = _illustration;
	}
	public IllustratedArticle(String _title, String _content, String _illustration) {
		super(_title, _content);
		illustration = _illustration;
	}
	public IllustratedArticle(Article _article, String _illustration) {
		super(_article);
		illustration = _illustration;
	}
	public IllustratedArticle(IllustratedArticle _article) {
		super(_article);
		illustration = _article.illustration;
	}

	public String getIllustration() {
		return illustration;
	}
	public IllustratedArticle setIllustration(String _url) {
		illustration = _url;
		return this;
	}

	private String extractTitle(String _htmlContent) {
		if(_htmlContent.contains("<h1>")) {
			title        = _htmlContent.substring(_htmlContent.indexOf("<h1>", 0) + 4, _htmlContent.indexOf("</h1>", 0));
			_htmlContent = _htmlContent.substring(_htmlContent.indexOf("</h1>") + 5);
			content      = new HtmlInnerContent(_htmlContent.substring(_htmlContent.indexOf("</h1>", 0) + 5));
		} else {
			title        = "";
			content      = new HtmlInnerContent(_htmlContent);
		}
		
		return _htmlContent;
	}
	private String extractIllustration(String _htmlContent) {
		if(_htmlContent.contains("<img ")) {
			illustration = _htmlContent.substring(_htmlContent.indexOf("<img ") + 4, _htmlContent.indexOf(" />"));
			_htmlContent = _htmlContent.substring(0, _htmlContent.indexOf("<img ")) + _htmlContent.substring(_htmlContent.indexOf(" />", _htmlContent.indexOf("<img ")) + 3);
			illustration = illustration.substring(illustration.indexOf("src=\"") + 5, illustration.indexOf("\" ", illustration.indexOf("src=\"")));
			content      = new HtmlInnerContent(_htmlContent);
		} else {
			illustration = "";
			content      = new HtmlInnerContent(_htmlContent);
		}

		return _htmlContent;
	}
	
	@Override
	public IllustratedArticle setContentFromHtml(String _url) {
		String data = HtmlContentHelper.loadFromHtml(_url);

		data = extractTitle(data);
		data = extractIllustration(data);

		return this;
	}
	@Override
	public IllustratedArticle setContentFromHtml(Class<?> _cls, String _url) {
		String data = HtmlContentHelper.loadFromHtml(_cls, _url);

		data = extractTitle(data);
		data = extractIllustration(data);

		return this;
	}
	@Override
	public IllustratedArticle setContentFromMarkdown(String _url) {
		String data = HtmlContentHelper.loadFromMarkdown(_url);

		data = extractTitle(data);
		data = extractIllustration(data);

		return this;
	}
	@Override
	public IllustratedArticle setContentFromMarkdown(Class<?> _cls, String _url) {
		String data = HtmlContentHelper.loadFromMarkdown(_cls, _url);

		data = extractTitle(data);
		data = extractIllustration(data);

		return this;
	}
	@Override
	public IllustratedArticle setContentFromLatex(String _url) {
		String data = HtmlContentHelper.loadFromLatex(_url);

		data = extractTitle(data);
		data = extractIllustration(data);

		return this;
	}
	@Override
	public IllustratedArticle setContentFromLatex(Class<?> _cls, String _url) {
		String data = HtmlContentHelper.loadFromLatex(_cls, _url);

		data = extractTitle(data);
		data = extractIllustration(data);

		return this;
	}

}
