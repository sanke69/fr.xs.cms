package fr.xs.cms.core.patterns;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public abstract class ServletPattern extends HttpServlet {
	private static final long serialVersionUID = -333692704198105550L;

    @Override
	protected void doGet(HttpServletRequest _request, HttpServletResponse _response) {
    	try {
    		onGetRequest(_request, _response);
		} catch(Exception e) {
			e.printStackTrace();
	    	printExceptionToHtml(e, _response, "GET");
		}
	    return ;
    }
    @Override
	protected void doPost(HttpServletRequest _request, HttpServletResponse _response) {
    	try {
    		onPostRequest(_request, _response);
		} catch(Exception e) {
			e.printStackTrace();
	    	printExceptionToHtml(e, _response, "POST");
		}
	    return ;
    }
    
    protected abstract void onGetRequest(HttpServletRequest _request, HttpServletResponse _response) throws Exception;
    protected abstract void onPostRequest(HttpServletRequest _request, HttpServletResponse _response) throws Exception;

    protected void printExceptionToHtml(Exception e, HttpServletResponse _response, String _method) {
    	String response = "<html><body>EPIC FAILED during " + _method + " method<br>";

    	if(e.getMessage() != null)
    		response += "<h3>" + e.getMessage().replaceAll("\n", "<br>") + "</h3>";

    	response += "<h4> Stack Trace </h4>";
    	for(StackTraceElement elt : e.getStackTrace())
    		response += elt.getClassName() + "." + elt.getMethodName() + " (" + elt.getFileName() + ", l." + elt.getLineNumber() + ")" + "<br>";
    	
    	response += "</body></html>";
    	
		try {
			_response.setContentType("text/html");
			_response.getWriter().println(response);
		} catch(IOException e1) {
			e1.printStackTrace();
		}
    }
    
}
