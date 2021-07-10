package com.project.manager;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.employee.NoticeList;

public class ManagerNotice {

	private static Scanner scan;
	private final static String ANONYMOUS_NOTICE_DATA;
	private static ArrayList<NoticeList> list3;

	static {

		scan = new Scanner(System.in);
		ANONYMOUS_NOTICE_DATA = "C:\\Project\\익명게시판_공지사항_더미.txt";
		list3 = new ArrayList<NoticeList>();

	}
	
	public static void managerNotice() {
		
		//load();

		boolean loop = true;

		while (loop) {

			String sel = menu();

			if (sel.equals("1")) {

				noticeAdd();

			} else if (sel.equals("2")) {

				noticeUpdate();

			} else if (sel.equals("3")) {

				noticeDelete();

			} else {

				loop = false;

			}

			System.out.println("프로그램을 종료합니다.");
			System.out.println();

			manegerNoticeSave();

		}
	}

	/**
	 * 새로 작성한 공지사항 게시글을 저장해주는 메소드입니다.
	 */
	private static void manegerNoticeSave() {

		try {

			BufferedWriter writer = new BufferedWriter(new FileWriter(ANONYMOUS_NOTICE_DATA));

			for (NoticeList noticeList : list3) {

				writer.write(String.format("%s■%s■%s\n%s=====================\n", noticeList.getSeq(),
						noticeList.getTitle(), noticeList.getRegDate(), noticeList.getContent()));

			}

			writer.close();

		} catch (Exception e) {
			System.out.println("manegerNoticeSave: " + e);
		}

	}

	public static void load() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(ANONYMOUS_NOTICE_DATA));

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

	}// load

	/**
	 * 관리자 전용 공지 게시판에서 게시글을 삭제할 수 있는 메소드입니다.
	 */
	private static void noticeDelete() {

		System.out.println("<공지사항 삭제>");
		System.out.println("=================================================");
		System.out.println("[번호]\t[제목]\t[날짜]");

		for (NoticeList noticeList : list3) {

			String title = noticeList.getTitle().replace("\r\n", " ");

			if (title.length() > 5) {

				title = title.substring(0, 5) + "..";

			}

			System.out.printf("%s\t%s\t%s\n", noticeList.getSeq(), title, noticeList.getRegDate());

		}

		System.out.print("삭제할 공지사항 선택(exit: 0): ");

		String seq = scan.nextLine();

		if (!seq.equals("0")) {

			for (int i = 0; i < list3.size(); i++) {

				if (list3.get(i).getSeq().equals(seq)) {

					list3.remove(i);

				}

			}

		}

		System.out.println();

		System.out.println("삭제가 완료되었습니다.");

		pause();

	}// noticeDelete

	/**
	 * 관리자 전용 공지 게시판에서 게시글을 수정할 수 있는 메소드입니다.
	 */
	private static void noticeUpdate() {

		System.out.println("<공지사항 수정하기>");
		System.out.println("=================================================");
		System.out.println("[번호]\t[제목]\t[날짜]");

		for (NoticeList noticeList : list3) {

			String title = noticeList.getTitle().replace("\r\n", " ");

			if (title.length() > 5) {

				title = title.substring(0, 5) + "..";

			}

			System.out.printf("%s\t%s\t%s\n", noticeList.getSeq(), title, noticeList.getRegDate());

		}

		System.out.println("=================================================");

		System.out.println("0: 목록 끝내기");

		System.out.print("수정할 게시글 자세히 보기 선택(번호): ");

		String sel = scan.nextLine();
		System.out.println();

		if (sel != "") {

			if (!sel.equals("0")) {

				System.out.println("<공지사항 게시판>");
				System.out.println("===========================================================");

				NoticeList noticeList = getNotice(sel);

				System.out.printf("등록날짜: %s\n\n", noticeList.getRegDate());
				System.out.printf("제목: 	%s\n\n", noticeList.getTitle());
				System.out.printf("내용: %s\n", noticeList.getContent());
				System.out.println("===========================================================");

				try {

					Calendar now = Calendar.getInstance();
					noticeList.setRegDate(String.format("%tF\n", now));

					System.out.print("수정할 제목: ");
					String title = scan.nextLine();
					noticeList.setTitle(title);

					System.out.print("수정할 내용(exit: 입력 종료): ");
					String content = "";

					while (true) {

						String temp = scan.nextLine();

						if (temp.equals("exit")) {

							break;

						}

						content += temp + "\r\n";

					}

					noticeList.setContent(content);

					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(ANONYMOUS_NOTICE_DATA));

					bufferedWriter.write(noticeList.getRegDate());
					bufferedWriter.write(noticeList.getTitle());
					bufferedWriter.write(noticeList.getContent());

					System.out.println();
					System.out.println("공지사항 수정을 완료하였습니다.");
					System.out.println();

					bufferedWriter.close();

				} catch (Exception e) {
					System.out.println("noticeUpdate: " + e);
				}

			}

		}

	}// noticeUpdate

	private static NoticeList getNotice(String sel) {

		for (NoticeList noticeList : list3) {

			if (noticeList.getSeq().equals(sel)) {

				return noticeList;

			}

		}

		return null;
	}

	/**
	 * 관리자 전용 공지 게시판에서 게시글을 등록할 수 있는 메소드입니다.
	 */
	private static void noticeAdd() {

		System.out.println("<공지게시글 작성>");
		System.out.println("===========================================");

		System.out.print("제목: ");
		String title = scan.nextLine();

		System.out.print("내용(exit: 입력종료): ");
		String content = "";

		while (true) {

			String temp = scan.nextLine();

			if (temp.equals("exit")) {
				break;
			}

			content += temp + "\r\n";

		}

		System.out.println("===========================================");

		NoticeList noticeList = new NoticeList();

		noticeList.setTitle(title);
		noticeList.setContent(content);

		Calendar now = Calendar.getInstance();
		noticeList.setRegDate(String.format("%tF", now));
		noticeList.setSeq(getNoticeSeq());

		list3.add(0, noticeList);

		System.out.println();
		System.out.println("새로운 공지사항 등록을 완료하였습니다.");
		System.out.println();

		pause();

	}// noticeAdd

	/**
	 * 새롭게 작성하는 공지사항 글에 번호를 부여하는 메소드입니다.
	 * 
	 * @return
	 */
	private static String getNoticeSeq() {

		int max = 0;

		for (NoticeList noticeList : list3) {

			if (Integer.parseInt(noticeList.getSeq()) > max) {

				max = Integer.parseInt(noticeList.getSeq());

			}

		}

		return (max + 1) + "";
	}

	/**
	 * 관리자 전용 공지게시판 메뉴를 호출하는 메소드입니다.
	 */
	private static String menu() {

		System.out.println("<관리자 전용 공지게시판>");
		System.out.println("===========================================");
		System.out.println("1. 공지 게시글 등록");
		System.out.println("2. 공지 게시글 수정");
		System.out.println("3. 공지 게시글 삭제");
		System.out.println("===========================================");
		System.out.println("0: 목록 끝내기");

		System.out.print("번호 선택: ");
		String sel = scan.nextLine();

		return sel;
	}// menu

	private static void pause() {

		System.out.println("엔터를 누르면 다음으로 진행합니다.");

		scan.nextLine();

	}

}// ManegerNotice
