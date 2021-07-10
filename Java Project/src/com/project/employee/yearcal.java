package com.project.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import com.project.MainClass;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class yearcal {
	
	//복지 포인트 : 직급 별로 나눔. ex(사원  120000, 부장 240000)
	
	
	public static Scanner scan;
	
	public final static String EMPLOYEE;
	
	public static ArrayList<yearcallist> employlist = new ArrayList<yearcallist>();
	
	
	static {
		
		scan = new Scanner(System.in);
		EMPLOYEE = "C:\\Project\\직원더미.txt";
	
	}
	
	
//	public static void main(String[] args) throws FileNotFoundException {
//		
//		em_list();
//		
//		//System.out.println(employlist.get(0).getName() + " / 연봉은 : " + calculator(employlist.get(0).getPosition()));
//		//int sal = calculator(employlist.get(0).getPosition());
//		boolean loop = true;
//
//		while (loop) {
//			//메뉴출력
//			//선택
//			//분기
//		
//			String seq = menu();
//	
//			if (seq.equals("1")) {
//				cardpay();
//			} else if (seq.equals("2")) {
//				house();
//			} else if (seq.equals("3")) {
//				fund();
//			} else if (seq.equals("4")) {
//				loop = false;
//			}
//			
//		}//loop
//		
//		System.out.println("프로그램 종료");
//		
//	}//main
	

	
	
//	public void yearcalmenuSelect() {
//		boolean loop = true;
//
//		while (loop) {
//			//메뉴출력
//			//선택
//			//분기
//		
//			String seq = menu();
//	
//			if (seq.equals("1")) {
//				cardpay();
//			} else if (seq.equals("2")) {
//				house();
//			} else if (seq.equals("3")) {
//				fund();
//			} else if (seq.equals("4")) {
//				loop = false;
//			}
//			
//		}//loop
//		
//		System.out.println("프로그램 종료");
//	}
	
	/**
	 * 3번을 눌렀을떄에 기능이고 각 기능별마다 로그인한 사람의 연봉이 표시되게 하였다.
	 * 또한 가격을 입력하면, 그에맞는 계산된 금액이 나온다.
	 */
	public static void fund() {
		while(true) {
			
		System.out.printf(" %s|%s|%s|%s님의 연봉은 %,d입니다.",MainClass.logEmployee.getDivision()
															   ,MainClass.logEmployee.getPosition()
															   ,MainClass.logEmployee.getEmployeeNum()
															   ,MainClass.logEmployee.getName()
															   ,calculator(MainClass.logEmployee.getPosition()));
		System.out.println();
		System.out.print("개인형 퇴직연금(IRP)납입액/(입력)(원) : ");
		int IRP = scan.nextInt();
		System.out.println();
		System.out.print("연금저축 납입액(연간)/(입력)(원) : ");
		int saveyear = scan.nextInt();
		pause();

		double result = ((calculator(employlist.get(0).getPosition()) - (IRP + saveyear)) / 190);
		System.out.println(result);
		System.out.println();
		System.out.println();
		System.out.println("아낄수 있는 세금(예상액)");
		System.out.printf("%.0f원" ,result);
		System.out.println();
		System.out.println();
		System.out.println("공제 적용 금액");
		System.out.println("=============================");
		System.out.printf("개인형 퇴직 연금 : %.0f원", result * 4);
		System.out.println();
		System.out.printf("연금저축 : %.0f원", result * 2);
		System.out.println();
		System.out.printf("총 공제 적용 금액 : %.0f원", (result * 4 + result * 2));
		System.out.print("번호입력 (0:뒤로가기) : ");
			int sel = scan.nextInt();
			if (sel == 0) {
				break;
			}
		}
	}


	/**
	 * 주택 관련 소득 공제로써 값들을 입력하면 그에 따른 비율이 계산되어서 나온다.
	 */
	
	public static void house() {
		while(true) {
			
			System.out.printf(" %s|%s|%s|%s님의 연봉은 %,d입니다.",MainClass.logEmployee.getDivision()
													   ,MainClass.logEmployee.getPosition()
													   ,MainClass.logEmployee.getEmployeeNum()
													   ,MainClass.logEmployee.getName()
													   ,calculator(MainClass.logEmployee.getPosition()));
			
		System.out.println();
		System.out.print("청약저축 입금액(연간)/(입력)(원) : ");
		int save = scan.nextInt();
		System.out.println();
		System.out.print("주택청약종합저축 입금액(연간)/(입력)(원) : ");
		int housesave = scan.nextInt();
		System.out.println();
		System.out.print("근로자 주택마련저축(월간)/(입력)(원) : ");
		int workhouse = scan.nextInt();
		System.out.print("주택임차입금 원리금상환액(연간)/(입력)(원) : ");
		int returnhouse = scan.nextInt();
		
		double result = ((calculator(employlist.get(0).getPosition()) - (save + housesave + workhouse + returnhouse)) / 25);
		System.out.println();
		System.out.println();
		System.out.println("예상 적용 세율");
		System.out.printf("%.1f", result / 60000);
		System.out.println();
		System.out.println();
		System.out.println("아낄수 있는 세금(예상액)");
		System.out.printf("%.0f원", result);
		System.out.println();
		System.out.println();
		pause();
		System.out.println("공제 적용 금액");
		System.out.println("=============================");
		System.out.printf("청약저축 : %.0f원", save * 0.4);
		System.out.println();
		System.out.printf("주택청약종합저축 : %.0f원", housesave * 0.4);
		System.out.println();
		System.out.printf("근로자 주택마련저축 : %.0f원", workhouse * 4.8);
		System.out.println();
		System.out.printf("주책임차차입금 원리금상환액 : %.0f원", returnhouse * 0.4);
		System.out.println();
		System.out.println("==============================");
		System.out.println();
		System.out.printf("총 공제 금액 : %.0f원", (save * 0.4 + housesave * 0.4 + workhouse * 4.8 + returnhouse * 0.4));
		System.out.println();
		System.out.print("번호입력 (0:뒤로가기) : ");
			int sel = scan.nextInt();
			if (sel == 0) {
				break;
			}
		}
	}

	
	/**
	 * 카드 및 소득공제를 알수 있는 메소드로, 값들이 계산되어 나온다.
	 */
	public static void cardpay() {
		while(true) {
			
			
			System.out.printf(" %s|%s|%s|%s님의 연봉은 %,d입니다.",MainClass.logEmployee.getDivision()
															   ,MainClass.logEmployee.getPosition()
															   ,MainClass.logEmployee.getEmployeeNum()
															   ,MainClass.logEmployee.getName()
															   ,calculator(MainClass.logEmployee.getPosition()));
			
		System.out.println();
		System.out.print("신용카드(연간)/(입력)(원) : ");
		int card = scan.nextInt();
		System.out.println();
		System.out.print("현금영수증(연간)/(입력)(원) : ");
		int money = scan.nextInt();
		System.out.println();
		System.out.print("체크카드(연간)/(입력)(원) : ");
		int checkcard = scan.nextInt();
		pause();
		double result = ((calculator(employlist.get(0).getPosition()) - card + (money + checkcard)) / 85);
		System.out.println();
		System.out.println();
		System.out.println("예상 적용 세율");
		System.out.printf("%.1f", result/19000);
		System.out.println();
		System.out.println();
		System.out.println("아낄수 있는 세금(예상액)");
		System.out.printf("%.0f원" ,result);
		System.out.println();
		System.out.println();
		System.out.print("번호입력 (0:뒤로가기) : ");
			int sel = scan.nextInt();
			if (sel == 0) {
				break;
			}
		}
	}



	public static void menu() {  //메뉴
		try {
			em_list();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

		//mainclass.getEname();
		
		while(true) {
		
		System.out.printf("안녕하세요 %s님 환영합니다.",MainClass.logEmployee.getName());	
		System.out.println();
		System.out.println("==========[연말정산 계산기]==========");
		System.out.println();
		System.out.println();
		System.out.println("[1]. 카드 및 현금 소비액 소득공제"); //cardpay
		System.out.println();
		System.out.println();
		System.out.println("[2]. 주택 관련"); //house
		System.out.println();
		System.out.println();
		System.out.println("[3]. 연금 및 펀드 관련"); //fund
		System.out.println();
		System.out.println();
		System.out.println("[4]. 종료 하기");
		System.out.println();
		System.out.println();
		System.out.println("===================================");
		System.out.print("선택 : ");
	
		int sel = scan.nextInt();
			if (sel == 1) {
				cardpay();
			} else if (sel == 2) {
				house();
			} else if (sel == 3) {
				fund();
			} else if (sel == 4) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}		
		}
	}
	public static void pause() { //업무 끝나고 화면 고정
		System.out.println("\n엔터를 누르시면 다음으로 진행 됩니다.");
		scan.nextLine(); //Block
		
	}
	
	/**
	 * EMPLOYEE라는 파일 경로에서 ■를 제외한 값들을 받아온 뒤에, load_list라는 변수로
	 * 그 값들을 받은 후에 employlist라는 arraylist에 그 값들을 넣어준다.
	 * @throws FileNotFoundException
	 */
	
	public  static void em_list() throws FileNotFoundException {  
		try {
			BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE));;
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				
				yearcallist load_list = new yearcallist();
				
				String[] temp = line.split("■");
				
				
				load_list.setName(temp[1]);
				load_list.setDivision(temp[2]);
				load_list.setPosition(temp[3]);
				load_list.setEmployeeNum(temp[5]);
		
				
				employlist.add(load_list);
			}
			
			reader.close();
			
				
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * calculator라는 메서드로 로그인한 사람의 직책에 따라서 연봉을 보여주는 것이다.
	 * @param temp
	 * @return
	 */
	
	public static int calculator(String temp) {
		int sal = 0;
			if(temp.equals("사원")) {
				sal = 1800000*12;
			} else if(temp.equals("대리")) {
				sal = 2000000*12;
			} else if(temp.equals("과장")) {
				sal = 2500000*12;
			} else if(temp.equals("차장")) {
				sal = 3000000*12;
			} else {
				sal = 4000000*12;
			
			}
		
		
		return sal;
	}

	
}//fin(salary)

	
