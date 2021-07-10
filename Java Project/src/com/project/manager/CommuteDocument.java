package com.project.manager;

import java.io.BufferedReader;
import java.io.FileReader;

public class CommuteDocument {

	private final static String DATA;

	static {
		DATA = "C:\\Project\\직원서류서명더미.txt";
	}

	public void commuteDocument() {

		System.out.println("===================");
		System.out.println("<미완료된 서류>");

		try {

			BufferedReader reader = new BufferedReader(new FileReader(DATA));

			String line = "";

			int i = 1;
			while ((line = reader.readLine()) != null) {

				// 구대혁■지각■2021-05-07

				String[] temp = line.split("■");

				System.out.printf("%d%s\t%s\t%s\n", i, temp[1], temp[2], temp[3]);
				i++;
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("===================");
	}

}
