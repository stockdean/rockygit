package com.model;

import java.util.ArrayList;
import java.util.Date;



public class Task {
	private int taskID;
	private String name;
	private String sourceID; // The guest who proposed this task
	private String destinationID; // The guest that the task was given to 
	private float reward; // The amount of money

	private Date submitTime;
	private Date dueTime;
	private Date finishedTime;
	
	private boolean accepted;
	private boolean finished;
	

	private String textFilePath;
	private String sourceLang;
	private String targetLang;
	private String text;
	
	private int wordCount; // Total word count in the file
	private int answerCount;

	private int fileType; // The type can be Article, Book, Brochure, Website, etc
	
	private ArrayList<Answer> receivedAnswerArrayList;
	
	
	

		
	public ArrayList<Answer> getReceivedAnswerArrayList() {
		return receivedAnswerArrayList;
	}
	public void setReceivedAnswerArrayList(ArrayList<Answer> receivedAnswerArrayList) {
		this.receivedAnswerArrayList = receivedAnswerArrayList;
	}
	
	public int getFileType() {
		return fileType;
	}

	public void setFileType(int fileType) {
		this.fileType = fileType;
	}
	public int getAnswerCount() {
		return answerCount;
	}

	public void setAnswerCount(int answerCount) {
		this.answerCount = answerCount;
	}
	public int getTaskID() {
		return taskID;
	}

	public void setTaskID(int taskID) {
		this.taskID = taskID;
	}

	public Date getSubmitTime() {
		return submitTime;
	}

	public void setSubmitTime(Date submitTime) {
		this.submitTime = submitTime;
	}

	public Date getDueTime() {
		return dueTime;
	}

	public void setDueTime(Date dueTime) {
		this.dueTime = dueTime;
	}

	public Date getFinishedTime() {
		return finishedTime;
	}

	public void setFinishedTime(Date finishedTime) {
		this.finishedTime = finishedTime;
	}

	public String getSourceID() {
		return sourceID;
	}

	public void setSourceID(String sourceID) {
		this.sourceID = sourceID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isAccepted() {
		return accepted;
	}

	public void setAccepted(boolean accepted) {
		this.accepted = accepted;
	}

	public boolean isFinished() {
		return finished;
	}

	public void setFinished(boolean finished) {
		this.finished = finished;
	}

	public String getDestinationID() {
		return destinationID;
	}

	public void setDestinationID(String destinationID) {
		this.destinationID = destinationID;
	}

	public float getReward() {
		return reward;
	}

	public void setReward(float reward) {
		this.reward = reward;
	}
	
	public String getTextFilePath() {
		return textFilePath;
	}

	public void setTextFilePath(String textFilePath) {
		this.textFilePath = textFilePath;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public void setSourceLang(String sourceLang)
	{
		this.sourceLang = sourceLang; 
	}
	public String getSourceLang()
	{
		return sourceLang;
	}
	public void setTargetLang(String targetLang)
	{
		this.targetLang = targetLang; 
	}
	public String getTargetLang()
	{
		return targetLang;
	}
	public static Task findTask(int taskid,Guest guest)
	{	
		ArrayList <Task> myTaskList= guest.getProposedTaskList();
		Task task = null;
		for(int i = 0;i < myTaskList.size();i++)
		{
			if(myTaskList.get(i).getTaskID()==taskid)
			{
				 task = myTaskList.get(i);
				 return task;
			}
			
		}
		
		
		 return task;	
		
	}

}
