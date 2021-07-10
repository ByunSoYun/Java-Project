package com.project;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class Load {
	
	private final static String DATA;
	private final static String DATA2;
	private final static String DATA3;
	
	public static ArrayList<Employee> list;
	public static ArrayList<EmployeeCommute> list2;
	public static Employee logEmployee;
	public static EmployeeCommute logCommute;


	static {
		DATA = "C:\\Project\\직원더미.txt";
		DATA2 = "C:\\Project\\직원연차더미.txt";
		DATA3 = "C:\\Project\\직원출퇴근더미.txt";
		list = new ArrayList<Employee>();
		list2 = new ArrayList<EmployeeCommute>();
		logEmployee = new Employee();
	}
	
	public void documentLoad() {
		
	}
}
