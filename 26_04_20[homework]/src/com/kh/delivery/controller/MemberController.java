package com.kh.delivery.controller;

import com.kh.delivery.dto.DeliMember;
import com.kh.delivery.service.MemberService;

public class MemberController {
	
	private MemberService ms = new MemberService();
	
	public int signIn(DeliMember dm) {
		
		return ms.signIn(dm);
		
	}

	public void signUp() {
		
		ms.signUp();
	}
	
	
	
}
