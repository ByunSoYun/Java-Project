package com.project.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class EmployeeNotice {
	
	private static Scanner scan;
	private final static String EMPLOYEE_NOTICE_DATA;
	private static ArrayList<NoticeList> list3;
	
	static {
		
		scan = new Scanner(System.in);
		EMPLOYEE_NOTICE_DATA = "C:\\Project\\익명게시판_공지사항_더미.txt";
		list3 = new ArrayList<NoticeList>();
		
	}
	
	
	public static void load() {
		
		try {
			
			BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE_NOTICE_DATA));
			
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				
				NoticeList noticeList = new NoticeList();
				
				String[] temp = line.split("■");
				
				noticeList.setSeq(temp[0]);
				noticeList.setTitle(temp[1]);
				noticeList.setRegDate(temp[2]);
				
				String tempContent = "";
				
				while (!(line = reader.readLine()).equals("=====================")) {
					
					tempContent += line + "\r\n";
					
				}
				
				noticeList.setContent(tempContent);
				
				list3.add(noticeList);
				
			}
			
			reader.close();
			
		} catch (Exception e) {
			System.out.println("EmployeeNotice load: " + e);
		}
		
	}

	/**
	 * 공지사항을 보여주는 메소드입니다.
	 */
	public static void noticeBoard() {
		
		//load();
		
		System.out.println("<공지사항 게시판>");
		System.out.println("===========================================================");
		System.out.println("[번호]\t[제목]\t\t[날짜]");
		
		for (NoticeList noticeList : list3) {
			
			String title = noticeList.getTitle().replace("\r\n", " ");
			
			if (title.length() > 5) {
				
				title = title.substring(0, 5) + "..";
				
			}
			
			System.out.printf("%s\t%s\t%s\n", noticeList.getSeq(), title, noticeList.getRegDate());
			
		}
		
		System.out.println("===========================================================");
		
		System.out.println("0: 목록 끝내기");
		
		System.out.print("게시글 자세히 보기 선택(번호): ");
		
		String sel = scan.nextLine();
		System.out.println();
		
		if (sel != "") {
		
			if (!sel.equals("0")) {
				
				System.out.println("<공지사항 게시판>");
				System.out.println("===========================================================");
				
				NoticeList noticeList = getNotice(sel);
				
				System.out.printf("등록날짜: %s\n\n", noticeList.getRegDate());
				System.out.printf("제목: %s\n\n", noticeList.getTitle());
				System.out.printf("내용: %s\n\n", noticeList.getContent());
				System.out.println("===========================================================");
				System.out.println();
				
			}
		
		}
		
		pause();
		
	}
	
	/**
	 * 공지사항을 자세히 보기 위해 필요한 메소드 입니다.
	 * @param seq
	 * @return
	 */
	private static NoticeList getNotice(String sel) {
		
		for (NoticeList noticeList : list3) {
			
			if (noticeList.getSeq().equals(sel)) {
				
				return noticeList;
				
			}
			
		}
		
		return null;
	}//getNotice
	
	/**
	 * 화면을 구현한 뒤 다음으로 넘어가려면 엔터를 입력하라는 뜻의 메서드입니다.
	 */
	private static void pause() {
		
		System.out.println("엔터를 누르면 다음으로 진행합니다.");
		
		scan.nextLine();
		
	}

}//Manager
