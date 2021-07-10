package com.project.employee;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Calendar;

import com.project.MainClass;
/**
 * 
 * @author 3조
 *
 *해당월의 급여명세서를 보여주는 클래스
 *
 *
 */
public class Salary {

	public static Calendar now = Calendar.getInstance();
	private final static String DATA;
	public static int basePay; // 기본급
	public static int bonus; // 상여금
	public static int lunchPay; // 식대7
	public static int overtimePay; // 야근수당
	public static int positionPay; // 직책수당
	public static int welfarePay; // 복리후생비
	public static int timePay; // 시급
	public static int endTimeHour; // 퇴근시간 (hour)
	public static int lastworkday; // 지난 달 근무 횟수

	static {
		DATA = "C:\\Project\\직원출퇴근더미.txt";

		basePay = 0; // 기본급
		bonus = 0; // 상여금
		lunchPay = 0; // 식대
		overtimePay = 0;// 야근수당
		positionPay = 0;// 직책수당
		welfarePay = 0; // 복리후생비
		timePay = 0; // 시급
		endTimeHour = 0; // 끝나는 시간의 시

	}

	public void salary() {

		System.out.printf("<%d월 급여명세서>\n", now.get(Calendar.MONTH));
		System.out.println("======================");
		System.out.printf("사원명 : %s\n", MainClass.logEmployee.getName());
		System.out.printf("사원번호 : %s\n", MainClass.logEmployee.getEmployeeNum());
		System.out.printf("지급연월 : %s년 %s월\n", now.get(Calendar.YEAR), now.get(Calendar.MONTH) + 1);
		System.out.printf("소속 : %s %s\n", MainClass.logEmployee.getDivision(), MainClass.logEmployee.getPosition());
		System.out.println("======================");
		System.out.println();
		System.out.println("<지급내역>");
		System.out.println("======================");

		if (MainClass.logEmployee.getPosition().equals("부장")) {
			basePay = 4000000;
			positionPay = 400000;
			overtimepay();
		} else if (MainClass.logEmployee.getPosition().equals("차장")) {
			basePay = 3000000;
			positionPay = 300000;
			overtimepay();
		} else if (MainClass.logEmployee.getPosition().equals("과장")) {
			basePay = 2500000;
			positionPay = 250000;
			overtimepay();
		} else if (MainClass.logEmployee.getPosition().equals("대리")) {
			basePay = 2000000;
			positionPay = 200000;
			overtimepay();
		} else if (MainClass.logEmployee.getPosition().equals("사원")) {
			basePay = 1800000;
			positionPay = 180000;
			overtimepay();
		} else {
			System.out.println("직원이 아닙니다.");
		}

		System.out.printf("기본급:\t %,10d￦\n", basePay);
		System.out.printf("직책수당:\t %,10d￦\n", positionPay);
		lunchPay();
		System.out.printf("식대:\t %,10d￦\n", lunchPay);
		bonus = (int) (basePay * 0.05);

		System.out.printf("상여금:\t %,10d￦\n", bonus);
		System.out.printf("야근수당:\t %,10d￦\n", overtimePay);
		System.out.printf("복리후생비: %,10d￦\n", 123000);

		int getSum = basePay + positionPay + bonus + lunchPay + overtimePay + 123000;

		System.out.println("======================");
		System.out.println("\n<공제내역>");
		System.out.println("======================");
		System.out.printf("국민연금:\t %,10d￦\n", (int)(getSum * 0.045));
		System.out.printf("건강보험:\t %,10d￦\n",(int)( getSum * 0.0343));
		System.out.printf("고용:\t %,10d￦\n", (int)(getSum * 0.008));
		System.out.printf("근로소득세: %,10d￦\n", (int)(getSum * 0.01));

		int taxSum = (int) (getSum * 0.045 + getSum * 0.0343 + getSum * 0.008 + getSum * 0.01);

		System.out.println("======================");
		System.out.printf("지급합계:\t %,10d￦\n", getSum);
		System.out.printf("공제합계:\t %,10d￦\n", taxSum);
		System.out.printf("실 지급액:\t %,10d￦\n", getSum - taxSum);
		System.out.println("======================");
		System.out.println();
		System.out.println("귀하의 노고에 진심으로 감사합니다.");
		System.out.println("======================");

	}

	public static void overtimepay() {
		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";

			while ((line = reader.readLine()) != null) {
				// 2021-01-04■김민수■07:49■18:51■20101010

				String[] temp = line.split("■");

				String month = String.format("%02d", now.get(Calendar.MONTH));

				if (temp[4].equals(MainClass.logEmployee.getEmployeeNum())) {
					if (temp[0].substring(5, 7).equals(month)) {

						endTimeHour = Integer.parseInt(temp[3].substring(0, 2));
						// 끝나는 시간의 시는 근태관리에서의 끝나는 시간의 시이다.
						int timePay = basePay / 20 / 9; // 시급

						if (endTimeHour == 19) {
							overtimePay += (int) (timePay * 1.5);
						} else if (endTimeHour == 20) {
							overtimePay += (int) (2 * (timePay * 1.5));
						} else if (endTimeHour == 21) {
							overtimePay += (int) (3 * (timePay * 1.5));
						} else {
							overtimePay += 0;
						}

					}

				}

			} // while
			reader.close();

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public static void lunchPay() {

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";

			while ((line = reader.readLine()) != null) {
				// 2021-01-04■김민수■07:49■18:51■20165555
				String[] temp = line.split("■");

				String month = String.format("%02d", now.get(Calendar.MONTH));

				if (temp[4].equals(MainClass.logEmployee.getEmployeeNum())) {
					if (temp[0].substring(5, 7).equals(month)) {
						lastworkday++;
					}

				}

			} // while

			lunchPay = lastworkday * 8000;

		} catch (Exception e) {
			System.out.println(e);
		}

	}

}
