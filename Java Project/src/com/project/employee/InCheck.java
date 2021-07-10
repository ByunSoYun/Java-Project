package com.project.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Calendar;

import com.project.MainClass;

/**
 * @author 3조
 * 입력된 출근 및 지각 시간의 
 * 데이터를 저장해주는 클래스
 *
 */
public class InCheck {

	public static Calendar now = Calendar.getInstance();
	public static String startTime;
	private final static String DATA;
	private final static String DATA2;

	static {
		startTime = "";
		DATA = "C:\\Project\\직원출퇴근더미.txt";
		DATA2 = "C:\\Project\\직원서류서명더미.txt";
	}
	
	/**
	 * 출근시간 및 지각시간을 나타내 출근정보를 알게해주고 지각한 데이터를 저장해주는 메서드
	 */
	public void incheck() {

		System.out.println("<출근 체크>");
		System.out.println("=====================");
		// 캘린더를 이용해서 출근 체크를 누른 시간을 불러와서 출근시간을 startTime 변수에 저장한다.
		if (now.get(Calendar.HOUR_OF_DAY) < 9) {

			startTime = String.format("%02d:%02d", now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));
			System.out.print("현재시각 :" + startTime);
			System.out.println("\n출근시간 체크 완료되었습니다");
			System.out.println("=====================");
		} else {
			startTime = String.format("%02d:%02d", now.get(Calendar.HOUR_OF_DAY), now.get(Calendar.MINUTE));
			System.out.println("현재시각 :" + startTime + "\n지각하셨습니다.");
			System.out.println("=====================");

			try {
				//1■유민우■지각■2021-05-03■20198974
				BufferedReader reader = new BufferedReader(new FileReader(DATA2));
				String line ="";
				
				int count =0;
				while((line = reader.readLine())!= null) {
					count ++;
				}
				
				BufferedWriter writer = new BufferedWriter(new FileWriter(DATA2, true));
				

				writer.write(String.format("\n%d■%s■지각■%tF■%s",count,MainClass.logEmployee.getName(),now,MainClass.logEmployee.getEmployeeNum()));
				
				reader.close();
				writer.close();
				
			} catch (Exception e) {
				System.out.println(e);
			}

		}

	}

}
