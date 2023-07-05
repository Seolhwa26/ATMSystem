package signService;

import java.time.Year;
import java.util.Calendar;
import java.util.Scanner;
import java.util.regex.Pattern;

import atm.account.Account;
import atm.data.Data;

/**
 * 비회원이 회원 가입을 하는 클래스입니다. 
 */
public class Sign {
	
	/**
	 * 회원가입 정보를 입력해서 가입이 완료되면 회원리스트에 추가하는 메소드입니다. 
	 */
	public static void signUp() {
		      
		boolean loop = true;
		      
		while(loop) {
			Scanner scan = new Scanner(System.in);
			System.out.println();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃                                      *회원가입");
			System.out.print("┃이름: ");
			String name = scan.nextLine();
			while (!nameCheck(name)) {
				System.out.print("┃이름: ");
				name = scan.nextLine();
			}
			System.out.print("┃아이디(4~16글자): "); 
			String id = scan.nextLine();
			while (!idCheck(id)) { // 성공
				id = scan.nextLine();
			}
			System.out.print("┃비밀번호(숫자 4글자): ");
			String pass = scan.nextLine();
			while (!passCheck(pass)) {
				System.out.print("┃비밀번호(숫자 4글자): ");
				pass = scan.nextLine();
			}
			System.out.print("┃주민등록번호(생년월일8자리): ");
			String joomin = scan.nextLine();
			Calendar now1 = Calendar.getInstance();
			if (Integer.parseInt(joomin.substring(0, 4)) > now1.get(Calendar.YEAR)- 19) {
				System.out.println("┃" + joomin.substring(0, 4) + "년생은 미성년자로 은행에서 계좌발급이 가능합니다.");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				Data.pause();
				loop = false;
			} else {
				while (!joominCheck(joomin)) {
					System.out.print("┃주민등록번호(생년월일8자리): ");
					joomin = scan.nextLine();
				}

				System.out.print("┃전화번호: ");
				String tel = scan.nextLine();
				while (!isTel(tel)) { // 성공
					System.out.print("┃전화번호: ");
					tel = scan.nextLine();
				}
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

				Calendar c = Calendar.getInstance();

				String toDay = String.format("%tF", c);

				int accNum = Data.createAccNum();
				String birth = joomin.substring(0, 4) + "-" + joomin.substring(4, 6) + "-" + joomin.substring(6, 8);

				joomin = joomin.replace("-", "");
				joomin = joomin.substring(2, 8) + "-" + joomin.substring(8, 15);
				tel = tel.replace("-", "");
				tel = tel.substring(0, 3) + "-" + tel.substring(3, 7) + "-" + tel.substring(7);

				Account acc = new Account();
				// 계좌번호, 가입일, PW, 이름, 생년월일, 전화번호, 주민등록번호, 정지유무(true, false), ID
				acc.setAccountNumber(accNum);
				acc.setName(name);
				acc.setJoinDay(toDay);
				acc.setPW(pass);
				acc.setBirth(birth);
				acc.setTel(tel);
				acc.setJoomin(joomin);
				acc.setStop(true);
				acc.setID(id);
				acc.setBalance(0);
				Data.account.add(acc); // 데이터 집합에 새로운 학생을 추가(*********)

				loop = false;
				System.out.printf("회원가입이 완료되었습니다.\n*'%s'님의 계좌번호는 '%d'입니다.\n", name, accNum);
				System.out.println();
			}
		}

	}// signUp

	/**
	 * 전화번호의 유효성 검사를 하는 메소드입니다. 
	 * 하이픈(-)의 유무에 관계없이 입력가능하고 형식은 010-XXXX-XXXX로 숫자가 입력되어야 합니다. 
	 */
	public static boolean isTel(String tel) {

		boolean check = true;

		if (tel.length() == 13 && tel.substring(0, 4).equals("010-") && tel.substring(8, 9).equals("-")) {
			tel = tel.replace("-", "");
		}

		if (tel.length() == 11 && tel.substring(0, 3).equals("010")) {
			for (int i = 0; i < tel.length(); i++) {

				if (tel.charAt(i) >= '0' && tel.charAt(i) <= '9') {

				} else {
					check = false;
				}

			}
		} else {
			check = false;
		}

		if (check) {
			return true;
		} else {
			System.out.println("┃*전화번호 형식이 맞지 않습니다. 다시 입력해주세요");
			return false;
		}

	}

	/**
	 * 비밀번호의 유효성 검사를 하는 메소드입니다. 
	 * 4자리의 숫자만 설정할 수 있습니다. 
	 */
	public static boolean passCheck(String pass) {

		boolean check = true;

		char pw = pass.charAt(0);

		// 비밀번호 숫자

		if (pass.length() == 4) {

			for (int i = 0; i < pass.length(); i++) {

				if (pw >= '0' && pw <= '9') {

				} else {
					System.out.println("┃*비밀번호는 숫자로만 입력해주세요.");
					return false;
				}

			}

		} else {
			System.out.println("┃*비밀번호는 4자리로 입력해주세요.");
			check = false;
		}

		return check;

	}

	/**
	 * 아이디의 유효성 검사를 하는 메소드입니다. 
	 * 길이는 4~16자로 제한하고 소문자, 숫자만으로 구성 가능하고 첫 글자는 소문자, 아이디는 중복이 될 수 없습니다.  
	 */
	public static boolean idCheck(String id) {
//		      - 길이는 4-16자로 제한한다.
//		      - 영어 소문자, 숫자만 아이디에 사용할 수 있다. 
//		      - 첫 글자는 영어 소문자여야 한다.
//		      - 기존 회원들의 아이디와 중복되는 아이디를 입력했을 경우 메시지를 띄우고 다시 입력 받는다. 
//		      - 조건에 맞지 않는 아이디를 입력했을 경우 메시지를 띄우고 다시 입력 받는다.
		String reg = "^[a-z0-9]{4,12}$";
		if (!Pattern.matches(reg, id) || id.charAt(0) < 'a' || id.charAt(0) > 'z') {
			System.out.println("┃*ID를 형식에 맞게 입력해주세요.");
			System.out.print("┃아이디 재 입력: ");
			return false;
		} // 유효성
		String line = null;
		for (Account acc : Data.account) {
			if (id.equals(acc.getID())) {
				System.out.println("┃*이미 사용중인 아이디입니다.");
				System.out.print("┃아이디 재 입력: ");
				return false;
			}
		} // 중복
		return true;
	} // idCheck

	/**
	 * 이름의 유효성 검사를 하는 메소드입니다. 
	 * 이름은 3자이고, 한글만 가능합니다.  
	 */
	public static boolean nameCheck(String name) {
		if (name.length() == 3 && name.matches(".*[ㄱ-ㅎㅏ-ㅣ가-힣]+.*")) {
			return true;
		} else {
			System.out.println("┃*올바르지않은 이름입니다.");
			return false;
		}

	}

	/**
	 * 주민번호의 유효성 검사를 하는 메소드입니다. 
	 * 하이픈(-)의 유무에 관계없이 저장가능하고 주민번호의 유효성 검사를 통과한 주민번호만 저장됩니다. 
	 */
	public static boolean joominCheck(String joomin) {

		String s = "";
		int n = 0;
		int n2 = 2;
		int n3 = 0;
		int lastNum = 0;
		joomin = joomin.replace("-", "");

		for (int i = 2; i < joomin.length() - 1; i++) {
			s = joomin.substring(i, i + 1);
			n = Integer.parseInt(s);
			if (n2 < 10) {
				n3 += n * n2;
				n2++;
			} else {
				n2 = 2;
				n3 += n * n2;
				n2++;
			}

		}

		lastNum = 11 - (n3 % 11);
		if (lastNum > 10) {
			lastNum = lastNum / 10;
		}
		if (joomin.length() == 15) { // 1234 56 78 9
			
			int end = Integer.parseInt(joomin.substring(14, 15));
			if (end == lastNum) {
				return true;
			}

		}
		
		System.out.println("┃*올바르지 않은 주민등록번호입니다.");
		return false;
	
	
	}// juminCheck
}
