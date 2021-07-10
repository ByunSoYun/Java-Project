package com.project.employee;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.project.MainClass;

/**
 * 
 * @author 3조
 * 직원들이 로그인 후 
 * 확인할 수 있는 목록을 보여주고
 * 확인 할 메뉴를 선택하는 클래스입니다.
 *
 */
public class EmployeeOption {

	private static Scanner scan;

	static {
		scan = new Scanner(System.in);
	}
	

	/**
	 * menu() 메서드를 통해 입력받은 번호로
	 * 이동해 해당 클래스로 이동하게하는 메소드
	 * 0: 로그인 화면으로 돌아가기
	 * 해당번호가 없으면 다시 입력받기
	 * @throws FileNotFoundException 
	 */
	public static void option() throws FileNotFoundException {

		boolean loop = true;

		String sel = menu();

	
		while (loop) {
			
			if(sel.equals("0")){
				// 종료
				System.out.println("로그인화면으로 돌아갑니다.");
				MainClass.login();
				
			}else if (sel.equals("1")) {
				//스케줄 확인
				Schedule s = new Schedule();
				s.schedule();
				pause();
				option();

			} else if (sel.equals("2")) {
				//연차 신청
				AnnualLeave aL = new AnnualLeave();
				aL.annualLeave();
				pause();
				option();

			} else if (sel.equals("3")) {
				//근태 관리
				Commute c = new Commute();
				c.commute();
				pause();
				option();

			} else if (sel.equals("4")) {
				//서류 서명
				Document d = new Document();
				d.document(); 
				pause();
				option();

			} else if (sel.equals("5")) {
				//코로나 자가진단
				Covid c = new Covid();
				c.covid(); 
				pause();
				option();
				
			} else if (sel.equals("6")) {
				//급여 명세서
				Salary s = new Salary();
				s.salary();
				pause();
				option();

			} else if (sel.equals("7")) {
				//퇴직금
				SeverancePay sp = new SeverancePay();
				sp.severancePay();
				
				pause();
				option();

			} else if (sel.equals("8")) {
				//복지포인트
				
				Welfare w = new Welfare();
				w.salary_menu();
				pause();
				option();

			} else if (sel.equals("9")) {
				//연말정산
				yearcal y = new yearcal();
				y.menu();
				pause();
				option();

			} else if (sel.equals("10")) {
				//공지사항 게시판
				
				EmployeeNotice n = new EmployeeNotice();
				n.noticeBoard();
				pause();
				option();

			} else if (sel.equals("11")) {
				//익명게시판
				
				Anonymous a = new Anonymous();
				a.anonymous(); 
				pause();
				option();

			} else {
				System.out.println("번호를 다시 선택해주세요.");
				option();
			}

		} // while
		

	}//option
	
	
	/**
	 * @author 3조
	 * 선택가능한 메뉴들을 보여주고
	 * 확인할 메뉴를 선택받는 메소드
	 */
	public static String menu() {

		System.out.println("===================");
		System.out.println("1. 스케줄 확인");
		System.out.println("2. 연차 신청");
		System.out.println("3. 근태 관리");
		System.out.println("4. 서류 서명");
		System.out.println("5. 코로나 자가진단");
		System.out.println("6. 급여 명세서");
		System.out.println("7. 퇴직금");
		System.out.println("8. 복지 포인트");
		System.out.println("9. 연말 정산");
		System.out.println("10. 공지사항 게시판");
		System.out.println("11. 익명 게시판");
		System.out.println("===================");
		System.out.print("선택(0:로그인 화면이동): ");

		String sel = scan.nextLine();

		return sel;

	}//menu
	
	/**
	 * 해당 메뉴에서 업무를 끝낸 후 
	 * 뒤로 돌아갈 때 바로 넘어가지 않고 
	 * 한 템포 쉬기위해 Enter를 입력받게 하는 메소드
	 */
	private static void pause() {
		System.out.println("Enter를 누르시면 목록으로 이동합니다.");
		scan.nextLine();// Block
	}// pause

}//EmployeeOption
