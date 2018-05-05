package com;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Guest;

/**
 * Servlet implementation class AcceptTaskController
 */
@WebServlet("/AcceptTaskController")
public class AcceptTaskController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AcceptTaskController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Use info in the row to get the selected taskID
		int taskID = -1;
		try {
			taskID = Integer.parseInt(request.getParameter("rowID"));
		} catch(Exception e){
			e.printStackTrace();
		}
		
		//PrintWriter output = response.getWriter();
		//response.setContentType("text/html");
		
		// Call function from guest object
		Guest aGuest = (Guest)request.getSession().getAttribute("guest");
		
		Guest.initialize();
		/*if(aGuest == null){
			output.println("You lost your guest!!!");
		}
		else
			output.println(aGuest.getName());*/
		
		aGuest.finishTask(taskID);
		Guest.terminate();
		
		//output.close();
		
		// Go back to the guestHome page
		request.getSession().setAttribute("guest", aGuest);
    	RequestDispatcher dispatcher = request.getRequestDispatcher("/JSP/guestHome.jsp");
    	dispatcher.forward(request, response);
	}

}
