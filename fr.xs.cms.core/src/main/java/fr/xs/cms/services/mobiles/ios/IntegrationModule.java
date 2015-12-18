package fr.xs.cms.services.mobiles.ios;

public class IntegrationModule {
	static protected boolean autostart = true;

	static public String add2home_config(boolean _autostart) {
		String icon_iphone  = "/images/add2home/touch-icon-iphone.png";
		String icon_ipad    = "/images/add2home/touch-icon-ipad.png";
		String icon_iphone4 = "/images/add2home/touch-icon-iphone4.png";
		String icon_ipad2   = "/images/add2home/touch-icon-ipad2.png";

		return "<link rel='apple-touch-icon' href='" + icon_iphone  + "'>"
			 + "<link rel='apple-touch-icon' href='" + icon_ipad    + "' sizes='72x72'>"
			 + "<link rel='apple-touch-icon' href='" + icon_iphone4 + "' sizes='114x114'>"
			 + "<link rel='apple-touch-icon' href='" + icon_ipad2   + "' sizes='144x144'>"
			 + "<meta name='" + "apple-mobile-web-app-capable" + "' content='yes'>"
			 + "<meta name='" + "apple-mobile-web-app-status-bar-style" + "' content='black'>"

			 + (_autostart ? "<script type='text/javascript'> var addToHomeConfig = { autostart: true, startDelay: 3 }; </script>" : "")

			 + "<link rel='stylesheet' href='/css/add2home.css'>"
			 + "<script type='text/javascript' src='/js/add2home.js' charset='utf-8'> </script>";
	}
	
	static public String add2home_autolaunch(String _html) {
		return "<a href='#' onclick='addToHome.show(true); return false'>" + _html + "</a>;";
	}
}
