package com.project.employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.manager.AnnualLeaveLoad;

public class Schedule {
	
	private static final String DATA;
	private static final String DATA1;
	private static Scanner scan;
	private static ArrayList<AnnualLeaveList> list;
	private static ArrayList<AnnualLeaveReason> list1;
	private static ArrayList<String> employeeLeave;
	public static int mainMonth;
	
	
	
	static{
		DATA = "C:\\Project\\직원연차더미.txt";
		DATA1 = "C:\\Project\\직원연차사유더미.txt";
		scan = new Scanner(System.in);
		employeeLeave = new ArrayList<String>(); //"직원이름, 연차날짜"가 저장된 배열
		list = new ArrayList<AnnualLeaveList>();
		list1 = new ArrayList<AnnualLeaveReason>();
		mainMonth = -1;
	}

	public void schedule() {
		
		AnnualLeaveLoad load = new AnnualLeaveLoad();
		load.AnnualLeaveLoad();;
		this.list = load.list;
		this.list1 = load.list1;
		this.employeeLeave = load.employeeLeave;
		
		menu();
		
	}


	private void menu() {
		
		System.out.println("===============");
		System.out.println("<스케줄 확인>");
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
		
		int selectNum = scan.nextInt();
		if (selectNum == 1 || selectNum == 2 || selectNum == 3 || selectNum == 4 || selectNum == 5 || selectNum == 6 || selectNum == 7 || selectNum == 8 || selectNum == 9 || selectNum == 10 || selectNum == 11 || selectNum == 12) {
			this.mainMonth = selectNum;
			scheduleCalendar(selectNum);
			
		} else if(selectNum == 0) {
			
		} else {
			System.out.println("입력한 번호를 다시 확인 하십시오.");
			menu();
		}
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
		//System.out.println("|   일   |   월   |   화   |   수   |   목   |   금   |   토   |" );
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
		
		
		//날짜 상세보기 메소드
		if (0 < nextNum && nextNum< weekLastDate) {
			pause();
			pause();
			scheduleCalendarDetail(nextNum);
			
		} else if(nextNum == 0) {
			pause();
			pause();
			menu();
		}
		else {
			pause();
			pause();
			System.out.println("입력한 번호를 다시 확인 하십시오.");
			menu();
		}
		
		
		
		
	}//month

	

	/**
	 * 날짜별 연차직원정보 상세히 보기 페이지입니다.
	 * 
	 */
	
	private void scheduleCalendarDetail(int nextNum) {
		
		
		//입력한 날짜로 캘린더 셋팅
		Calendar c = Calendar.getInstance();
		c.set(Calendar.MONTH, mainMonth-1);
		c.set(Calendar.DATE, nextNum);
		
		System.out.println("================");
		System.out.printf("<%tF 스케줄 확인>\n", c);
		
		//연차를 쓴 사람의 수를 세기 위한 변수(없을 때 없다고 해야함)
		int num = 0; 
		
		for(AnnualLeaveReason a : list1) {
			if(a.getDate().equals(String.format("%tF", c)) && a.getCheck().equals("결재완료")) {
				System.out.println("- " + a.getName() + "(" + a.getDivision() + ")");
				System.out.printf("연차종류: %s\n", a.getType());
				System.out.printf("연차사유: %s\n\n", a.getReason());
				num++;
			}
		}
		
		
		//연차를 쓴 사람이 없을 때 출력
		if( num==0) {
			System.out.println("연차를 쓴 사람이 없습니다.");
		}
		System.out.println("================");
		
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
	
	private static void pause() {
		System.out.println("Enter를 누르시면 목록으로 이동합니다.");
		scan.nextLine();// Block
	}// pause
	
	
}
