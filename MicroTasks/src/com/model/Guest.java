package com.model;

import java.sql.Connection;
import java.util.ArrayList;

import com.dao.GuestDA;

public class Guest {
	private String guestID;
	private String name;
	private String password;
	private String avatar;
	private String photo;


	private String nativelang;
	private String lang;
	private String diploma;
	private int certificated;
	private int score;
	private int level;
	
	


	private ArrayList<Task> proposedTaskList;
	private ArrayList<Task> acceptedTaskList;
	private ArrayList<Task> receivedTaskList;
	private ArrayList<Answer> receivedAnswerArrayList;
	

	private int nbProposedTasks = 0;
	private int nbAcceptedTasks = 0;

	/*
	 * Submit a new task to the system
	 */
	public String getNativelang() {
		return nativelang;
	}

	public void setNativelang(String nativelang) {
		this.nativelang = nativelang;
	}

	public String getLang() {
		return lang;
	}

	public void setLang(String lang) {
		this.lang = lang;
	}

	public String getDiploma() {
		return diploma;
	}

	public void setDiploma(String diploma) {
		this.diploma = diploma;
	}

	public int getCertificated() {
		return certificated;
	}

	public void setCertificated(int certificated) {
		this.certificated = certificated;
	}
	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	
	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public void submitTask() {
		
	}
	
	public String getTaskSolution(int taskID){
		return "";
	}
	
	public String getTaskStatus(int taskID){
		return "";
	}
	
	
	public static Connection initialize(){
		return GuestDA.initialize();
	}
	
	public static void terminate(){
		GuestDA.terminate();
	}
	
	public static Guest find(String key) {
		return GuestDA.find(key);
	}
	
	public void updateAnswerCount(int taskID)
	{
		GuestDA.updateAnswerCount(taskID);
	}
	
	public void updateScore(String guestid,int score)
	{
		GuestDA.updateScore(guestid, score);
	}
	public void updateDestinationID(String guestid,int taskid)
	{
		GuestDA.updateDestinationID(guestid, taskid);
	}
	public void updatePhoto(String guestid,String photo)
	{
		GuestDA.updatePhoto(guestid, photo);
	}
	public void updateInfo(Guest guest)
	{
		GuestDA.updateInfo(guest);
	}
	
	// To publish a new task
	public void addNewTask(Task t){
		GuestDA.addNewTask(t);

		this.nbProposedTasks += 1;
		GuestDA.updateTaskRelatedInfo(this);
	}
	// To accept a received task
	public void finishTask(int taskID){
		GuestDA.finishTask(taskID);
		
		this.nbAcceptedTasks += 1;
		GuestDA.updateTaskRelatedInfo(this);
		
		/*	Task tTmp = null;
		
		// Add this task to the accepted task list
		for(Task t : this.receivedTaskList){
			if(t.getTaskID() == taskID){
				tTmp = t;
				this.acceptedTaskList.add(t);
			}
		}
		
		// Remove this task from received task list
		if(!tTmp.equals(null))
			this.receivedTaskList.remove(tTmp);*/
	}
	
	public ArrayList<Task> findProposedTasks(){
		return GuestDA.findProposedTasks(this.guestID);
	}
	
	public ArrayList<Task> findReceivedTasks(){
		return GuestDA.findReceivedTasks(this.guestID);
	}
	
	public ArrayList<Task> findAcceptedTasks(){
		return GuestDA.findAcceptedTasks(this.guestID);
	}
	
	public String getGuestID() {
		return guestID;
	}

	public void setGuestID(String guestID) {
		this.guestID = guestID;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNbProposedTasks() {
		return nbProposedTasks;
	}

	public void setNbProposedTasks(int nbProposedTasks) {
		this.nbProposedTasks = nbProposedTasks;
	}

	public int getNbAcceptedTasks() {
		return nbAcceptedTasks;
	}

	public void setNbAcceptedTasks(int nbAcceptedTasks) {
		this.nbAcceptedTasks = nbAcceptedTasks;
	}

	public ArrayList<Task> getProposedTaskList() {
		return proposedTaskList;
	}

	public void setProposedTaskList(ArrayList<Task> proposedTaskList) {
		this.proposedTaskList = proposedTaskList;
	}

	public ArrayList<Task> getAcceptedTaskList() {
		return acceptedTaskList;
	}

	public void setAcceptedTaskList(ArrayList<Task> acceptedTaskList) {
		this.acceptedTaskList = acceptedTaskList;
	}

	public ArrayList<Task> getReceivedTaskList() {
		return receivedTaskList;
	}

	public void setReceivedTaskList(ArrayList<Task> receivedTaskList) {
		this.receivedTaskList = receivedTaskList;
	}
	public ArrayList<Answer> getReceivedAnswerArrayList() {
		return receivedAnswerArrayList;
	}

	public void setReceivedAnswerArrayList(ArrayList<Answer> receivedAnswerArrayList) {
		this.receivedAnswerArrayList = receivedAnswerArrayList;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
