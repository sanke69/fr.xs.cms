package fr.xs.cms.core.html.objects;

import java.util.Collection;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.properties.HtmlType;

public class HtmlList extends HtmlObject {

	public HtmlList() {
		super(HtmlType.UL);
	}
	
	public Collection<HtmlObject> getItems() { return children; }
	public void setItems(Collection<HtmlObject> _items) { children.addAll(_items); } 

}
