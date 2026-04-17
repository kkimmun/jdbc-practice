package com.kh.member.model.service;

import static com.kh.keeper.common.JdbcTemplate.close;
import static com.kh.keeper.common.JdbcTemplate.commit;
import static com.kh.keeper.common.JdbcTemplate.getConnection;

import java.sql.Connection;
import java.util.List;

import com.kh.member.exception.DuplicateMemberIdException;
import com.kh.member.exception.MemberIdTooLargeException;
import com.kh.member.model.dao.MemberDao;
import com.kh.member.model.dto.BoardDto;
import com.kh.member.model.dto.LoginResponse;
import com.kh.member.model.dto.MemberDto;

public class MemberService {

	public boolean checkId(String memberId) {
		Connection conn = getConnection();
		boolean result = new MemberDao().checkId(conn, memberId);
		close(conn);
		return result;
	}

	public int signUp(MemberDto memberDto) {
		if(memberDto.getMemberId().length() > 30) {
			throw new MemberIdTooLargeException("아이디가 너무 길어요");
		}
		// 모든검사를 다 넘어갔다고 가정
		Connection conn = getConnection();
		MemberDao md = new MemberDao();
		if(md.checkId(conn, memberDto.getMemberId())) {
			throw new DuplicateMemberIdException("중복아이디입니다.");
		}
		int result = md.signUp(conn, memberDto);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public LoginResponse login(MemberDto member) {
		Connection conn = getConnection();
		// INSERT 를 먼저하고 SELECT 를 한다.
		// SELECT 를 먼저하고 INSERT 를 한다.

		LoginResponse lr = new MemberDao().login(conn, member);
		// 로그인 성공시 lr -> memberId, memberName 필드에 값이 있는 객체 주소값
		// 로그인 실패시 lr -> null
		if(lr != null) {
			int result = new MemberDao().loginCheck(conn, member.getMemberId());
			if(result > 0) {
				commit(conn);
			}
		}
		close(conn);
		return lr;
	}

	public int selectInsertCount(String memberId) {
		Connection conn = getConnection();
		int result = new MemberDao().selectInsertCount(conn, memberId);
		close(conn);
		return result;
	}

	public int insertBoard(BoardDto boardDto) {
		Connection conn = getConnection();
		int result = new MemberDao().insertBoard(conn, boardDto);
		if(result > 0) {
			commit(conn);
		}
		close(conn);
		return result;
	}

	public List<BoardDto> selectBoardList() {
		Connection conn = getConnection();
		List<BoardDto> boards = new MemberDao().selectBoardList(conn);
		close(conn);
		return boards;
	}

	public BoardDto selectBoard(String boardNo) {
		Connection conn = getConnection();
		BoardDto board = new MemberDao().selectBoard(conn, Integer.parseInt(boardNo));
		close(conn);
		return board;
	}
}
