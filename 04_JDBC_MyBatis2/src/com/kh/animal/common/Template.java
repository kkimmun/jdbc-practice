package com.kh.animal.common;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class Template {
	
	// Connection 객체 생성~ 하지 않고
	// sqlSession 객체를 만드는 메소드를 구현할 것
	
	public static SqlSession getSqlSeeion() {
		
		SqlSession sqlSession = null;
		// mybatis-config.xml 파일에 작성한 environment 요소의 내용을 읽어와서
		// 해당 DB와 연결된 SqlSessoin 객체 생성
		String config = "mybatis-config.xml";
		try {
			InputStream stream = Resources.getResourceAsStream(config);
			// 1단계 : SqlSessionFactoryBuiler 만들기
			// 만드는 법 : 기본생성자 호출
			sqlSession = new SqlSessionFactoryBuilder().build(stream).openSession();
			// 2단계 : SqlSessionFactory 만들기
			// 만드는 법 : Builer를 참조해서 메소드를 호출한다. SSFB.build(stream)
			
			// 3단계 : SqlSession 만들기
			// 만드는 법 : Factory를 참조해서 메소드를 호출한다.
			// .openSession();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sqlSession;
	}

}
