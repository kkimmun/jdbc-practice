package com.kh.member.view;

import java.util.List;
import java.util.Scanner;

import com.kh.member.controller.MemberController;
import com.kh.member.exception.DuplicateMemberIdException;
import com.kh.member.exception.MemberIdTooLargeException;
import com.kh.member.model.dto.BoardDto;
import com.kh.member.model.dto.LoginResponse;
import com.kh.member.model.dto.MemberDto;

public class MemberView {

	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	private LoginResponse lr = null;
	/*
	 * 오늘의 실습 겸 숙제
	 * 
	 * 나만의 테이블 삼종세트
	 * JDBC 오늘버번(driver.properties분리, 매퍼.xml분리)으로
	 * CRUD 구현하기(할 수 있다면 JOIN도 해보기)
	 */
	
	public void mainMenu() {
		while(true) {
			System.out.println();
			System.out.println("회원서비스에 오신것을 환영합니다.");
			if(lr != null) {
				System.out.println("1. 로그아웃");
				System.out.println("2. 게시판 이동");
			} else {
				System.out.println("1. 회원가입");
				System.out.println("2. 로그인");
			}
			System.out.println("0. 프로그램 종료");
			System.out.print("원하시는 메뉴를 선택해주세요 > ");
			String menu = sc.nextLine();
			
			if(lr != null) {
				switch(menu) {
				case "1" : logout(); break;
				case "2" : boardMenu(); break;
				case "0" : return;
				}
			} else {
				switch(menu) {
				case "1" : signUp(); break;
				case "2" : login(); break;
				case "0" : return;
				}
			}
		}
	}
	
	private void logout() {
		lr = null;
		System.out.println("로그아웃 성공");
	}
	
	private void boardMenu() {
		while(true) {
			System.out.println("게시판 서비스입니다.");
			System.out.println("1. 게시글 작성");
			System.out.println("2. 게시글 전체 조회");
			System.out.println("3. 게시글 상세 조회");
			System.out.println("4. 회원서비스로 돌아가기");
			System.out.print("메뉴선택 > ");
			String menu = sc.nextLine();
			
			switch(menu) {
			case "1" : insertBoard(); break;
			case "2" : selectBoardList(); break;
			case "3" : selectBoard(); break;
			case "0" : return;
			}
		}
	}
	
	private void selectBoard() {
		System.out.println();
		System.out.println("게시글 상세보기 서비스 입니다.");
		System.out.println();
		selectBoardList();
		System.out.print("상세보기를 원하시는 게시글 번호를 입력해주세요 > ");
		String boardNo = sc.nextLine();
		BoardDto board = mc.selectBoard(boardNo);
		if(board != null) {
			System.out.println("=============================");
			System.out.println(board.getBoardNo() + "번 게시글");
			System.out.println("-----------------------------");
			System.out.println("제목 : " + board.getBoardTitle());
			System.out.println("-----------------------------");
			System.out.println("내용 : " + board.getBoardContent());
			System.out.println("-----------------------------");
			System.out.println("작성자 : " + board.getBoardWriter());
			System.out.println("=============================");
		} else {
			System.out.println("존재하지 않는 게시글 번호입니다.");
		}
		
	}

	private void selectBoardList() {
		System.out.println("게시글 전체 조회 서비스입니다.");
		List<BoardDto> boards = mc.selectBoardList();
		if(boards.isEmpty()) {
			System.out.println("게시글이 존재하지 않습니다.");
		} else {
			for(BoardDto bd : boards) {
				System.out.println("==================================================");
				System.out.println("게시글 번호 : " + bd.getBoardNo() + ", 게시글 제목 : " + bd.getBoardTitle() + ", 작성자 : " + bd.getBoardWriter() );
				System.out.println("==================================================");
			}
		}
	}

	private void insertBoard() {
		// 먼저 글을 작성할 수 있는 권한이 있는지 체크
		int result = mc.selectInsertCount(lr.getMemberId());
		if(5 > result) {
			System.out.println("게시글 작성 권한이 없습니다.");
			return;
		} 
		
		System.out.println("게시글 작성 서비스입니다.");
		System.out.print("게시글 제목을 입력하세요 > ");
		String title = sc.nextLine();
		System.out.print("게시글 내용을 입력하세요 > ");
		String content = sc.nextLine();
		
		int insertResult = mc.insertBoard(new BoardDto(title, content, lr.getMemberId()));
		if(insertResult > 0) {
			System.out.println("게시글 작성 성공~");
		} else {
			System.out.println("게시글 작성 실패...");
		}
	}
	
	private void signUp() {
		
		System.out.println("회원가입 서비스입니다.");
		String memberId;
		while(true) {
			System.out.print("사용하실 아이디를 입력해주세요 > ");
			memberId = sc.nextLine();
			boolean result = mc.checkId(memberId);
			if(!result) {
				break;
			}
			System.out.println("이미 존재하는 아이디입니다.");
		}
		System.out.print("사용하실 비밀번호를 입력해주세요 > ");
		String memberPwd = sc.nextLine();
		System.out.print("사용하실 이름을 입력해주세요 > ");
		String memberName = sc.nextLine();
		
		try {
			int result = mc.signUp(new MemberDto(memberId, memberPwd, memberName));
			if(result > 0) {
				System.out.println("회원가입 성공 ~ ");
			} else {
				System.out.println("회원가입 실패 ㅠㅠ");
			}
		} catch(MemberIdTooLargeException e) {
			System.err.println("아이디 깁니다용~~ 짧게 다시 시도하세요");
		} catch(DuplicateMemberIdException e) {
			System.err.println("이미 존재하는 아이디입니다. 다른 아이디로 시도하세요!");
		}
	}
	
	private void login() {
		// 사용자에게 아이디 / 비밀번호를 입력받고
		// JDBC_MEMBER 테이블에서 조건을 통해 조회하는 메소드
		System.out.print("아이디를 입력해주세요 > ");
		String memberId = sc.nextLine();
		System.out.print("비밀번호를 입력해주세요 > ");
		String memberPwd = sc.nextLine();
		
		MemberDto loginMember = new MemberDto();
		loginMember.setMemberId(memberId);
		loginMember.setMemberPwd(memberPwd);
		LoginResponse lr = mc.login(loginMember);
		
		if(lr != null) {
			System.out.println("로그인에 성공~");
			this.lr = lr;
		} else {
			System.out.println("로그인 실패..");
		}
	}
}
