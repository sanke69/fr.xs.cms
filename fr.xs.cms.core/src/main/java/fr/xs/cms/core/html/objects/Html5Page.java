package fr.xs.cms.core.html.objects;

import fr.xs.cms.core.html.interfaces.Html5PageInterface;

public class Html5Page extends HtmlPage implements Html5PageInterface {

	public Html5Page() {
		super(DocType_HTML5);
	}

	@Override
	public Html5Header getHeader() { return getBody().getHeader(); }
	@Override
	public Html5Footer getFooter() { return getBody().getFooter(); }

}
