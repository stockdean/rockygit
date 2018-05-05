package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.AnswerDA;
import com.model.Answer;
import com.model.Guest;
@WebServlet("/AddAnswerController")
public class AddAnswerController extends HttpServlet {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		req.setCharacterEncoding("UTF-8");
		Answer answer = new Answer();
		Guest guest = new Guest();
		
		String taskidstring = req.getParameter("a_taskid");
		String guestid = req.getParameter("a_guestid");
		String translation  = req.getParameter("a_translation");
		String targetid = req.getParameter("a_targetid");
		int taskid = Integer.parseInt(taskidstring);
		
		answer.setTaskid(taskid);
		answer.setGuestid(guestid);
		answer.setTraslation(translation);
		answer.setTargetid(targetid);
		
		Answer.initialize();
		AnswerDA.addAnswer(answer);
		Answer.terminate();
		
		Guest.initialize();
		guest.updateAnswerCount(taskid);
		Guest.terminate();
		
		
		req.getRequestDispatcher("/JSP/guestHome.jsp").forward(req, resp);
		
	}

}
