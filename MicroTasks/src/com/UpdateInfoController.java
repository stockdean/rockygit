package com;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Guest;
@WebServlet("/UpdateInfoController")
public class UpdateInfoController extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   @Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		   
		   req.setCharacterEncoding("utf-8");
		   resp.setCharacterEncoding("utf-8");
		  String name = req.getParameter("my_name");
		  String password = req.getParameter("my_pwd");
		  String nativelang = req.getParameter("my_nativelang");
		  String lang = req.getParameter("my_lang");
		  
		  Guest guest = (Guest)req.getSession().getAttribute("guest");
		  guest.setName(name) ;
		  guest.setPassword(password);
		  guest.setNativelang(nativelang);
		  guest.setLang(lang);
		  
		  Guest.initialize();
		  guest.updateInfo(guest);
		  Guest.terminate();
		  
		  RequestDispatcher dispatcher = req.getRequestDispatcher("/JSP/guestHome.jsp");
		  dispatcher.forward(req, resp);
		  
	}

}
