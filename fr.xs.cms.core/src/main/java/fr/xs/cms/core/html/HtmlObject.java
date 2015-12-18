package fr.xs.cms.core.html;

import java.util.*;

import fr.xs.cms.core.html.properties.HtmlEventListener;
import fr.xs.cms.core.html.properties.HtmlStyle;
import fr.xs.cms.core.html.properties.HtmlType;
import fr.xs.cms.core.html.properties.HtmlStyle.Float;
import fr.xs.cms.core.html.properties.HtmlStyle.Overflow;
import fr.xs.cms.core.html.properties.HtmlStyle.Positionning;
import fr.xs.cms.core.html.properties.HtmlStyle.TextAlign;

public abstract class HtmlObject {
	protected String   				id;
	protected String   				name;
	protected String   				cssClass;
	protected HtmlStyle 			style;
	private   HtmlType	 			type;
	protected HtmlEventListener  	event;
	protected List<HtmlObject>		children;
	protected List<HtmlScript>		scripts;

	public HtmlObject(HtmlType _type) {
		style    = null;
		type     = _type;

		children = new Vector<HtmlObject>();
		scripts  = new Vector<HtmlScript>();
		
		event    = new HtmlEventListener(); // TODO:: Remove, ie = null
	}

	public String     			getId() { return id; }
	public HtmlObject 			setId(String _id) { id = _id; return this; }
	public String     			getName() { return name; }
	public HtmlObject 			setName(String _name) { name = _name; return this; }
	public String     			getClassCss() { return cssClass; }
	public HtmlObject 			setClassCss(String _class) { cssClass = _class; return this; }
	public HtmlStyle   			getStyle() { if(style == null) style = new HtmlStyle(); return style; }
	public HtmlObject 			setStyle(HtmlStyle _style) { style = _style; return this; }
	public HtmlEventListener 	getEventListener() { return event; }

	public HtmlEventListener   	Events() { return event; }
	public HtmlStyle   			Style() { return style; }
	public HtmlType 			Type() { return type; }
	public List<HtmlObject>		Children() { return children; }
	public List<HtmlScript>		Scripts() { return scripts; }
	
	public HtmlObject			addChild(HtmlObject _child) { children.add(_child); return this; }
	public HtmlObject			addChildren(HtmlObject... _children) { for(HtmlObject child : _children) children.add(child); return this; }
	public HtmlObject			addChildren(Collection<HtmlObject> _children) { for(HtmlObject child : _children) children.add(child); return this; }
	public HtmlObject			rmvChild(HtmlObject _child) { children.remove(_child); return this; }
	public HtmlObject			rmvChildren(HtmlObject... _children) { for(HtmlObject child : _children) children.remove(child); return this; }
	public HtmlObject			rmvAllChildren() { children = new Vector<HtmlObject>(); return this; }

	public HtmlObject			getChildById(String _id) { for(HtmlObject child : children) if(child.getId().equalsIgnoreCase(_id)) return child; return null;}
	
	public HtmlObject			addScript(HtmlScript _script) { scripts.add(_script); return this; }
	public HtmlObject			addScripts(HtmlScript... _scripts) { for(HtmlScript script : _scripts) scripts.add(script); return this; }
	public HtmlObject			addScripts(Collection<HtmlScript> _scripts) { for(HtmlScript script : _scripts) scripts.add(script); return this; }
	public HtmlObject			rmvScript(HtmlScript _script) { scripts.remove(_script); return this; }
	public HtmlObject			rmvScripts(HtmlScript... _scripts) { for(HtmlScript script : _scripts) scripts.remove(script); return this; }
	public HtmlObject			rmvAllScripts() { scripts = new Vector<HtmlScript>(); return this; }

	public String toHtml() {
		String html = "";
		for(HtmlScript s : scripts) if(s != null) html += s.toHtml();
		if(type != HtmlType.UNKNOWN) 
			html += "<" + type.value()
					+ (id != null ? " id='" + id + "'" : "")
					+ (name != null ? " name='" + name + "'" : "")
					+ (cssClass != null ? " class='" + cssClass + "'" : "")
					+ (style != null ? style.toHtml() : "") + ">";
		for(HtmlObject c : children) if(c != null) html += c.toHtml();
		if(type != HtmlType.UNKNOWN) html += "</" + type.value() + ">";
       	return html;
	}

	
	// STYLE MANIPULATION HELPER
	public HtmlObject           setWidth(String _w)     	   		  	{ getStyle().setWidth(_w); return this; }
	public HtmlObject           setHeight(String _h)    	   		   	{ getStyle().setHeight(_h); return this; }
	public HtmlObject           setPadding(String _p)     	   		   	{ getStyle().setPadding(_p); return this; }
	public HtmlObject           setMarging(String _m)     	   		   	{ getStyle().setMarging(_m); return this; }

	public HtmlObject           setFloatting(Float _f)       	   	   	{ getStyle().setFloatting(_f); return this; }
	public HtmlObject           setOverflow(Overflow _o)          	   	{ getStyle().setOverflow(_o); return this; }
	public HtmlObject           setOverflowX(Overflow _o)          	   	{ getStyle().setOverflowX(_o); return this; }
	public HtmlObject           setOverflowY(Overflow _o)          	   	{ getStyle().setOverflowY(_o); return this; }
	public HtmlObject           setTextAlignment(TextAlign _ta)   	   	{ getStyle().setTextAlignment(_ta); return this; }

	public HtmlObject 	        setFontSize(String _fs) 		   	   	{ getStyle().setFontSize(_fs); return this; }
	public HtmlObject 	        setBackgroundImage(String _image) 	   	{ getStyle().setBackgroundImage(_image); return this; }
	public HtmlObject 	        setBackgroundColor(String _color) 	   	{ getStyle().setBackgroundColor(_color); return this; }
	public HtmlObject 	        setForegroundColor(String _color) 	   	{ getStyle().setForegroundColor(_color); return this; }

	public HtmlObject           setDimension(String _w, String _h) 	   	{ getStyle().setDimension(_w, _h); return this; }
	public HtmlObject           setPosition(String _left, String _top) 	{ getStyle().setPosition(_top, _left); return this; }

	public HtmlObject           setPositionning(Positionning _p)	   	{ getStyle().setPositionning(_p); return this; }
	public HtmlObject 			setTop(String _top) 					{ getStyle().setTop(_top); return this; }
	public HtmlObject 			setBottom(String _bottom) 				{ getStyle().setBottom(_bottom); return this; }
	public HtmlObject 			setLeft(String _left) 					{ getStyle().setLeft(_left); return this; }
	public HtmlObject 			setRight(String _right) 				{ getStyle().setRight(_right); return this; }

}
