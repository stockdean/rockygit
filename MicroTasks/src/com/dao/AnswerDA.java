package com.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.print.PrintException;

import com.model.Answer;

public class AnswerDA {
	static Connection aConnection;
	static Statement aStatement;
	static PreparedStatement pstmt;
	
	public static Connection initialize(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			
			aConnection = DriverManager.getConnection("jdbc:mysql://localhost:3306/microtask?"
		            + "user=root&password=root");
			aStatement = aConnection.createStatement();
		}
		catch(SQLException ex){
			System.out.println("SQLException: " + ex.getMessage());
		    System.out.println("SQLState: " + ex.getSQLState());
		    System.out.println("VendorError: " + ex.getErrorCode());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return aConnection;
	}
	
	public static void terminate(){
		try{
			aStatement.close();
			aConnection.close();
		}
		catch(SQLException e){
			System.out.println(e);
		}
	}
	
	
	public static void addAnswer(Answer answer){
		Date date=new Date(System.currentTimeMillis());
		String sql = "INSERT INTO answer_table(taskid, guestid, targetid, translation, time)" + "VALUES('" + answer.getTaskid() + "','" + answer.getGuestid() + "','" + answer.getTargetid() +"','"+ answer.getTraslation() + "','"+ new Timestamp(date.getTime()) + "')";
		try {
			aStatement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static ArrayList<Answer> findAnswer(String targetid)
	{
		ArrayList<Answer> answers = new ArrayList<Answer>();
		String sql ="select * from answer_table where targetid = '"+targetid+"'";
		try {
			
			ResultSet rs = aStatement.executeQuery(sql);
			
			while(rs.next())
			{
				Answer answer = new Answer();
				answer.setAnswerid(rs.getInt(1));
				answer.setTaskid(rs.getInt(2));
				answer.setTargetid(rs.getString(3));
				answer.setGuestid(rs.getString(4));
				answer.setTraslation(rs.getString(5));
				answer.setTime(new Date(rs.getTimestamp(6).getTime()));
				answer.setLike(rs.getInt(7));
				answer.setAccepted(rs.getInt(8));
				answers.add(answer);
			
			}
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return answers;
	}
	public static void findAnswerCount(Answer answer)
	{	String sql="select count(*) from answer_table where taskid ="+answer.getTaskid();

		try {
			aStatement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public static void answerAccepted(int answerid)
	{
			try {
			
			pstmt = aConnection.prepareStatement("UPDATE answer_table SET accepted = 1 WHERE answerid = "+answerid);
			pstmt.executeUpdate();
			pstmt.close();
				} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
					}
	
		
	}
	

}

