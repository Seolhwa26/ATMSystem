package atm.findService;

import java.util.ArrayList;
import java.util.Scanner;

import atm.account.Account;
import atm.data.Data;
import signService.Sign;

/**
 * 회원정보를 찾는 클래스입니다. 
 */
public class Find {
	
	/**
	 * 정보찾기를 실행하는 메소드입니다. 
	 */
	public static void findInfo() {
		boolean loop = true;

		Scanner scan = new Scanner(System.in);
		while (loop) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃           	                  *회원정보 찾기 ");
			System.out.println("┃=========	    ");
			System.out.println("┃1.아이디찾기	    ");
			System.out.println("┃=========	    ");
			System.out.println("┃2.비밀번호 찾기   ");
			System.out.println("┃=========	    ");
			System.out.println("┃3.뒤로    		");
			System.out.println("┃=========	    ");
			System.out.println("┃	     ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("선택: ");
			String select = scan.nextLine(); 
			System.out.println();

			if (select.equals("1")) {
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃           	                    *아이디 찾기 ");
				System.out.print("┃이름: ");
				String nameInput = scan.nextLine();
				System.out.print("┃주민등록번호: ");
				String juminInput = scan.nextLine();
				while (!findId(nameInput, juminInput)) {
					System.out.print("┃이름: ");
					nameInput = scan.nextLine();
					System.out.print("┃주민등록번호: ");
					juminInput = scan.nextLine();
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();

			} else if (select.equals("2")) {
				System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
				System.out.println("┃           	                  *비밀번호 찾기 ");
				System.out.print("┃이름: ");
				String nameInput = scan.nextLine();
				System.out.print("┃아이디: ");
				String idInput = scan.nextLine();
				System.out.print("┃주민등록번호: ");
				String juminInput = scan.nextLine();
				while (!findPass(nameInput, idInput, juminInput)) {
					System.out.print("┃이름: ");
					nameInput = scan.nextLine();
					System.out.print("┃아이디: ");
					idInput = scan.nextLine();
					System.out.print("┃주민등록번호: ");
					juminInput = scan.nextLine();
				}

				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				System.out.println();
			} else {
				loop = false;
			}
		}

	}

	/**
	 * 아이디를 찾는 메소드입니다. 
	 */
	private static boolean findId(String name, String jumin) {

		boolean check = true;

		if (jumin.length() == 13) { // 하이픈(-) 입력 안해도 찾을 수 있게
			jumin = jumin.substring(0, 6) + "-" + jumin.substring(6);
		}

		ArrayList<Account> acclist = Data.findId(name, jumin);

		if (acclist.size() != 0) {

			System.out.println("┃");
			acclist.stream().forEach(acc -> System.out.printf("┃%s님의 아이디는 %s입니다.", acc.getName(), acc.getID()));
			System.out.println("");

		} else {
			System.out.println("┃");
			System.out.println("┃일치하는 정보가 없습니다.");
			System.out.println("┃다시 입력해주세요.");
			System.out.println("┃");

			check = false;
		}

		return check;

	}

	/**
	 * 비밀번호를 찾는 메소드입니다. 
	 */
	private static boolean findPass(String name, String id, String jumin) {

		boolean check = true;

		if (jumin.length() == 13) { // 하이픈(-) 입력 안해도 찾을 수 있게
			jumin = jumin.substring(0, 6) + "-" + jumin.substring(6);
		}

		ArrayList<Account> acclist = Data.findPw(name, id, jumin);

		if (acclist.size() != 0) {

			System.out.println("┃");
			acclist.stream().forEach(acc -> System.out.printf("┃%s님의 비밀번호는 %s입니다.", acc.getName(), acc.getPW()));
			System.out.println("");

		} else {
			System.out.println("┃");
			System.out.println("┃일치하는 정보가 없습니다.");
			System.out.println("┃다시 입력해주세요.");
			System.out.println("┃");

			check = false;
		}

		return check;

	}
}
