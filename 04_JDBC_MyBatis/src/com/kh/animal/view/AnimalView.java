package com.kh.animal.view;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import com.kh.animal.controller.AnimalController;
import com.kh.animal.controller.KeeperController;
import com.kh.animal.model.dto.AnimalDto;
import com.kh.animal.model.dto.KeeperDto;

public class AnimalView {

	private Scanner sc = new Scanner(System.in);
	private AnimalController ac = new AnimalController();
	/*
	 * ANIMAL
	 * 
	 * insertAnimal
	 * selectAnimalList
	 * selectAnimal
	 * updateAnimal
	 * deleteAnimal
	 * 
	 * ======================
	 * 
	 * save
	 * findAll
	 * findByXXX
	 * update
	 * deleteByXXX
	 */
	public void mainMenu() {
		
		while(true) {
			System.out.println();
			System.out.println("동물 관리 시스템에 오신것을 환영합니다~");
			System.out.println("1. 동물 추가하기");
			System.out.println("2. 동물 전체조회");
			System.out.println("3. 동물 단일 조회하기");
			System.out.println("4. 동물 이름 키워드로 조회하기");
			System.out.println("5. 사육사 및 담당동물 조회하기");
			System.out.println("6. 사육사 검색하기");
			System.out.println("7. 동물 정보 수정하기");
			System.out.println("8. 동물 삭제하기");
			System.out.println("0. 프로그램 종료");
			System.out.print("메뉴 선택 > ");
			String menu = sc.nextLine();
			
			switch(menu) {
			case "1" : save(); break;
			case "2" : findAll(); break;
			case "3" : findById(); break;
			case "4" : findByKeyword(); break;
			case "5" : selectKeeperAndAnimals(); break;
			case "6" : selectKeeperByCondition(); break;
			case "7" : updateAnimal(); break;
			case "8" : deleteAnimal(); break;
			case "0" : sc.close(); return;
			}
		}
	}
	
	/*
	 * mybatis-config.xml -> DB 접속정보, 환경설정 등이 들어감
	 *                      .getConnection()를 호출 시 전달했던 인자값을 대체
	 * mapper.xml         -> SQL이 들어가는 파일
	 *                       보편적으로 테이블 당 하나씩 만들어줌
	 * SqlSessionFactory  -> DB세션을 만들어주는 공장 -> 프로그램 시작시 한번만 만들어줌
	 * 
	 * SqlSession         -> 실제로 SQL을 실행시키는 객체 -> JDBC의 Connection + Statement
	 * 
	 * 우리가 작성한 mapper.xml 파일을 읽어서 PreparedStatement 를 만들고 파라미터를 자동으로 바인딩하고 ResultSet 순회나 close 도 다 알아서 처리
	 * -> pstmt.setString(1, animalName);   -> #{animal}                                   
	 * 
	 * 개발자는 SQL작성 및 호출코드 작성
	 */
	
	private void save() {
		
		System.out.println();
		System.out.println("동물 추가 서비스입니다.");
		System.out.print("동물의 이름을 입력하세요 > ");
		String name = sc.nextLine();
		
		System.out.print("동물의 종 번호를 입력하세요 > ");
		String speciesId = sc.nextLine();
		
		System.out.print("동물의 구역 번호를 입력하세요 > ");
		String zoneId = sc.nextLine();
		
		System.out.print("동물의 사육사 번호를 입력하세요 > ");
		String keeperId = sc.nextLine();
		
		System.out.print("동물의 성별을 입력하세요(M/F) > ");
		String gender = sc.nextLine();
		
		System.out.print("동물의 몸무게를 입력하세요 > ");
		double kg = sc.nextDouble();
		
		int result = ac.save(new AnimalDto(name, speciesId, zoneId, keeperId, gender, kg));
		if (result > 0) {
			System.out.println(name + "추가성공!");
		} else {
			System.out.println(name + "추가실패...");
		}
	}
	
	private void findAll() {
		
		System.out.println();
		System.out.println("동물 전체 조회 서비스입니다.");
		List<AnimalDto> animals = ac.findAll();
		if(animals.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			for(AnimalDto animal : animals) {
				System.out.println("동물번호 : " + animal.getAnimalId()
				               + ", 동물이름 : " + animal.getAnimalName()
				               + ", 종 이름 : " + animal.getSpeciesId()
				               + ", 구역 이름 : " + animal.getZoneId()
				               + ", 사육사 이름 : " + animal.getKeeperId()
				               + ", 성별 : " + animal.getGender()
				               + ", 몸무게 : " + animal.getWeightKg() + "KG");
			}
		}
	}
	
	private void findById() {
		System.out.println();
		System.out.println("동물 단일 조회 서비스입니다.");
		System.out.print("검색하실 동물의 ID를 입력해주세요 > ");
		String id = sc.nextLine();
		AnimalDto animal = ac.findById(id);
		if(animal != null) {
			System.out.println();
			System.out.println(animal.getAnimalId() + "의 정보");
			System.out.println("동물 이름 : " + animal.getAnimalName());
			System.out.println("종 이름 : " + animal.getSpeciesId());
			System.out.println("구역 이름 : " + animal.getZoneId());
			System.out.println("사육사 : " + animal.getKeeperId());
			System.out.println("성별 : " + animal.getGender());
			System.out.println("몸무게 : " + animal.getWeightKg());
			System.out.println();
		} else {
			System.out.println("동물이 존재하지 않습니다.");
		}
	}
	
	private void findByKeyword() {
		System.out.println();
		System.out.println("동물 이름 키워드 검색 서비스입니다.");
		System.out.print("검색하실 키워드를 입력해주세요 > ");
		String keyword = sc.nextLine();
		List<AnimalDto> animals = ac.findByKeyword(keyword);
		
		if(animals.isEmpty()) {
			System.out.println("조회 결과가 존재하지 않습니다.");
		} else {
			for(AnimalDto animal : animals) {
				System.out.println(animal);
			}
		}
	}
	
	private void selectKeeperAndAnimals() {
		// 사육사 전체 조회 + 각 사육사들이 담당하는 동물들 전체 조회
		List<KeeperDto> keepers = new KeeperController().selectKeeperAndAnimals();
		
		System.out.println(keepers);
		for(KeeperDto keeper : keepers) {
			System.out.println(keeper.getKeeperName() + "의 담당 동물들");
			System.out.println(keeper.getAnimals());
			System.out.println("=====================================");
		}
	}
	
	private void selectKeeperByCondition() {
		System.out.println("사육사 검색 서비스입니다.");
		System.out.println("검색 조건을 선택해주세요 > ");
		System.out.print("1. 사육사 번호 / 2. 사육사 이름 / 3. 담당구역 > ");
		String menuNo = sc.nextLine();
		System.out.print("검색하실 키워드를 입력해주세요 >");
		String keyword = sc.nextLine();
		
		// 똑같은 것만 찾기 VS 포함되면 다 찾기
		
		Map<String, String> arguments = new HashMap();
		arguments.put("menuNo", menuNo);
		arguments.put("keyword", keyword);
		
		List<KeeperDto> keepers = 
				new KeeperController().selectKeeperByCondotion(arguments);
		if(keepers.isEmpty()) {
			System.out.println("조회결과가 존재하지 않습ㄴ디ㅏ.");
		}else {
			for(KeeperDto keeper : keepers) {
				System.out.println(keeper);
			}
		}
	}
	
	private void updateAnimal() {
		
		System.out.println("동물 정보 변경 서비스입니다.");
		findAll();
		System.out.print("정보를 변경하실 동물의 번호를 입력해주세요 > ");
		String animalId = sc.nextLine();
		System.out.print("변경하실 구역 번호를 입력해주세요 >");
		String zoneId = sc.nextLine();
		System.out.print("변경하실 사육사 번호를 입력해주세요 >");
		String keeperId = sc.nextLine();
		System.out.print("변경하실 몸무게를 입력해주세요 >");
		double weightKg = sc.nextDouble();
		
		AnimalDto animal = new AnimalDto();
		animal.setAnimalId(animalId);
		animal.setZoneId(zoneId);
		animal.setKeeperId(keeperId);
		animal.setWeightKg(weightKg);
		
		int result = ac.updateAnimal(animal);
		
		if(result > 0) {
			System.out.println("정보 수정에 성공했습니다.");
		}else {
			System.out.println("정보 수정에 실패했습니다.");
		}
	}
	
	private void deleteAnimal() {
		
		System.out.println("동물 정보 삭제 서비스입니다.");
		findAll();
		System.out.print("삭제하실 동물의 번호를 입력하세요 > ");
		String animalId = sc.nextLine();
		
		int result = ac.deleteAnimal(animalId);
		
		if(result > 0) {
			System.out.println("삭제 성공");
		}else {
			System.out.println("삭제 실패");
		}
	}
	
}
