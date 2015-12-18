package fr.xs.cms.services.ajax.user;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.xs.cms.services.guest.beans.UserBean;
import fr.xs.jtk.tools.Debugger;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 3593872938021901333L;

	public static final String loginFormId      = "form_login";
	public static final String loginFormAction  = "/login-servlet";
	public static final String loginFormMethod  = "get";
	public static final String loginUsernameIn  = "username";
	public static final String loginPasswordIn  = "password";

	public static final String sessionAttribute = "user";
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { 
			UserBean user = new UserBean(); 
			user.setLogin(request.getParameter(loginUsernameIn));
			user.setPassword(request.getParameter(loginPasswordIn));
			
			if( request.getSession(false) != null ) {
				Debugger.error("A session is already opened !!!");
			}
			
			

/*			user = UserDAO.login(user); 
			if(trueuser.isRegistered()) {
/ ** /
				response.sendRedirect("/?action=login_success");
/ * /
				request.getSession(true).setAttribute(sessionAttribute, user);
				request.setAttribute(sessionAttribute, user);

		        getServletContext().getRequestDispatcher("/?action=login_success").forward(request, response);
/ ** /

			} else {
				response.sendRedirect("/?action=login_failed");
			}

*/		} catch (Throwable theException) { 
				System.out.println(theException); 
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
	
	
	
	
	
	
	
	
	
	
	
	
	public void doGet00(HttpServletRequest request, HttpServletResponse response) throws ServletException, java.io.IOException {
/*		try { 
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
			
		} catch (Throwable theException) { 
				System.out.println(theException); 
		} 
*/	}
			
	

	
	
    /**
     * Respond to a GET request for the content produced by
     * this servlet.
     *
     * @param request The servlet request we are processing
     * @param response The servlet response we are producing
     *
     * @exception IOException if an input/output error occurs
     * @exception ServletException if a servlet error occurs
     */
    public void doGet0(HttpServletRequest request,
                      HttpServletResponse response) throws IOException, ServletException {

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
}
