package com.project.manager;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

import com.project.employee.AnnualLeaveList;
import com.project.employee.AnnualLeaveReason;

public class AnnualLeaveLoad {
	
	private static final String DATA;
	private static final String DATA1;
	public static ArrayList<AnnualLeaveList> list;
	public static ArrayList<AnnualLeaveReason> list1;
	public static ArrayList<String> employeeLeave;
	
	static {
		DATA = "C:\\Project\\직원연차더미.txt";
		DATA1 = "C:\\Project\\직원연차사유더미.txt";
		list = new ArrayList<AnnualLeaveList>();
		list1 = new ArrayList<AnnualLeaveReason>();
		employeeLeave = new ArrayList<String>(); //"직원이름, 연차날짜"가 저장된 배열
	}
	

	/**
	 * 연차더미와 연차사유더미를 객체로 만든다.
	 * employeeLeave어레이 리스트를 만든다.
	 */
	public void AnnualLeaveLoad() {
		this.list.clear();
		this.list1.clear();
		this.employeeLeave.clear();
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			String line = "";
			
			while ((line = reader.readLine()) != null) {
				AnnualLeaveList annualList = new AnnualLeaveList();
				String[] temp = line.split("■");
				
				annualList.setName(temp[0]);
				annualList.setDivision(temp[1]);
				annualList.setPosition(temp[2]);
				annualList.setRemainvalue(temp[3]);
				annualList.setUsedvalue(temp[4]);
				annualList.setEmployeeNum(temp[5]);
				
				while (!(line = reader.readLine()).equals("==============")) {
					String[] temp1 = line.split(",");
					for (int i=0; i<temp1.length; i++) {
						annualList.addDate(temp1[i]);
					}
				}

				list.add(annualList);
				
			} // while

			reader.close();

		} catch (Exception e) {
			System.out.println("load: load DATA!!" + e);
		}
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA1));
			String line = "";

			while ((line = reader.readLine()) != null) {
				AnnualLeaveReason reasonArray = new AnnualLeaveReason();
				String[] temp = line.split("■");
				
				reasonArray.setName(temp[0]);
				reasonArray.setDivision(temp[1]);
				reasonArray.setPosition(temp[2]);
				reasonArray.setRemainvalue(temp[3]);
				reasonArray.setUsedvalue(temp[4]);
				reasonArray.setDate(temp[5]);
				reasonArray.setReason(temp[6]);
				reasonArray.setType(temp[7]);
				reasonArray.setAplyDate(temp[8]);
				reasonArray.setCheck(temp[9]);
				
				while (!(line = reader.readLine()).equals("===========")) {
				
				}

				list1.add(reasonArray);
				
			} // while

			reader.close();

		} catch (Exception e) {
			System.out.println("load: " + e);
		}
		
		employeeLeaveLoad();
	
		
	}
	

	/**
	 * employeeLeave 어레이리스트를 만드는 배열 입니다.
	 * "직원이름,2021-03-09"
	 */

	public void employeeLeaveLoad() {
		
		for(AnnualLeaveList a: list) {
		
			String info = "";
			info = a.getName() + ",";
			for(int i=0; i<a.getDate().size(); i++) {
				String newinfo = info + a.getDate().get(i);
				employeeLeave.add(newinfo);
				
			}
		}
		
	}


}
