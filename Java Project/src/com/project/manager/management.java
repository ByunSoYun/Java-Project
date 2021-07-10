package com.project.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;
import java.util.ArrayList;
import java.util.Scanner;

import com.project.EmployeeCommute;
import com.project.MainClass;
import com.project.employee.AnnualLeaveList;
import com.project.employee.AnnualLeaveReason;
import com.project.employee.InCheck;
import com.project.employee.OutCheck;
import com.project.employee.employee;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class management {

	public static Scanner scan;
	public final static String EMPLOYEE;

	public static ArrayList<employee> employeelist;

	private static String selectDeleteName;
	private static String selectUpdateName;

	static {
		EMPLOYEE = "C:\\Project\\직원더미.txt";
		scan = new Scanner(System.in);
		employeelist = new ArrayList<employee>();
		selectDeleteName = "";
		selectUpdateName = "";
	}

	/**
	 * 여기서는 add한 직원들의 인적사항을 더미파일에 저장시켜 주는 메서드이다.
	 * 
	 */

	public static void save() {

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(EMPLOYEE)); // bufferedWriter를 이용하여서 직원더미에 연결후에
																					// 저장시켜준다.
			for (int i = 0; i < employeelist.size(); i++) {

				writer.write(String.format("%s■%s■%s■%s■%s■%s■%s■%s■%s\n", (i + 1), employeelist.get(i).getName(),
						employeelist.get(i).getDivision(), employeelist.get(i).getPosition(),
						employeelist.get(i).getJoinDate(), employeelist.get(i).getEmployeeNum(),
						employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
						employeelist.get(i).getAddress()));
			}
			writer.close();

		} catch (Exception e) {
			System.out.println(e);
		}

//		for(int i =0; i<a.list.size() ; i++) {
//			
//			if(list.get(i).getName().equals(selectDeleteName)) {
//				System.out.println(list);
//				list.remove(i);
//			}
//		}
//		System.out.println(list);
//		

	}

	/**
	 * 이것은 직원을 삭제하는 메서드이다. 삭제할 사원번호를 입력 받아 그 사원의 정보를 삭제한다. 만약 삭제 되었을떄에는 더미파일에서도 그
	 * 정보들이 삭제된다.
	 */

	public static void delete() { // 삭제

		boolean result = true;
		while (result) {
			scan.nextLine(); // 안할시 오류
			int index = 0;

			System.out.print("삭제할 사원번호 (0번 뒤로가기): ");
			String employeeNum = scan.nextLine(); // employeenum을 받아서 0번이면 그대로 멈춘다.

//			if (employeeNum.equals("0")) {
//				break;
//			} else {
//				for (int i = 0; i < employeelist.size(); i++) { // for문을 활용하여 arraylist인 employeelist의 사원번호가 똑같다면 index
//																// 에 i를 넣어 순서를 찾는다.
//					if (employeeNum.equals(employeelist.get(i).getEmployeeNum())) {
//						index = i;
//						break;
//					} else if (!employeeNum.equals(employeelist.get(i).getEmployeeNum())) {
//						result = false;
//						break;
//					}
//				}
//			}
//			if (result == false) {
//				break;
//			}
//			

			if (employeeNum.equals("0")) {
				break;
			} else {
				for (int i = 0; i < employeelist.size(); i++) {
					if (employeeNum.equals(employeelist.get(i).getEmployeeNum())) {
						index = i;
						System.out.println("번호 같을떄");
						break;
					} else {
						if (i == employeelist.size() - 1) {
							result = false;
							break;
						}
					}
				}
			}
			if (result == false) {
				break;
			}

			System.out.println("<직원 삭제>");
			System.out.println("================================================================================");
			System.out.println("[이름]    [생년월일]    [전화번호]    [사원번호]    [입사일]    [직급]    [부서]    [주소]");
			System.out.printf("%s    %s    %s   %s    %s    %s    %s   %s\n", employeelist.get(index).getName(),
					employeelist.get(index).getBirth(), employeelist.get(index).getPhoneNum(),
					employeelist.get(index).getEmployeeNum(), employeelist.get(index).getJoinDate(),
					employeelist.get(index).getPosition(), employeelist.get(index).getDivision(),
					employeelist.get(index).getAddress());

			selectDeleteName = employeelist.get(index).getName();

			System.out.printf("%s님을 정말 삭제하시겠습니까? (Yes: 1, No: 0)\n", employeelist.get(index).getName());
			String del_employee = scan.nextLine();
			if (del_employee.equals("1")) { // 만약 1을 누를시 삭제되고, 0번을 누를시에 삭제되지 않는다.
				System.out.printf("%s님이 삭제되었습니다", employeelist.get(index).getName());
				employeelist.remove(index);

			} else if (del_employee.equals("0")) {
				continue;
			}

			AnnualLeaveLoad a = new AnnualLeaveLoad();
			a.AnnualLeaveLoad();

			// list 연차더미 수정
			for (int i = 0; i < a.list.size(); i++) {
				if (a.list.get(i).getName().equals(selectDeleteName)) {
					a.list.remove(i);
				}
			}

			ManagerSchedule m = new ManagerSchedule();
			m.list = a.list;

			// list1 연차사유더미수정
			for (int i = 0; i < a.list1.size(); i++) {
				if (a.list1.get(i).getName().equals(selectDeleteName)) {
					a.list1.remove(i);
				}
			}

			m.list1 = a.list1;
			m.save();

			// 출퇴근더미 수정
			MainClass.commuteLoad();

			for (int i = 0; i < MainClass.list2.size(); i++) {
				if (MainClass.list2.get(i).getName().equals(selectDeleteName)) {
					MainClass.list2.remove(i);
				}
			}
			// 출퇴근더미 저장

			try {

				BufferedWriter writer = new BufferedWriter(new FileWriter(OutCheck.DATA));
				// 2021-05-11■김민지■07:02■18:51 - > 직원출퇴근더미 파일에 내용 추가

				for (int i = 0; i < MainClass.list2.size(); i++) {

					writer.write(String.format("%s■%s■%s■%s■%s\n", MainClass.list2.get(i).getCommuteday(),
							MainClass.list2.get(i).getName(), MainClass.list2.get(i).getStartTime(),
							MainClass.list2.get(i).getEndTime(), MainClass.list2.get(i).getEmployeeNum()));
				}

				writer.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} // fin(true)
	}// fin(delete)

	/**
	 * 여기서는 직원들의 사원번호를 입력받아서 그 직원의 인적사항을 수정하는 메서드이다. 수정하고 싶은 정보가 없을 경우에는 엔터를 눌러서
	 * 다음으로 넘어갈수 있다. 엔터를 누른 정보는 값이 바뀌지 않고 초기값 그대로 저장된다.
	 */
	public static void update() {

		// 수정

		boolean result = true;
		while (result) {
			scan.nextLine();
			int index = 0;

			System.out.print("사원번호 (0번 뒤로가기): ");
			String employeeNum = scan.nextLine();

			if (employeeNum.equals("0")) {
				break;
			} else {
				for (int i = 0; i < employeelist.size(); i++) {
					if (employeeNum.equals(employeelist.get(i).getEmployeeNum())) {
						index = i;
						System.out.println("번호 같을떄");
						break;
					} else {
						if (i == employeelist.size() - 1) {
							result = false;
							break;
						}
					}
				}
			}
			if (result == false) {
				break;
			}
			System.out.println("<직원 수정>");
			System.out.println("================================================================================");
			System.out.println("[이름]    [생년월일]    [전화번호]    [사원번호]    [입사일]    [직급]    [부서]    [주소]");
			System.out.printf("%s    %s    %s   %s    %s    %s    %s   %s\n", employeelist.get(index).getName(),
					employeelist.get(index).getBirth(), employeelist.get(index).getPhoneNum(),
					employeelist.get(index).getEmployeeNum(), employeelist.get(index).getJoinDate(),
					employeelist.get(index).getPosition(), employeelist.get(index).getDivision(),
					employeelist.get(index).getAddress());

			selectUpdateName = employeelist.get(index).getName();

			System.out.println("===========수정하기==========");
			System.out.println("바꿀 정보가 없을시에 아무것도 입력하지 말고 Enter를 누르세요");

			System.out.print("변경할 이름(0번 뒤로가기) : ");
			String edit_name = scan.nextLine();
			if (edit_name.equals("0")) {
				break;
			} else if (edit_name.isEmpty()) { // edit_name이 엔터를 입력 받을시에 값이 바뀌지 않고 그대로 진행된다.
				edit_name = employeelist.get(index).getName();
			} else {
				employeelist.get(index).setName(edit_name);
			}

			System.out.print("변경할 생년월일(0번 뒤로가기) : ");
			String edit_birth = scan.nextLine();
			if (edit_birth.equals("0")) {
				break;
			} else if (edit_birth.isEmpty()) { // edit_birth가 엔터를 입력 받을시에 값이 바뀌지 않고 그대로 진행된다.

			} else {
				employeelist.get(index).setBirth(edit_birth);
			}

			System.out.print("변경할 전화번호(0번 뒤로가기) : ");
			String edit_phoneNum = scan.nextLine();
			if (edit_phoneNum.equals("0")) {
				break;
			} else if (edit_phoneNum.isEmpty()) { // edit_phoneNum이 엔터를 입력 받을시에 값이 바뀌지 않고 그대로 진행된다.

			} else {
				employeelist.get(index).setPhoneNum(edit_phoneNum); // index만큼의 edit_num을 setPhobneNum으로 바꾼다.
			}

			System.out.print("변경할 사원번호(0번 뒤로가기) : ");
			String edit_employeeNum = scan.nextLine();
			if (edit_employeeNum.equals("0")) {
				break;
			} else if (edit_employeeNum.isEmpty()) {
				edit_employeeNum = employeelist.get(index).getEmployeeNum();
			} else {
				employeelist.get(index).setEmployeeNum(edit_employeeNum);
			}

			System.out.print("변경할 부서(0번 뒤로가기) : ");
			String edit_division = scan.nextLine();
			if (edit_division.equals("0")) {
				break;
			} else if (edit_division.isEmpty()) {

			} else {
				employeelist.get(index).setDivision(edit_division);
			}

			System.out.print("변경할 입사일(0번 뒤로가기) : ");
			String edit_joinDate = scan.nextLine();

			if (edit_joinDate.equals("0")) {
				break;
			} else if (edit_joinDate.isEmpty()) {

			} else {
				employeelist.get(index).setJoinDate(edit_joinDate);
			}

			System.out.print("변경할 직급(0번 뒤로가기) : ");
			String edit_position = scan.nextLine();
			if (edit_position.equals("0")) {
				break;
			} else if (edit_position.isEmpty()) {

			} else {
				employeelist.get(index).setPosition(edit_position);
			}

			System.out.print("변경할 주소(0번 뒤로가기) : ");
			String edit_address = scan.nextLine();
			if (edit_address.equals("0")) {
				break;
			} else if (edit_address.isEmpty()) {

			} else {
				employeelist.get(index).setAddress(edit_address);
			}

			System.out.println("=============================================================================");
			System.out.printf("수정이 완료 되었습니다.");

			AnnualLeaveLoad a = new AnnualLeaveLoad();
			a.AnnualLeaveLoad();

			// list 연차더미 수정
			for (int i = 0; i < a.list.size(); i++) {
				if (a.list.get(i).getName().equals(selectUpdateName)) {
					a.list.get(i).setName(edit_name);
					a.list.get(i).setEmployeeNum(edit_employeeNum);
				}
			}

			ManagerSchedule m = new ManagerSchedule();
			m.list = a.list;

			// list1 연차사유더미수정
			for (int i = 0; i < a.list1.size(); i++) {
				if (a.list1.get(i).getName().equals(selectUpdateName)) {
					a.list1.get(i).setName(edit_name);
				}
			}

			m.list1 = a.list1;
			m.save();

			// 출퇴근더미 수정
			MainClass.commuteLoad();

			for (int i = 0; i < MainClass.list2.size(); i++) {
				if (MainClass.list2.get(i).getName().equals(selectUpdateName)) {
					MainClass.list2.get(i).setName(edit_name);
					MainClass.list2.get(i).setEmployeeNum(edit_employeeNum);

				}
			}
			// 출퇴근더미 저장

			try {

				BufferedWriter writer = new BufferedWriter(new FileWriter(OutCheck.DATA));
				// 2021-05-11■김민지■07:02■18:51 - > 직원출퇴근더미 파일에 내용 추가

				for (int i = 0; i < MainClass.list2.size(); i++) {

					writer.write(String.format("%s■%s■%s■%s■%s\n", MainClass.list2.get(i).getCommuteday(),
							MainClass.list2.get(i).getName(), MainClass.list2.get(i).getStartTime(),
							MainClass.list2.get(i).getEndTime(), MainClass.list2.get(i).getEmployeeNum()));
				}

				writer.close();
			} catch (Exception e) {
				System.out.println(e);
			}

		} // fin(while true)
	}// fin(update)

	/**
	 * 직원을 추가하는 메서드이다. 직원들의 인적사항을 전부다 입력 받아서 arraylist인 employeelist로 그 값들을 저장시킨다.
	 */

	public static void add() {
		scan.nextLine(); // add라는 메소드를 사용하여 사용자를 직접 추가시켜준다.
		while (true) {
			System.out.println("<직원추가>      (0: 뒤로가기)");
			System.out.println("==========================");
			System.out.println();
			scan.nextLine();

			System.out.print("이름 : ");
			String name = scan.nextLine();

			System.out.print("생년월일 : ");
			String birth = scan.nextLine();

			System.out.print("전화번호 : ");
			String phoneNum = scan.nextLine();

			System.out.print("사원번호 : ");
			String employeeNum = scan.nextLine();

			System.out.print("부서 : ");
			String division = scan.nextLine();

			System.out.print("입사일 : ");
			String joinDate = scan.nextLine();

			System.out.print("직급 : ");
			String position = scan.nextLine();

			System.out.print("주소 : ");
			String address = scan.nextLine();

			employee employee = new employee(); // 새로운 객체 생성

			employee.setName(name);
			employee.setBirth(birth);
			employee.setPhoneNum(phoneNum);
			employee.setEmployeeNum(employeeNum);
			employee.setDivision(division);
			employee.setJoinDate(joinDate);
			employee.setPosition(position); // set을그대로하고 위에 nextline을 읽어옴
			employee.setAddress(address);

			employeelist.add(employee); // employeelist에 add해서 저장시켜줌

			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel = scan.nextInt();
			if (sel == 0) {
				break;
			}
		}
	}

	/**
	 * 관리자로 로그인 후에 직원 관리를 누르면 나오는 메서드이다. 여기서 1,2,3,4에 따라서 그 메서드들로 이동한다.
	 */

	public static void menu() { // 처음메뉴 직원 목록, 추가, 수정으로 갈수 있다.
		while (true) {
			System.out.println();
			System.out.println("<직원관리>");
			System.out.println("==========================");
			System.out.println();
			System.out.println("[1]. 직원 목록"); // menu1
			System.out.println();
			System.out.println();
			System.out.println("[2]. 직원 추가"); // add
			System.out.println();
			System.out.println();
			System.out.println("[3]. 직원 수정"); // update
			System.out.println();
			System.out.println();
			System.out.println("[4]. 직원 삭제"); // delete
			System.out.println();
			System.out.println();
			System.out.println("[0]. 종료 하기");
			System.out.println();
			System.out.println();
			System.out.println("=========================");
			System.out.print("선택 : ");

			int sel = scan.nextInt(); // sel값을 받아서 숫자만큼 원하는 곳으로 이동한다.

			if (sel == 1) {
				menu1();
			} else if (sel == 2) {
				add();
			} else if (sel == 3) {
				update();
			} else if (sel == 4) {
				delete();
			} else if (sel == 0) {
				System.out.println("프로그램을 종료합니다.");
				break;
			}
		}

	}

	public static void pause() { // 업무 끝나고 화면 고정
		System.out.println("\n엔터를 누르시면 다음으로 진행 됩니다.");
		scan.nextLine(); // Block
	}

	/**
	 * 직원 목록을 눌렀을때에 나오는 메뉴이다. 여기에서 원하는 번호를 누르면 그 번호에 해당하는 조회로 이동한다.
	 * 
	 */

	public static void menu1() { // 메뉴에서 그다음 메뉴로 직원 관리를 할수 있는 메뉴
		while (true) {
			System.out.println();
			System.out.println("<직원관리>");
			System.out.println("==========================");
			System.out.printf("전체 직원수 : %s명", employeelist.size());
			System.out.println();
			System.out.println();
			System.out.println("[1]. 부서별 직원 조회");
			System.out.println();
			System.out.println();
			System.out.println("[2]. 직급별 직원 조회");
			System.out.println();
			System.out.println();
			System.out.println("[3]. 전체 직원 조회");
			System.out.println();
			System.out.println();
			System.out.println("[0]. 뒤로 가기");
			System.out.println();
			System.out.println();
			System.out.println("=========================");
			System.out.print("선택 : ");

			int sel = scan.nextInt();

			if (sel == 1) {
				divisionmenu();
			} else if (sel == 2) {
				positionmenu();
			} else if (sel == 3) {
				allmenu();
			} else if (sel == 0) {
				break;
			}
		}

	}

	/**
	 * 이것은 전체 직원들을 한번에 볼수 있는 메소드이다. 직원들의 전체적인 정보들을 받아서 한눈에 볼수 있다. 순서는 더미파일에 있는 순서이다.
	 */

	public static void allmenu() {
		while (true) {
			System.out.println("<전체직원>");
			System.out.println("===================================================================");
			System.out.println("[이름]    [생년월일]    [전화번호]    [사원번호]    [입사일]    [직급]    [부서]");
			for (int i = 0; i < employeelist.size(); i++) {
				System.out.printf("%s   %s    %s   %s    %s    %s   %s\n", employeelist.get(i).getName(),
						employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
						employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
						employeelist.get(i).getDivision(), employeelist.get(i).getPosition());
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel = scan.nextInt();
			if (sel == 0) {
				break;
			}
		}
	}

	/**
	 * 이것은 직급별로 부서별 인원수를 한번에 보기 위한 메서드이다. 그 직급별로 나눠진 곳에 클릭을 하면 다른 직급의 인원들을 파악할 수 있다.
	 */

	public static void positionmenu() {
		while (true) {

			int bujang = 0;
			int chajang = 0;
			int gwajang = 0;
			int daeli = 0;
			int sawon = 0;
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getPosition().equals("부장")) {
					bujang++;
				} else if (employeelist.get(i).getPosition().equals("차장")) {
					chajang++;
				} else if (employeelist.get(i).getPosition().equals("과장")) {
					gwajang++;
				} else if (employeelist.get(i).getPosition().equals("대리")) {
					daeli++;
				} else if (employeelist.get(i).getPosition().equals("사원")) {
					sawon++;
				}
			}
			System.out.println("<직원관리 - 직급별>");
			System.out.println("==========================");
			System.out.printf("[1]. 부장 : %s명", bujang);
			System.out.println();
			System.out.printf("[2]. 차장 : %s명", chajang);
			System.out.println();
			System.out.printf("[3]. 과장 : %s명", gwajang);
			System.out.println();
			System.out.printf("[4]. 대리 : %s명", daeli);
			System.out.println();
			System.out.printf("[5]. 사원 : %s명", sawon);
			System.out.println();
			System.out.println("=========================");
			System.out.print("번호입력 (0:뒤로가기) : ");

			int sel = scan.nextInt();
			if (sel == 1) {
				bujangmenu();
			} else if (sel == 2) {
				chajangmenu();
			} else if (sel == 3) {
				gwajangmenu();
			} else if (sel == 4) {
				daelimenu();
			} else if (sel == 5) {
				sawonmenu();
			} else if (sel == 0) {
				break;
			}
		}
	}

	/**
	 * 직원 관리에서 직급 직원보기를 누른 뒤에, 그 직급을 누르면 그 직급에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 직급이 사원인
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void sawonmenu() {
		while (true) {
			System.out.println("<사원 - 직원목록>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[부서]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getPosition().equals("사원")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}

		}
	}

	/**
	 * 직원 관리에서 직급 직원보기를 누른 뒤에, 그 직급을 누르면 그 직급에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 직급이 대리인
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void daelimenu() {
		while (true) {
			System.out.println("<대리 - 직원목록>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[부서]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getPosition().equals("대리")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}

		}

	}

	/**
	 * 직원 관리에서 직급 직원보기를 누른 뒤에, 그 직급을 누르면 그 직급에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 직급이 과장인
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void gwajangmenu() {
		while (true) {
			System.out.println("<과장 - 직원목록>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[부서]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getPosition().equals("과장")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}

		}

	}

	/**
	 * 직원 관리에서 직급 직원보기를 누른 뒤에, 그 직급을 누르면 그 직급에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 직급이 차장인
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void chajangmenu() {
		while (true) {
			System.out.println("<차장 - 직원목록>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[부서]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getPosition().equals("차장")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}

		}

	}

	/**
	 * 직원 관리에서 직급 직원보기를 누른 뒤에, 그 직급을 누르면 그 직급에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 직급이 부장인
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void bujangmenu() {
		while (true) {
			System.out.println("<부장 - 직원목록>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[부서]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getPosition().equals("부장")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}

		}

	}

	/**
	 * 메뉴에서 각 사원들의 부서별로 나눠주는 메서드이다. 여기서 부서별로 인원수가 궁금한 인원들을 파악할수 있다.
	 */

	public static void divisionmenu() {

		while (true) {
			int insapeople = 0;
			int gyebalpeople = 0;
			int youngubpeople = 0;
			int hwigyepeople = 0;
			int gwihwikpeople = 0;
			for (int i = 0; i < employeelist.size(); i++) {
				// System.out.println(employeelist.get(i).getDivision());
				if (employeelist.get(i).getDivision().equals("인사부")) {
					insapeople++;
				} else if (employeelist.get(i).getDivision().equals("개발부")) {
					gyebalpeople++;
				} else if (employeelist.get(i).getDivision().equals("영업부")) {
					youngubpeople++;
				} else if (employeelist.get(i).getDivision().equals("회계부")) {
					hwigyepeople++;
				} else if (employeelist.get(i).getDivision().equals("기획부")) {
					gwihwikpeople++;
				}
			}
			System.out.println("<직원관리 - 부서별>");
			System.out.println("==========================");
			System.out.printf("[1]. 인사부 : %s명", insapeople);
			System.out.println();
			System.out.printf("[2]. 개발부 : %s명", gyebalpeople);
			System.out.println();
			System.out.printf("[3]. 회계부 : %s명", hwigyepeople);
			System.out.println();
			System.out.printf("[4]. 영업부 : %s명", youngubpeople);
			System.out.println();
			System.out.printf("[5]. 기획부 : %s명", gwihwikpeople);
			System.out.println();
			System.out.println("=========================");
			System.out.print("번호입력 (0:뒤로가기) : ");

			int sel2 = scan.nextInt();
			if (sel2 == 1) {
				insamenu();
			} else if (sel2 == 2) {
				gyebalmenu();
			} else if (sel2 == 3) {
				hwigyemenu();
			} else if (sel2 == 4) {
				youngubmenu();
			} else if (sel2 == 5) {
				gwihwikmenu();
			} else if (sel2 == 0) {
				break;
			}
		}
	}

	/**
	 * 직원 관리에서 부서별 직원보기를 누른 뒤에, 그 부서를 누르면 그 부서에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 기획부
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void gwihwikmenu() {
		while (true) {
			System.out.println("<기획부>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[직급]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getDivision().equals("기획부")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}
		}
	}

	/**
	 * 직원 관리에서 부서별 직원보기를 누른 뒤에, 그 부서를 누르면 그 부서에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 영업부
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void youngubmenu() {
		while (true) {
			System.out.println("<영업부>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[직급]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getDivision().equals("영업부")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}
		}
	}

	/**
	 * 직원 관리에서 부서별 직원보기를 누른 뒤에, 그 부서를 누르면 그 부서에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 회계부
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void hwigyemenu() {
		while (true) {
			System.out.println("<회계부>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[직급]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getDivision().equals("회계부")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}
		}
	}

	/**
	 * 직원 관리에서 부서별 직원보기를 누른 뒤에, 그 부서를 누르면 그 부서에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 개발부
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void gyebalmenu() {
		while (true) {
			System.out.println("<개발부>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[직급]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getDivision().equals("개발부")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}
		}
	}

	/**
	 * 직원 관리에서 부서별 직원보기를 누른 뒤에, 그 부서를 누르면 그 부서에 따른 사원들의 정보들을 볼수 있는 메서드이다. 이것은 인사부
	 * 사원들의 정보를 볼수 있다.
	 */

	public static void insamenu() {
		while (true) {
			System.out.println("<인사부>");
			System.out.println("========================================================");
			System.out.println("[이름]\t[생년월일]\t[전화번호]\t[사원번호]\t[입사일]\t[직급]\t");
			for (int i = 0; i < employeelist.size(); i++) {
				if (employeelist.get(i).getDivision().equals("인사부")) {
					System.out.printf("%s   %s    %s    %s       %s        %s\n", employeelist.get(i).getName(),
							employeelist.get(i).getBirth(), employeelist.get(i).getPhoneNum(),
							employeelist.get(i).getEmployeeNum(), employeelist.get(i).getJoinDate(),
							employeelist.get(i).getPosition());
				}
			}
			System.out.println("=========================================================");
			System.out.print("번호입력 (0:뒤로가기) : ");
			int sel2 = scan.nextInt();
			if (sel2 == 0) {
				break;
			}
		}
	}

	/**
	 * empplist라는 클래스 메소드를 활용하여서 ■로 값을 나눠준 것들을 정렬해준뒤에 load_empl이란 변수로 그 값들을 받아주고,
	 * employeelist라는 arraylist에 그 값들을 넣어준다.
	 * 
	 * @throws FileNotFoundException
	 */

	public static void empllist() throws FileNotFoundException { // 사용내역 파일에서 불러오기
		try { // empllist라는 클래스메소드를 활용하여 employeelist라는 array변수에 load_empl이라는 값을 넣는다.

			employeelist.clear();

			BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE));
			;
			String line = "";

			while ((line = reader.readLine()) != null) {

				employee load_empl = new employee();

				String[] temp = line.split("■");

				load_empl.setName(temp[1]);
				load_empl.setDivision(temp[2]);
				load_empl.setPosition(temp[3]);
				load_empl.setJoinDate(temp[4]);
				load_empl.setEmployeeNum(temp[5]);
				load_empl.setBirth(temp[6]);
				load_empl.setPhoneNum(temp[7]);
				load_empl.setAddress(temp[8]);

				employeelist.add(load_empl);
			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

}// fin(management)
