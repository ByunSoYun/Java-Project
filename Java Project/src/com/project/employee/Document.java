package com.project.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

import com.project.MainClass;
/**
 * 
 * @author 3조
 * 서류 서명해야할 로그인한 본인의 자료들을 볼수있고 서류 서명을 할수 있는 클래스 
 *
 */
public class Document {

	private static Scanner scan;

	private final static String DATA;
	private final static String DATA2;
	private static File file;
	private static File file2;

	static {
		scan = new Scanner(System.in);
		DATA = "C:\\Project\\직원서류서명더미.txt";
		file = new File(DATA);
		DATA2 = "C:\\Project\\직원서류서명더미2.txt";
		file2 = new File(DATA2);

	}

	/**
	 * 
	 * 근태서류(서류서명해야할 서류)의 목록을 보여주는 메서드
	 */
	public void document() {

		System.out.println("<근태서류>");
		System.out.println("===================");
		System.out.println("1. 목록보기");
		System.out.println("===================");
		System.out.print("번호 입력(0: 뒤로가기): ");
		String sel = scan.nextLine();

		boolean loop = true;

		while (loop) {

			if (sel.equals("1")) {
				System.out.println("목록보기");
				sign();
				break;
			} else {
				break;
			}
		}

	}

	
	/**
	 * 서류서명해야하는 서류들을 서명을 할 수 있고
	 * 로그인된 자신의 이름과 서명하는 이름이 같지않은경우 데이터를 계속 저장해주는 메서드
	 */
	public void sign() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2));

			String line = "";

			int i = 1;
			int j = 1;
			while ((line = reader.readLine()) != null) {

				// 1■구대혁■지각■2021-05-07■2010004

				String[] temp = line.split("■");

				if (temp[1].equals(MainClass.logEmployee.getName())) {
					System.out.printf("%d%s\t%s\t%s\n", i, temp[1], temp[2], temp[3]);
					System.out.print("서류의 내용이 맞으면 이름을 적어주세요: ");
					String singname = scan.nextLine();

					if (!(singname.equals(MainClass.logEmployee.getName()))) {
						writer.write(String.format("%d■%s■%s■%s■%s\n", j, temp[1], temp[2], temp[3], temp[4]));
						j++;
					}
					i++;
				} else {
					writer.write(String.format("%d■%s■%s■%s■%s\n", j, temp[1], temp[2], temp[3], temp[4]));
					j++;
				}

			}
			writer.close();
			reader.close();

			file.delete();
			file2.renameTo(file);
		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
