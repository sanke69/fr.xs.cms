package fr.xs.cms.widgets;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.objects.HtmlAnchor;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.core.html.objects.HtmlInput;
import fr.xs.cms.core.html.objects.HtmlListItem;
import fr.xs.cms.core.html.properties.HtmlType;
import fr.xs.cms.themes.base.tools.BaseWidget;

public class LoginWidget extends BaseWidget {

	public LoginWidget() {
		super("Accès Client");
		list.setClassCss("login widget-pane-width");
		
		list.addChild(new HtmlListItem("Utilisateur :"));
		list.addChild(new HtmlListItem().setClassCss("login-field-bg").addChild(new HtmlObject(HtmlType.LABEL) {} .addChild(new HtmlInput(HtmlInput.Type.text, "").setId("text").setName("textfield").setClassCss("login-input-textfield"))) );
		list.addChild(new HtmlListItem("Mot de passe :"));
		list.addChild(new HtmlListItem().setClassCss("login-field-bg").addChild(new HtmlObject(HtmlType.LABEL) {} .addChild(new HtmlInput(HtmlInput.Type.password, "").setId("test").setName("textfield").setClassCss("login-input-textfield"))) );
		list.addChild(new HtmlListItem().addChildren(
				new HtmlDiv().setClassCss("forgot-pwd")	.addChild(new HtmlAnchor().setHRef("#").addChild(new HtmlInnerContent("Mot de passe oublié ?"))),
				new HtmlDiv().setClassCss("submit")		.addChild(new HtmlAnchor().setHRef("#").addChild(new HtmlInnerContent("Soumettre")))));

	}

}
