package atm.data;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;

import atm.account.Account;
import atm.deal.Deal;
import atm.managerService.ATMCash;

/**
 * 회원정보를 이용해서 필요한 정보를 추출하는 클래스입니다. 
 */
public class Data {

	private final static String ACCOUNT;
	private final static String DEAL;
	private final static String ATMCASH;

	public static ArrayList<Account> account; // = account.txt
	public static ArrayList<Deal> deal; // = deal.tct
	public static ArrayList<ATMCash> atmcash; // = ATMCash.txt

	static {

		ACCOUNT = ".\\dat\\account.txt";
		DEAL = ".\\dat\\deal.txt";
		ATMCASH = ".\\dat\\ATMCash.txt";

		account = new ArrayList<Account>();
		deal = new ArrayList<Deal>();
		atmcash = new ArrayList<ATMCash>();
	}

	/**
	 * 새로 가입한 회원의 계좌번호를 반환해주는 메소드입니다. 
	 */
	public static int createAccNum() {

		int s1 = account.get(account.size() - 1).getAccountNumber() + 1;

		return s1;
	}

	/**
	 * 매개변수로 받은 계좌번호와 일치하는 거래내역들울 arrayList로 반환하는 메소드입니다. 
	 */
	public static ArrayList<Deal> listDeal(int accountNumber, String name, boolean stop) {

		// account.txt(계좌번호, 이름, 정지유무) -> deal.txt

		ArrayList<Deal> list = new ArrayList<Deal>();

		for (Deal d : deal) {

			if (d.getAccountNumber() == accountNumber) {

				list.add(d);
			}
		}

		return list;
	}

	/**
	 * 회원정보, 거래내역, ATM금액정보가 있는 텍스트파일을 각각 1줄씩 읽어와서 객체 변수에 저장하는 메소드입니다. 
	 */
	public static void load() {

		// 텍스트 파일 > 컬렉션

		try {
			BufferedReader reader = new BufferedReader(new FileReader(ACCOUNT));

			String line = null;

			while ((line = reader.readLine()) != null) {

				String[] temp = line.split(",");

				Account acc = new Account();

				// 계좌번호, 가입일, PW, 이름, 생년월일, 전화번호, 주민등록번호, 정지유무(true, false), ID
				acc.setAccountNumber(Integer.parseInt(temp[0]));
				acc.setJoinDay(temp[1]);
				acc.setPW(temp[2]);
				acc.setName(temp[3]);
				acc.setBirth(temp[4]);
				acc.setTel(temp[5]);
				acc.setJoomin(temp[6]);
				acc.setStop(Boolean.valueOf(temp[7]));
				acc.setID(temp[8]);
				acc.setBalance(Integer.parseInt(temp[9]));

				account.add(acc);

			} // while

			reader.close();

			reader = new BufferedReader(new FileReader(DEAL));

			line = null;

			while ((line = reader.readLine()) != null) {

				// 계좌번호, 거래일, 송신 이름, 수신 이름, 입금금액, 출금금액, 거래후잔액

				String[] temp = line.split(",");

				Deal dl = new Deal();

				dl.setAccountNumber(Integer.parseInt(temp[0]));
				dl.setTradingDay(temp[1]);
				dl.setTransName(temp[2]);
				dl.setReceiveName(temp[3]);
				dl.setDeposit(Integer.parseInt(temp[4]));
				dl.setWithdrawal(Integer.parseInt(temp[5]));
				dl.setCashmoney(Integer.parseInt(temp[6]));
				deal.add(dl);

			}

			reader.close();

			reader = new BufferedReader(new FileReader(ATMCASH));

			line = null;

			while ((line = reader.readLine()) != null) {

				// 숫자, 잔액

				String[] temp = line.split(",");

				ATMCash atc = new ATMCash();

				atc.setNumber(Integer.parseInt(temp[0]));
				atc.setCash(Integer.parseInt(temp[1]));

				atmcash.add(atc);

			}

			reader.close();

		} catch (Exception e) {
			System.out.println("Data.load");
			e.printStackTrace();
		}
	}// load

	/**
	 * 회원정보, 거래내역, ATM금액정보가 있는 텍스트 파일에 각각 값을 추가하는 메소드입니다. 
	 */
	public static void save() {
		// 컬렉션 > 텍스트 파일

		try {

			// 계좌번호, 가입일, PW, 이름, 생년월일, 전화번호, 주민등록번호, 정지유무(true, false), ID
			BufferedWriter writer = new BufferedWriter(new FileWriter(ACCOUNT));

			for (Account acc : account) {
				String data = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%d", acc.getAccountNumber(), acc.getJoinDay(),
						acc.getPW(), acc.getName(), acc.getBirth(), acc.getTel(), acc.getJoomin(), acc.isStop(),
						acc.getID(), acc.getBalance());

				writer.write(data + "\r\n");
			}
			writer.close();

			// 계좌번호, 거래일, 송신 이름, 수신 이름, 입금금액, 출금금액, 거래후잔액
			writer = new BufferedWriter(new FileWriter(DEAL));

			for (Deal dl : deal) {
				String data = String.format("%s,%s,%s,%s,%d,%d,%d", dl.getAccountNumber(), dl.getTradingDay(),
						dl.getTransName(), dl.getReceiveName(), dl.getDeposit(), dl.getWithdrawal(), dl.getCashmoney());

				writer.write(data + "\r\n");
			}
			writer.close();

			// 숫자, 금액
			writer = new BufferedWriter(new FileWriter(ATMCASH));

			for (ATMCash atc : atmcash) {
				String data = String.format("%d,%d", atc.getNumber(), atc.getCash());

				writer.write(data + "\r\n");
			}
			writer.close();

		} catch (Exception e) {
			System.out.println("Data.save");
			e.printStackTrace();
		}
	}// save

	/**
	 * 매개변수로 받은 계좌번호의 정보와 일치하는 회원객체를 반환하는 메소드입니다. 
	 */
	public static Account getAccount(int acc) {

		for (Account a : account) {

			if (a.getAccountNumber() == acc) {

				return a;
			}

		}

		return null;
	}

	/**
	 * 무한루프를 사용하는 곳에서 값을 입력받기 전까지 잠깐 정지하는 메소드입니다. 
	 */
	public static void pause() {

		System.out.println();
		System.out.println("계속하시려면 엔터를 입력하세요.");

		Scanner scan = new Scanner(System.in);

		scan.nextLine();
		System.out.println();
	}

	/**
	 * 매개변수로 입력받은 이름, 아이디, 주민번호와 일치하는 회원객체를 리스트에 추가하여 그 리스트를 반환하는 메소드입니다. 
	 */
	public static ArrayList<Account> findPw(String name, String id, String jumin) {

		ArrayList<Account> acclist = new ArrayList<Account>();

		for (Account acc : account) {

			if (acc.getName().equals(name) && acc.getID().equals(id) && acc.getJoomin().equals(jumin)) {

				acclist.add(acc);
			}

		}

		return acclist;

	}

	/**
	 * 매개변수로 입력받은 계좌번호와 이름이 일치하는 회원객체를 회원객체 리스트에서 삭제하는 메소드입니다. 
	 */
	public static boolean deleteAcc(int accountNumber, String name) {

		for (int i = 0; i < account.size(); i++) {
			if (account.get(i).getAccountNumber() == accountNumber && account.get(i).getName().equals(name)) {
				account.remove(i);
			}
		}

		return false;

	}

	/**
	 * ATM기기 현금 변동의 순서를 반환해주는 메소드입니다. 
	 */
	public static int createCashNum() {

		int n = atmcash.get(atmcash.size() - 1).getNumber() + 1;

		return n;

	}

	/**
	 * 매개변수로 입력받은 이름, 주민번호와 일치하는 회원객체를 리스트에 추가하여 그 리스트를 반환하는 메소드입니다. 
	 */
	public static ArrayList<Account> findId(String name, String jumin) {
		ArrayList<Account> idlist = new ArrayList<Account>();

		for (Account acc : account) {

			if (acc.getName().equals(name) && acc.getJoomin().equals(jumin)) {

				idlist.add(acc);
			}

		}

		return idlist;

	}

	/**
	 * 계정을 삭제한 회원의 거래내역을 삭제하는 메소드입니다. 
	 */
	public static void deleteDeal(int input1) {

		for (int i = deal.size() - 1; i >= 0; i--) {
			if (deal.get(i).getAccountNumber() == input1) {
				deal.remove(i);
			}
		}
	}

}
