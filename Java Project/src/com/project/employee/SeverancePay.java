package com.project.employee;

import java.util.Calendar;
import java.util.Scanner;

import com.project.MainClass;

/**
 * 
 * @author 3조
 * 퇴직금을 확인하는 클래스
 *
 */
public class SeverancePay {
	
	private static Scanner scan;

	private static Calendar join;		//입사일(Tick값 계산을위해 Calendar로 바꿔준다)
	private static Calendar retire;		//퇴사일(Tick값 계산을위해 Calendar로 바꿔준다)
	private static Calendar threeMonth;	//퇴사일로부터 3개월 전

	private static String joindate;		//로그인한 직원의 입사일 가져오기
	private static String retireDate;	//퇴사일
	
	private static int Three_totalWage;	//3개월 급여 총액
	private static int totalBonus;		//1년간 받은 상여금(인센티브)
	private static int yearWage;		//연차수당
	private static long totalWorkDay;	//총 근무 일수(입사~퇴사일)

	static {
		scan = new Scanner(System.in);
		
		join = Calendar.getInstance();
		retire = Calendar.getInstance();
		threeMonth = Calendar.getInstance();
		
		Three_totalWage = 0;
		totalBonus = 0;
		yearWage = 0;
	}
	

	/**
	 * 입사일/ 퇴사일을 입력받고
	 * 평균임금을 직접입력할지 계산기를 사용할지 선택하는 메소드
	 */
	public void severancePay() {

		System.out.println("<퇴직금>");
		System.out.println("===================");
		System.out.printf("입사일: %s\n", MainClass.logEmployee.getJoinDate());
		System.out.print("퇴직일 입력(숫자 8자리): ");
		retireDate = scan.nextLine();
		System.out.println("===================");

		joindate = MainClass.logEmployee.getJoinDate();
		int Jyear = Integer.parseInt(joindate.substring(0, 4));
		int Jmonth = Integer.parseInt(joindate.substring(5, 7));
		int Jday = Integer.parseInt(joindate.substring(8, 10));

		join.set(Jyear, Jmonth, Jday);

		// retireDate 20201010
		int Ryear = Integer.parseInt(retireDate.substring(0, 4));
		int Rmonth = Integer.parseInt(retireDate.substring(4, 6));
		int Rday = Integer.parseInt(retireDate.substring(6, 8));

		retire.set(Ryear, Rmonth, Rday);
		
		threeMonth.set(Ryear, Rmonth-3, Rday);
		System.out.printf("3개월 전 날짜:%tF", threeMonth);
		

		totalWorkDay = (retire.getTimeInMillis() - join.getTimeInMillis()) / 1000 / 60 / 60 / 24;

		
		System.out.println("<퇴직금>");
		System.out.println("===================");
		System.out.println("1. 평균임금 계산");
		System.out.println("2. 평균임금 직접입력");
		System.out.println("===================");
		System.out.print("번호입력(0:뒤로가기): ");
		String sel = scan.nextLine();

		if (sel.equals("1")) {
			// 평균임금 계산
			averageAutoCal();

		} else if (sel.equals("2")) {
			// 평균임금 직접입력
			averagDayCal();
		} else {
			// 번호를 잘못입력하였을 때 다시 입력받기위해 메소드를 다시 호출
			severancePay();
		}


	}//severance

	/**
	 * 평균임금을 계산해주는 메소드
	 * 3개월 급여 총액, 연간 상여금, 연차수당을 입력하면
	 * 퇴직금을 계산해주어 보여준다.
	 */
	private void averageAutoCal() {
		// 평균임금 계산기
		System.out.println("<평균임금 계산기>");
		System.out.println("===================");
		System.out.print("3개월 급여 총액입력(원): ");
		Three_totalWage = Integer.parseInt(scan.nextLine());
		System.out.print("연간 상여금 총액(원): ");
		totalBonus = Integer.parseInt(scan.nextLine());
		System.out.print("연차수당(원): ");
		yearWage = Integer.parseInt(scan.nextLine());
		System.out.println("===================");


		double total = Three_totalWage + (totalBonus * 3 /12) + (yearWage * 3 / 12);
		System.out.println("3개월 총액"+total);

		//3개월간 재직일수 
		long threeMonthtick = (retire.getTimeInMillis() - threeMonth.getTimeInMillis()) / 1000 / 60 / 60 / 24;

		double dayWage = (double)total / (threeMonthtick) ;
		
		double severance = dayWage * 30 * ((double)totalWorkDay / 365);
		
		System.out.printf("총 재직일수: %d일\n", totalWorkDay);
		System.out.printf("1일 평균임금: %,d원\n", (int)dayWage);
		System.out.printf("예상 퇴직금(세전기준): %,d원\n", (int) severance);
		System.out.println("약간의 오차가 발생할 수 있습니다.");
		
	}//averageAutoCal

	
	/**
	 * 평균임금을 직접입력하면
	 * 퇴직금을 계산해주는 메소드
	 */
	private void averagDayCal() {// 평균임금 직접입력

		System.out.println("<평균임금 직접입력>");
		System.out.println("===================");
		System.out.print("1일 평균 임금(원): ");
		int averageDayWage = Integer.parseInt(scan.nextLine());

		double severance = averageDayWage * 30 * ((double) totalWorkDay / (365));

		System.out.printf("재직일수: %d일\n", totalWorkDay);
		System.out.printf("평균임금: %,d원\n", averageDayWage);
		System.out.printf("예상 퇴직금(세전기준): %,d원\n", (int) severance);
		System.out.println("약간의 오차가 발생할 수 있습니다.");

	}// averagDayCal

}
