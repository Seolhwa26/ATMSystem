package atm.main;

import java.util.Scanner;

import atm.account.Account;
import atm.atmService.Atm;
import atm.data.Data;
import atm.findService.Find;
import atm.loginService.Login;
import signService.Sign;

/**
 * 프로그램의 시작메뉴가 있는 클래스입니다. 
 */
public class main {

	public static void main(String[] args) {
		Data.load();

		boolean loop = true;

		int atmAmount = Data.atmcash.get(Data.atmcash.size() - 1).getCash();

		Scanner scan = new Scanner(System.in);
		while (loop) {
 
			System.out.println();
			logo();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃           *****계좌 관리 프로그램*****           ");
			System.out.println("┃==========");
			System.out.println("┃1.회원가입	┏━━━━━━━━━━━━━━━━━━━━━━━━┓                  ");
			System.out.println("┃==========	┃ 입금 ┃ 출금 ┃ 명세 ┃    =========");
			System.out.println("┃2.회원정보찾기	┃━━━━━━━━━━━━━━━━━━━━━━━━┃       4.종료");
			System.out.printf("┃==========	┃  %s   ┃  %s  ┃   %s  ┃    =========\n", "O", (atmAmount >= 1000000 ? "O" : "X"), "O");
			System.out.println("┃3.로그인	┗━━━━━━━━━━━━━━━━━━━━━━━━┛     ");
			System.out.println("┃==========			       ");
			System.out.println("┃				   	   ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("선택: ");
			String select = scan.nextLine();
			System.out.println();
			Account id;
			if (select.equals("1")) {
				// 회원가입
				Sign.signUp();
			} else if (select.equals("2")) {
				// 회원정보찾기
				Find.findInfo();
			} else if (select.equals("3")) {
				//로그인
				if ((id = Login.loginMain()) != null) {
					Atm.atmProgram(id);
				}
			} else {
				// 종료
				loop = false;
				System.out.println("프로그램 종료");
			} // if

		} // while

		Data.save();
	}

	/**
	 * 프로그램의 로고를 출력해주는 메소드입니다. 
	 */
	private static void logo() {
		System.out.println("          :::     ::::::::::: ::::     ::::  \r\n"
				+ "        :+: :+:       :+:     +:+:+: :+:+:+ \r\n"
				+ "       +:+   +:+      +:+     +:+ +:+:+ +:+ \r\n"
				+ "      +#++:++#++:     +#+     +#+  +:+  +#+ \r\n"
				+ "      +#+     +#+     +#+     +#+       +#+ \r\n"
				+ "      #+#     #+#     #+#     #+#       #+# \r\n" 
				+ "      ###     ###     ###     ###       ### ");

	}

}