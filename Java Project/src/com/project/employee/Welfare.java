package com.project.employee;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Scanner;

import com.project.MainClass;

public class Welfare {

	// 복지 포인트 : 직급 별로 나눔. ex(사원 120000, 부장 240000)

	public static Scanner scan;
	public final static String DATA;
	public final static String DATA1;
	public final static String DATA2;
	public final static String DATA3;
	public final static String DATA4;
	public final static String EMPLOYEE;
	public final static String BUY_LIST;
//	public static ArrayList<getpoint> list; //포인트 받는 get/set

	public static ArrayList<productList> product_list = new ArrayList<productList>(); // 상품리스트 받는 get/set
	public static ArrayList<buylist> buy_list = new ArrayList<buylist>(); // 구매한 상품 리스트 get/set
	public static ArrayList<employee> employee = new ArrayList<employee>();
	public static ArrayList<point> point_list = new ArrayList<point>();

	static int point = 0; // 현재 로그인된 사용자의 포인트 현황


	static {
		scan = new Scanner(System.in);
		DATA = "C:\\Project\\point.dat";
		DATA1 = "C:\\Project\\product1.dat";
		DATA2 = "C:\\Project\\product2.dat";
		DATA3 = "C:\\Project\\product3.dat";
		DATA4 ="C:\\Project\\product4.dat";
		BUY_LIST = "C:\\Project\\buylist.dat";
		EMPLOYEE = "C:\\Project\\직원더미.txt";
//		list = new ArrayList<getpoint>();

	}

	/**
	 * 로그인후 복지 포인트를 눌렀을때에 나오는 첫 화면이다. 번호를 선택하면 그 번호의 메소드를 호출해준다.
	 * 
	 * @return
	 */
	public static String menu() { // 메뉴

		System.out.println("==========[복지 포인트 관리]==========");
		System.out.println();
		System.out.println();
		System.out.println("[1]. 잔여 복지 포인트"); // check
		System.out.println();
		System.out.println();
		System.out.println("[2]. 상품 목록"); // list
		System.out.println();
		System.out.println();
		System.out.println("[3]. 사용 내역"); // usedlist
		System.out.println();
		System.out.println();
		System.out.println("[4]. 종료 하기");
		System.out.println();
		System.out.println();
		System.out.println("===================================");
		System.out.print("선택 : ");

		String sel = scan.nextLine();

		pause();
		return sel;

	}

	public void salary_menu() throws FileNotFoundException  {
		boolean loop = true;
		load_dat(); // buylist.dat 파일에 있는 구매내역들 불러오기 buy_list 변수에 저장됨

		load_employee();

		employee employee = new employee();
		//MainClass mc = new MainClass();
		employee.setName(MainClass.logEmployee.getName());
		employee.setEmployeeNum(MainClass.logEmployee.getEmployeeNum());

		load_point(employee);

		// write_point_file();

		System.out.println("point : " + point);

		while (loop) {
			Calendar now = Calendar.getInstance();
			String now_date = String.format("%tF %tT", now, now);

			// 메뉴출력
			// 선택
			// 분기

			String seq = menu();

			if (seq.equals("1")) {
				check();
			} else if (seq.equals("2")) {
				showList(now_date, employee);

			} else if (seq.equals("3")) {
				usedlist(employee);
			} else if (seq.equals("4")) {
				loop = false;
			}

		} // loop

		System.out.println("프로그램 종료");
	}

	/**
	 * employee get/set을 user라는 매개변수를 써서 사용가능하게 한다. for문으로 상품 구매 목록을 사용자에게 보여준다.
	 * 
	 * @param user
	 */

	public static void usedlist(employee user) { // 상품 구매 목록
		// TODO Auto-generated method stub
		System.out.println("==========[구매한 상품 목록]==========");

		for (int i = 0; i < buy_list.size(); i++) { // buy_list만큼 for문 증가
			if (user.getEmployeeNum().equals(buy_list.get(i).getBuylist_empNo())) {
				System.out.print(buy_list.get(i).getBuylist_prodNo() + " ");
				System.out.print(buy_list.get(i).getBuylist_prodname() + " ");
				System.out.println(buy_list.get(i).getBuylist_prodprice() + " ");
				System.out.println(buy_list.get(i).getBuylist_empNo() + " ");
				System.out.println(buy_list.get(i).getBuylist_date());
			}
		}

	}// fin(usedlist)

	/**
	 * 3가지 기능이 있는데 첫번째로는 사용전 포인트를 사용자가 구매하였을떄에 가지고 있는 포인트가 물건보다 쌀경우에는 구매가 되게 한다.
	 * 물건보다 비쌀경우 구매할수 없다. 사용하고 남은 포인트를 저장해주고, 사용시에 사용목록에 사원의 이름, 상품명, 가격, 사원번호, 날짜를
	 * 저장해준다. buy_list에 filewriter를 이용하여 저장시켜 준다.
	 * 
	 * @param now_date
	 * @param employee
	 * @throws FileNotFoundException
	 */

	public static void showList(String now_date, employee employee) throws FileNotFoundException {
		String choosed_list = list();
		productList selected_product = new productList();
		buylist buy_product = new buylist();
		selected_product = product_list(choosed_list); // 사용자가 선택한 상품 정보 들어가는 코드
		if (buy(selected_product)) { // 만약 사용자가 구매하기(y)를 입력했다면 실행
			if (Integer.parseInt(selected_product.getProduct_price()) > point) {
				System.out.println("구매할수 없는 상품입니다.");
			} else {
				System.out.println(selected_product.getProduct_name() + "을(를) 구매하셨습니다 !");
				System.out.printf("사용 전 포인트 : %d / ", point);
				point = point - Integer.parseInt(selected_product.getProduct_price());
				write_point(employee);
				System.out.printf("%d 포인트 사용하고 %d 포인트 남았습니다.\n", Integer.parseInt(selected_product.getProduct_price()),
						point);
				buy_product.setBuylist_prodNo(Integer.toString(buy_list.size() + 1));
				buy_product.setBuylist_prodname(selected_product.getProduct_name());
				buy_product.setBuylist_prodprice(selected_product.getProduct_price());
				buy_product.setBuylist_empNo(employee.getEmployeeNum());
				buy_product.setBuylist_date(now_date);
				buy_list.add(buy_product); // 사용내역에 사용자가 선택한 상품정보를 추가하는 코드
				try {
					BufferedWriter writer = new BufferedWriter(new FileWriter(BUY_LIST));
					for (int i = 0; i < buy_list.size(); i++) { // 사용내역 변수에 있는 내용들을 하나씩 갖고와서 .dat파일로 저장해주는 코드
						writer.write(String.format("%s,%s,%s,%s,%s\n", buy_list.get(i).getBuylist_prodNo(),
								buy_list.get(i).getBuylist_prodname(), buy_list.get(i).getBuylist_prodprice(),
								buy_list.get(i).getBuylist_empNo(), buy_list.get(i).getBuylist_date()));
					}
					writer.close(); // 꼭 닫기.!!
				} catch (Exception e) {
					System.out.println(e);
				}
			}
		} else {
			System.out.println("구매를 취소하였습니다.");
		}
		product_list.clear();
	}

	/**
	 * 상품을 구매하게 하기 위한 메소드로 상품을 구매시 "y"를 누르면 상품이 구매되고 n을 입력하면 상품을 구매할수 없게 한다. 다른 값을
	 * 넣은 경우에는 다시 입력해달라는 말을 호출한다.
	 * 
	 * @param product
	 * @return
	 */
	public static boolean buy(productList product) { // selected_product를 product라는 매개변수로 받아옴.
		while (true) {
			System.out.println(product.getProduct_name() + " 상품을 구매하시겠습니까? y / n ");

			System.out.print("선택 : ");
			String name = scan.nextLine();

			if (name.equals("y")) {
				return true;
			} else if (name.equals("n")) {
				return false;
			} else {
				System.out.println("다시 입력해 주세요");
			}
		}

	}

	/**
	 * list라는 메소드로 상품 목록을 콘솔창에서 출력시켜주는 메소드다.
	 * 
	 * @return
	 */

	public static String list() {
		while (true) {
			System.out.println("[상품목록]");
			System.out.println("[1] 전자제품");
			System.out.println("[2] 가전제품");
			System.out.println("[3] 여행패키지");
			System.out.println("[4] 건강패키지");

			System.out.print("선택 : ");
			String proname = scan.nextLine();

			return proname;
		}

	}

	/**
	 * product_list를 choosed로 사용하고, 상품목록에 관한 더미파일들을 읽어준다. 그리고 product_list라는
	 * arraylist에 product 번호, 이름, 가격을 저장해준다.
	 * 
	 * @param choosed
	 * @return
	 * @throws FileNotFoundException
	 */

	public static productList product_list(String choosed) throws FileNotFoundException { // 상품 리스트
		productList select = new productList();
		try {
			BufferedReader reader;
			if (choosed.equals("1")) {
				reader = new BufferedReader(new FileReader(DATA1));
			} else if (choosed.equals("2")) {
				reader = new BufferedReader(new FileReader(DATA2));
			} else if (choosed.equals("3")) {
				reader = new BufferedReader(new FileReader(DATA3));
			} else if (choosed.equals("4")) {
				reader = new BufferedReader(new FileReader(DATA4));
			} else {
				return null; // 예외처리
			}
			String line = "";

			while ((line = reader.readLine()) != null) {

				// productList 객체 1개 생성
				productList product = new productList();

				String[] temp = line.split(",");

				product.setProduct_no(temp[0]); // 새로운 객체 생성후 no, name price temp[0][1][2]에 저장
				product.setProduct_name(temp[1]);
				product.setProduct_price(temp[2]);

				product_list.add(product); // arraylist add로 product 추가.

			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e);
		}

		System.out.println("==========[상품 목록]==========");
		for (int i = 0; i < product_list.size(); i++) { // product_list만큼 닫기
			System.out.println("[" + product_list.get(i).getProduct_no() + "]. " + product_list.get(i).getProduct_name() // 위에
																															// no,
																															// name,
																															// price
																															// set으로
																															// 값받기
					+ "    " + product_list.get(i).getProduct_price());
		}

		String selected = scan.nextLine();
		select.setProduct_no(product_list.get(Integer.parseInt(selected) - 1).getProduct_no());
		select.setProduct_name(product_list.get(Integer.parseInt(selected) - 1).getProduct_name());
		select.setProduct_price(product_list.get(Integer.parseInt(selected) - 1).getProduct_price());

		return select;

	}

	/**
	 * 각 사용자마다 사용 가능한 복지 포인트를 보여주는 메소드이고, 로그인한 사람에 따라서 가지고 있는 포인트가 다르게 나온다.
	 */

	public static void check() {

		System.out.print("==========[사용 가능한 복지 포인트]==========");
		System.out.println();
		System.out.printf("남은 잔여 point = %d 입니다.", point);

		pause();
	}

	/**
	 * scan_nextLine()을 넣어줘서 엔터를 누를시에 다음으로 진행되게 해주는 메소드이다.
	 */

	public static void pause() { // 업무 끝나고 화면 고정
		System.out.println("\n엔터를 누르시면 다음으로 진행 됩니다.");
		scan.nextLine(); // Block

	}

	/**
	 * 물건의 번호, 이름, 가격, 사원번호, 날짜를 buy_list라는 arraylist에 저장시켜준다.
	 * 
	 * @throws FileNotFoundException
	 */

	public static void load_dat() throws FileNotFoundException { // 사용내역 파일에서 불러오기
		try {
			BufferedReader reader = new BufferedReader(new FileReader(BUY_LIST));
			;
			String line = "";

			while ((line = reader.readLine()) != null) {

				buylist load_list = new buylist();

				String[] temp = line.split(",");

				load_list.setBuylist_prodNo(temp[0]);
				load_list.setBuylist_prodname(temp[1]);
				load_list.setBuylist_prodprice(temp[2]);
				load_list.setBuylist_empNo(temp[3]);
				load_list.setBuylist_date(temp[4]);

				buy_list.add(load_list);
			}

			reader.close();

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * EMPLOYEE라는 파일경로에 있는 파일을 ■마다 자른뒤에 그 값을 load_employee에다가 넣어주고 employee
	 * arraylist에 저장시켜준다.
	 */
	public void load_employee() { // 사원 목록 파일에서 불러오기
		try {
			BufferedReader reader = new BufferedReader(new FileReader(EMPLOYEE));
			;
			String line = "";

			while ((line = reader.readLine()) != null) {
				employee load_employee = new employee();

				String[] temp = line.split("■");

				load_employee.setName(temp[1]);
				load_employee.setDivision(temp[2]);
				load_employee.setPosition(temp[3]);
				load_employee.setJoinDate(temp[4]);
				load_employee.setEmployeeNum(temp[5]);
				load_employee.setBirth(temp[6]);
				load_employee.setPhoneNum(temp[7]);
				load_employee.setAddress(temp[8]);

				employee.add(load_employee);
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * DATA라는 파일 경로에 있는 파일을 ■마다 자른뒤에 그 값을 loaded_point에다가 넣어주고 point_list에 그 값을
	 * 저장시켜준다.
	 * 
	 * @param user
	 */

	public static void load_point(employee user) { // 포인트를 파일에서 불러오는 함수
		try {
			BufferedReader reader = new BufferedReader(new FileReader(DATA));
			;
			String line = "";

			while ((line = reader.readLine()) != null) {
				point loaded_point = new point();
				String[] temp = line.split(",");

				loaded_point.setEmployeeNum(temp[0]);
				loaded_point.setPoint(Integer.parseInt(temp[1]));

				point_list.add(loaded_point);

				if (user.getEmployeeNum().equals(temp[0])) {
					point = Integer.parseInt(temp[1]);
				}
			}
			reader.close();
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * DATA에 경로에 있는 값들을 사원번호와, point값들만 받아서 저장시켜준다.
	 * 
	 * @param user
	 */

	public static void write_point(employee user) { // 포인트를 파일에 저장해주는 함수
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA));
			for (int i = 0; i < point_list.size(); i++) { // 사용내역 변수에 있는 내용들을 하나씩 갖고와서 .dat파일로 저장해주는 코드

				if (user.getEmployeeNum().equals(point_list.get(i).getEmployeeNum())) {
					writer.write(String.format("%s,%s\n", point_list.get(i).getEmployeeNum(), point));
					continue;
				}
				writer.write(
						String.format("%s,%s\n", point_list.get(i).getEmployeeNum(), point_list.get(i).getPoint()));
			}
			writer.close(); // 꼭 닫기.!!
		} catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * DATA에 경로에 있는 값들을 각 사원별로 포인트를 다르게 지급해준다. 이 코드는 한번만실행한다.
	 */
//	public static void write_point_file() {
//		
//		try {
//			BufferedWriter writer = new BufferedWriter(new FileWriter(DATA));
//			
//			for(int i=0; i<employee.size(); i++) {			// 사용내역 변수에 있는 내용들을 하나씩 갖고와서 .dat파일로 저장해주는 코드
//				//System.out.println(employee.get(99).getName());
//				if(employee.get(i).getPosition().equals("부장")) {
//					writer.write(String.format("%s,%s\n"
//							,employee.get(i).getEmployeeNum()
//							,1000000));
//				} else if(employee.get(i).getPosition().equals("차장")) {
//					writer.write(String.format("%s,%s\n"
//							,employee.get(i).getEmployeeNum()
//							,800000));
//				} else if(employee.get(i).getPosition().equals("과장")) {
//					writer.write(String.format("%s,%s\n"
//							,employee.get(i).getEmployeeNum()
//							,600000));
//				} else if(employee.get(i).getPosition().equals("대리")) {
//					writer.write(String.format("%s,%s\n"
//							,employee.get(i).getEmployeeNum()
//							,400000));
//				} else if(employee.get(i).getPosition().equals("사원")) {
//					writer.write(String.format("%s,%s\n"
//							,employee.get(i).getEmployeeNum()
//							,200000));
//				}
//			}
//			writer.close(); //꼭 닫기.!!
//		} catch (Exception e) {
//			System.out.println(e);
//		}
//	}

}// fin(salary)
