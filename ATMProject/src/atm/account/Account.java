package atm.account;

/**
 * 회원 정보를 저장하는 클래스입니다. 
 */
public class Account { 
	 //계좌번호, 가입일, PW, 이름, 생년월일, 전화번호, 주민등록번호, 정지유무(true, false), ID
	   
	   private int accountNumber; //계좌번호
	   private String joinDay; //가입일
	   private String PW; //비번
	   private String name; //이름
	   private String birth; //생년월일
	   private String tel;
	   private String joomin; //주민등록번호
	   private boolean stop;
	   private String ID; //아이디
	   private int balance;
	   
	   public int getBalance() {
	      return balance;
	   }
	   public void setBalance(int balance) {
	      this.balance = balance;
	   }
	   public int getAccountNumber() {
	      return accountNumber;
	   }
	   public void setAccountNumber(int accountNumber) {
	      this.accountNumber = accountNumber;
	   }
	   public String getJoinDay() {
	      return joinDay;
	   }
	   public void setJoinDay(String joinDay) {
	      this.joinDay = joinDay;
	   }
	   public String getPW() {
	      return PW;
	   }
	   public void setPW(String pW) {
	      PW = pW;
	   }
	   public String getName() {
	      return name;
	   }
	   public void setName(String name) {
	      this.name = name;
	   }
	   public String getBirth() {
	      return birth;
	   }
	   public void setBirth(String birth) {
	      this.birth = birth;
	   }
	   public String getTel() {
	      return tel;
	   }
	   public void setTel(String tel) {
	      this.tel = tel;
	   }
	   public String getJoomin() {
	      return joomin;
	   }
	   public void setJoomin(String joomin) {
	      this.joomin = joomin;
	   }
	   public boolean isStop() {
	      return stop;
	   }
	   public void setStop(boolean stop) {
	      this.stop = stop;
	   }
	   public String getID() {
	      return ID;
	   }
	   public void setID(String iD) {
	      ID = iD;
	   }
	   
	   
	   
	}