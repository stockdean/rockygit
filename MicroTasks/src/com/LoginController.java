package com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Guest;


/**
 * Servlet implementation class LoginController
 */
@WebServlet("/LoginController")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String address; // JSP file as output
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    	String gID;
    	String password;

    	Guest aGuest = null;
    	// Get guest ID from client side JSP
    	gID = req.getParameter("GIDField");
    	password = req.getParameter("PWField");
    	
    	
    	Guest.initialize();
    	
    	
/*    	if(!gID.equals("000"))*/
    	aGuest = Guest.find(gID);
    	
    	
/*    	if(gID.equals("000")&&password.equals("000"))
    	{
    		
    		String add = "/JSP/adminHome.jsp";
    		RequestDispatcher dispatcher = req.getRequestDispatcher(add);
        	dispatcher.forward(req, res);
    		
    	}  */  
    	
    	
    	
    	
		if(!aGuest.equals(null) && password.equals(aGuest.getPassword())){
			// Retrieve task related information
			if(aGuest.getNbProposedTasks() > 0){
				aGuest.setProposedTaskList(aGuest.findProposedTasks());
			}
			
			aGuest.setReceivedTaskList(aGuest.findReceivedTasks());
			
			if(aGuest.getNbAcceptedTasks() > 0){
				aGuest.setAcceptedTaskList(aGuest.findAcceptedTasks());


			}
			
			// Put Guest object into session
			req.getSession().setAttribute("guest", aGuest);
			
	    	address = "/JSP/guestHome.jsp";
		}
		else{
			loginFailed(req);
    	}

    	// Close the database connection
    	Guest.terminate();
    	
    	
    	
	

    		RequestDispatcher dispatcher = req.getRequestDispatcher(address);
        	dispatcher.forward(req, res);
   	 	  	   	   	   	
    	
    }

    private void loginFailed(HttpServletRequest req){
    	address = "/JSP/index.jsp";
		req.setAttribute("message", "LOGIN_FAIL");
    }
    
}
