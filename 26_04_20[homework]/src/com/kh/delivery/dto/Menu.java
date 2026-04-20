package com.kh.delivery.dto;

public class Menu {
	
	private String menuNo;
	private String restNo;
	private String menuName;
	private int number;
	private char soldOut;
	public Menu() {
		super();
	}
	public Menu(String menuNo, String restNo, String menuName, int number, char soldOut) {
		super();
		this.menuNo = menuNo;
		this.restNo = restNo;
		this.menuName = menuName;
		this.number = number;
		this.soldOut = soldOut;
	}
	public String getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(String menuNo) {
		this.menuNo = menuNo;
	}
	public String getRestNo() {
		return restNo;
	}
	public void setRestNo(String restNo) {
		this.restNo = restNo;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public char getSoldOut() {
		return soldOut;
	}
	public void setSoldOut(char soldOut) {
		this.soldOut = soldOut;
	}
	@Override
	public String toString() {
		return "Menu [menuNo=" + menuNo + ", restNo=" + restNo + ", menuName=" + menuName + ", number=" + number
				+ ", soldOut=" + soldOut + "]";
	}
	
	
	
	
	
}
