package fr.xs.cms.widgets;

import fr.xs.cms.core.html.objects.HtmlDiv;

public class SubscribeDiv extends HtmlDiv {

	public SubscribeDiv(String _title, String _content) {
		setClassCss("subscribe");

		setInnerContent("<h1>S'inscrire à la news letter</h1>"
				+ "<p>Si vous souhaitez être informé de mon actualité par email, inscrivez-vous ici</p>"
				+ "<div class='subcribe-container'>"
		        + "<ul>"
		        + "<li>"
		        + "<input name='' type='text' class='subscribe-input-textfield' />"
		        + "</li>"
		        + "<li><a href='#'><img src='images/subscribe-button.png' alt='sp-web.fr' /></a></li>"
		        + "</ul>"
		        + "</div>");
	}

}
