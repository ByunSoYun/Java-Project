package com.project.employee;

import java.util.ArrayList;

public class AnnualLeaveList {
	
	 private String name;
	 private String division;
	 private String position;
	 private String remainvalue;
	 private String usedvalue;
	 private String employeeNum; // 사원번호
	 private ArrayList<String> date = new ArrayList<String>();
	 
	 
	 
	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getDivision() {
		return division;
	}



	public void setDivision(String division) {
		this.division = division;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public String getRemainvalue() {
		return remainvalue;
	}



	public void setRemainvalue(String remainvalue) {
		this.remainvalue = remainvalue;
	}



	public String getUsedvalue() {
		return usedvalue;
	}



	public void setUsedvalue(String usedvalue) {
		this.usedvalue = usedvalue;
	}



	public String getEmployeeNum() {
		return employeeNum;
	}



	public void setEmployeeNum(String employeeNum) {
		this.employeeNum = employeeNum;
	}



	public ArrayList<String> getDate() {
		return date;
	}



	public void setDate(ArrayList<String> date) {
		this.date = date;
	}

	public void addDate(String day) {
		this.date.add(day);
	}


	@Override
	public String toString() {
		return "AnnualLeaveList [name=" + name + ", division=" + division + ", position=" + position + ", remainvalue="
				+ remainvalue + ", usedvalue=" + usedvalue + ", employeeNum=" + employeeNum + ", date=" + date + "]";
	}
	 
	 
}

