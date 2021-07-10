package com.project.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Scanner;

import com.project.MainClass;

/**
 * @author 3조
 * 코로나 자가진단서를 작성하여 
 * 질의응답을 파일에 저장하여
 * 관리자가 확인할 수 있도록 해주는 클래스
 *
 */
public class Covid {

	private final static String DATA;  // 설문조사 질문지가 저장되어있는 파일경로
	private final static String DATA2; // 완료된 설문조사를 저장할 파일경로

	private static Scanner scan;

	static {
		scan = new Scanner(System.in);
		DATA = "C:\\Project\\COVID.txt";
		DATA2 = "C:\\Project\\COVID완료.txt";
	}

	
	/**
	 * 설문지의 내용을 읽어오고 Scanner로 응답을 받아 파일에 저장해주는 메서드
	 */
	public void covid() {

		String answer = "";

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2,true));  //완료된 설문조사를 파일에 추가하기

			String line = "";
			
			Calendar now = Calendar.getInstance();
			int year = now.get(Calendar.YEAR);
			int month =now.get(Calendar.MONTH); 
			int day = now.get(Calendar.DATE);
			now.set(year, month,day);  // 설문조사 작성 날짜 설정
			
			//유민우■20198974■2021-05-07 // 이 형식으로 저장
			writer.write(String.format("%s■%s■%tF\n", 
											MainClass.logEmployee.getName(),
											MainClass.logEmployee.getEmployeeNum(),
											now));
			
			while ((line = reader.readLine()) != null) {
				
				//1.■질문
				String[] temp = line.split("■");
				System.out.print(temp[0]+temp[1]);  // 질문을 보여준다.
				answer = scan.nextLine();   //답변을 입력받는다.
				
				//답변이 y/n일 경우(제대로 적었을경우) 파일에 적어준다.
				if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n")) {
					writer.write(String.format("%s. ■%s: ■%s\n", temp[0], temp[1], answer.toLowerCase()));
				} else if (answer.equals("0")) { // 답변이 0이면 설문조사를 멈추고 되돌아간다.
					EmployeeOption.option();
				} else { // 잘못된 답변을 받았을 경우 질문을 다시 보여주고 답변을 다시 받는다.
					System.out.print(temp[0]+temp[1]);
					answer = scan.nextLine(); 
					question(answer, writer, temp);					
				}		
				
			} // while
			
			writer.write("==========\n");
			writer.close();
			reader.close();

		} catch (Exception e) {
			System.out.println("load: " + e);
		}

	}//covid

	
	/**
	 * 자가진단서에 답변을 잘못 입력(y/n, 0 제외한 나머지)하였을 때 답변을 다시 받기위한 메소드
	 * 답변이 정상적으로 입력될 때까지 재호출된다.
	 * @param answer 자가진단서 답변을 받아오는 매개변수
	 * @param writer BufferedWriter로 질의응답을 파일에 써주는 매개변수 
	 * @param temp   BufferedReader로 읽어온 자가진단서 질문을 받아오는 매개변수
	 * @throws IOException
	 */
	private void question(String answer, BufferedWriter writer, String[] temp){
		
		try {
			if (answer.toLowerCase().equals("y") || answer.toLowerCase().equals("n")) {
				// System.out.println();
				writer.write(String.format("%s. ■%s: ■%s\n", temp[0], temp[1], answer.toLowerCase()));
			} else if (answer.equals("0")) { // 답변이 0이면 설문조사를 멈추고 되돌아간다.
				EmployeeOption.option();
			} else {
				System.out.print(temp[0]+temp[1]);
				answer = scan.nextLine(); 
				question(answer, writer, temp);	
			}
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}//question

}//Covid
