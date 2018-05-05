package com.model;

import java.sql.Connection;
import java.util.Date;

import com.dao.AnswerDA;

public class Answer {
	int answerid;
	int taskid;
	String targetid;


	String guestid;
	String traslation;
	Date time;
	int like;
	int accepted;
	public void answerAccept(int answerid)
	{
		AnswerDA.answerAccepted(answerid);
	}
	public String getTargetid() {
		return targetid;
	}
	public void setTargetid(String targetid) {
		this.targetid = targetid;
	}
	public int getAccepted() {
		return accepted;
	}
	public void setAccepted(int accepted) {
		this.accepted = accepted;
	}
	public int getAnswerid() {
		return answerid;
	}
	public void setAnswerid(int answerid) {
		this.answerid = answerid;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	
	public int getTaskid() {
		return taskid;
	}
	public void setTaskid(int taskid) {
		this.taskid = taskid;
	}
	public String getGuestid() {
		return guestid;
	}
	public void setGuestid(String guestid) {
		this.guestid = guestid;
	}
	public String getTraslation() {
		return traslation;
	}
	public void setTraslation(String traslation) {
		this.traslation = traslation;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	public static Connection initialize(){
		return AnswerDA.initialize();
	}
	
	public static void terminate(){
		AnswerDA.terminate();
	}
	
	
	

}
