package com.kh.delivery.dao;

import org.apache.ibatis.session.SqlSession;

import com.kh.delivery.dto.DeliMember;

public class MemberDao {
	public int signIn(SqlSession session, DeliMember dm) {
		
		return session.insert("MemberMapper.signIn",dm);
		
	}

	public void signUp(SqlSession session) {
		
		session.selectList("memberMapper.signUp");
		
	}
}
