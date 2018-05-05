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

import com.model.Guest;
import com.model.Task;
import com.sun.crypto.provider.RSACipher;


public class GuestDA {
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
	
	public static void add(Guest aGuest) {
		
		String sql = "INSERT INTO guest_table(guestid,name,password,avatar,nativelang,lang,diploma,score)" + "VALUES('" + aGuest.getGuestID() + "','" + aGuest.getName() + "','"+ aGuest.getPassword() + "','"  + aGuest.getAvatar() + "','" + aGuest.getNativelang() + "','" + aGuest.getLang() +"','" + aGuest.getDiploma() +  "','" + aGuest.getScore() +"')";
		try {
			aStatement.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
/*	public static Task findTaskbyID(int taskid)
	{	Task task = null;
		String sql = "SELECT text, filepath From task_table where taskid ="+taskid;
		try {
			ResultSet rs = aStatement.executeQuery(sql);
			boolean gotIt = rs.next();
			if(gotIt){
				task = new Task();
				task.setText(rs.getString(1));
				task.setTextFilePath(rs.getString(2));
			
			}
			rs.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		return task;
	}*/
	
	
	
	
	
	
	
	
	public static Guest find(String key) {
		Guest aGuest = null;
		String sql = "SELECT guestid, name, password, score, nbproposed, nbaccepted ,avatar,photo,nativelang,lang,diploma,level,certificated FROM guest_table WHERE guestid='" + key+"'";
		
		try{
			ResultSet rs = aStatement.executeQuery(sql);
			
			boolean gotIt = rs.next();
			if(gotIt){
				aGuest = new Guest();
				aGuest.setGuestID(rs.getString(1));
				aGuest.setName(rs.getString(2));
				aGuest.setPassword(rs.getString(3));
				aGuest.setScore(rs.getInt(4));
				aGuest.setNbProposedTasks(rs.getInt(5));
				aGuest.setNbAcceptedTasks(rs.getInt(6));
				aGuest.setAvatar(rs.getString(7));
				aGuest.setPhoto(rs.getString(8));
				aGuest.setNativelang(rs.getString(9));
				aGuest.setLang(rs.getString(10));
				aGuest.setDiploma(rs.getString(11));
				aGuest.setLevel(rs.getInt(12));
				aGuest.setCertificated(rs.getInt(13));
			}
			rs.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return aGuest;
	}
	
	public static void addNewTask(Task t){
		try {
			pstmt = aConnection.prepareStatement("INSERT INTO task_table (name, sourceid, destinationid, reward, submittime, duetime,sourcelang,targetlang, filepath,filetype,text) VALUES(?,?,?,?,?,?,?,?,?,?,?)");
			
			pstmt.setString(1, t.getName());
			pstmt.setString(2, t.getSourceID());
			pstmt.setString(3, t.getDestinationID());
			pstmt.setFloat(4, t.getReward());
			pstmt.setTimestamp(5, new Timestamp(t.getSubmitTime().getTime()));
			pstmt.setTimestamp(6, new Timestamp(t.getDueTime().getTime()));
			pstmt.setString(7, t.getSourceLang());
			pstmt.setString(8, t.getTargetLang());
			pstmt.setString(9, t.getTextFilePath());
			pstmt.setInt(10, t.getFileType());
			pstmt.setString(11, t.getText());	
			
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
    public static ArrayList<Guest> findAllGuest()
    {
    	ArrayList<Guest> guestList = new ArrayList<Guest>();
    	String sql ="SELECT * FROM guest_table ";
    	
    	
    	
    	return guestList;
    }
	public static ArrayList<Task> findProposedTasks(String gID) {
		ArrayList<Task> tasklist = new ArrayList<Task>();
		
		String sql = "SELECT taskid, name, destinationid, reward, accepted, finished, submittime, duetime, finishedtime, text ,filepath ,filetype,answercount FROM task_table WHERE sourceid='" + gID+"'";
		
		try{
			ResultSet rs = aStatement.executeQuery(sql);
			
			while(rs.next()){
				Task t = new Task();
				t.setTaskID(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setSourceID(gID);
				t.setDestinationID(rs.getString(3));
				t.setReward(rs.getFloat(4));
				t.setAccepted(rs.getBoolean(5));
				t.setFinished(rs.getBoolean(6));
				t.setSubmitTime(new Date(rs.getTimestamp(7).getTime()));
				t.setDueTime(new Date(rs.getTimestamp(8).getTime()));
				t.setTextFilePath(rs.getString("filepath"));
				t.setFileType(rs.getInt("filetype"));
				t.setAnswerCount(rs.getInt("answercount"));
				/*if(t.isFinished())
					t.setFinishedTime(new Date(rs.getTimestamp(9).getTime()));*/
				t.setText(rs.getString(10));
				tasklist.add(t);
			}
			
			rs.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasklist;
	}
	
	public static ArrayList<Task> findReceivedTasks(String gID){
		ArrayList<Task> tasklist = new ArrayList<Task>();
		
		String sql = "SELECT * FROM task_table WHERE finished=0";
		
		try{
			ResultSet rs = aStatement.executeQuery(sql);
			
			while(rs.next()){
				Task t = new Task();
				t.setTaskID(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setSourceID(rs.getString(3));
				t.setDestinationID(rs.getString(4));
				t.setReward(rs.getFloat(5));
				t.setSubmitTime(new Date(rs.getTimestamp(6).getTime()));
				t.setDueTime(new Date(rs.getTimestamp(7).getTime()));
				t.setAccepted(rs.getBoolean(8));
				t.setFinished(rs.getBoolean(9));
				t.setSourceLang(rs.getString("sourcelang"));
				t.setTargetLang(rs.getString("targetlang"));
				t.setText(rs.getString("text"));
				t.setTextFilePath(rs.getString("filepath"));
				t.setFileType(rs.getInt("filetype"));
				//t.setFinishedTime(rs.getTimestamp(10));
				
				tasklist.add(t);
			}
			
			rs.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasklist;
	}
	
	public static ArrayList<Task> findAcceptedTasks(String gID){
		ArrayList<Task> tasklist = new ArrayList<Task>();
		
		String sql = "SELECT * FROM task_table WHERE  destinationid = '"+gID+"'";
		
		try{
			ResultSet rs = aStatement.executeQuery(sql);
			
			while(rs.next()){
				Task t = new Task();
				t.setTaskID(rs.getInt(1));
				t.setName(rs.getString(2));
				t.setSourceID(rs.getString(3));
				t.setDestinationID(rs.getString(4));
				t.setReward(rs.getFloat(5));
				t.setAccepted(rs.getBoolean(6));
				t.setFinished(rs.getBoolean(7));
				
				if(t.isFinished())
					t.setFinishedTime(new Date(rs.getTimestamp(10).getTime()));
				
				tasklist.add(t);
			}
			
			rs.close();
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return tasklist;
	}
	
	// Update all task related fields
	public static void updateTaskRelatedInfo(Guest aGuest) {
		try {
			pstmt = aConnection.prepareStatement("UPDATE guest_table SET score= ?, nbproposed=?, nbaccepted=? WHERE guestid ='"+aGuest.getGuestID()+"'");
			
			pstmt.setInt(1, aGuest.getScore());
			pstmt.setInt(2, aGuest.getNbProposedTasks());
			pstmt.setInt(3, aGuest.getNbAcceptedTasks());
			pstmt.executeUpdate();
			
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	// Set the task 'accepted' field to true
	public static void finishTask(int taskID){
		try {
			pstmt = aConnection.prepareStatement("UPDATE task_table SET finished=1 WHERE taskid="+taskID);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateScore(String guestid,int score)
	{
		try {
			
			pstmt = aConnection.prepareStatement("UPDATE guest_table SET score=? WHERE guestid = '"+guestid+"'");
			pstmt.setInt(1, score);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public static void updateAnswerCount(int taskid)
	{
		try {
			
			pstmt = aConnection.prepareStatement("UPDATE task_table SET answercount= answercount + 1 WHERE taskid = "+taskid);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void updatePhoto(String guestid,String photoName)
	{
		try {
			
			pstmt = aConnection.prepareStatement("UPDATE guest_table SET photo=? WHERE guestid = '"+guestid+"'");
			pstmt.setString(1, photoName);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void updateDestinationID(String guestid,int taskid)
	{
			try {
			
			pstmt = aConnection.prepareStatement("UPDATE task_table SET destinationid=? WHERE taskid = "+taskid);
			pstmt.setString(1, guestid);
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static void updateInfo(Guest guest)
	{
			try {
			
			pstmt = aConnection.prepareStatement("UPDATE guest_table SET name=?,password=?,nativelang=?,lang=? WHERE guestid = '"+guest.getGuestID()+"'");
			pstmt.setString(1, guest.getName());
			pstmt.setString(2, guest.getPassword());
			pstmt.setString(3, guest.getNativelang());
			pstmt.setString(4, guest.getLang());
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static boolean hasSameValue(String GuestID,String value){
		Guest.initialize();
		boolean result = false;

		ResultSet rs = null;
		try{
			pstmt = aConnection.prepareStatement("select * from guest_table where "+GuestID +"=?");
			pstmt.setString(1,value);
			rs = pstmt.executeQuery();
			if(rs.next())
				result = true;
			else {
				result = false;
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally{
			Guest.terminate();
		}
		
		return result;
	}

}
