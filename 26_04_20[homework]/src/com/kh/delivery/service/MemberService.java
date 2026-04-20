package com.kh.delivery.service;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.common.Template;
import com.kh.delivery.dao.MemberDao;
import com.kh.delivery.dto.DeliMember;

public class MemberService {
	
	SqlSession session = Template.getSqlSession();
	
	public int signIn(DeliMember dm) {
		
		int result = new MemberDao().signIn(session, dm);
		
		return result;
		
	}

	public void signUp() {
		
		new MemberDao().signUp(session);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
