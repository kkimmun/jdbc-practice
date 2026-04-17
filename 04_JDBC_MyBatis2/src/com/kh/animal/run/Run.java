package com.kh.animal.run;

import com.kh.animal.view.AnimalView;

public class Run {
/*
 * SQL문을 따로 분리
 * SQL 분리 장점
 * 1. SQL 수정이 컴파일 없이 가능함
 *    코드가 아니라 XML만 수정하는거라서 운영중에서 SQL튜닝이 쉽다.
 * 2. 가독성이 좋다.
 * 3. 같은 SQL문의 재사용이 가능 여러 메소드에서 비슷한 SQL을 공유할 수 있음
 * 4. DBA(DB담당자)랑 분업이 쉬워짐. DBA가 자바를 몰라도 XML만 보고 고칠 수 있음
 *    
 */
	public static void main(String[] args) {
		new AnimalView().mainMenu();
		
		
	}

}
