package atm.managerService;

/**
 * 입출금 또는 ATM기기에서 현금 추가, 회수시 보유금액을 저장하는 클래스입니다. 
 */
public class ATMCash {

    private int number; 
    private int cash; 
    
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
    
	
    
}