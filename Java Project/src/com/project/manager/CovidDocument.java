package com.project.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;
import java.util.Scanner;

/**
 * 
 * 전체 직원의 코로나설문조사 중 
 * 오늘날짜기준으로 
 * 설문조사에 이상이 있는 경우만 보여준다.
 *
 */

public class CovidDocument {

	private final static String DATA;  // 완료된 설문지가 저장되어 있는 파일 경로
	private static Calendar today; // 오늘날짜
	private static Calendar covidDate; // 설문조사 한 날
	private static Scanner scan;
	private static String number;  //설문지를 자세히 확인할 직원의 사원번호

	static {
		DATA = "C:\\Project\\COVID완료.txt";
		scan = new Scanner(System.in);
		today = Calendar.getInstance();
		covidDate = Calendar.getInstance();
	}

	
	public void covidDocument() {

		System.out.println("\n===================\n");
		System.out.println("코로나 자가진단서 목록\n");
		System.out.printf("오늘: %tF\n", today);
		
		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";
			/* 
			 * 유민우■20198974■2021-05-07 
			 * 1■발열(37.5C°이상)이 있습니까?: ■n 
			 * 2■호흡기(감기) 증상이 있습니까?: ■n
			 * 3■최근 14일 이내에 해외를 방문한 사실이 있습니까?: ■n 
			 * 4■확진 환자와 밀접하게 접촉한 후 14일 이내에 발열 또는 호흡기 증상이 타난 사실이 있습니까?: ■n 
			 * 5■감염우려가 높은 지역(밀집지역)을 방문하신 사실이 있습니까?: ■n
			 * ==========
			 */
			int i = 1;
			
			while ((line = reader.readLine()) != null) {

				String[] temp = line.split("■");
				String date = temp[2];

				int year = Integer.parseInt(date.substring(0, 4));
				int month = Integer.parseInt(date.substring(5, 7));
				int day = Integer.parseInt(date.substring(8, 10));

				covidDate.set(year, month - 1, day);  // 

				String result = "";
				result += line + "\r\n";

				while (!(line = reader.readLine()).equals("==========")) {
					result += line + "\r\n";
				}

				long tick = (today.getTimeInMillis() / 1000 / 60 / 60 / 24)
						- (covidDate.getTimeInMillis() / 1000 / 60 / 60 / 24);

				if (result.contains("y") && tick == 0) {
					System.out.printf("%d. <%s> %s\n", i, temp[0], temp[1]);
					i++;
				}

			} // while

			reader.close();

			while(true) {
				
				System.out.print("확인할 사원의 사원번호를 입력하세요(0:뒤로가기): ");
				number = scan.nextLine();
				
				if(!number.equals("0")) {
					details();
				}else {
					ManagerOption.option();
				}
				
			}
			

		} catch (Exception e) {
			System.out.println("load: " + e);
		}

	}

	private void details(){

		try {

			BufferedReader reader1 = new BufferedReader(new FileReader(DATA));
			String line;
			while ((line = reader1.readLine()) != null) {

				String[] temp = line.split("■");

				String result = "";
				result += "<" + temp[0] + "> " + temp[1] + " " + temp[2] + "\r\n";

				while (!(line = reader1.readLine()).equals("==========")) {
					String[] temp1 = line.split("■");
					result += temp1[0] + temp1[1] + "  " + temp1[2] + "\r\n";
				}

				if (number.equals(temp[1])) {
					System.out.println(result);
				}

			} // while

		} catch (Exception e) {
			System.out.println(e);
		}

	}//details

}//CovidDocument
