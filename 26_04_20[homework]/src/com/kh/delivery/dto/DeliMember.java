package com.kh.delivery.dto;

import java.sql.Date;

public class DeliMember {
	
	private String memberNo;
	private String memberId;
	private String memberPw;
	private String memberName;
	private String Address;
	private Date enrollDate;
	
	
	
	
	
	public DeliMember(String memberId, String memberPw) {
		super();
		this.memberId = memberId;
		this.memberPw = memberPw;
	}
	@Override
	public String toString() {
		return "DeliMember [memberNo=" + memberNo + ", memberId=" + memberId + ", memberPw=" + memberPw
				+ ", memberName=" + memberName + ", Address=" + Address + ", enrollDate=" + enrollDate + "]";
	}
	public String getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(String memberNo) {
		this.memberNo = memberNo;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPw() {
		return memberPw;
	}
	public void setMemberPw(String memberPw) {
		this.memberPw = memberPw;
	}
	public String getMemberName() {
		return memberName;
	}
	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public Date getEnrollDate() {
		return enrollDate;
	}
	public void setEnrollDate(Date enrollDate) {
		this.enrollDate = enrollDate;
	}
	public DeliMember(String memberNo, String memberId, String memberPw, String memberName, String address,
			Date enrollDate) {
		super();
		this.memberNo = memberNo;
		this.memberId = memberId;
		this.memberPw = memberPw;
		this.memberName = memberName;
		Address = address;
		this.enrollDate = enrollDate;
	}
	public DeliMember() {
		super();
	}
	
}
