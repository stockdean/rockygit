package com;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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
@WebServlet("/SaveTranslationController")
public class  SaveTranslationController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		req.setCharacterEncoding("utf-8");
		resp.setCharacterEncoding("utf-8");
		Answer answer = new Answer();
		Guest guest = (Guest)req.getSession().getAttribute("guest");
		String translation = "Done";
		String targetid = (String)req.getSession().getAttribute("srcUser");
		int taskid = (int)req.getSession().getAttribute("taskid");
		String guestid = guest.getGuestID();
		
		answer.setTaskid(taskid);
		answer.setGuestid(guestid);
		answer.setTraslation(translation);
		answer.setTargetid(targetid);
		
		
		
		
		String result = req.getParameter("dstfile");
		String dstPath = req.getParameter("dstPath");
		try {
             BufferedWriter bw = new BufferedWriter(new FileWriter(dstPath));
	         bw.write(result);
	         bw.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
		
		Answer.initialize();
		AnswerDA.addAnswer(answer);
		Answer.terminate();
		
		Guest.initialize();
		guest.updateAnswerCount(taskid);
		Guest.terminate();
		
    	RequestDispatcher dispatcher = req.getRequestDispatcher("/JSP/guestHome.jsp");
    	dispatcher.forward(req, resp);
    	return;
	}

}
