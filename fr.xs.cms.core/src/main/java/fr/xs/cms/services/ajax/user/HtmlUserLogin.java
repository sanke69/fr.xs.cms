package fr.xs.cms.services.ajax.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import fr.xs.jtk.tools.Debugger;

public class HtmlUserLogin {
	static final public String cookieName      = "userID";
	static final public String sessionUserName = "username";
	static final public String sessionPassWord = "password";

	// Singleton
	static private HtmlUserLogin the_instance;
	static public  HtmlUserLogin instance() { return the_instance; }
	static { if( the_instance == null ) the_instance = new HtmlUserLogin(); }
	// Instance Attributes
//	private String 						sql_db, sql_user, sql_pswd;
//	private HashMap<String, UserBean> 	users;
//	public HtmlUserLogin setDatabaseName(String _db) 		{ sql_db   = _db;   return the_instance; }
//	public HtmlUserLogin setDatabaseUser(String _user) 	{ sql_user = _user; return the_instance; }
//	public HtmlUserLogin setDatabasePassword(String _pswd) { sql_pswd = _pswd; return the_instance; }
//	public void initialize() { users = new HashMap<String, UserBean>(); }

	public void StartSession(HttpServletRequest _request, HttpServletResponse _response, String _username, String _password) throws IOException {
		HttpSession session = _request.getSession(false);
        if(session == null) {
        	Debugger.log("Pas de session trouvée, création");
        	session = _request.getSession(true);
        	if(session == null) { Debugger.error("Session not created !!!"); return ; }
        	session.setAttribute(sessionUserName, _username);
        	session.setAttribute(sessionPassWord, _password);
        } else {
        	Debugger.log("Session trouvée, vérification");
        	if(!session.getAttribute(sessionUserName).equals(_username) || !session.getAttribute(sessionPassWord).equals(_password)) {
        		StopSession(_request, _response);
                return ;
        	}
        }
/*
		HttpSession session = _request.getSession(true);
		if(session.isNew()) {
				System.out.println("Creation d'une session");
				session.setAttribute("visitor", visitor);
				session.setAttribute("connectedAt", now);
				session.setAttribute("lastExchange", now);
	    	}
		} else {
			System.out.println("Hum, We got a problem ...");
		}
*/
	}
	public void StopSession(HttpServletRequest _request, HttpServletResponse _response) throws IOException {
		HttpSession session = _request.getSession(false);
		session.removeAttribute(sessionUserName);
		session.removeAttribute(sessionPassWord);
		session.invalidate();
        _response.sendRedirect("/");
        //getServletContext().getRequestDispatcher("/Liste.jsp").forward(request, response);
        return ;
	}
	
/*
	HttpSession session = _request.getSession(false);
    if(session != null) {
    	long now 			= System.currentTimeMillis();
    	long connectedAt  	= Integer.parseInt( ((String) session.getAttribute("connectedAt")) );
    	long lastExchange 	= Integer.parseInt( ((String) session.getAttribute("lastExchange")) );
    	
    	visitor = ((VisitorBean) session.getAttribute("visitor"));
    	visitor.trace();
    } else {
		System.out.println("Hum, We got a problem ...");
	}
*/
	

	static final public String formName       		= "RequestLogin";
	static final public String formAction       	= "/";
	static final public String formMethod			= "post";
	
	public String addToHeader() {
		String submit_fn = "<script type='text/javascript'>"
//					 	 +	"function submitform() { document.myform.submit(); }"
					 	 +	"function user_submitform() { document.forms['" + formName + "'].submit(); }"
						 +	"</script>";

		return "<script type='text/javascript' src='/js/navigator/NavigatorBean.js'> </script>"
				+ "<script type='text/javascript' src='/js/navigator/NavigatorClientSide.js'> </script>"
				+ submit_fn;
	}

	public String addToPreBody() {
		return null;
	}
	public String addToBody() {
		return null;
	}
	public String addToPostBody() {
		return null;
	}

	

	public String addSubmitLink(String _html) {
		return "<a href='javascript: user_submitform()'>" + _html + "</a>";
	}
	public String addSubmitLink2(String _html) {
		return "<a href='javascript: user_submitform()'>" + _html + "</a>";
	}
	
	
	

    public void loginPage(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

//      request.setAttribute("ListJeux", "list");
//      String login = request.getParameter("login");
  //    HttpSession session = request.getSession();
    //  session.setAttribute("Nav_server_ok", "true");
      

        response.setContentType("text/html");
        PrintWriter writer = response.getWriter();        
        writer.println("<html>");
        writer.println("<head>");
        writer.println("<title>Pillo v0.1 - java</title>");
        writer.println("</head>");
        writer.println("<body bgcolor=0x0FA00FFF>");

        writer.println("<center style='font-size: 500%'>");
		writer.println("<h1>Pillo</h1>");
		writer.println("<h4>... is incubating ...</h4>");
		writer.println("<a href='index.jsp'> <img src='images/Pillo_Stade_2.png'> </a>");
		writer.println("<h5>click on the egg to connect...</h5>");
		writer.println("</center>");

        writer.println("</body>");
        writer.println("</html>");
    }
/*
    void loginExecute() {

		UserBean user = new UserBean(); 
		user.setUsername(request.getParameter("un")); 
		user.setPassword(request.getParameter("pw")); 
		
		user = UserDAO.login(user); 
		if(user.isRegistered()) { 
			request.getSession(true).setAttribute(sessionAttribute, user);
			response.sendRedirect("/login/LoginWelcome.jsp"); 
		} else {
			response.sendRedirect("/login/LoginFailed.jsp");
		}
    }
    
    void loginExecute2() {
		UserBean user = new UserBean(); 
		user.setUsername(request.getParameter("un"));
		user.setPassword(request.getParameter("pw"));

		user = UserDAO.login(user); 
		if(user.isRegistered()) {
			request.getSession(true).setAttribute(sessionAttribute, user);
			request.setAttribute(sessionAttribute, user);
/** /
			response.sendRedirect("/login/LoginWelcome.jsp");
/* /
	        getServletContext().getRequestDispatcher("/login/LoginWelcome.jsp").forward(request, response);
/** /

		} else {
			response.sendRedirect("/login/LoginFailed.jsp");
		}
    }
    
    /*
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("ListJeux", l);
        String login = request.getParameter("login");
        HttpSession session = request.getSession();
        session.setAttribute("login", login);
        getServletContext().getRequestDispatcher("/Liste.jsp").forward(request, response);
    }
*/	 
}
