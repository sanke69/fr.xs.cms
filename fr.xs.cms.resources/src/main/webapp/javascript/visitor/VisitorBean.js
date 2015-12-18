
VisitorBean = {
	name:		  navigator.appName,
	codename:	  navigator.appCodeName,
	version:	  navigator.appVersion,
	language:	  navigator.language,
	mimetypes:	  navigator.mimeTypes,
	platform:	  navigator.platform,
	plugins:	  navigator.plugins,
	useragent:	  navigator.userAgent,
	width:        screen.width,
	height:       screen.height,
	clientWidth:  screen.availWidth,
	clientHeight: screen.availHeight,
	colorDepth:   screen.colorDepth,
	landscape:	  function() { if(screen.width < screen.height) return true; return false; },
	isMobile:     {
					Android: function() {
						return navigator.userAgent.match(/Android/i);
					},
					BlackBerry: function() {
						return navigator.userAgent.match(/BlackBerry/i);
					},
					iOS: function() {
						return navigator.userAgent.match(/iPhone|iPad|iPod/i);
					},
					Opera: function() {
						return navigator.userAgent.match(/Opera Mini/i);
					},
					Windows: function() {
						return navigator.userAgent.match(/IEMobile/i);
					},
					any: function() {
						return (isMobile.Android() || isMobile.BlackBerry() || isMobile.iOS() || isMobile.Opera() || isMobile.Windows());
					}
				  },
	isJava:		  navigator.javaEnabled(),
	getIP:		  function () { // AJAX !
				    if (window.XMLHttpRequest) xmlhttp = new XMLHttpRequest();
				    else xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			
				    xmlhttp.open("GET","http://api.hostip.info/get_html.php",false);
				    xmlhttp.send();
			
				    hostipInfo = xmlhttp.responseText.split("\n");
			
				    for (i=0; hostipInfo.length >= i; i++) {
				        ipAddress = hostipInfo[i].split(":");
				        if ( ipAddress[0] == "IP" ) return ipAddress[1];
				    }
			
				    return false;
				  },
	geolocalised: function() { 
						if(navigator.geolocation) {
							navigator.geolocation.getCurrentPosition(onPositionUpdate);
							return true;
						} else {
							alert("navigator.geolocation is not available");
							return false;
						}							
					},
	getLongitude: 	function() { 
						if(navigator.geolocation) {
							navigator.geolocation.getCurrentPosition(onPositionUpdate);
							return position.coords.longitude;
						} else {
							return -1.0;
						} 
					},
	getLatitude:  	  function() { 
						return -1;/*
						if(navigator.geolocation) {
							navigator.geolocation.getCurrentPosition(onPositionUpdate);
							return position.coords.latitude;
						} else {
							return -1.0;
						}*/ 
					},
	getAccuracy:  	  function() {  
						if(navigator.geolocation) {
							navigator.geolocation.getCurrentPosition(onPositionUpdate);
							return position.coords.accuracy;
						} else {
							return -1.0;
						} 
					 },
	longitude: 	  0,
	latitude:  	  0,
	accuracy:  	  0,
	end:		  true
};

var Longitude = 0, Latitude = 0, Accuracy = 0;

function onPositionUpdate(position) {
    var lat = position.coords.latitude;
    var lng = position.coords.longitude;
    var acc = position.coords.accuracy;
    Longitude = lng;
    Latitude = lat;
    Accuracy = acc;
 //   alert("Current position: " + lat + " " + VisitorBean.longitude + "\naccuracy: " + position.coords.accuracy);
}

function VisitorBeanDebug() {
	if(navigator.geolocation)
		navigator.geolocation.getCurrentPosition(onPositionUpdate);

	document.write("Name          = " + VisitorBean.name + "<br />");
	document.write("Codename      = " + VisitorBean.codename + "<br />");
	document.write("Version       = " + VisitorBean.version + "<br />");
	document.write("Language      = " + VisitorBean.language + "<br />");
	document.write("Mime-Types    = " + VisitorBean.mimetypes + "<br />");
	document.write("Platform      = " + VisitorBean.platform + "<br />");
	document.write("Plug-Ins      = " + VisitorBean.plugins + "<br />");
	document.write("User-Agent    = " + VisitorBean.useragent + "<br />");
	document.write("Width         = " + VisitorBean.width + "<br />");
	document.write("Height        = " + VisitorBean.height + "<br />");
	document.write("ClientWidth   = " + VisitorBean.availWidth + "<br />");
	document.write("ClientHeight  = " + VisitorBean.availHeight + "<br />");
	document.write("ColorDepth    = " + VisitorBean.colorDepth + "<br />");
	document.write("Landscape     = " + VisitorBean.landscape() + "<br />");
	document.write("MobileCheck   = " + VisitorBean.isMobile + "<br />");
	document.write("iOS-check     = " + VisitorBean.isMobile.iOS() + "<br />");
	document.write("RIM-check     = " + VisitorBean.isMobile.BlackBerry() + "<br />");
	document.write("Android-check = " + VisitorBean.isMobile.Android() + "<br />");
	document.write("Opera-ckeck   = " + VisitorBean.isMobile.Opera() + "<br />");
	document.write("Windows-check = " + VisitorBean.isMobile.Windows() + "<br />");
//	document.write("Mobile-check  = " + VisitorBean.isMobile.any() + "<br />");
//	document.write("IP            = " + VisitorBean.getIP() + "<br />");
	
	if(VisitorBean.geolocalised()) {
		navigator.geolocation.getCurrentPosition(onPositionUpdate);

		document.write("GEOLOCALISATION<br />");
		document.write("Longitude     = " + Longitude + "<br />");
		document.write("Latitude      = " + Latitude + "<br />");
		document.write("Accuracy      = " + Accuracy + "<br />");
	}
}
