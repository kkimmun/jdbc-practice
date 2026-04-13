package com.kh.species.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.kh.species.model.dto.SpeciesDto;

public class SpeciesDao {
	
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/*
	{
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	public SpeciesDao() {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	*/
	
	
	
	/*
	 * 오늘의 주인공 JDBC
	 * 
	 * JDBC용 인터페이스
	 * 
	 * - Connection : 데이터베이스와 세션(연결)을 나타내는 인터페이스
	 * -> 데이터베이스와의 통신 채널 연결
	 * -> SQL문을 실행을 위한 Statement객체 생성
	 * -> 트랜잭션 관리
	 * 
	 * 조심해야 할 점)
	 * 메모리 누수를 방지하기 위해 항상 close()
	 * 트랜잭션을 직접 관리한다면 DML수행 이후 반드시 commit / rollback
	 * 
	 * - statement : SQL문을 실행하고 결과를 받아오기 위한 인터페이스
	 * 1. statement : 정적 SQL문(완성된 SQL문) 실행
	 * 2. PreparedStatement : 파라미터화된 SQL문(미완성된 SQL문) 실행
	 * 3. CallableStatement : 저장 프로시저 호출
	 * 
	 * - ResultSet : Select문 실행결과를 담는 테이블 형태의 데이터 셋
	 * -> 커서(cursor)라는 개념을 이용해서 테이터에 접근
	 * -> 다양한 데이터타입 변환 메소드를 제공
	 * 
	 * 1) JDBC Driver 등록 : DBMS제조사에서 제공하는 클래스를 리플랙션을 통해 등록
	 * 2) Connection 객체 생성 : 접속정보를 전달하면서 Connection객체 반환
	 * 3) Statement 객체 생성 : Connection 객체를 이용해서 생성
	 * 4) SQL문을 전달하며 실행 :
	 * > SELECT -> executeQuery() 호출
	 * > DML    -> executeUpdate() 호출
	 * 5) 결과받기
	 * > SELECT -> ResultSet(조회된 데이터들이 테이블모양으로 반환) 객체로 받기
	 * > DML    -> int(처리된 행 수)로 받기
	 * 6)
	 * > SELECT -> ResultSet에 담겨있는 데이터를 하나하나 뽑아서 가공
	 * > DML    -> 트랜잭션을 수동으로 처리한다면 commit / rollback
	 * 7) 자원반납 -> close() -> 생성의 역순으로
	 * 8) 결과값 반환
	 * > SELECT -> 6에서 만든거
	 * > DML    -> 처리된 행 수
	 */
	public int save(SpeciesDto sd) {
		
		// 0) 필요한 변수 선언 및 null 값으로 초기화
		Connection conn = null; // DB서버와의 연결 정보를 담는 객체
		Statement stmt = null; // SQL문 실행 후 결과를 받는 객체
		int result = 0; // DML 수행 후 결과를 받기 위한 변수
		
		
		// SQL문(정적인 형태)
		/*
		 * INSERT INTO SPECIES
		 * 	VALUES ('S' || SEQ_SPECIES.NEXTVAL, 
		 * 			'사용자가 입력한 값', 
		 * 			'사용자가 입력한 값')
		 */
		String sql = "INSERT INTO SPECIES "
						+"VALUES ('S' || SEQ_SPECIES.NEXTVAL"
						+ ",'" + sd.getSpeciesName() + "'"
						+ ",'" + sd.getSpeicesClass() + "')";
		// System.out.println(sql);
		// SQL에 문법적인 문제가 존재한다
		// SQLSyntaxErrorException이 발생함
		
		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 1. 패키지 경로 / 클래스식별자에서 오타가 날 경우
			// 2. 프로젝트에 라이브러리를 추가하지 않아서 진짜로 클래스가 없는 경우
			// -> ClassNotFoundException
			
			// 2) Connection 객체생성(URL, USERNAME, PASSWORD)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
										"C##SG",
										"SG");
			// 느낌만(비유) new Connection("URL","계정명","비밀번호")
			/* 1. URL을 잘못 적었을 수 있음
			 * 2. 서버가 안 열려(켜저) 있을 수 있음
			 * 3. 계정명을 잘못 적었을 수 있음
			 * 4. 비밀번호를 잘못 적었을 수 있음
			 * 5. 접속 권한을 부여받지 못했을 경우
			 * 6. 비밀번호 갱신을 안했을 수 있음
			 * -> SQLException이 발생
			 */
			
			// AutoCommit 끄기
			conn.setAutoCommit(false);
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL문 실행 요청 및 응답 반환
			result = stmt.executeUpdate(sql);
			/* INSERT 시 값에 문제가 있을 수 있음
			 * 자료형이 맞지 않음
			 * 제약조건에 위배
			 * 데이터의 크기가 컬럼의 크기보다 큼
			 * SQLException이 발생
			 */
			
			// 6) 트랜잭션처리
			if(result > 0) {
				conn.commit();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {}
		}
		// DML 수행 결과 반환
		return result;
		
	}

	public List<SpeciesDto> findAll() {
		// 0) 필요한 변수 선언
		// Connetection, Statement
		Connection conn = null; // 연결된 DB정보
		Statement stmt = null; // SQL문 실행 + 응답 반환
		ResultSet rset = null;
		List<SpeciesDto> list = new ArrayList();
		
		String sql = """
					SELECT
							SPECIES_ID,
							SPECIES_NAME,
							SPECIES_CLASS
						FROM
							SPECIES
							ORDER
							BY
							SPECIES_ID DESC
				""";
		
		try {
			// 1) JDBC Driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			
			// 2) Connection 객체 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
					"C##SG",
					"SG");
			
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			
			// 4, 5) SQL(SELECT)문 실행 후 응답 ResultSet에 대입
			rset = stmt.executeQuery(sql);
			
			// 6) Mapping
			// 서로 다른 형태의 데이터 모델 간의 연결을 정의하는 과정
			// 관계형 데이터베이스 == 테이블 형태
			// 구조를 맞춰주는 작업
			// 자바 == 객체 형태 
			
			// 현재 조회 결과는 ResultSet에 담겨있음
			// => 한 행씩 접근해서 데이터를 뽑아서 => VO/DTO객체의 필드에 담기
			// rset.next()
			// 커서를 한 줄 아래로 옮긴 뒤 존재한다 true / 없다 false
			while(rset.next()) {
				
				// 현재 rset의 커서가 가르키고 있는 행의 데이터를
				// 하나하나씩 뽑아서 SpeciesDTO 객체의 필드에 대입
				SpeciesDto sd = new SpeciesDto();
				
				// ResultSet객체로부터
				// 어떤 컬럼의 값을 뽑을건지 메소드를 호출하면서 컬럼명을 명시
				// rset.getInt(컬럼명) : 컬럼값이 정수여서 int형으로 매핑할 때
				// rset.getString(컬럼명) : 문자열형값을 String매핑할 때
				// rset.getDate(컬럼명) : 날짜형 값을 java.sql.Date로 매핑
				// 컬렴명 : 대소문자를 가리지 않음
				// 컬럼명 말고 컬럼의 순번 / 별칭으로 가능함
				// 권장사항 : 컬럼명으로 작성하고 대문자로 작성
				
				// System.out.println(rset.getString("SPECIES_NAME"));
				// String speciesClass = rset.getString("SPECIES_CLASS");
				// System.out.println("클래스명 : " + speciesClass);
				sd.setSpeciesId(rset.getString("SPECIES_ID"));
				sd.setSpeciesName(rset.getString("SPECIES_NAME"));
				sd.setSpeicesClass(rset.getString("SPECIES_CLASS"));
				// System.out.println(sd);
				// 컬럼명이 오타났을 때 SQLException이 발생
				// 테이블에 직접접근해서 컬럼값을 뽑는 것이 아니고
				// ResultSet에 조회된 결과값을 뽑아내는 것
				
				// 한 행에 모든 컬럼값을
				// 각각의 필드에 담아 DTO객체로 옮겨 담으면 끝
				
				// 몇 개?? => 조회된 개수만큼 싹다 돌려보내야함
				// 조회 결과가 몇행일지 특정짓기가 어려움
				// 여러 주소값을 담아줄 저장소 ==> List
				// List<SpeciesDto> list = new ArrayList();
				
				list.add(sd);
				// return list;
			}
			//return list;
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			// 7) 다 쓴 JDBC용 객체 자원반납(생성의 역순으로)
			
			
			try {
				rset.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			// return list
			// 8) 매핑된 객체들 반환
			// 조회결과물을 매핑해놓은 DTO객체들의 주소값을 요소로 가지고 있는
			// List의 주소값을 반환
			return list;
			
		}
	
		
	}

	public int update(SpeciesDto sd) {
		// UPDATE -> updatedRows -> int
		int result = 0;
		String sql = "UPDATE "
							+ "SPECIES "
						+ "SET "
							+ "SPECIES_NAME = '" + sd.getSpeciesName() + "', "
							+ "SPECIES_CLASS = '" + sd.getSpeicesClass() + "'"
						+ "WHERE "
								+ "SPECIES_ID = '" + sd.getSpeciesId() + "'";
		
		// Class.forName("oracle.jdbc.driver.OracleDriver");
		
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
														  "C##SG",
														  "SG");
			Statement stmt = conn.createStatement()){
			result = stmt.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public int delete(String speciesId) {
		int result = 0;
		String sql = "Delete "
						+"FROM "
						+"SPECIES "
						+"WHERE "
							+"SPECIES_ID = '" + speciesId + "'";
		
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
															"C##SG",
															"SG");
				Statement stmt = conn.createStatement()){
			result = stmt.executeUpdate(sql);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}
	
	public SpeciesDto findById(String speciesId) {
		SpeciesDto sd = null;
		
		String sql ="""
					SELECT
							SPECIES_ID, 
							SPECIES_NAME, 
							SPECIES_CLASS
						FROM
							SPECIES
						WHERE
							SPECIES_ID =
				""";
		
		sql += "'" + speciesId + "'";
		
		
		try(Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
				"C##SG",
				"SG");
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(sql)){
				
			// 6 ) 매핑
			// 조회결과가 담긴 ResultSet객체에서
			// 조회결과가 존재하면 DTO객체의 필드에 옮겨담기
			// ID가지고 검색 => UNIQUE => 한 행
			if(rset.next()) {
				sd = new SpeciesDto(rset.getString("SPECIES_ID"),
									rset.getString("SPECIES_NAME"),
									rset.getString("SPECIES_CLASS"));
			}
			
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		return sd;
	}
	
	public List<SpeciesDto> findByKeyword(String keyword) {
		
		// 돌아갈 때
		List<SpeciesDto> list = new ArrayList();
		
		String sql = "SELECT "
						+ "SPECIES_ID, "
						+ "SPECIES_NAME, "
						+ "SPECIES_CLASS "
						+ "FROM "
							+"SPECIES "
						+ "WHERE "
							+ "SPECIES_NAME LIKE '%" + keyword + "%' "
						+ "ORDER "
							+"BY "
								+"SPECIES_ID DESC";
		
		try(Connection conn = 
				DriverManager.getConnection("jdbc:oracle:thin:@127.0.0.1:1521:XE",
											"C##SG",
											"SG");
				Statement stmt = conn.createStatement();
				ResultSet rset = stmt.executeQuery(sql)){
				
				while(rset.next()) {
					list.add(new SpeciesDto(rset.getString("SPECIES_ID"),
											rset.getString("SPECIES_NAME"),
											rset.getString("SPECIES_CLASS"))
							);
				}
				
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	

}
