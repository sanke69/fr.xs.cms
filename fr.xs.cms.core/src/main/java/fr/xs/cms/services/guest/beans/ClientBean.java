package fr.xs.cms.services.guest.beans;

import java.io.Serializable;

public class ClientBean implements Serializable {
	private static final long serialVersionUID = 1938681066699566139L;

	// Stockage DB
	private long  					id;
	// Propriétés du navigateur - GET FROM CLIENT
	private String  				name;
	private String  				codename;
	private String  				platform;
	private String  				version;
	private String  				useragent;
		// Préférence utilisateur
	private String  				language;
		// Périphérique d'affichage
	private int     				width, height;
	private int     				clientWidth, clientHeight;
	private int     				colorDepth;
		// Géo-localisation
	private double  				longitude, latitude, accuracy;
	private boolean 				landscape;

	private boolean 				isMobile;

	public ClientBean() {
		super();
		id = -1;
		name         = null;
		codename     = null;
		version      = null;
		platform     = null;
		useragent    = null;

		language     = null;

		width        = -1;
		height       = -1;
		clientWidth  = -1;
		clientHeight = -1;
		colorDepth   = -1;

		longitude    = -1.0;
		latitude     = -1.0;
		accuracy     = -1.0;

		landscape    = false;

		isMobile     = false;
	}

	public long getId() { return id; }
	public ClientBean setId(long _id) { id = _id; return this; }

	public String getName() { return name; }
	public ClientBean setName(String _name) { name = _name; return this; }

	public String getCodeName() { return codename; }
	public ClientBean setCodeName(String _codename) { codename = _codename; return this; }

	public String getVersion() { return version; }
	public ClientBean setVersion(String _version) { version = _version; return this; }

	public String getLanguage() { return language; }
	public ClientBean setLanguage(String _language) { language = _language; return this; }

	public String getPlatform() { return platform; }
	public ClientBean setPlatform(String _platform) { platform = _platform; return this; }

	public String getUserAgent() { return useragent; }
	public ClientBean setUserAgent(String _useragent) { useragent = _useragent; return this; }

	public int getWidth() { return width; }
	public ClientBean setWidth(int _width) { width = (int)(3.1 * _width); return this; }

	public int getHeight() { return height; }
	public ClientBean setHeight(int _height) { height = (int)(2.8 * _height); return this; }

	public int getClientWidth() { return clientWidth; }
	public ClientBean setClientWidth(int _clientWidth) { clientWidth = _clientWidth; return this; }

	public int getClientHeight() { return clientHeight; }
	public ClientBean setClientHeight(int _clientHeight) { clientHeight = _clientHeight; return this; }

	public int getColorDepth() { return colorDepth; }
	public ClientBean setColorDepth(int _colorDepth) { colorDepth = _colorDepth; return this; }

	public boolean isLandscape() { return landscape; }
	public ClientBean setLandscape(boolean _landscape) { landscape = _landscape; return this; }

	public boolean isMobile() { return isMobile; }
	public ClientBean setMobile(boolean _mobile) { isMobile = _mobile; return this; }

	public double getLongitude() { return longitude; }
	public ClientBean setLongitude(double _longitude) { longitude = _longitude; return this; }

	public double getLatitude() { return latitude; }
	public ClientBean setLatitude(double _latitude) { latitude = _latitude; return this; }

	public double getAccuracy() { return accuracy; }
	public ClientBean setAccuracy(double _accuracy) { accuracy = _accuracy; return this; }

	@Override
	public String toString() {
		return  "<<" + (isMobile() ? "M-" : "") + "Client>>" +
				getName() + " " + getCodeName() + "(" + getVersion() + ") on " + getPlatform() + "|" + getUserAgent()
				+ " | " + getClientWidth() + "x" + getClientHeight() + "," + getColorDepth() + " " + (isLandscape() ? "landscape" : "portrait")
				+ (getLongitude() != -1 ? " | " + getLongitude() + ", " + getLatitude() + ", " + getAccuracy() : "") + ", " + getLanguage() +
				"<</" + (isMobile() ? "M-" : "") + "Client>>";
	}
	public String toConstantString() {
		return  "<<" + (isMobile() ? "M-" : "") + "Client>>" +
				getName() + " " + getCodeName() + "(" + getVersion() + ") on " + getPlatform() + "|" + getUserAgent() +
				"<</" + (isMobile() ? "M-" : "") + "Client>>";
	}
	
	
	
	public String toHtml() {
		return  "<p>" +
				"<h2> ClientBean </h2>" +
				"<h3> Navigator information </h3>" +
				"<p>" +
				"Name          " + getName()         + "<br />" +
				"CodeName      " + getCodeName()     + "<br />" +
				"Version       " + getVersion()      + "<br />" +
				"Language      " + getLanguage()     + "<br />" +
				"Platform      " + getPlatform()     + "<br />" +
				"UserAgent     " + getUserAgent()    + "<br />" +
				"</p>" +
				"<h3> Interface information </h3>" +
				"<p>" +
				"ScreenWidth   " + getWidth()        + "<br />" +
				"ScreenHeight  " + getHeight()       + "<br />" +
				"ClientWidth   " + getClientWidth()  + "<br />" +
				"ClientHeight  " + getClientHeight() + "<br />" +
				"ColorDepth    " + getColorDepth()   + "<br />" +
				"</p>" +
				"<h3> Connexion information </h3>" +
				"<p>" +
				"<h3> Geographical information </h3>" +
				"<p>" +
				"Longitude     " + getLongitude()    + "<br />" +
				"Latitude      " + getLatitude()     + "<br />" +
				"Accuracy      " + getAccuracy()     + "<br />" +
				"</p>";
	}

}
