package com.project.manager;

import java.io.FileNotFoundException;
import java.util.Scanner;

import com.project.MainClass;

/**
 * 
 * @author 3조
 * 관리자로 로그인 후 
 * 확인할 수 있는 목록을 보여주고
 * 확인 할 메뉴를 선택하는 클래스입니다.
 *
 */
public class ManagerOption {
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

		/**
		 * 메뉴를 고르는 코드입니다.
		 */

		while (loop) {

			if (sel.equals("1")) {
				//직원관리
				EmployeeManage em = new EmployeeManage();
				em.employeeManage();
				pause();
				option();
				
			} else if (sel.equals("2")) {
				//결제서류
				DocumentManage dm = new DocumentManage();
				dm.documentManage();
				pause();
				option();
				
			} else if (sel.equals("3")) {
				//스케줄관리
				ManagerSchedule s = new ManagerSchedule();
				s.managerSchedule();
				pause();
				option();
			} else if (sel.equals("4")) {
				//공지사항 게시판
				ManagerNotice n = new ManagerNotice();
				n.managerNotice();
				pause();
				option();
			}else if(sel.equals("0")){
				//종료
				System.out.println("로그인화면으로 돌아갑니다.");
				MainClass.login();
			}else {
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
		System.out.println("1. 직원관리");
		System.out.println("2. 결제 서류");
		System.out.println("3. 스케줄 관리");
		System.out.println("4. 공지사항 게시판");
		System.out.println("===================");
		System.out.print("선택(0:로그인화면으로 돌아가기): ");

		String sel = scan.nextLine();

		return sel;

	}//menu
	
	
	/**
	 * 해당 메뉴에서 업무를 끝낸 후 
	 * 뒤로 돌아갈 때 바로 넘어가지 않고 
	 * 한 템포 쉬기위해 Enter를 입력받게 하는 메소드
	 */
	private static void pause() {
		System.out.println("엔터를 누르시면 목록으로 이동합니다.");
		scan.nextLine();// Block
	}// pause

}//ManagerOption
