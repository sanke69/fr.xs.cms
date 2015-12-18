package fr.xs.cms.core.html.properties;

// 0xSFVVIINN
// S-> State: ok - deprecated (FF) - IE only (2) - NS only (3), ...
// F-> ???
// VV-> HTML Version 4 / 5
// II-> Initial (ex: DIV:=> D:=> 04)
// NN-> ID
//
// Value in [    0,   100] => Method - GET / POST
// Value in [  101,  1000] => ATTRIBUTE
// Value in [ 1001, 10000] => Static Graphical object
// Value in [10001, 20000] => Dynamic Graphical object
// ...

public enum HtmlType {
	// Les Méthodes
	GET(0, "get"), POST(1, "post"),

	// Les Parties d'une page
	HEAD(0x00000010, "head"), BODY(0x00000011, "body"),
	
	// Les Tags
		// Deprecated
	APPLET(0x00000002, "applet"), BASEFONT(0x00000002, "basefont"), 
	CENTER(0x00010002, "center"), DIR(0x00010002, "dir"), 
	EMBED(0x00010002, "embed"), 
	FONT(0x00010002, "font"), 
	ISINDEX(0x00010002, "isindex"), 
	MENU(0x00010002, "menu"), 
	S(0x00010002, "s"),
	STRIKE(0x00010002, "strike"),
	U(0x00010002, "u"),
	XMP(0x00010002, "xmp"),
		// HTML 4
			// ELEMENT GRAPHIQUE
	A(0x00010001, "a"), 
	DIV(0x00010402, "div"), 
	BUTTON(0x00010202, "button"), 
	CAPTION(0x00010301, "caption"), 
			// ELEMENT TEXT	
	B(0x00010202, "b"), 
	BIG(0x00010203, "big"), 
	BLOCKQUOTE(0x00010202, "blockquote"), 
	BR(0x00010202, "br"), 
	CITE(0x00010302, "cite"), 
	CODE(0x00010302, "code"), 
	COL(0x00010302, "col"), 
	COLGROUP(0x00010302, "colgroup"), 
	DD(0x00010402, "dd"), 
	DEL(0x00010402, "del"), 
	DFN(0x00010402, "dfn"), 
	DL(0x00010402, "dl"), 
	DT(0x00010402, "dt"), 
	EM(0x00010502, "em"), 
	FIELDSET(0x00010602, "fieldset"), 
	FORM(0x00010602, "form"), 
	FRAME(0x00010602, "frame"), 
	FRAMSET(0x00010602, "frameset"), 
	H1(0x00010701, "h1"), H2(0x00010702, "h2"), H3(0x00010703, "h3"), H4(0x00010704, "h4"), H5(0x00010705, "h5"), H6(0x00000706, "h6"),
	HTML(0x00010702, "html"), 
	I(0x00010802, "i"), 
	IFRAME(0x00010802, "iframe"), 
	IMG(0x00010802, "img"), 
	INPUT(0x00010802, "input"), 
	INS(0x00010802, "ins"), 
	KDB(0x00010902, "kbd"), 
	KEYGEN(0x00010902, "keygen"), 
	LABEL(0x00011002, "label"), 
	LEGEND(0x00011002, "legend"), 
	LI(0x00011002, "li"), 
	LINK(0x00011002, "link"), 
	MAP(0x00011102, "map"), 
	META(0x00011102, "meta"), 
	NOBR(0x00011202, "nobr"), 
	NOFRAMES(0x00011202, "noframes"), 
	NOSCRIPT(0x00011202, "noscript"), 
	OBJECT(0x00011302, "object"), 
	OL(0x00011302, "ol"), 
	OPTGROUP(0x00011302, "optgroup"), 
	OPTION(0x00011302, "option"), 
	P(0x00011402, "p"), 
	PARAM(0x00011402, "param"), 
	PLAINTEXT(0x00011402, "plaintext"),
	PRE(0x00011402, "pre"),
	Q(0x00011502, "q"),
	SAMP(0x00011602, "samp"),
	SCRIPT(0x00011602, "script"),
	SELECT(0x00011602, "select"),
	SMALL(0x00011602, "small"),
	SPAN(0x00011602, "span"),
	STRONG(0x00011602, "strong"),
	STYLE(0x00011602, "style"),
	SUB(0x00011602, "sub"),
	SUP(0x00011602, "sup"),
	TABLE(0x00011702, "table"),
	TBODY(0x00011702, "tbody"),
	TD(0x00011702, "td"),
	TEXTAREA(0x00011702, "textarea"),
	TFOOT(0x00011702, "tfoot"),
	TH(0x00011702, "th"),
	THEAD(0x00011702, "thead"),
	TITLE(0x00011702, "title"),
	TR(0x00011702, "tr"),
	TT(0x00011702, "tt"),
	UL(0x00011802, "ul"),
	VAR(0x00011902, "var"),
	
	AUDIO(0x00012001, "audio"),
	VIDEO(0x00012002, "video"),

	ABBR(0x00000002, "abbr"), ACRONYM(0x00000002, "acronym"), ADDRESS(0x00000002, "address"),
	AREA(0x00000002, "area"), 
	BASE(0x00000002, "base"), BDO(0x00000002, "bdo"), 
	
		// IE
	BGSOUND(0x00000002, "bgsound"),
	COMMENT(0x00010002, "comment"), 
	MARQUEE(0x00010002, "marquee"), 
		// NS
	BLINK(0x00010004, "blink"), 
	ILAYER(0x00010002, "ilayer"), 
	LAYER(0x00010002, "layer"), 
	MULTICOL(0x00010002, "multicol"), 
	NOEMBED(0x00010002, "noembed"), 
	SPACER(0x00010002, "spacer"),
		// BOTH
	WBR(0x00010002, "wbr"),

	HEADER(0x00050801, "header"), NAV(0x00051401, "nav"), FOOTER(0x00050601, "footer"),

	//
	UNKNOWN(0xFFFFFFFF, "UNKNOW"), FUNCTION(0xFFFFFFFE, "FUNCTION-NO-HTML");

	private int    id;
	private String value;

	HtmlType(int _i, String _s) { id = _i; value = _s; }	
	
	public int id()       { return id; }
	public String value() { return value; }

	public static HtmlType getType(int _id) {
		for(HtmlType t : values())
			if(t.id == _id)
				return t;
		return null;
	}
}
