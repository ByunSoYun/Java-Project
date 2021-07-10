package com.project.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

public class Anonymous {

	private static Scanner scan;
	private final static String ANONYMOUS_POST_DATA;
	private final static String ANONYMOUS_COMMENT_DATA;
	private static ArrayList<AnonymousList> list;
	private static ArrayList<CommentList> list2;
	private static String wantLine;
	public static String addComment;

	static {

		scan = new Scanner(System.in);
		ANONYMOUS_POST_DATA = "C:\\Project\\익명게시판_게시글목록_더미.txt";
		ANONYMOUS_COMMENT_DATA = "C:\\Project\\익명게시판_댓글_더미.txt";
		list = new ArrayList<AnonymousList>();
		list2 = new ArrayList<CommentList>();
		wantLine = "";
		addComment = "";

	}

	public static void anonymous() {

		System.out.println("<익명 게시판>");
		System.out.println("================");
		System.out.println("1. 게시글 보기");
		System.out.println("2. 게시글 작성하기");
		System.out.println("3. 게시글 수정하기");
		System.out.println("4. 게시글 삭제하기");
		System.out.println("5. 댓글 작성하기");
		System.out.println("================");
		System.out.print("선택(0:직원 목록 화면으로 돌아가기): ");
		String sel = scan.nextLine();

		if (!sel.equals("0")) {

			if (sel.equals("1")) {

				postRead();

			} else if (sel.equals("2")) {

				postAdd();

			} else if (sel.equals("3")) {

				postUpdate();

			} else if (sel.equals("4")) {

				postDelete();

			} else if (sel.equals("5")) {

				commentAdd();

			}

		}
		Anonymous.postSave();
		Anonymous.commentSave();

	}

	/**
	 * 댓글을 저장하게 해주는 메소드입니다.
	 */
	public static void commentSave() {

		String temp = "";
		// 2■댓글: 잘되실 겁니다!■댓글: 힘내세요!
		for (CommentList c : list2) {

			try {

				temp += c.getCommentList() + "\r\n" + "=====================" + "\r\n";

			} catch (Exception e) {
				System.out.println(e);
			}

		}

		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(ANONYMOUS_COMMENT_DATA));

			writer.write(temp);

			writer.close();
		} catch (Exception e) {
			System.out.println("commentSave: " + e);
		}

	}// commentSave

	/**
	 * 게시글 작성, 수정, 삭제 등 변경 사항을 저장하는 메서드입니다.
	 */
	public static void postSave() {

		try {

			// 1■회사앞으로 익명의 기부자가 나타났습니다!!■2021-03-25
			// 아무거나내용아무거나내용
			// 내용아무거나내용아무거나

			BufferedWriter writer = new BufferedWriter(new FileWriter(ANONYMOUS_POST_DATA));

			for (AnonymousList anonymousList : list) {

				writer.write(String.format("%s■%s■%s\n%s=====================\n", anonymousList.getSeq(),
						anonymousList.getTitle(), anonymousList.getRegdate(), anonymousList.getContent()));

			}

			writer.close();

		} catch (Exception e) {
			System.out.println("postSave: " + e);
		}

	}// postSave

	/**
	 * 댓글을 load하는 메서드입니다.
	 */
	public static void commentLoad() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(ANONYMOUS_COMMENT_DATA));

			String line = "";

			while ((line = reader.readLine()) != null) {

				CommentList commentList = new CommentList();

				String[] temp = line.split("■");

				for (int i = 0; i < temp.length; i++) {

					commentList.setSeq(temp[0]);
					commentList.addComment(temp[i]);

				}

//				String tempContent = "";

				while (!(line = reader.readLine()).equals("=====================")) {

//					tempContent += line + "\r\n";

				}

//				commentList.setComment3(tempContent);
				list2.add(commentList);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println("익명게시판댓글load2: " + e);
		}

	}

	/**
	 * 게시글 수정하는 메소드입니다.
	 */
	private static void postUpdate() {

		System.out.println("<게시글 수정하기>");

		System.out.println("=====================================");
		System.out.println("[번호]\t[제목]\t\t[날짜]");

		postList();

		System.out.print("수정할 게시글 자세히 보기 선택(번호): ");

		String seq = scan.nextLine();
		System.out.println();

		if (seq != "") {

			if (!seq.equals("0")) {

				System.out.println("<익명 게시판>");
				System.out.println("===========================================================");

				AnonymousList anonymousList = getAnony(seq);

				System.out.printf("등록날짜: %s\n\n", anonymousList.getRegdate());
				System.out.printf("제목: %s\n\n", anonymousList.getTitle());
				System.out.printf("내용: %s\n", anonymousList.getContent());
				System.out.println("===========================================================");

				try {

					Calendar now = Calendar.getInstance();
					anonymousList.setRegdate(String.format("%tF\n", now));

					System.out.print("수정할 제목: ");
					String title = scan.nextLine();
					anonymousList.setTitle(title);

					System.out.print("수정할 내용(exit: 입력종료): ");
					String content = "";

					while (true) {

						String temp = scan.nextLine();

						if (temp.equals("exit")) {

							break;

						}

						content += temp + "\r\n";

					}

					anonymousList.setContent(content);

					BufferedWriter writer = new BufferedWriter(new FileWriter(ANONYMOUS_POST_DATA));

					writer.write(anonymousList.getRegdate());
					writer.write(anonymousList.getTitle());
					writer.write(anonymousList.getContent());

					writer.close();

				} catch (Exception e) {
					System.out.println("postUpdate: " + e);
				}

			}

		}

		System.out.println();

		System.out.println("게시글 수정이 완료되었습니다.");

	}// postUpdate

	/**
	 * 게시글 목록을 보여주는 메소드입니다.
	 */
	private static void postList() {

		for (AnonymousList anonymousList : list) {

			String title = anonymousList.getTitle().replace("\r\n", " ");

			if (title.length() > 5) {

				title = title.substring(0, 5) + "..";

			}

			System.out.printf("%s\t%s\t%s\n", anonymousList.getSeq(), title, anonymousList.getRegdate());

		}

		System.out.println("=====================================");

		System.out.println("0: 목록 끝내기");
	}

	/**
	 * 게시글을 삭제할 수 있는 메서드 입니다.
	 */
	private static void postDelete() {

		System.out.println("<게시글 삭제하기>");

		System.out.println("=====================================");
		System.out.println("[번호]\t[제목]\t\t[날짜]");

		postList();

		System.out.print("삭제할 게시글 선택: ");

		String seq = scan.nextLine();

		if (!seq.equals("0")) {

			for (int i = 0; i < list.size(); i++) {

				if (list.get(i).getSeq().equals(seq)) {

					list.remove(i);

				}

			}

		} else {

			anonymous();

		}

		System.out.println("게시글 삭제가 완료되었습니다.");

	}// postDelete

	/**
	 * 익명게시판 게시물을 작성하는 메서드입니다.
	 */
	private static void postAdd() {

		System.out.println("<익명게시판 게시물 작성>");
		System.out.println("=========================");

		System.out.print("제목: ");
		String title = scan.nextLine();

		System.out.print("내용(exit:입력종료): ");
		String content = "";

		while (true) {

			String temp = scan.nextLine();

			if (temp.equals("exit")) {
				break;
			}

			content += temp + "\r\n";

		}

		System.out.println("=========================");

		AnonymousList anonymousList = new AnonymousList();

		anonymousList.setTitle(title);
		anonymousList.setContent(content);

		Calendar now = Calendar.getInstance();
		anonymousList.setRegdate(String.format("%tF", now));
		anonymousList.setSeq(getPostSeq()); // 마지막 게시물 번호에 1을 더한다.

		list.add(0, anonymousList);

		System.out.println("새로운 게시글이 작성되었습니다.");

	}// postAdd

	/**
	 * 새로 추가하는 게시물의 번호를 만들어 주는 메서드입니다.
	 * 
	 * @return
	 */
	private static String getPostSeq() {

		int max = 0;

		for (AnonymousList anonymousList : list) {

			if (Integer.parseInt(anonymousList.getSeq()) > max) {
				max = Integer.parseInt(anonymousList.getSeq());
			}

		}

		return (max + 1) + "";

	}

	/**
	 * 익명게시판 게시글 목록을 읽어오는 메서드입니다.
	 */
	private static void postRead() {

		System.out.println("<익명 게시판>");
		System.out.println("=====================================");
		System.out.println("[번호]\t[제목]\t\t[날짜]");

		postList();

		System.out.println();
		System.out.print("게시글 자세히 보기 선택(번호): ");

		String seq = scan.nextLine();
		System.out.println();

		if (seq != "") {

			if (!seq.equals("0")) {

				System.out.println("<익명 게시판>");
				System.out.println("===========================================================");

				AnonymousList anonymousList = getAnony(seq);
				CommentList commentList = getComment(seq);

				System.out.printf("등록날짜: %s\n\n", anonymousList.getRegdate());
				System.out.printf("제목: %s\n\n", anonymousList.getTitle());
				System.out.printf("내용: %s\n", anonymousList.getContent());
				System.out.println("===========================================================");

				int j = 0;
				// int k=0;
				for (CommentList c : list2) {
					j++;
					if (seq.equals(c.getSeq())) {
						String[] temp1 = c.getCommentList().split("■");
						if (j == list2.size() - 1) {
							for (int i = 0; i < temp1.length; i++) {

								System.out.printf("%s\n\n", temp1[i]);

							}
						}
					}

				}

				System.out.println("===========================================================");

			}

		}

	}

	/**
	 * 댓글을 작성할 수 있도록 해주는 메서드입니다.
	 */
	private static void commentAdd() {

		System.out.println("<댓글 추가하기>");

		System.out.println("=====================================");
		System.out.println("[번호]\t[제목]\t\t[날짜]");

		postList();

		System.out.print("게시글 자세히 보기 선택(번호): ");

		String seq = scan.nextLine();
		System.out.println();

		if (seq != "") {

			if (!seq.equals("0")) {

				System.out.println("<익명 게시판>");
				System.out.println("===========================================================");

				AnonymousList anonymousList = getAnony(seq);

				System.out.printf("등록날짜: %s\n\n", anonymousList.getRegdate());
				System.out.printf("제목: %s\n\n", anonymousList.getTitle());
				System.out.printf("내용: %s\n", anonymousList.getContent());
				System.out.println("===========================================================");
				System.out.println(list2);

				for (int i = 0; i < list2.size(); i++) {
					System.out.println("게시글... 들어옴");
					if (list2.get(i).getSeq().equals(seq)) {
						String[] temp1 = list2.get(i).getCommentList().split("■");
						for (int j = 0; j < temp1.length; j++) {
							System.out.println("댓글보기.. 들어옴");

							System.out.printf("%s\n\n", temp1[j]);

						}

					}

				}

				System.out.println("===========================================================");

				try {

					System.out.print("추가할 댓글(exit: 입력종료): ");
					String comment = "";

					while (true) {

						String temp = scan.nextLine();

						if (temp.equals("exit")) {

							break;

						}

						comment += temp + "\r\n";

					} // while

					addComment = "댓글: " + comment;

					for (CommentList c : list2) {

						if (c.getSeq().equals(seq)) {

							c.addComment(addComment);

						}
						if(!c.getSeq().equals(seq)) {
							CommentList a = new CommentList();
							a.setSeq(seq);
							a.addComment(addComment);
							list2.add(a);
						}

					}

				} catch (Exception e) {
					System.out.println("commentAdd: " + e);
				}

			}

		}

	}// commentAdd

	/**
	 * 익명게시판 게시글의 댓글을 보여줄 수 있는 메서드입니다.
	 * 
	 * @param seq
	 * @return
	 */
	private static CommentList getComment(String seq) {

		for (CommentList commentList : list2) {

			if (commentList.getSeq().equals(seq)) {

				return commentList;

			}

		}

		return null;

	}// getComment

	/**
	 * 익명 게시판의 게시글 중 하나를 선택하면 자세히 볼 수 있는 메서드입니다.
	 * 
	 * @param seq
	 * @return
	 */
	private static AnonymousList getAnony(String seq) {

		for (AnonymousList anonymousList : list) {

			if (anonymousList.getSeq().equals(seq)) {

				return anonymousList;

			}

		}

		return null;
	}// getAnony

	public static void postLoad() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(ANONYMOUS_POST_DATA));

			String line = "";

			while ((line = reader.readLine()) != null) {

				AnonymousList anonymousList = new AnonymousList();

				String[] temp = line.split("■");

				anonymousList.setSeq(temp[0]);
				anonymousList.setTitle(temp[1]);
				anonymousList.setRegdate(temp[2]);

				String tempContent = "";

				while (!(line = reader.readLine()).equals("=====================")) {

					tempContent += line + "\r\n";

				}

				anonymousList.setContent(tempContent);

				list.add(anonymousList);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println("익명게시글load: " + e);
		}

	}

	/**
	 * 화면을 구현한 뒤 다음으로 넘어가려면 엔터를 입력하라는 뜻의 메서드입니다.
	 */
	private static void pause() {

		System.out.println("엔터를 누르면 다음으로 진행합니다.");

		scan.nextLine();

	}

}
