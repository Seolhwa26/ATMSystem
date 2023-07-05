package atm.managerService;

import java.util.Scanner;

import atm.atmService.Atm;
import atm.data.Data;

/**
 * 관리자 메뉴를 보여주는 클래스입니다. 
 */
public class Manager {
	
	/**
	 * 관리자 메뉴를 보여주는 메소드입니다. 
	 */
	public static void managerMain() {
		boolean loop = true;

		Scanner scan = new Scanner(System.in);

		while (loop) { // 화면 시작

			int atmAmount = Data.atmcash.get(Data.atmcash.size() - 1).getCash();
			
			System.out.println();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃           *계좌 관리 프로그램(관리자모드)*        ");
			System.out.println("┃==========                            ");
			System.out.println("┃1.계정 조회	┏━━━━━━━━━━━━━━━━━━━━━━━━┓    ");
			System.out.println("┃==========	┃ 입금 ┃ 출금 ┃ 명세 ┃    =========");
			System.out.println("┃2.기기현금관리  ┃━━━━━━━━━━━━━━━━━━━━━━━━┃       3.종료");
			System.out.printf("┃==========	┃  O   ┃  %s  ┃   O  ┃    ========= \n", (atmAmount >= 1000000 ? "O" : "X"));
			System.out.println("┃        	┗━━━━━━━━━━━━━━━━━━━━━━━━┛     ");
			System.out.println("┃         		       ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("선택: ");
			String select = scan.nextLine();
			
			if (select.equals("1")) {
				// 계정조회
				Manager_AccountCheck.AccountCheck();
			} else if (select.equals("2")) {
				// 기기현금관리
				Manager_AtmCash.CashMangement();
			} else {
				loop = false;
			}
		}
		Data.pause();
	}

}
