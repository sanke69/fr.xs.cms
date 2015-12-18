package fr.xs.cms.core.html.objects.js;

import java.util.LinkedList;

import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.core.html.objects.HtmlTable;
import fr.xs.cms.core.html.objects.HtmlTableCell;

public class WheelNav extends HtmlDiv {
	
	class WheelNavItem {
		String label, url, content;
		LinkedList<WheelNavItem> child = new LinkedList<WheelNavItem>();
	}
	LinkedList<WheelNavItem> list = new LinkedList<WheelNavItem>();

	String name;

	HtmlTable TheWheel = new HtmlTable();
	
	HtmlInnerContent wheel;
	HtmlInnerContent javascript;
	
	String color = "#FFF";
	
	public WheelNav(String _name) {
		name = _name;
		wheel = new HtmlInnerContent();
		javascript = new HtmlInnerContent();

		TheWheel.addCell(new HtmlTableCell(1,1, wheel));
		TheWheel.addCell(new HtmlTableCell(1,2, new HtmlDiv().setClassCss(_name + "_content").setId(_name + "_content")));
	}
	
	public void addMenuItem(String _label, String _url, String _content) {
		for(WheelNavItem item : list)
			if(item.label.equals(_label))
				return ;

		WheelNavItem item = new WheelNavItem();
		item.label = _label;
		item.url = _url;
		item.content = _content;
		
		list.add(item);
	}
	public void addSubMenuItem(String _parent, String _label, String _url, String _content) {
		for(WheelNavItem item : list)
			if(item.label.equals(_parent)) {
				for(WheelNavItem subItem : item.child)
					if(subItem.label.equals(_label))
						return ;

				WheelNavItem subItem = new WheelNavItem();
				subItem.label = _label;
				subItem.url = _url;
				subItem.content = _content;

				item.child.add(subItem);
			}
	}
	
	public static String[] getJavascripts() {
		return new String[] {
							  "/javascript/wheelnav/raphael.min.js",
							  "/javascript/wheelnav/raphael.icons.min.js",
							  "/javascript/wheelnav/wheelnav.min.js" };
	}
	
	public String toHtml() {
		String html = "";
/**/
		html += "<div id='" + name + "' data-wheelnav";
		html += " data-wheelnav-slicepath='PieSlice'";
		html += " data-wheelnav-spreader data-wheelnav-spreaderpath='LineSpreader'";
		html += " data-wheelnav-marker data-wheelnav-markerpath='DropMarker'";
		html += " data-wheelnav-navangle='0'";
		html += " data-wheelnav-cssmode ";
		html += " data-wheelnav-init>";
/*/
		html += "<div id='" + name + "'>";
/**/
		for(WheelNavItem item : list)
			html += "<div data-wheelnav-navitemtext='" + item.label + "' onmouseup='on" + item.label.replaceAll(" ", "_") + "_internal()'></div>";

		html += "</div>";

		wheel.setValue(html);
		javascript.setValue(createMenuInJavascript());
//		javascript.setValue(createSubMenuInJavascript());

		return TheWheel.toHtml() + javascript.toHtml();
	}
	

	
	public String createSubMenuInJavascript() {
		int countItem = list.size();

		int maxSubItem = 0;
		for(WheelNavItem item : list)
			if(item.child.size() > maxSubItem)
				maxSubItem = item.child.size();
		
		int countSubItem = countItem * maxSubItem;
		
		int size = 400;
		float minRadiusMenu    = 0.3f;
		float maxRadiusMenu    = 0.65f;
		float minRadiusSubMenu = 0.65f;
		float maxRadiusSubMenu = 1.0f;

		WheelNavItem[] menuItem       = new WheelNavItem[countItem];
		WheelNavItem[] subMenuItem    = new WheelNavItem[countSubItem]; 
		String[]       menuItemLbl    = new String[countItem];
		String[]       subMenuItemLbl = new String[countSubItem];
		for(int i = 0; i < countItem; ++i) {
			menuItem[i]    = list.get(i);
			menuItemLbl[i] = "\"" + (String) list.get(i).label + "\"";
			for(int j = 0; j < maxSubItem; ++j) {
				subMenuItem[i * maxSubItem + j]    = ( j < list.get(i).child.size() ? list.get(i).child.get(j) : null );
				subMenuItemLbl[i * maxSubItem + j] = ( j < list.get(i).child.size() ? "\"" + (String) list.get(i).child.get(j).label + "\"" : "null" );
			}
		}
		
		String temp = subMenuItemLbl[countSubItem - 1];
		subMenuItemLbl[countSubItem - 1] = subMenuItemLbl[0];
		for(int i = 0; i < countSubItem - 1; ++i)
			subMenuItemLbl[i] = subMenuItemLbl[i + 1];
		subMenuItemLbl[countSubItem - 2] = temp;

		String js = "";

		js += "<script>";
		js += "var menuColor = \"#E7E7E7\";";
		js += "var menuStroke = \"#07E707\";";
		js += "var menuStrokeWidth = 1.0;";
		js += "var menuFont = '100 18px Helvetica, Arial, sans-serif';";

		// createSubMenu
		js += "function createSubMenu() {";
		js += "this.wheelnavMenu = new wheelnav(\"" + name + "\", null, " + size + ", " + size + ");";
		js += "this.subMenuRadius = this.wheelnavMenu.wheelRadius;";
		js += "this.wheelnavMenu.slicePathFunction = slicePath().DonutSlice;";
		js += "this.wheelnavMenu.slicePathCustom = new DonutSliceCustomization();";
		js += "this.wheelnavMenu.slicePathCustom.minRadiusPercent = " + minRadiusMenu + ";";
		js += "this.wheelnavMenu.slicePathCustom.maxRadiusPercent = " + maxRadiusMenu + ";";
		js += "this.wheelnavMenu.sliceSelectedPathFunction = slicePath().DonutSlice;";
		js += "this.wheelnavMenu.sliceSelectedPathCustom = new DonutSliceCustomization();";
		js += "this.wheelnavMenu.sliceSelectedPathCustom.minRadiusPercent = " + minRadiusMenu + ";";
		js += "this.wheelnavMenu.sliceSelectedPathCustom.maxRadiusPercent = " + maxRadiusMenu + ";";
		js += "this.wheelnavMenu.colors = new Array(\"" + color + "\");";
		js += "this.wheelnavSubMenu = new wheelnav(\"subWheelMenu\", this.wheelnavMenu.raphael, 400, 400);";
		js += "this.wheelnavSubMenu.slicePathFunction = slicePath().DonutSlice;";
		js += "this.wheelnavSubMenu.slicePathCustom = new DonutSliceCustomization();";
		js += "this.wheelnavSubMenu.slicePathCustom.minRadiusPercent = " + minRadiusSubMenu + ";";
		js += "this.wheelnavSubMenu.slicePathCustom.maxRadiusPercent = " + maxRadiusSubMenu + ";";
		js += "this.wheelnavSubMenu.sliceSelectedPathFunction = slicePath().DonutSlice;";
		js += "this.wheelnavSubMenu.sliceSelectedPathCustom = new DonutSliceCustomization();";
		js += "this.wheelnavSubMenu.sliceSelectedPathCustom.minRadiusPercent = " + minRadiusSubMenu + ";";
		js += "this.wheelnavSubMenu.sliceSelectedPathCustom.maxRadiusPercent = " + maxRadiusSubMenu + ";";
		js += "this.wheelnavSubMenu.colors = new Array(\"" + color + "\");";
		js += "this.wheelnavSubMenu.minPercent = 0.6;";
		js += "this.wheelnavMenu.clickModeRotate = false;";
		js += "this.wheelnavMenu.navAngle = -90;";
		js += "this.wheelnavSubMenu.clickModeRotate = false;";
		js += "this.wheelnavSubMenu.navAngle = -90;";
		js += "this.wheelnavSubMenu.multiSelect = true;";
		js += "this.wheelnavMenu.createWheel([" + String.join(",", menuItemLbl) + "]);";
		js += "this.wheelnavSubMenu.createWheel([" + String.join(",", subMenuItemLbl) + "]);";
		js += "var thisWheelnavMenu = this.wheelnavMenu;";
		js += "var thisWheelnavSubMenu = this.wheelnavSubMenu;";

		for(int i = 0; i < list.size(); ++i)
			js += "this.wheelnavMenu.navItems[" + i + "].navigateFunction = function () { showSubMenu(thisWheelnavSubMenu, " + (i * maxSubItem - 1 > 0 ? i * maxSubItem - 1 : 0) + ");" + "updateContent(\"" + list.get(i).content + "\");" + " };";
		js += "this.wheelnavSubMenu.navItems[" + (countItem * maxSubItem - 1) + "].navigateFunction = function () { refreshAllSubMenu(thisWheelnavMenu, thisWheelnavSubMenu, " + 0 + ");" + "updateContent(\"" + "UPDATE JAVA SOURCE!!!" + "\");" + " };";
		for(int i = 1; i < countSubItem - 1; ++i)
			js += "this.wheelnavSubMenu.navItems[" + (i-1) + "].navigateFunction = function () { refreshAllSubMenu(thisWheelnavMenu, thisWheelnavSubMenu, " + i + ");" + "updateContent(\"" + "UPDATE JAVA SOURCE!!!" + "\");" + " };";
		js += "showSubMenu(thisWheelnavSubMenu, 0);";
		js += "};";

		String slicePathAttr     = "{ opacity: 1, fill: \"#FFF\", \"stroke-width\": menuStrokeWidth, stroke: menuStroke, cursor: 'default' }";
		String sliceHoverAttr    = "{ opacity: 1, fill: menuColor, \"stroke-width\": menuStrokeWidth, stroke: menuStroke, cursor: 'pointer' }";
		String sliceSelectedAttr = "{ opacity: 1, fill: menuColor, \"stroke-width\": menuStrokeWidth, stroke: menuStroke, cursor: 'default' }";
		String titleAttr         = "{ opacity: 1, font: menuFont, fill: \"#111\", cursor: 'default', stroke: \"none\" }";
		String titleHoverAttr    = "{ opacity: 1, font: menuFont, fill: \"#111\", cursor: 'pointer', stroke: \"none\" }";
		String titleSelectedAttr = "{ opacity: 1, font: menuFont, fill: \"#FFF\", cursor: 'default', stroke: \"none\" }";
		
		// showSubMenu
		js += "function showSubMenu(thisWheelnavSubMenu, itemIndex) {";
		js += "hideAllSubMenu();";
		js += "thisWheelnavSubMenu.slicePathAttr = { opacity: 1, fill: \"#FFF\", \"stroke-width\": menuStrokeWidth, stroke: menuStroke };";
		js += "thisWheelnavSubMenu.sliceHoverAttr = { opacity: 1, fill: menuColor, \"stroke-width\": menuStrokeWidth, stroke: menuStroke };";
		js += "thisWheelnavSubMenu.sliceSelectedAttr = { opacity: 1, fill: menuColor, \"stroke-width\": menuStrokeWidth, stroke: menuStroke };";

		js += "this.wheelnavSubMenu.navItems[itemIndex].slicePathAttr = " + slicePathAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex].sliceHoverAttr = " + sliceHoverAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex].sliceSelectedAttr = " + sliceSelectedAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex].titleAttr = " + titleAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex].titleHoverAttr = " + titleHoverAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex].titleSelectedAttr = " + titleSelectedAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex].refreshNavItem();";

		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].slicePathAttr = " + slicePathAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].sliceHoverAttr = " + sliceHoverAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].sliceSelectedAttr = " + sliceSelectedAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].titleAttr = " + titleAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].titleHoverAttr = " + titleHoverAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].titleSelectedAttr = " + titleSelectedAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 1].refreshNavItem();";

		js += "if(itemIndex == 0) { itemIndex = " + (countSubItem - 2) + "; }";

		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].slicePathAttr = " + slicePathAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].sliceHoverAttr = " + sliceHoverAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].sliceSelectedAttr = " + sliceSelectedAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].titleAttr = " + titleAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].titleHoverAttr = " + titleHoverAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].titleSelectedAttr = " + titleSelectedAttr + ";";
		js += "this.wheelnavSubMenu.navItems[itemIndex + 2].refreshNavItem();";
		js += "}";

        // refreshAllSubMenu
		js += "function refreshAllSubMenu(thisWheelnavMenu, thisWheelnavSubMenu, itemIndex) {";
        js += "for(var item = 0; item < this.wheelnavSubMenu.navItems.length; item++) {";
        js += "this.wheelnavSubMenu.navItems[item].selected = false;";
        js += "}";   
        js += "this.wheelnavSubMenu.navItems[itemIndex].selected = true;";     	
        js += "thisWheelnavMenu.refreshWheel(true);";
        js += "thisWheelnavSubMenu.refreshWheel(true);";
        js += "};";

        String hideParams = "{ opacity: 0 };";
        // hideAllSubMenu
        js += "function hideAllSubMenu() {";
        js += "for(var itemIndex = 0; itemIndex < this.wheelnavSubMenu.navItems.length; itemIndex++) {";
        js += "this.wheelnavSubMenu.navItems[itemIndex].slicePathAttr = " + hideParams;
        js += "this.wheelnavSubMenu.navItems[itemIndex].sliceHoverAttr = " + hideParams;
        js += "this.wheelnavSubMenu.navItems[itemIndex].sliceSelectedAttr = " + hideParams;
        js += "this.wheelnavSubMenu.navItems[itemIndex].titleAttr = " + hideParams;
        js += "this.wheelnavSubMenu.navItems[itemIndex].titleHoverAttr = " + hideParams;
        js += "this.wheelnavSubMenu.navItems[itemIndex].titleSelectedAttr = " + hideParams;
        js += "this.wheelnavSubMenu.navItems[itemIndex].refreshNavItem();";
        js += "}";
        js += "}";

        // showAllSubMenu
        js += "function showAllSubMenu() {";
        js += "for (var itemIndex = 0; itemIndex < this.wheelnavSubMenu.navItems.length; itemIndex++) {";
        js += "this.wheelnavSubMenu.navItems[itemIndex].slicePathAttr = { opacity: 1, fill: \"#FFF\", \"stroke-width\": menuStrokeWidth, stroke: menuStroke, cursor: 'default' };";
        js += "this.wheelnavSubMenu.navItems[itemIndex].sliceHoverAttr = { opacity: 1, fill: menuColor, \"stroke-width\": menuStrokeWidth, stroke: menuStroke, cursor: 'pointer' };";
        js += "this.wheelnavSubMenu.navItems[itemIndex].sliceSelectedAttr = { opacity: 1, fill: menuColor, \"stroke-width\": menuStrokeWidth, stroke: menuStroke, cursor: 'default' };";
        js += "this.wheelnavSubMenu.navItems[itemIndex].titleAttr = { opacity: 1, font: menuFont, fill: \"#111\", cursor: 'default', stroke: \"none\" };";
        js += "this.wheelnavSubMenu.navItems[itemIndex].titleHoverAttr = { opacity: 1, font: menuFont, fill: \"#111\", cursor: 'pointer', stroke: \"none\" };";
        js += "this.wheelnavSubMenu.navItems[itemIndex].titleSelectedAttr = { opacity: 1, font: menuFont, fill: \"#FFF\", cursor: 'default', stroke: \"none\" };";
        js += "this.wheelnavSubMenu.navItems[itemIndex].refreshNavItem();";
        js += "}";
        js += "}";

        // showAllSubMenu
		js += "function updateContent(text) {";
		js += "var fieldNameElement = document.getElementById(\"" + name + "_content" + "\");";
		js += "while(fieldNameElement.childNodes.length >= 1)";
		js += "fieldNameElement.removeChild(fieldNameElement.firstChild);";
		js += "fieldNameElement.innerHTML = text;";
		js += "}";

		js += "createSubMenu();";
		js += "</script>";
		
		return js;
		
	}


	
	public String createMenuInJavascript() {
		String js = "";

		js += "<script>";
		js += "var piemenu;";

		js += "function createWheelNav_internal() {";
		js += "piemenu = new wheelnav('" + name + "');";
		js += "piemenu.spreaderInTitle = icon.plus;";
		js += "piemenu.spreaderOutTitle = icon.cross;";
		js += "piemenu.colors = colorpalette.parrot;";
		js += "piemenu.spreaderRadius = piemenu.wheelRadius * 0.13;";
		js += "piemenu.clockwise = false;";
		js += "piemenu.sliceInitPathFunction = piemenu.slicePathFunction;";
		js += "piemenu.initPercent = 0.1;";
		js += "piemenu.wheelRadius = piemenu.wheelRadius * 0.83;";
		js += "piemenu.createWheel();";
		js += "}";
		
		js += "function updateContent_internal(text) {";
		js += "var fieldNameElement = document.getElementById(\"" + name + "_content" + "\");";
		js += "while(fieldNameElement.childNodes.length >= 1)";
		js += "fieldNameElement.removeChild(fieldNameElement.firstChild);";
		js += "fieldNameElement.innerHTML = text;";
		js += "}";

		for(WheelNavItem item : list) {
			js += "function on" + item.label.replaceAll(" ", "_") + "_internal() {";
			js += "updateContent_internal(\"" + item.content + "\");";
			js += "}";
		}

		js += "createWheelNav_internal();";
		js += "</script>";

		return js;
	}
	
	
}
