package com.project.manager;

import java.util.Scanner;
/**
 * 
 * 완료되지 않은 근태서류와 코로나 자가진단서를 관리자가 확인할 수 있는 클래스
 * 
 * @author 3조
 *
 */
public class DocumentManage {
	
	private static Scanner scan;

	static {
		scan = new Scanner(System.in);
	}
	/**
	 * 서명이 완료되지 않거나 서명이 잘못된 경우의 근태서류를 관리자가 확인할 수 있고 
	 * 코로나 자가진단서에서 해당날짜에 코로나 특이사항이 있는 경우의 서류를 관리자가 확인 할 수 있는 메서드
	 */
	public void documentManage() {
		
		System.out.println("===================");
		System.out.println("1. 근태 서류");
		System.out.println("2. 코로나 자가진단서");
		System.out.println("===================");
		System.out.print("번호 입력(0: 뒤로가기): ");
		String sel = scan.nextLine();
		
		if(sel.equals("1")) {
			CommuteDocument cd = new CommuteDocument();
			cd.commuteDocument();
		}else if(sel.equals("2")){
			CovidDocument covid = new CovidDocument();
			covid.covidDocument();
			
		}else {
			
		}
		
	}
	
}
