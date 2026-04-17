package com.kh.keeper.model.service;
import java.sql.Connection;
import java.util.List;
import java.util.Map;

import com.kh.keeper.common.JdbcTemplate;
import com.kh.keeper.model.dao.KeeperDao;
/*
 * 비즈니스 로직 작성(의사결정코드) -> 데이터 가공, 중복체크, 연산처리, 암호화 등등
 * 트랜잭션관리
 * 여러 DAO 조합
 * 예외 처리 및 예외 발생
 * 보안 및 권한 검사
 */
import com.kh.keeper.model.dto.KeeperDto;
import com.kh.keeper.model.vo.Keeper;

public class KeeperService {
	/*
	 * Service : Connection 을 생성해서 DAO로 전달 +
	 *           만약 SQL문을 수행하는데 필요한 값이 있다면 Controller 로부터
	 *           전달받아서 Connection 과 같이 넘겨줄 것 +
	 *           DAO에서 DB작업이 끝나면 Service 단에서 결과에 따른 트랜잭션 처리도 진행
	 *           -> DAO에는 순수하게 SQL문을 처리하는 부분만 감겨놓을 것
	 */
	public int insertKeeper(KeeperDto kd) {
		// DAO 호출 후 결과값 Controller 로 반환
		Connection conn = JdbcTemplate.getConnection();
		int result = new KeeperDao().insertKeeper(conn, kd);
		if(result > 0) {
			JdbcTemplate.commit(conn);	
		}
		JdbcTemplate.close(conn);
		return result;
	}

	public List<Keeper> selectKeeperList() {
		// 1. 커넥션 객체 받아오기
		Connection conn = JdbcTemplate.getConnection();
		// 2. 적절한 DAO단의 메소드 호출하기
		List<Keeper> keepers = new KeeperDao().selectKeeperList(conn);
		// 3. 할 일이 모두 끝난 커넥션 close
		JdbcTemplate.close(conn);
		return keepers;
	}

	public int updateKeeper(Map<String, String> keeper) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new KeeperDao().updateKeeper(conn, keeper);
		if(result > 0) {
			JdbcTemplate.commit(conn);
		}
		JdbcTemplate.close(conn);
		return result;
	}

	public int deleteKeeper(String keeperId) {
		Connection conn = JdbcTemplate.getConnection();
		int result = new KeeperDao().deleteKeeper(conn, keeperId);
		if(result > 0) {
			JdbcTemplate.commit(conn);
		}
		JdbcTemplate.close(conn);
		return result;
	}
}
