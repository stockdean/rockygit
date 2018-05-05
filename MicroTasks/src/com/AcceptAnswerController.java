package com;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.GuestDA;
import com.model.Answer;
import com.model.Guest;
import com.model.Task;
@WebServlet("/AcceptAnswerController")
public class AcceptAnswerController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html;charset=utf-8");
	
		int answerid =Integer.parseInt(req.getParameter("answerid")) ;
		int taskid = Integer.parseInt(req.getParameter("taskid"));
		String sourceid = req.getParameter("guestid");
		
		Answer answer = new Answer();
		Guest guest = (Guest)req.getSession().getAttribute("guest");
		
		Task task = Task.findTask(taskid, guest);

		float reward = task.getReward();
		
		Guest.initialize();
		Guest sourceGuest = Guest.find(sourceid);
		Guest.terminate();
		
		Guest.initialize();
		guest.updateDestinationID(sourceid, taskid);
		Guest.terminate();
		
		
		
		
		Answer.initialize();
		
		answer.answerAccept(answerid);
		Answer.terminate();
		
		int score = sourceGuest.getScore();
		score = (int)(score + reward);
		
	/*	Guest.initialize();
		guest.updateScore(sourceid, score);
		Guest.terminate();*/
		
		Guest.initialize();
		sourceGuest.setScore(score);
		sourceGuest.finishTask(taskid);
		if(guest.getNbProposedTasks() > 0){
			guest.setProposedTaskList(guest.findProposedTasks());
		}
		
		guest.setReceivedTaskList(guest.findReceivedTasks());
		
		if(guest.getNbAcceptedTasks() > 0){
			guest.setAcceptedTaskList(guest.findAcceptedTasks());


		}
		
		// Put Guest object into session
		
		Guest.terminate();
		req.getSession().setAttribute("guest", guest);
		req.getRequestDispatcher("/JSP/guestHome.jsp").forward(req, resp);
		

	}
	


}
