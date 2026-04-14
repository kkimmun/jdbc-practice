package com.kh.member.model.dto;

public class MemberDto {
	private String memberId;
	private String memberPwd;
	private String membername;
	public MemberDto() {}
	public MemberDto(String memberId, String memberPwd, String membername) {
		this.memberId = memberId;
		this.memberPwd = memberPwd;
		this.membername = membername;
	}
	public String getMemberId() {
		return memberId;
	}
	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}
	public String getMemberPwd() {
		return memberPwd;
	}
	public void setMemberPwd(String memberPwd) {
		this.memberPwd = memberPwd;
	}
	public String getMembername() {
		return membername;
	}
	public void setMembername(String membername) {
		this.membername = membername;
	}
	@Override
	public String toString() {
		return "MemberDto [memberId=" + memberId + ", memberPwd=" + memberPwd + ", membername=" + membername + "]";
	}
	
	
	
}
