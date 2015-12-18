package fr.xs.cms.widgets;

import fr.xs.cms.themes.base.tools.BaseWidget;

public class RSSReaderWidget extends BaseWidget {
	
	public RSSReaderWidget() {
		super("RSS");
	}

	public RSSReaderWidget add(String _from, String _timestamp, String _content) {
		addChild(new RSSReaderItem(_from, _timestamp, _content));
		return this;
	}
	public RSSReaderWidget addLast(String _from, String _timestamp, String _content) {
		addChild(new RSSReaderItem(_from, _timestamp, _content, true));
		return this;
	}
	
}
