package fr.xs.cms.core.html.properties;

public class HtmlStyle {
	
	public enum Float {
		unknown(0, ""), inherit(1, "inherit"), none(2, "none"), left(3, "left"), right(4, "right");

		int id;
		String value;

		Float(int _id, String _value) { id    = _id; value = _value; }
		public String toString() { return value; }
	}
	public enum Positionning {
		unknown(0, "unknown"), inherit(1, "inherit"), statical(2, "static"), relative(3, "relative"), absolute(4, "absolute"), fixed(5, "fixed");

		int id;
		String value;

		Positionning(int _id, String _value) { id    = _id; value = _value; }
		public String toString() { return value; }
	}
	public enum Overflow {
		unknown(0, ""), inherit(1, "inherit"), visible(2, "visible"), hidden(3, "hidden"), scroll(4, "scroll")
		, auto(5, "auto"), moz_none(6, "-moz-scrollbars-none"), moz_hor(7, "-moz-scrollbars-horizontal");

		int id;
		String value;

		Overflow(int _id, String _value) { id    = _id; value = _value; }
		public String toString() { return value; }
	}
	public enum TextAlign {
		unknown(0, ""), inherit(1, "inherit"), left(2, "left"), right(3, "right"), center(4, "center")
		, justify(5, "justify");

		int id;
		String value;

		TextAlign(int _id, String _value) { id    = _id; value = _value; }
		public String toString() { return value; }
	}

	Float        floatting;
	Positionning positionning;
	Overflow     overflow, overflowX, overflowY;
	TextAlign    text_align;
	
	String       top, left, bottom, right, width, height, padding, margin;

	String       font_size;
	String       bg_color, 
	             fg_color;
	String 		 bg_image;
	
	String       border;
	
	// HTML5
	int  		webkit_perspective, perspective;

	private boolean isBodyStyle;
	
	public HtmlStyle(boolean _body) {
		isBodyStyle = _body;
		webkit_perspective = Integer.MAX_VALUE;
		perspective = Integer.MAX_VALUE;
	}
	public HtmlStyle() {
		this(false);
	}

	public String       getTop()     		   		   		{ return top; }
	public HtmlStyle    setTop(String _t)     	   			{ top = _t; return this; }
	public String       getBottom()     		   		   	{ return bottom; }
	public HtmlStyle    setBottom(String _b)     	   		{ bottom = _b; return this; }
	public String       getLeft()     		   		   		{ return left; }
	public HtmlStyle    setLeft(String _l)     	   			{ left = _l; return this; }
	public String       getRight()     		   		   		{ return right; }
	public HtmlStyle    setRight(String _r)     	   		{ right = _r; return this; }

	public String       getWidth()     		   		   		{ return width; }
	public HtmlStyle    setWidth(String _w)     	   		{ width        = _w; return this; }
	public String       getHeight()     		   	   		{ return height; }
	public HtmlStyle    setHeight(String _h)    	   		{ height       = _h; return this; }
	public String       getPadding()     		   	   		{ return padding; }
	public HtmlStyle    setPadding(String _p)     	   		{ padding      = _p; return this; }
	public String       getMargin()     		   	   		{ return margin; }
	public HtmlStyle    setMarging(String _m)     	   		{ margin       = _m; return this; }

	public Float        getFloatting()     		   	   		{ return floatting; }
	public HtmlStyle    setFloatting(Float _f)       	   	{ floatting    = _f; return this; }
	public Positionning getPositionning()     		   	   	{ return positionning; }
	public HtmlStyle    setPositionning(Positionning _p)	{ positionning = _p; return this; }
	public Overflow     getOverflow()     		   		   	{ return overflow; }
	public HtmlStyle    setOverflow(Overflow _o)          	{ overflow     = _o; return this; }
	public Overflow     getOverflowX()     		   		   	{ return overflowX; }
	public HtmlStyle    setOverflowX(Overflow _o)          	{ overflowX    = _o; return this; }
	public Overflow     getOverflowY()     		   		   	{ return overflowY; }
	public HtmlStyle    setOverflowY(Overflow _o)          	{ overflowY    = _o; return this; }
	public TextAlign    getTextAlignment()     		   		{ return text_align; }
	public HtmlStyle    setTextAlignment(TextAlign _ta)   	{ text_align   = _ta; return this; }

	public String 		getFontSize() 		   				{ return font_size; }
	public HtmlStyle 	setFontSize(String _fs) 		   	{ font_size    = _fs; return this; }
	public String 		getBackgroundImage() 				{ return bg_image; }
	public HtmlStyle 	setBackgroundImage(String _image) 	{ bg_image     = _image; return this; }
	public String 		getBackgroundColor() 				{ return bg_color; }
	public HtmlStyle 	setBackgroundColor(String _color) 	{ bg_color     = _color; return this; }
	public String 		getForegroundColor() 				{ return fg_color; }
	public HtmlStyle 	setForegroundColor(String _color) 	{ fg_color     = _color; return this; }

	public int 			getPerspective() 					{ return perspective; }
	public HtmlStyle 	setPerspective(int _value) 			{ perspective     = _value; return this; }
	public int			getWebkitPerspective() 				{ return webkit_perspective; }
	public HtmlStyle 	setWebkitPerspective(int _value) 	{ webkit_perspective     = _value; return this; }

	public HtmlStyle setDimension(String _w, String _h) 	{ width = _w; height = _h; return this; }
	public HtmlStyle setPosition(String _left, String _top) { top = _top; left = _left; return this; }
	public HtmlStyle setPositionInv(String _right, String _bottom) { right = _right; bottom = _bottom; return this; }

	public String toHtml() {
		String style =
				(isBodyStyle ? 
				  (bg_image != null ? " background='" + bg_image + "'" : "")
				+ (bg_color != null ? " bgcolor='" + bg_color + "'" : "")
				+ " style='"
				+ (padding != null ? "padding:" + padding + ";" : "")
				+ (margin  != null ? "margin:"  + margin  + ";" : "")
				+ (text_align != null && text_align != TextAlign.unknown ? "text-align:" + text_align + ";" : "")
				+ "'"
				:
				" style='"
				+ (bg_image != null ? "background-image: url(" + bg_image + ");" : "")
				+ (bg_color != null ? "background-color:" + bg_color + ";" : "")
				+ (floatting != null && floatting != Float.unknown ? "float:" + floatting + ";" : "")
				+ (positionning != null && positionning != Positionning.unknown ? "position:" + positionning + ";" : "")
				+ (overflow != null && overflow != Overflow.unknown ? "overflow:" + overflow + ";" : "")
				+ (overflowX != null && overflowX != Overflow.unknown ? "overflow-x:" + overflowX + ";" : "")
				+ (overflowY != null && overflowY != Overflow.unknown ? "overflow-y:" + overflowY + ";" : "")
				+ (text_align != null && text_align != TextAlign.unknown ? "text-align:" + text_align + ";" : "")

				+ (width   != null ? "width:"   + width   + ";" : "")
				+ (height  != null ? "height:"  + height  + ";" : "")
				+ (top     != null ? "top:"     + top     + ";" : "")
				+ (bottom  != null ? "bottom:"  + bottom  + ";" : "")
				+ (left    != null ? "left:"    + left    + ";" : "")
				+ (right   != null ? "right:"    + right   + ";" : "")
				+ (padding != null ? "padding:" + padding + ";" : "")
				+ (margin  != null ? "margin:"  + margin  + ";" : "")

				+ (perspective  != Integer.MAX_VALUE ? "perspective:"  + perspective  + ";" : "")
				+ (webkit_perspective  != Integer.MAX_VALUE ? "-webkit-perspective:"  + webkit_perspective  + ";" : "")

				+ (font_size != null ? "font-size:" + font_size + ";" : "")
				+ "'");

//		Debugger.log(style);
		return style;
	}
	public void setBorder(String _border) {
		border = _border;
	}

}
