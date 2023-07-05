package atm.managerService;

import java.util.ArrayList;
import java.util.Scanner;

import atm.account.Account;
import atm.atmService.Atm;
import atm.data.Data;

/**
 * 사용자 계정을 관리하는 클래스입니다. 
 */ 
public class Manager_AccountCheck {
	
	/**
	 * 사용자계정 관리 메뉴를 보여주는 메소드입니다.  
	 */
	public static void AccountCheck() {
		boolean loop = true;

		Scanner scan = new Scanner(System.in);

		int atmAmount = Data.atmcash.get(Data.atmcash.size() - 1).getCash();

		while (loop) { // 화면 시작

			System.out.println();
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃            *계좌 관리 프로그램(관리자모드)*        ");
			System.out.println("┃==========                 ");
			System.out.println("┃1.계정삭제	┏━━━━━━━━━━━━━━━━━━━━━━━━┓                  ");
			System.out.println("┃==========	┃ 입금 ┃ 출금 ┃ 명세 ┃    =========");
			System.out.println("┃2.계좌정지	┃━━━━━━━━━━━━━━━━━━━━━━━━┃       4.종료");
			System.out.printf("┃==========	┃  O   ┃  %s  ┃   O  ┃    =========\n", (atmAmount >= 1000000 ? "O" : "X"));
			System.out.println("┃3.계정정보	┗━━━━━━━━━━━━━━━━━━━━━━━━┛     ");
			System.out.println("┃==========			       ");
			System.out.println("┃				   	   ");
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			System.out.print("선택: ");
			String select = scan.nextLine();
			System.out.println();
			
			if (select.equals("1")) {
				// 계정삭제
				delete();
			} else if (select.equals("2")) {
				// 계좌정지
				accStop();
			} else if (select.equals("3")) {
				// 계정정보
				accInfo();
			} else {
				// 종료
				loop = false;
			}
		}
	}

	/**
	 * 계좌정지를 하는 메소드입니다. 
	 */
	private static void accStop() {

		Scanner scan = new Scanner(System.in);

		System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
		System.out.println("┃                                      *계좌정지");
		System.out.print("┃계좌 : ");
		int acc = scan.nextInt();
		scan.nextLine();
		System.out.print("┃비밀번호 : ");
		String pw = scan.nextLine();

		Account a = Data.getAccount(acc);

		if (a.getAccountNumber() == acc && a.getPW().equals(pw)) {
			a.setStop(false);
			System.out.printf("┃계좌번호 %d(이)가 정지되었습니다.\n", acc);
		} else {
			System.out.println("┃계좌번호 또는 비밀번호가 다릅니다.");
		}
		System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		System.out.println();
		Data.save();
		Data.pause();

	}

	/**
	 * 계정정보를 보는 메소드입니다. 
	 */
	private static void accInfo() {
		Scanner scan = new Scanner(System.in);
		ArrayList<Account> acc = Data.account;

		boolean check = true;

		while (check) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃                                      *계정정보");
			System.out.println("┃확인하실 계정의 계좌번호와 이름을 입력해주세요.");
			System.out.print("┃계좌번호: ");
			int input1 = scan.nextInt();
			scan.nextLine();
			System.out.print("┃이름: ");
			String input2 = scan.nextLine();
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			for (int i = 0; i < acc.size(); i++) {
				if (input1 == acc.get(i).getAccountNumber() && input2.equals(acc.get(i).getName())) {
					System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.printf("┃계좌번호: %s\n", acc.get(i).getAccountNumber());
					System.out.printf("┃가입일: %s\n", acc.get(i).getJoinDay());
					System.out.printf("┃아이디: %s\n", acc.get(i).getID());
					System.out.printf("┃비밀번호: %s\n", acc.get(i).getPW());
					System.out.printf("┃생년월일: %s\n", acc.get(i).getBirth());
					System.out.printf("┃전화번호: %s\n", acc.get(i).getTel());
					System.out.printf("┃주민번호: %s\n", acc.get(i).getJoomin());
					System.out.printf("┃계좌정지: %s\n", acc.get(i).isStop() ? "X" : "O");
					System.out.printf("┃잔액: %,d원\n", acc.get(i).getBalance());
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					System.out.println("1.더보기 2.나가기");
					System.out.print("선택: ");
					System.out.println();
					
					int input3 = scan.nextInt();
					
					if (input3 == 1) {
						i = acc.size();
					} else {
						i = acc.size();
						check = false;
					}
				} else if (i == acc.size() - 1) {
					System.out.println("존재하지않는 계정입니다.");

				}
			}

		}

	}

	/**
	 * 계정삭제를 하는 메소드입니다. 
	 */
	private static void delete() {
		Scanner scan = new Scanner(System.in);
		ArrayList<Account> acc = Data.account;

		boolean check = true;

		while (check) {
			System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
			System.out.println("┃                                      *계정삭제");
			System.out.print("┃계좌번호: ");
			int input1 = scan.nextInt();
			scan.nextLine();
			System.out.print("┃이름: ");
			String input2 = scan.nextLine();
			System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
			for (int i = 0; i < acc.size(); i++) {
				if (input1 == acc.get(i).getAccountNumber() && input2.equals(acc.get(i).getName())) {
					System.out.println();
					System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
					System.out.printf("┃계좌번호: %s\n", acc.get(i).getAccountNumber());
					System.out.printf("┃이름: %s\n", acc.get(i).getName());
					System.out.printf("┃잔액: %,d원\n", acc.get(i).getBalance());
					System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
					System.out.print("계정을 삭제하시겠습니까?(y,n): ");
					String del = scan.nextLine();
					if (del.equals("y")) {
						if (acc.get(i).getBalance() == 0) {
							Data.deleteAcc(input1, input2);
							System.out.println("*계정이 삭제 되었습니다.");
							Data.deleteDeal(input1);
						} else {
							System.out.println("*계좌에 잔액이 있어 계정삭제가 불가능합니다.");
						}
					} else if (del.equals("n")) {
						System.out.println("*계정삭제를 취소합니다.");
					} else {
						System.out.println("*y 또는 n을 입력해주세요.");
					}
					Data.pause();
					Data.save();
					i = acc.size();
					check = false;
				} else if (i == acc.size() - 1) {
					System.out.println("*존재하지않는 계정입니다.");

				}
			}
			System.out.println();
		}
	}// delete
}