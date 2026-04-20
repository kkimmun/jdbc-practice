package com.kh.delivery.view;

import java.util.Scanner;

import com.kh.delivery.controller.MemberController;
import com.kh.delivery.dto.DeliMember;

public class DeliveryMenu {
	
	private Scanner sc = new Scanner(System.in);
	private MemberController mc = new MemberController();
	public void mainMenu() {
		
		while(true) {
		
		System.out.println("배달의 만족입니다.");
		System.out.println("원하시는 메뉴를 선택해주세요 >");
		System.out.println("1. 로그인");
		System.out.println("2. 회원가입");
		System.out.println("0. 종료하기");
		String menu = sc.nextLine();
		
		switch(menu) {
		case "1" : signIn(); break;
		case "2" : signUp(); break;
		
		
		case "0" : sc.close();return;
			}
		}
		
	}
	
	private void signIn() {
		System.out.print("아이디를 입력해주세요 > ");
		String id = sc.nextLine();
		System.out.print("\n비밀번호를 입력해주세요 >");
		String pw = sc.nextLine();
		
		DeliMember dm = new DeliMember(id, pw);
		
		int result = mc.signIn(dm);
		
		if(result > 0) {
			System.out.println("로그인 성공!");
		}else {
			System.out.println("로그인 실패...");
		}
	}
	
	private void signUp() {
		mc.signUp();
	}
	
	
	
}
