package com.project.manager;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Scanner;

import com.project.employee.AnnualLeaveList;
import com.project.employee.AnnualLeaveReason;

public class ManagerSchedule {
	
	private static final String DATA;
	private static final String DATA1;
	private static Scanner scan;
	public static ArrayList<AnnualLeaveList> list;
	public static ArrayList<AnnualLeaveReason> list1;
	private static ArrayList<String> employeeLeave;
	private static int mainMonth;
	
	static {
		DATA = "C:\\Project\\직원연차더미.txt";
		DATA1 = "C:\\Project\\직원연차사유더미.txt";
		scan = new Scanner(System.in);
		list = new ArrayList<AnnualLeaveList>();
		list1 = new ArrayList<AnnualLeaveReason>();
		employeeLeave = new ArrayList<String>();
		mainMonth = -1;
	}
	
	
	public void managerSchedule(){
		
		//직원 연차 더미들을 객체로 초기화 한다.
		AnnualLeaveLoad load = new AnnualLeaveLoad();
		load.AnnualLeaveLoad();;
		this.list = load.list;
		this.list1 = load.list1;
		this.employeeLeave = load.employeeLeave;
		
		boolean loop = true;
		
		while (loop) {
			
			//메뉴
			//선택
			//분기
			String sel = menu();
			
			if (sel.equals("1")) {
				list();
			} else if (sel.equals("2")) {
				correctCalendar();
			}  else if (sel.equals("3")) {
				save();
				loop = false;
			} else {
				loop = false;
				
			}
					
		}
		
	}
	
	/**
	 * 직원연차더미, 직원연차사유더미에 저장
	 */
	public static void save() {

		//연차더미 저장
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA));
			
			for (AnnualLeaveList annual : list) {
				String a = "";
				a += annual.getName() + "■" + annual.getDivision() + "■" + annual.getPosition() + "■" + annual.getRemainvalue() +"■" +  annual.getUsedvalue() +"■" + annual.getEmployeeNum() + "\r\n";
				
				String b = "";
				for(int i=0; i<annual.getDate().size(); i++) {
					b+= annual.getDate().get(i) + ",";
				}
				a += b + "\r\n" + "==============" + "\r\n";
				
				writer.write(a);	
				
			}
			
			writer.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		//연차사유더미 저장
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA1));
			
			for (AnnualLeaveReason annual : list1) {
				String a = "";
				a += annual.getName() + "■" + annual.getDivision() + "■" + annual.getPosition() + "■" + annual.getRemainvalue() +"■" +  annual.getUsedvalue() +"■" + annual.getDate() + "■" + annual.getReason() + "■" + annual.getType() + "■" + annual.getAplyDate() + "■" + annual.getCheck() +"\r\n";
				a += "===========" + "\r\n";
				
				//맨 마지막 list정보를 문자열로 직원 연차 사유더미에 저장
				writer.write(a);	
				
			}
			
			writer.close();
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		System.out.println("저장을 완료했습니다.");
		
		
		
	}
	
	/**
	 *직원 스케줄 캘린더를 수정할 수 있는 클래스
	 */
	private void correctCalendar() {
		System.out.println("================");
		System.out.println("<연차신청 달>");
		System.out.println("1. 1월");
		System.out.println("2. 2월");
		System.out.println("3. 3월");
		System.out.println("4. 4월");
		System.out.println("5. 5월");
		System.out.println("6. 6월");
		System.out.println("7. 7월");
		System.out.println("8. 8월");
		System.out.println("9. 9월");
		System.out.println("10. 10월");
		System.out.println("11. 11월");
		System.out.println("12. 12월");
		System.out.println();
		System.out.println("===============");
		System.out.println("번호 입력 (0: 뒤로가기) :");
		
		int mainMonth = scan.nextInt();
		if (mainMonth == 1 || mainMonth == 2 || mainMonth == 3 || mainMonth == 4 || mainMonth == 5 || mainMonth == 6 || mainMonth == 7 || mainMonth == 8 || mainMonth == 9 || mainMonth == 10 || mainMonth == 11 || mainMonth == 12) {
			
			this.mainMonth = mainMonth;
			scheduleCalendar(mainMonth);
			
		} else {
			System.out.println("입력한 번호를 다시 확인 하십시오.");
		}
		System.out.println("================");
	}
	
	/**
	 * 입력한 달의 달력을 보여주는 메소드 입니다.
	 * 
	 */
	
	private void scheduleCalendar(int mainMonth) {
		
		
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mainMonth-1);
		c.set(Calendar.DATE, 1);
		
		
		//구하고자 하는 달의 시작일의 요일을 구함
		int weekFirstDay = c.get(Calendar.DAY_OF_WEEK);
		
		//구하고자 하는 달의 마지막 일을 구함
		Calendar lastC = Calendar.getInstance();
		lastC.set(Calendar.MONTH, mainMonth);
		lastC.set(Calendar.DATE, 1);
		lastC.add(Calendar.DATE	, - 1);
		int weekLastDate = lastC.get(Calendar.DATE);
		
				
		

		System.out.println("=======================================================");
		System.out.println("          	      <스케줄 확인>");
		System.out.printf("                      %d년 %d월                  \n", c.get(Calendar.YEAR), c.get(Calendar.MONTH) +1);
		 System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", "월", "화", "수","목", "금", "토", "일");  
		
		//1일이 시작하기 전 공백을 위한 반복
		for(int i=0 ; i<weekFirstDay-1; i++	) {
			System.out.printf("\t");
		}
		

		//날짜 반복
		for (int i=1; i<=weekLastDate; i++) {
			
			c.set(Calendar.DAY_OF_MONTH, i);
			
			
			//토요일이면 출력하고 다음줄로 이동
			if(c.get(Calendar.DAY_OF_WEEK)==7) {
				System.out.printf("%d\t\n", i);
				
				
			} else {
				//토요일을 제외한 날 출력
				System.out.printf("%d\t ", i);
				
			}
		}//for
		
		System.out.println();
		System.out.println("-------------------------------------------------------");
		System.out.println();
		
		//달력의 1일 날짜로 다시 설정
		c.set(Calendar.DATE, 1);
		
		//각 날짜에 대한 연차 사용 직원 출력
		annualLeave(c,weekLastDate);
		
		System.out.println("=======================================================");
		System.out.println("날짜(일) 입력:");
		System.out.println("(0: 뒤로가기)");
		
		int nextNum = scan.nextInt();
		pause();
		
		
		
		//날짜 상세보기 메소드
		if (0 < nextNum && nextNum< weekLastDate) {
			scheduleCalendarDetail(nextNum);
			
		} else {
			System.out.println("입력한 번호를 다시 확인 하십시오.");
		}
		
		
		
		
	}//month
	

	/**
	 * 스케줄 캘린더 자세히 보기
	 */
	private void scheduleCalendarDetail(int nextNum) {

		System.out.println("==========================");
		System.out.printf("%d월 %d일 스케줄\n", mainMonth, nextNum);
		System.out.println("==========================");
		Calendar now = Calendar.getInstance();
		now.set(Calendar.MONTH, mainMonth -1);
		now.set(Calendar.DATE, nextNum);
		
		//해당날짜 연차를 사용하는 직원의 이름과 날짜를 저장한다.
		ArrayList<String> usedList = new ArrayList<String>(); 
		
		//해당 날짜에 연차를 사용하는 직원의이름을 출력한다.
		for(int i=0; i<employeeLeave.size(); i++) {
			
			if(employeeLeave.get(i).indexOf(String.format("%tF", now))!=-1) {
				
				String name = employeeLeave.get(i).substring(0,3);
				System.out.println(" - "+ name);
				
				//해당 날짜에 연차를 사용하는 직원의 연차 종류와 연차 사유를 출력한다.
				for(AnnualLeaveReason a : list1) {
					if(a.getName().equals(name) && a.getDate().equals(String.format("%tF", now)) && a.getCheck().equals("결재완료")) {
						System.out.printf("연차 종류: %s\n", a.getType()	);
						System.out.printf("연차 사유: %s\n", a.getReason());
						System.out.println("----------");
						usedList.add(a.getName()+ "," + a.getDate());
					}
				}
				
				
			} 
		}
		
		System.out.println("\n1. 추가하기");
		System.out.println("2. 삭제하기");
		System.out.println("==========================");
		
		System.out.println("번호입력 (0: 뒤로가기):");
		String num = scan.nextLine();
		pause();
		
		if(num.equals("0")) {
			scheduleCalendar(mainMonth);
		} else if ( num.equals("1")) {
			addAnnualLeave(now);
		} else if (num.equals("2")) {
			removeAnnualLeave(usedList);
		} else {
			System.out.println("번호를 잘못 입력 하셨습니다.");
			scheduleCalendar(mainMonth);
		}
		
		
	}
	
	
	/**
	 * 스케줄 삭제하기
	 */
	
	private void removeAnnualLeave(ArrayList<String> usedList) {
		System.out.println("===================");
		System.out.println("<삭제할 직원 선택>");
		
		//01. 박창윤,2021-01-01
		for( int i=0; i<usedList.size(); i++) {
			System.out.printf("%02d. %s\n", i+1, usedList.get(i));
		}
		
		System.out.println("===================");
		System.out.println("번호입력 (0: 뒤로가기): ");
		
		String num = scan.nextLine();
		pause();
		
		if(num.equals("0")) {
			scheduleCalendar(mainMonth);
		} 
		
		//list에서 삭제
		for (AnnualLeaveList a : list) {
			if(a.getName().equals(usedList.get(Integer.parseInt(num) - 1).substring(0,3))){
				for(int i=0; i<a.getDate().size(); i++) {
					if(a.getDate().get(i).equals(usedList.get(Integer.parseInt(num) - 1).substring(4))){
						a.getDate().remove(i);
						
					} 
				}
			}
		}
		
		//list1에서 삭제
		for (int i=0; i<list1.size(); i++) {
			if(list1.get(i).getName().equals(usedList.get(Integer.parseInt(num) - 1).substring(0,3)) && list1.get(i).getDate().equals(usedList.get(Integer.parseInt(num) - 1).substring(4))){
				list1.remove(i);
			}
		}
		
		
		//employeeLeave에서 삭제
		for (int i=0; i<employeeLeave.size(); i++) {
		if(employeeLeave.get(i).equals(usedList.get(Integer.parseInt(num) - 1))){
			employeeLeave.remove(i);
		}
	}
		
		
	}

	/**
	 * 스케줄 추가하기
	 */

	private void addAnnualLeave(Calendar selectDay) {
		
		
		System.out.println("===============");
		System.out.printf("<%tF 스케줄 추가>\n", selectDay);
		System.out.println("이름:");
		String name = scan.nextLine();
		System.out.println("연차 종류(오후반차, 오전반차, 연차):	");
		String type = scan.nextLine();
		System.out.println("연차 사유(10글자 이상):\n");
		String reason = scan.nextLine();
		
		System.out.println("===============");
		
		//list2에 추가
		AnnualLeaveReason newReason = new AnnualLeaveReason();
		
		newReason.setName(name);
		newReason.setType(type);
		newReason.setReason(reason);
		newReason.setDate(String.format("%tF", selectDay));
		Calendar now = Calendar.getInstance();
		newReason.setAplyDate(String.format("%tF", now));
		newReason.setCheck("결재완료");
		
		for(AnnualLeaveList a : list) {
			if(a.getName().equals(name)) {
				newReason.setDivision(a.getDivision());
				newReason.setPosition(a.getPosition());
				newReason.setRemainvalue(a.getRemainvalue());
				newReason.setUsedvalue(a.getUsedvalue());
			}
		}
		
		list1.add(newReason);
		
		
		//list에 추가

		for(AnnualLeaveList a : list) {
			if( a.getName().equals(name)) {
				a.addDate(String.format("%tF", selectDay));
			}
		}
		
		
		// employeeLeave 에 추가
		String newInfo = "";
		newInfo = name + "," + String.format("%tF", selectDay);
		employeeLeave.add(newInfo);
		
		

		
	}
	
	/**
	 * 연차 사유를 출력하는 메소드
	 */

	private void annualLeave(Calendar c, int weekLastDate) {
	
		boolean result = true;
		while(result) {
			
			
			//날짜 별로 이름을 담을 어레이리스트 선언
			ArrayList<String> temp = new ArrayList<String>();
			
			for(int i=0; i<employeeLeave.size(); i++) {

				//employeeLeave 배열에 저장된 값과 캘린더에서 받은 날짜 값이 같으면 어레이리스트에 저장
				//employeeLeave - "한영민,2021-04-13",  calendar-"2021-05-01"
				int year = Integer.parseInt(employeeLeave.get(i).substring(4, 8));
				int month = Integer.parseInt(employeeLeave.get(i).substring(9, 11)) - 1;
				int date= Integer.parseInt(employeeLeave.get(i).substring(12, 14));
				
				if((c.get(Calendar.YEAR)==year) && (c.get(Calendar.MONTH)==month) && (c.get(Calendar.DATE)==date)) {
					
					//이름을 어레이리스트에 추가
					temp.add(employeeLeave.get(i).substring(0,3));
					
				}
				
			}//for문
			
			
			if(temp.size()>0) {
				
				System.out.printf("%tF 연차 사용 직원: ", c);
				for (int i=0; i<temp.size(); i++) {
					
					if(i==temp.size()-1) {
						System.out.printf("%s", temp.get(i));
					} else {
						System.out.printf("%s,", temp.get(i));
					}
				}
				System.out.println();
				
			}
			
			//캘린더에 1을 더해서 다음 날짜로 이동
			c.add(Calendar.DATE, 1);
			
			//캘린더의 날짜가 달의 마지막 날이면 while문 종료
			if(c.get(Calendar.DATE) == weekLastDate) {
				result = false;
			}
		}//while
		

		
		
	}
	


	/**
	 * 연차신청서 목록을 보여주는 메소드
	 */
	
	private void list() {

		System.out.println("==========================================");
		System.out.println("<연차 신청서 목록>");
		System.out.print("[번호]\t[신청날짜]\t\t[이름]\t[연차종류]\t\t[연차날짜]\t\t[연차사유]\n");
		
		HashMap<Integer, String> selectName = new HashMap<Integer, String>();
		HashMap<Integer, String> selectDate = new HashMap<Integer, String>();
		int num = 1;
		for(AnnualLeaveReason a : list1) {
			
			if( a.getCheck().equals("미확인")) {
				String content = a.getReason();
				if(content.length() >10) {
					 content = a.getReason().substring(0, 10) + "..";
				}
				System.out.printf("%d\t%s\t%s\t%s\t\t%s\t%s\n", num, a.getAplyDate(), a.getName(), a.getType(), a.getDate(), content );
				selectName.put(num, a.getName());
				selectDate.put(num, a.getDate());
				num++;
			}
			}
			
		System.out.println("==========================================");
		System.out.println("번호입력 (0: 뒤로가기) : ");
		int selectNum = scan.nextInt();
		pause();
		
		if(selectNum > num || selectNum < 0) {
			System.out.println("번호를 잘못입력 하셨습니다.");
			list();
		} else if (selectNum == 0) {
			
		} else {
			
			for (AnnualLeaveReason selectEmployee : list1) {
				if(selectEmployee.getName().equals(selectName.get(selectNum)) && selectEmployee.getDate().equals(selectDate.get(selectNum)) ) {
					listDetail(selectEmployee);
					
				}
			}
			//선택한 번호의 직원 정보가 들어있는 배열 번호를 넘겨준다.
		}
		
		
		
	}

	
	private void listDetail(AnnualLeaveReason selectEmployee) {
		
		System.out.println(selectEmployee);
		
		//직원연차정보객체에서 선택한 직원의 사원번호를 가져온다.
		String selectEmployeeNum = "";
		for(AnnualLeaveList a : list) {
			if(a.getName().equals(selectEmployee.getName())) {
				 selectEmployeeNum = a.getEmployeeNum();
			}
		}
		
		System.out.println("=================================================");
		System.out.println("\t\t<연차 신청 직원>");
		System.out.println("=================================================");
		System.out.printf("사원명| %s\n" ,selectEmployee.getName());
		System.out.printf("사원번호| %s\n" ,selectEmployeeNum);
		System.out.printf("소속| %s %s\n" ,selectEmployee.getDivision(), selectEmployee.getPosition());
		System.out.printf("연차종류| %s\n" ,selectEmployee.getType());
		System.out.printf("잔여 연차| %s\n", selectEmployee.getRemainvalue());
		System.out.printf("연차사용날짜| %s\n" ,selectEmployee.getDate());
		System.out.printf("신청날짜| %s\n" ,selectEmployee.getAplyDate());
		System.out.printf("연차사유| %s\n\n\n" ,selectEmployee.getReason());
		
		System.out.println("1. 결재");
		System.out.println("2. 반려");
		System.out.println("=================================================");
		System.out.println("번호입력(0: 뒤로가기) :");
		int sel = scan.nextInt();
		pause();
		
		//결제했을때 
		if( sel == 1 ) {
			
			//list에 해당 직원의 연차 날짜를 추가함
			for (AnnualLeaveList a : list) {
				if(a.getName().equals(selectEmployee.getName())) {
					a.addDate(selectEmployee.getDate());
					a.setRemainvalue(Integer.parseInt(a.getRemainvalue()) - 1 + "");
					a.setUsedvalue(Integer.parseInt(a.getUsedvalue()) + 1 + "");
				}
			
			}
			
			//list1에서 check값 정정
			int a = 0; 
			for (AnnualLeaveReason b : list1) {
				if(b.getName().equals(selectEmployee.getName())) {
					b.setRemainvalue(Integer.parseInt(b.getRemainvalue()) - 1 + "");
					b.setUsedvalue(Integer.parseInt(b.getUsedvalue()) + 1 + "");
						
					if(b.getDate().equals(selectEmployee.getDate())){
						b.setCheck("결재완료");
						
					}
				}
				a++;
			}
			
			//employeeLeave 수정
			employeeLeave.add(selectEmployee.getName() + "," + selectEmployee.getDate());
			
			System.out.println("연차사용이 결재 되었습니다.");
			
			
			//반려했을 때
		} else if (sel == 2) {
			
			
			
			//list1에서 check값 정정
			int a = 0; 
			for (AnnualLeaveReason b : list1) {
				if(b.getName().equals(selectEmployee.getName()) && b.getDate().equals(selectEmployee.getDate())) {
					b.setCheck("반려완료");
				}
				a++;
			}
			
			System.out.println("연차사용이 반려 되었습니다.");
			
			
		} else if (sel == 0) {
			
			list();
		}
		
		
	
		
	}


	private String menu() {

		System.out.println("=====================");
		System.out.println("<직원 스케줄>");
		System.out.println("1. 연차 신청서 목록 보기");
		System.out.println("2. 직원 스케줄 캘린더 수정하기");
		System.out.println("3. 저장 후 나가기");
		System.out.println("=====================");
		System.out.println("번호입력(0: 뒤로가기):");
		String num = scan.nextLine();
		pause();
		
		return num;
		
	}

	
	private static void pause() {
		System.out.println("엔터를 누르시면 다음으로 진행합니다.");
		scan.nextLine();//Block
	}
	
}

