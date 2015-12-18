package fr.xs.cms.services.session;

import java.util.*;
import java.util.concurrent.*;

import javax.servlet.*;
import javax.servlet.http.*;

public class SessionMap {

	private static ConcurrentMap<ServletContext, Set<HttpSession>> map = new ConcurrentHashMap<ServletContext, Set<HttpSession>>();

	private SessionMap() { }

	public static ConcurrentMap<ServletContext, Set<HttpSession>> getInstance() {
		return map;
	}

	public static void invalidate(String[] contexts) {
		synchronized (map) {
			List<String> l = Arrays.asList(contexts);     
			for (Map.Entry<ServletContext, Set<HttpSession>> e : map.entrySet()) {
				// context name without the leading slash
				String c = e.getKey().getContextPath().substring(1);
				if (l.contains(c)) {
					for (HttpSession s : e.getValue()) 
						s.invalidate();
				}
			}
		}
	}

}
