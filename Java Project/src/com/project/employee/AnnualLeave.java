package com.project.employee;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.MainClass;
import com.project.manager.AnnualLeaveLoad;


public class AnnualLeave {
	
	private static final String DATA;
	private static final String DATA1;
	private static Scanner scan;
	public static int mainMonth;
	private static ArrayList<AnnualLeaveList> list;
	private static ArrayList<AnnualLeaveReason> list1;
	private static ArrayList<String> employeeLeave;
	
	static {
		DATA = "C:\\Project\\직원연차더미.txt";
		DATA1 = "C:\\Project\\직원연차사유더미.txt";
		scan = new Scanner(System.in);
		mainMonth = -1;
		list = new ArrayList<AnnualLeaveList>();
		list1 = new ArrayList<AnnualLeaveReason>();
		employeeLeave = new ArrayList<String>(); //"직원이름, 연차날짜"가 저장된 배열
	}
	
	public void annualLeave(){
		
		//연차더미를 객체로 만듬
		AnnualLeaveLoad load = new AnnualLeaveLoad();
		load.AnnualLeaveLoad();;
		AnnualLeave.list = AnnualLeaveLoad.list;
		AnnualLeave.list1 = AnnualLeaveLoad.list1;
		AnnualLeave.employeeLeave = AnnualLeaveLoad.employeeLeave;
		
		menu();
		
		
	}
	

	/**
	 * 연차 신청할 달을 선택하는 메뉴를 보여준다.
	 */
	private void menu() {
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
			
			AnnualLeave.mainMonth = mainMonth;
			scheduleCalendar(mainMonth);
			
		} else if( mainMonth == 0) {
			
		}else {
			System.out.println("입력한 번호를 다시 확인 하십시오.");
			menu();
		}
		System.out.println("================");
		
	}//memu();

	
	
	/**
	 * 입력한 달의 달력을 보여준다.
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
		//System.out.println("|   일   |   월   |   화   |   수   |   목   |   금   |   토   |" );
		 System.out.printf("%s\t%s\t%s\t%s\t%s\t%s\t%s\t\n", "월", "화", "수","목", "금", "토", "일");  
		
		//1일이 시작하기 전 공백을 위한 반복
		for(int i=0 ; i<weekFirstDay-1; i++	) {
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
		//Schedule클래스에서 연차사용한 직원불러옴
		annualLeave(c, weekLastDate);
		
		System.out.println("=======================================================");
		System.out.println("날짜(일) 입력:");
		System.out.println("(0: 뒤로가기)");
		
		int nextNum = scan.nextInt();
		
		
		//연차 신청 화면으로 넘어가기
		if (0 < nextNum && nextNum< weekLastDate) {
			
			//선택한 날짜를 캘런더에 세팅
			Calendar selectDay = Calendar.getInstance();
			selectDay.set(Calendar.MONTH, mainMonth - 1);
			selectDay.set(Calendar.DATE, nextNum);
			
			boolean right = false;
			
			for(AnnualLeaveList person : list) {
				//신청한 연차날짜에 같은 부서의 사람이 있으면 중복
				if (person.getDivision().equals(MainClass.logEmployee.getDivision())) {
						
						for(int i=0; i<person.getDate().size(); i++) {
							if( person.getDate().get(i).equals(String.format("%tF", selectDay))) {
								right = true;
								System.out.println("연차날짜 중복입니다.");
								System.out.printf("%s %s님이 이미 연차를 사용하셨습니다.\n", person.getDivision(), person.getName());
								pause();
								pause();
							scheduleCalendar(mainMonth);
							}
						}
				}
				
			}
			
			if(right == false) {
				AnnualLeaveApplication(nextNum);
			}
			
		} else if(nextNum == 0){
			pause();
			menu();
			
		} else {
			System.out.println("입력한 번호를 다시 확인 하십시오.");
			scheduleCalendar(mainMonth);
		}
		
		
		
	}

	/**
	 * 각 날짜에 대한 연차 사용 직원 출력
	 * 
	 */
	//System.out.println(now.get(Calendar.WEEK_OF_YEAR));	//몇주차
	public void annualLeave(Calendar c, int weekLastDate) {
		
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
			
			//출력
			//2021-01-06 연차 사용 직원: 정희윤,한희인,정래윤,임민효
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
	 * 연차신청을 하기 위한 화면
	 */
	private void AnnualLeaveApplication(int nextNum) {

		//ID: 20028420
		//PW: 7558
		
		
		//선택한 날짜를 캘런더에 세팅
		Calendar selectDay = Calendar.getInstance();
		selectDay.set(Calendar.MONTH, mainMonth - 1);
		selectDay.set(Calendar.DATE, nextNum);
		System.out.println("=====================");
		System.out.println("<연차신청>");
		System.out.printf("선택한 날짜: %tF\n", selectDay);
		
		//남은 연차 일수를 불러오기 위한 메소드
		String date = remainAnnualLeave(selectDay);
		System.out.printf("사용 가능한 연차 일수 : %s\n", date);
		
		System.out.println("1. 오전반차");
		System.out.println("2. 오후반차");
		System.out.println("3. 연차");
		System.out.println("연차 종류 번호를 입력하십시오 (뒤로가기 : 0): ");
		scan.nextLine();
		String leaveType = scan.nextLine();
		
		if(leaveType.equals("0")) {
			scheduleCalendar(mainMonth);
		}
		
		if(!leaveType.equals("1") && !leaveType.equals("2") &&  !leaveType.equals("3")) {
			System.out.println("잘못입력 하셨습니다.");
			AnnualLeaveApplication(nextNum);
		}
		
		System.out.println("연차 사유를 입력하십시오(10자 이상): ");
		String leaveReason = scan.nextLine();
		
		System.out.println("=====================");
		
			
			for(int i=0; i<list.size(); i++){
				if(list.get(i).getName().equals(MainClass.logEmployee.getName())) {
					AnnualLeaveReason a = new AnnualLeaveReason();
					a.setName(MainClass.logEmployee.getName());
					a.setDivision(MainClass.logEmployee.getDivision());
					a.setPosition(MainClass.logEmployee.getPosition());
					a.setRemainvalue(list.get(i).getRemainvalue());
					a.setUsedvalue(list.get(i).getUsedvalue());
					a.setDate(String.format("%tF",selectDay));
					if (leaveType.equals("1")) {
						a.setType("오전반차");
					} else if (leaveType.equals("2")) {
						a.setType("오후반차");
					} else  {
						a.setType("연차");
					} 
					a.setReason(leaveReason);
					a.setCheck("미확인");
					
					Calendar now = Calendar.getInstance();
					a.setAplyDate(String.format("%tF", now));
					
					
					list1.add(a);
				}
				
			}
			
			save(leaveType, leaveReason);
			
	}

	
	
	
	/**
	 *연차사유더미를 저장 
	 */
	private void save(String leaveType, String leaveReason) {
		
		try {
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA1));
			
			for (AnnualLeaveReason annual : list1) {
				String a = "";
				a += annual.getName() + "■" + annual.getDivision() + "■" + annual.getPosition() + "■" + annual.getRemainvalue() +"■" +  annual.getUsedvalue() +"■" + annual.getDate() + "■" + annual.getReason() + "■" + annual.getType() + "■" + annual.getAplyDate() + "■" + annual.getCheck() +"\r\n";
				a += "===========" + "\r\n";
				
				writer.write(a);	
				
			}
			
			writer.close();//****저장 꼭 해야합니다!!
			System.out.println("연차신청을 완료했습니다.");
			
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * 남은 연차를 String으로 반환하는 메소드
	 */
	private String remainAnnualLeave(Calendar selectDay) {
		
		String remainAnnaul = "";
		for (int i=0; i<list.size(); i++) {
			
			//로그인한 이름과 list에있는 직원별 연차정보가 같으면 남은 연차 return
			if(MainClass.logEmployee.getEmployeeNum().equals(list.get(i).getEmployeeNum())) {
				remainAnnaul = list.get(i).getRemainvalue();
			}
			
		}
		
		return remainAnnaul;
		
	}
	
	private static void pause() {
		System.out.println("엔터를 누르시면 다음으로 진행합니다.");
		scan.nextLine();//Block
	}
	
	

	

}

