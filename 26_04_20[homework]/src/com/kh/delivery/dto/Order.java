package com.kh.delivery.dto;

import java.sql.Date;

public class Order {
	
	private int orderNo;
	private int memberNo;
	private int menuNo;
	private int quantity;
	private Date orderDate;
	private String status;
	
	// JOIN 결과를 담을 추가 필드
    private String memberName;   // MEMBER 조인
    private String restName;     // RESTAURANT 조인
    private String menuName;     // MENU 조인
    private int price;           // MENU 조인
	
	
	
	public Order() {
		super();
	}
	public Order(int orderNo, int memberNo, int menuNo, int quantity, Date orderDate, String status) {
		super();
		this.orderNo = orderNo;
		this.memberNo = memberNo;
		this.menuNo = menuNo;
		this.quantity = quantity;
		this.orderDate = orderDate;
		this.status = status;
	}
	public int getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(int orderNo) {
		this.orderNo = orderNo;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getMenuNo() {
		return menuNo;
	}
	public void setMenuNo(int menuNo) {
		this.menuNo = menuNo;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Date getOrderDate() {
		return orderDate;
	}
	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Order [orderNo=" + orderNo + ", memberNo=" + memberNo + ", menuNo=" + menuNo + ", quantity=" + quantity
				+ ", orderDate=" + orderDate + ", status=" + status + "]";
	}
	
	
	
	
}
