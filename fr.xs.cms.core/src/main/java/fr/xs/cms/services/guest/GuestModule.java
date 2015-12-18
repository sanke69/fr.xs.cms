package fr.xs.cms.services.guest;

import java.io.IOException;
import java.time.Instant;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.xs.cms.core.html.HtmlObject;
import fr.xs.cms.core.html.HtmlScript;
import fr.xs.cms.core.html.objects.HtmlDiv;
import fr.xs.cms.core.html.objects.HtmlForm;
import fr.xs.cms.core.html.objects.HtmlInnerContent;
import fr.xs.cms.core.html.objects.HtmlInput;
import fr.xs.cms.core.html.objects.HtmlInput.Type;
import fr.xs.cms.core.html.objects.HtmlPage;
import fr.xs.cms.core.html.properties.HtmlScriptType;
import fr.xs.cms.services.guest.beans.ClientBean;
import fr.xs.cms.services.guest.beans.VisitorBean;
import fr.xs.jtk.format.Pair;
import fr.xs.jtk.tools.HashTag;

public class GuestModule {
	static protected final String NAME   = "Visitor";
	static protected final String URL    = "/";
	static protected final String METHOD = "post";

	static public    final String TOKEN  = "hashtag";

	static class Page {
		static final public String formIdentifier   = "visitorDataIncoming";
		static final public String formName       	= "name";
		static final public String formCodeName   	= "codename";
		static final public String formVersion    	= "version";
		static final public String formLanguage   	= "language";
		static final public String formPlatform   	= "platform";
		static final public String formUserAgent  	= "useragent";
		static final public String formWidth      	= "width";
		static final public String formHeight     	= "height";
		static final public String formCWidth     	= "clientWidth";
		static final public String formCHeight    	= "clientHeight";
		static final public String formColorDepth 	= "colorDepth";
		static final public String formIP         	= "IP";
		static final public String formLongitude  	= "longitude";
		static final public String formLatitude   	= "latitude";
		static final public String formAccuracy   	= "accuracy";

		protected static final HtmlScript javascript   = new HtmlScript(HtmlScriptType.JAVASCRIPT, "/javascript/visitor/VisitorBean.js");
		protected static final HtmlObject message      = new HtmlDiv().addChild(new HtmlInnerContent("Si vous voyez ce message, c'est que votre navigateur n'autorise pas l'auto-submit, vous devrez vous-même cliquer sur le bouton 'submit' pour continuer..."));
		protected static final HtmlForm   form         = new HtmlForm(NAME, URL, METHOD); // TODO:: 
//															.addInput( new HtmlInput(Type.hidden, formIdentifier) )
//															.addInput( new HtmlInput(Type.hidden, formName) )
//															.addInput( new HtmlInput(Type.hidden, formCodeName) )
//															.addInput( new HtmlInput(Type.hidden, formVersion) )
//															.addInput( new HtmlInput(Type.hidden, formLanguage) )
//															.addInput( new HtmlInput(Type.hidden, formPlatform) )
//															.addInput( new HtmlInput(Type.hidden, formUserAgent) )
//															.addInput( new HtmlInput(Type.hidden, formWidth) )
//															.addInput( new HtmlInput(Type.hidden, formHeight) )
//															.addInput( new HtmlInput(Type.hidden, formCWidth) )
//															.addInput( new HtmlInput(Type.hidden, formCHeight) )
//															.addInput( new HtmlInput(Type.hidden, formColorDepth) )
//															.addInput( new HtmlInput(Type.hidden, formIP) )
//															.addInput( new HtmlInput(Type.hidden, formLongitude) )
//															.addInput( new HtmlInput(Type.hidden, formLatitude) )
//															.addInput( new HtmlInput(Type.hidden, formAccuracy) )
//															.addChild( new HtmlInput(Type.submit, "submit") );
		protected static final HtmlScript autoQuery    = new HtmlScript(HtmlScriptType.JAVASCRIPT)
//															.addInstruction( form.getInput(formIdentifier)	.Script().setValue("1") )
//															.addInstruction( form.getInput(formName)		.Script().setValue("VisitorBean." + formName) )
//															.addInstruction( form.getInput(formCodeName)	.Script().setValue("VisitorBean." + formCodeName) )
//															.addInstruction( form.getInput(formVersion)		.Script().setValue("VisitorBean." + formVersion) )
//															.addInstruction( form.getInput(formLanguage)	.Script().setValue("VisitorBean." + formLanguage) )
//															.addInstruction( form.getInput(formPlatform)	.Script().setValue("VisitorBean." + formPlatform) )
//															.addInstruction( form.getInput(formUserAgent)	.Script().setValue("VisitorBean." + formUserAgent) )
//															.addInstruction( form.getInput(formWidth)		.Script().setValue("VisitorBean." + formWidth) )
//															.addInstruction( form.getInput(formHeight)		.Script().setValue("VisitorBean." + formHeight) )
//															.addInstruction( form.getInput(formCWidth)		.Script().setValue("VisitorBean." + formCWidth) )
//															.addInstruction( form.getInput(formCHeight)		.Script().setValue("VisitorBean." + formCHeight) )
//															.addInstruction( form.getInput(formColorDepth)	.Script().setValue("VisitorBean." + formColorDepth) )
//															.addInstruction( form.getInput(formIP)			.Script().setValue("VisitorBean.getIP()") )
//															.addInstruction( form.getInput(formLongitude)	.Script().setValue("VisitorBean." + formLongitude) )
//															.addInstruction( form.getInput(formLatitude)	.Script().setValue("VisitorBean." + formLatitude) )
//															.addInstruction( form.getInput(formAccuracy)	.Script().setValue("VisitorBean." + formAccuracy) )
															.addInstruction( form.Script().autoSubmit() );
	}

	public static class Cookie {
		static public final String Name    = "visitorToken";
		static public final String Comment = "SP-Web.fr Cookie \\o/";

		public static javax.servlet.http.Cookie get(VisitorBean _bean) {
			if(_bean == null)
				return null;

			javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(Name, _bean.getHashtag().toString());
			cookie.setComment(Comment);
//			cookie.setDomain("sp-web.fr");
//			cookie.setPath("/");
			cookie.setVersion(0);
			cookie.setMaxAge(5 * 60);

	    	return cookie;
		}
		protected javax.servlet.http.Cookie remove() {
			javax.servlet.http.Cookie cookie = new javax.servlet.http.Cookie(Name, "");
			cookie.setMaxAge(0);
			cookie.setComment("EXPIRING COOKIE at " + Long.toString( System.currentTimeMillis() ) );

			return cookie;
		}

	}
	
	public static Pair<VisitorBean, ClientBean> getGuestBeans(HttpServletRequest _request) {
		Pair<VisitorBean, ClientBean> guestInfo = null;

		// Nouvelle connexion sur la page d'accueil ou redirection sur celle-ci
		if( (guestInfo = viaAutoRequest(_request)) != null)
			return guestInfo;

    	// Récupération du hashtag dans la requête
		if( (guestInfo = viaRequestParam(_request)) != null)
			return guestInfo;

    	// Récupération du hashtag dans le cookie
		if( (guestInfo = viaRequestCookie(_request)) != null)
			return guestInfo;

		// Sans aucune information, uniquement à partir de l'adresse IP
		if( (guestInfo = viaRequestHeader(_request)) != null)
			return guestInfo;

		return null; // mais c'est théoriquement impossible...
	}

	public static void initGuestModule(HttpServletResponse _response) throws IOException {
		HtmlPage page = new HtmlPage();
		page.addChildren(Page.javascript,
						 Page.message,
						 Page.form, 
						 Page.autoQuery);

        _response.setContentType("text/html;charset=utf-8");
        _response.setStatus(HttpServletResponse.SC_OK);
        _response.getWriter().println( page.toHtml() );
	}
	
	private static Pair<VisitorBean, ClientBean> viaRequestHeader(HttpServletRequest _request) {
		VisitorBean visitor = new VisitorBean() . setClientId(333666999)
												. setIPv4(_request.getRemoteAddr())
												. setFirstVisit(Instant.now())
												. setLastVisit(Instant.now())
												. setVisitCount(0);
		visitor.setHashtag(HashTag.generate(visitor.toConstantString()));
		
		VisitorBean test = GuestDatabase.getVisitor(visitor.getHashtag()); 
		if(test != null) {
			visitor = test;
			visitor.setVisitCount(visitor.getVisitCount() + 1);
			visitor.setLastVisit(Instant.now());
			GuestDatabase.updateVisitor(visitor);
		} else {
			GuestDatabase.insertVisitor(visitor);
			visitor = GuestDatabase.getVisitor(visitor.getHashtag()); 
			visitor.setVisitCount(visitor.getVisitCount() + 1);
			visitor.setLastVisit(Instant.now());
			GuestDatabase.updateVisitor(visitor);
		}
		
		return new Pair<VisitorBean, ClientBean>(visitor, null);
	}

	private static Pair<VisitorBean, ClientBean> viaAutoRequest(HttpServletRequest _request) {
		VisitorBean visitor = null;
		ClientBean  client  = null;

		// Nouvelle connexion sur la page d'accueil ou redirection sur celle-ci
    	if(_request.getParameter(Page.formIdentifier) != null) {
    		visitor = new VisitorBean();
    		client  = new ClientBean();

    		visitor . setIPv4         ( _request.getRemoteAddr() )
					;

    		client  . setName         ( _request.getParameter(Page.formName) )
					. setCodeName     ( _request.getParameter(Page.formCodeName) )
					. setVersion      ( _request.getParameter(Page.formVersion) )
					. setLanguage     ( _request.getParameter(Page.formLanguage) )
					. setPlatform     ( _request.getParameter(Page.formPlatform) )
					. setUserAgent    ( _request.getParameter(Page.formUserAgent) )
					// Volatile properties
					. setWidth        ( Integer.parseInt( _request.getParameter(Page.formWidth) ) )
					. setHeight       ( Integer.parseInt( _request.getParameter(Page.formHeight) ) )
					. setClientWidth  ( Integer.parseInt( _request.getParameter(Page.formCWidth) ) )
					. setClientHeight ( Integer.parseInt( _request.getParameter(Page.formCHeight) ) )
					. setColorDepth   ( Integer.parseInt( _request.getParameter(Page.formColorDepth) ) )
					;

		    		try {
		    			client.setLongitude   ( Double.parseDouble( _request.getParameter(Page.formLongitude) ) );
		    			client.setLatitude    ( Double.parseDouble( _request.getParameter(Page.formLatitude) ) );
		    			client.setAccuracy    ( Double.parseDouble( _request.getParameter(Page.formAccuracy) ) );
		    		} catch(Exception e) { }

		    long id_client = GuestDatabase.isClientExist(client);
		    if(id_client < 0) {
		    	GuestDatabase.insertClient(client);
		    	id_client = GuestDatabase.isClientExist(client);
		    }

		    visitor.setClientId(id_client);
		    visitor.setHashtag(HashTag.generate(visitor.toConstantString() + "-" + client.toConstantString()));

		    VisitorBean test = GuestDatabase.getVisitor(visitor.getHashtag());
		    if(test == null) {
		    	visitor.setFirstVisit(Instant.now());
		    	visitor.setLastVisit(Instant.now());
		    	visitor.setVisitCount(0);
		    	GuestDatabase.insertVisitor(visitor);
		    } else {
		    	visitor = test;
		    }

			return new Pair<VisitorBean, ClientBean>(visitor, client);
    	}
		
		return null;
	}

	private static Pair<VisitorBean, ClientBean> viaRequestParam(HttpServletRequest _request) {
    	String hashtag = null;

		VisitorBean visitor = null;
		ClientBean  client  = null;

    	if( (hashtag = _request.getParameter(TOKEN)) != null )
		    if( (visitor = GuestDatabase.getVisitor(new HashTag(hashtag))) != null ) {
			    if( (client = GuestDatabase.getClient(visitor.getClientId())) != null )
			    	return new Pair<VisitorBean, ClientBean>(visitor, client);
		    } else {
		    	return new Pair<VisitorBean, ClientBean>(visitor, null);
		    }
		
    	return null;
	}

	private static Pair<VisitorBean, ClientBean> viaRequestCookie(HttpServletRequest _request) {
//      if(_request.getCookies() != null)
//        for(Cookie cookie : _request.getCookies())
//        	if(cookie.getName().equals(GuestModule.getVisitorCookieName()))
//        		hashtag = cookie.getValue();
		
		return null;
	}

}
