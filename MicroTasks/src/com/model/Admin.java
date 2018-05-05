package com.model;

import java.util.ArrayList;

public class Admin {
	private String name;
	private int guestNum;
	private ArrayList<Guest> guestList;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getGuestNum() {
		return guestNum;
	}
	public void setGuestNum(int guestNum) {
		this.guestNum = guestNum;
	}
	public ArrayList<Guest> getGuestList() {
		return guestList;
	}
	public void setGuestList(ArrayList<Guest> guestList) {
		this.guestList = guestList;
	}

}
