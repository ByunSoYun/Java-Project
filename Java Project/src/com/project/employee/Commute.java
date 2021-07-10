package com.project.employee;

import java.util.Scanner;

/**
 * 
 * @author 3조
 *
 * 출근시간과 퇴근시간을 체크해서 저장해주는 클래스입니다.
 *
 */

public class Commute {
	
	private static Scanner scan;

	static {
		scan = new Scanner(System.in);
	}

	public void commute() {
		
		System.out.println("===================");
		System.out.println("1. 출근 체크");
		System.out.println("2. 퇴근 체크");
		System.out.println("3. 근태 확인");
		System.out.println("===================");
		System.out.print("선택: ");

		String sel = scan.nextLine();
		
		if(sel.equals("1")) {
			//출근 체크
	
			
			InCheck ic = new InCheck();
			ic.incheck();
		}else if(sel.equals("2")) {
			
			OutCheck oc = new OutCheck();
			oc.outCheck();
			//퇴근 체크
		}else if(sel.equals("3")) {
			//근태확인
			
			CommuteCheck ch = new CommuteCheck();
			ch.commuteCheck();
			
		}else {
			System.out.println("");
		}
		
	}
	
}
