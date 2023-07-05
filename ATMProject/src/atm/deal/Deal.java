package atm.deal;

import java.util.Calendar;

/**
 * 거래 내역을 저장하는 클래스입니다. 
 */
public class Deal {
	
	//계좌번호, 거래일, 송신 이름, 수신 이름, 입금금액, 출금금액, 거래후잔액
	private int accountNumber; //계좌번호
	private String tradingDay; //거래일
	private String transName; //송신이름
	private String receiveName; //수신이름
	private int deposit; //입금금액
	private int withdrawal; //출금금액
	private int cashmoney; //잔액 
	

	public int getCashmoney() {
		return cashmoney;
	}
	public void setCashmoney(int cashmoney) {
		this.cashmoney = cashmoney;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getTradingDay() {
		return tradingDay;
	}
	public void setTradingDay(String tradingDay) {
		this.tradingDay = tradingDay;
	}
	public String getTransName() {
		return transName;
	}
	public void setTransName(String transName) {
		this.transName = transName;
	}
	public String getReceiveName() {
		return receiveName;
	}
	public void setReceiveName(String receiveName) {
		this.receiveName = receiveName;
	}
	public int getDeposit() {
		return deposit;
	}
	public void setDeposit(int deposit) {
		this.deposit = deposit;
	}
	public int getWithdrawal() {
		return withdrawal;
	}
	public void setWithdrawal(int withdrawal) {
		this.withdrawal = withdrawal;
	}
	
	
	
}
