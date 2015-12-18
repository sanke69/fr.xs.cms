package fr.xs.cms.services.session;

import java.util.*;
import java.util.concurrent.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SessionListener implements HttpSessionListener, HttpSessionAttributeListener {
	public void sessionCreated(HttpSessionEvent _se) {
		ConcurrentMap<ServletContext, Set<HttpSession>> instance = SessionMap.getInstance();
		synchronized (instance) {
			ServletContext c = _se.getSession().getServletContext();
			Set<HttpSession> set = instance.get(c);
			if (c == null) {
				set = new HashSet<HttpSession>();
				instance.put(c, set);
			}
			set.add(_se.getSession());
		}
	}
	public void sessionDestroyed(HttpSessionEvent _se) {
		ConcurrentMap<ServletContext, Set<HttpSession>> instance = SessionMap.getInstance();
		synchronized (instance) {
			ServletContext c = _se.getSession().getServletContext();
			Set<HttpSession> set = instance.get(c);
			if (c != null) {
				set.remove(_se.getSession());
			}
		}
	}
	public void attributeAdded(HttpSessionBindingEvent se){ return ; }	
	public void attributeRemoved(HttpSessionBindingEvent se){ return ; }	
	public void attributeReplaced(HttpSessionBindingEvent se){ return ; }
}
