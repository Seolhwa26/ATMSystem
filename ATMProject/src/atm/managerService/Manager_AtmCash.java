package atm.managerService;

import java.util.Scanner;
import atm.data.Data;

/**
 * ATM 기기의 현금을 관리하는 클래스입니다. 
 */
public class Manager_AtmCash {
	static int atmAmount;

	/**
	 * ATM 기기의 현금 관리 메뉴를 보여주는 메소드입니다. 
	 */
	public static void CashMangement() {

		atmAmount = Data.atmcash.get(Data.atmcash.size() - 1).getCash();

		boolean loop = true;

		Scanner scan = new Scanner(System.in);

		while (loop) { // 화면 시작

			System.out.println();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃             *계좌 관리 프로그램(관리자모드)*        ");
			System.out.println("┃==========                            ");
			System.out.println("┃1.현금조회	┏━━━━━━━━━━━━━━━━━━━━━━━━┓    ");
			System.out.println("┃==========	┃ 입금 ┃ 출금 ┃ 명세 ┃    =========");
			System.out.println("┃2.현금회수      ┃━━━━━━━━━━━━━━━━━━━━━━━━┃       4.종료");
			System.out.printf("┃==========	┃  O   ┃  %s  ┃   O  ┃    =========\n", (atmAmount >= 1000000 ? "O" : "X"));
			System.out.println("┃3.현금추가      ┗━━━━━━━━━━━━━━━━━━━━━━━━┛     ");
			System.out.println("┃=========        		       ");
			System.out.println("┃       		       ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("선택: ");
			String select = scan.nextLine();
			System.out.println();
			
			if (select.equals("1")) {
				// 현금조회
				cashCheck();
			} else if (select.equals("2")) {
				// 현금회수
				cashCollection();
			} else if (select.equals("3")) {
				// 현금추가
				cashAdd();
			} else {

				int num = Data.createCashNum();
				ATMCash atc = new ATMCash();

				atc.setNumber(num);
				atc.setCash(atmAmount);

				Data.atmcash.add(atc);
				Data.save();
				Data.pause();
				// 종료
				return;
			}
		}

	}

	/**
	 * 현금추가를 하는 메소드입니다. 
	 */
	private static void cashAdd() {
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃                                      *현금추가");
		Scanner scan = new Scanner(System.in);

		System.out.print("┃기기에 현금을 추가하시겠습니까?(y/n): ");
		String answer = scan.nextLine();
		if (answer.equals("y")) {
			System.out.print("┃기기에 얼마를 추가하시겠습니까? 10000원 단위로 입력해주세요: ");
			try {
				int cash = scan.nextInt();
				while (cash == 0 || cash % 10000 != 0) {
					System.out.print("┃만원 단위로 입력해주세요: ");
					cash = scan.nextInt();
				}
				atmAmount += cash;
				System.out.printf("┃기기에 %,d원을 추가하여 %,d원 남아있습니다.\n", cash, atmAmount);

			} catch (Exception e) {
				System.out.println("┃숫자만 입력해주세요.");
				System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
				Data.pause();
				return;
			}

		} else if (answer.equals("n")) {
			System.out.println("┃기기의 현금추가를 취소합니다.");
		} else {
			System.out.println("┃y 또는 n만 입력해주세요.");
		}

		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}

	/**
	 * 현금회수를 하는 메소드입니다. 
	 */
	private static void cashCollection() {

		Scanner scan = new Scanner(System.in);
		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃                                      *현금회수");
		if (atmAmount < 5000000) {
			System.out.println("┃기기에 보관된 금액이 500만원을 초과하지 않았습니다.");
		} else {
			System.out.print("┃기기의 현금을 회수하시겠습니까?(y/n): ");
			String answer = scan.nextLine();
			int cashBack = 0;
			if (answer.equals("y")) {
				System.out.print("┃회수할 금액: ");
				try {
					cashBack = scan.nextInt();
					if (atmAmount - cashBack >= 5000000) {
						atmAmount -= cashBack;
						System.out.printf("┃%,d원이 회수되어 %,d원이 남았습니다.\n", cashBack, atmAmount);
					} else {
						System.out.println("┃기기에 현금이 5,000,000원 남아있어야 합니다.");
					}
				} catch (Exception e) {
					System.out.println("┃숫자만 입력해주세요.");
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					Data.pause();
					return;
				}
			} else if (answer.equals("n")) {
				System.out.println("┃기기의 현금회수를 취소합니다.");
			} else {
				System.out.println("┃y 또는 n만 입력해주세요.");
			}

		}
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	}

	/**
	 * 현금조회를 하는 메소드입니다. 
	 */
	private static void cashCheck() {

		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃                                      *현금조회");
		System.out.printf("┃기기에 보관된 현금은 %,d원 입니다.\n", atmAmount);
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");

	}

}
