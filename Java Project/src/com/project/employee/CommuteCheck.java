package com.project.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Calendar;

import com.project.EmployeeCommute;
import com.project.MainClass;


/**
 * 이번달에 근태확인을 보여주는 클래스
 */


public class CommuteCheck {

	private final static String DATA;
	public static int workday;
	public static int lateday;
	public static int absenceday;
	public static int earlyleaveday;
	public static int lastworkday;

	// public static ArrayList<EmployeeCommute> list2;

	static {
		DATA = "C:\\Project\\직원출퇴근더미.txt";
		// list2 = new ArrayList<EmployeeCommute>();
	}
	
	/**
	 * 이번달에 지각,조퇴,근무횟수를 체크해주는 메서드
	 */
	
	public void commuteCheck() {

		// load();
		System.out.println("<근태확인>");

		// 2021-01-04■김민수■07:49■18:51

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";
			workday = 0;

			while ((line = reader.readLine()) != null) {

				// 2021-01-04■김민수■07:49■18:51■20147245
				// EmployeeCommute employeeCommute = new EmployeeCommute();

				String[] temp = line.split("■");

				Calendar now = Calendar.getInstance();
				String month = String.format("%02d", now.get(Calendar.MONTH) + 1);

				if (temp[4].equals(MainClass.logEmployee.getEmployeeNum())) {
					if (temp[0].substring(5, 7).equals(month)) {
						workday++;

					}

				}

			} // while
			System.out.printf("이번달 근무일수 : %d회\n", workday);

			// list2.add(employeeCommute);
			reader.close();

		} catch (Exception e) {
		}

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";
			lateday = 0;

			while ((line = reader.readLine()) != null) {

				// 2021-01-04■김민수■07:49■18:51■20147245

				String[] temp = line.split("■");

				Calendar now = Calendar.getInstance();
				String month = String.format("%02d", now.get(Calendar.MONTH) + 1);

				if (temp[4].equals(MainClass.logEmployee.getEmployeeNum())) {
					if (temp[0].substring(5, 7).equals(month)) {
						String hour = String.format("%s", temp[2].substring(0, 2));

						if (Integer.parseInt(hour) >= 9) {
							lateday++;
						}
					}

				}

			} // while
			System.out.printf("지각 : %d회\n", lateday);

			reader.close();

		} catch (Exception e) {
		}

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";
			earlyleaveday = 0;

			while ((line = reader.readLine()) != null) {

				// 2021-01-04■김민수■07:49■18:51■20147245

				String[] temp = line.split("■");

				/**
				 * 이번달 근무 횟수출력
				 */
				Calendar now = Calendar.getInstance();
				String month = String.format("%02d", now.get(Calendar.MONTH) + 1);

				if (temp[4].equals(MainClass.logEmployee.getEmployeeNum())) {
					if (temp[0].substring(5, 7).equals(month)) {
						String hour = String.format("%s", temp[3].substring(0, 2));

						if (Integer.parseInt(hour) < 18) {
							earlyleaveday++;
						}
					}
				}

			} // while
			System.out.printf("조퇴 : %d회\n", earlyleaveday);

			reader.close();

		} catch (Exception e) {
		}

	}

}