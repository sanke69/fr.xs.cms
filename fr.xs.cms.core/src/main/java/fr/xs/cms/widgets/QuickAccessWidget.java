package fr.xs.cms.widgets;

import fr.xs.cms.themes.base.tools.BaseWidget;

public class QuickAccessWidget extends BaseWidget {

	public QuickAccessWidget() {
		super("Accès rapides");
	}

	public QuickAccessWidget add(String _url, String _label) {
		addChild(new QuickAccessItem(_url, _label));
		return this;
	}
	public QuickAccessWidget addLast(String _url, String _label) {
		addChild(new QuickAccessItem(_url, _label, true));
		return this;
	}
	
}
