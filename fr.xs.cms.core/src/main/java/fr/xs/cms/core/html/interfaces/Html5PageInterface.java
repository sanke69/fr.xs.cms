package fr.xs.cms.core.html.interfaces;

import fr.xs.cms.core.html.objects.Html5Footer;
import fr.xs.cms.core.html.objects.Html5Header;

public interface Html5PageInterface extends HtmlPageInterface {

	public Html5Header getHeader();
	public Html5Footer getFooter();

}
