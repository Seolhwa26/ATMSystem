package atm.dummy;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

public class Dummy {
	 static String[] joinDayList = new String[50]; // 인원 증가시 여기도 바꿔줘야 함
	 static String[] nameList = new String[joinDayList.length];
	 static  int[] accountBalance = new int[joinDayList.length]; //회원들 잔액
	 
	   public static void main(String[] args) {

	      try { 
	    	  
	         accountDummy();
	         dealDummy();
	         accountDummy2();
	         atmDummy();
	      } catch (Exception e) {
	         System.out.println("Dummy.main");
	         e.printStackTrace();
	      }

	   } 





	private static void atmDummy() throws IOException {
		
		 FileWriter writer = new FileWriter(".\\dat\\ATMCash.txt");
		 String data = "1,5000000";
		 writer.write(data + "\r\n");
		 writer.close();
	}





	private static void accountDummy2() throws IOException {
		   
		   BufferedReader reader = new BufferedReader(new FileReader(".\\dat\\account.txt"));
		   
		   String line = null;
	       int index = 0;
	       String[] txt = new String[joinDayList.length];
	       
	       while((line = reader.readLine())!= null) {
	    	   txt[index]= line;
	    	   index++;
	       }
	       
	       reader.close();
	        
		   FileWriter writer = new FileWriter(".\\dat\\account.txt");
		      
		      for (int i=0; i<joinDayList.length; i++) {

		         String data = String.format("%s,%d"
		                , txt[i]
		                , accountBalance[i]);
		  
		         System.out.println(data);
		         
		         writer.write(data + "\r\n");
		         
		      }//for
		   
		      writer.close();
		      
	}

	private static void dealDummy() throws IOException {
	      //계좌번호, 거래일, 송신 이름, 수신 이름, 입금금액, 출금금액, 거래후잔액
	      //1,2007-02-03,ATM,홍길동,5000,0,6000 //입금
	      //2,2008-03-09,아무개,ATM,0,1000,2000 //출금
	      //3,2010-11-10,강감찬,김유신,1000,0,2000 //계좌이체 입금 
		  //4,2010-11-10,강감찬,김유신,0,1000,3000 //계좌이체 출금
		
		
	      //계좌번호 - 고유번호
	      int accountNumber = 1;
	      
	      //잔액
	      int balance = 0;
	      
	      //입금 금액
	      int depowit = 0;
	      
	      //출금 금액
	      int withdraw = 0;
	      
	      //입금?0 출금?1 계좌이체?2
	      int isDepowit = 0;
	      
	      Random rnd = new Random();
	      
	      FileWriter writer = new FileWriter(".\\dat\\deal.txt");
	      
	      for (int i=0; i<joinDayList.length; i++) {
	         depowit = rnd.nextInt(30)*1000 + 1000; //1000 ~ 30000 입금(가입금액)
	         String data = String.format("%d,%s,ATM,%s,%d,%d,%d", accountNumber, joinDayList[i], nameList[i], depowit, withdraw, balance += (depowit-withdraw));
	         System.out.println(data);
	         writer.write(data + "\r\n");
	         
	         accountNumber++;
	         
	         accountBalance[i] = balance; //계좌별 회원들 잔액 저장
	         depowit = 0;
	         withdraw = 0;
	         balance = 0;
	      } //처음 계좌 생성시 통장에 입금 내역 작성
	      
	      //accountDummy()에서 가입일 날짜 -> joinDayList[]에 저장 -> dealDummy()에서 거래일 표시, 마지막날짜 가져와서 같거나 그이후 날짜로 맞춤
	      Calendar c = Calendar.getInstance();
	      c.set(Integer.parseInt(joinDayList[joinDayList.length-1].substring(0, 4)),Integer.parseInt(joinDayList[joinDayList.length-1].substring(5, 7)),Integer.parseInt(joinDayList[joinDayList.length-1].substring(8)));
	      
	      for (int i=0; i<100; i++) { //거래내역 더 추가 하고 싶으면 숫자 더 증가
	   
	         accountNumber = rnd.nextInt(50) + 1; //1~50 계좌 랜덤, 계좌이체 보낼 계좌
	         int toAccountNumber = rnd.nextInt(50) + 1; //계좌이체 받을 계좌
	         while (accountNumber == toAccountNumber) { //계좌이체 보낼 계좌, 받을 계좌 서로 같으면 안됨
	            toAccountNumber = rnd.nextInt(50) + 1; 
	         }
	         
	         int money = rnd.nextInt(20)*1000 + 1000; //거래할떄 금액 랜덤 (0~20000)
	         isDepowit = rnd.nextInt(3); //입금?0 출금?1 계좌이체?2
	      
	         c.add(Calendar.DATE, rnd.nextInt(10)); //날짜 + (0~9)일 추가
	      
	         String data;
	         if (isDepowit == 0) { //입금
	            depowit = money;
	            data = String.format("%d,%tF,ATM,%s,%d,%d,%d", accountNumber, c, nameList[accountNumber - 1], depowit, withdraw, accountBalance[accountNumber - 1] += depowit);
	         } else if (isDepowit == 1) { //출금
	            if (money > accountBalance[accountNumber - 1]) { //출금시, 잔액보다 뺄 금액이 더 큰 경우 -> 입금으로 처리
	               depowit = money;
	               data = String.format("%d,%tF,ATM,%s,%d,%d,%d", accountNumber, c, nameList[accountNumber - 1], depowit, withdraw, accountBalance[accountNumber - 1] += depowit);
	            } else { //00002,2008-03-09,홍길동,ATM,0,1000,0 //출금
	               withdraw = money;
	               data = String.format("%d,%tF,%s,ATM,%d,%d,%d", accountNumber, c, nameList[accountNumber - 1], depowit, withdraw, accountBalance[accountNumber - 1] -= withdraw);
	            }
	         } else { //계좌이체
	            if (money > accountBalance[accountNumber - 1]) { //보낼계좌 잔액보다 뺼 금약이 더 큰 경우 > continue로 넘김
	               System.out.println("거래x");
	               continue;
	            } else { //2문장 추가 (송금 - 입금용, 출금용)
	               depowit = money;
	               data = String.format("%d,%tF,%s,%s,%d,%d,%d", toAccountNumber, c, nameList[accountNumber - 1], nameList[toAccountNumber - 1], depowit, withdraw, accountBalance[toAccountNumber - 1] += depowit);
	               System.out.println(data);
	               writer.write(data + "\r\n");
	               depowit = 0;
	               withdraw = money;
	               data = String.format("%d,%tF,%s,%s,%d,%d,%d", accountNumber, c, nameList[accountNumber - 1], nameList[toAccountNumber - 1], depowit, withdraw, accountBalance[accountNumber - 1] -= withdraw);
	            }
	         }
	         
	         System.out.println(data);
	         writer.write(data + "\r\n");
	      
	         depowit = 0;
	         withdraw = 0;
	      
	      }
	      
	      writer.close(); 
	      
	   }

	   private static void accountDummy() throws Exception{

	      //계좌번호, PW, 이름, 생년월일, 전화번호, 주민등록번호, 정지유무(true, false), ID
	      
	      Random rnd = new Random();
	      
	      //계좌번호 - 고유번호
	      int accountNumber = 1;
	      
	      //아이디
	      String[] id = new String[joinDayList.length]; //중복 제거한 아이디 들어감 
	       String[] id1 = { "ar", "bg", "cc", "ds", "ew", "ft", "gs", "ty", "tu", "ti", "to", "tp", "q2", "zx", "zc", "zb", "zn", "zm", "gd", "kl", "kj", "kg" };
	       String[] id2 = { "aq", "bw", "cg", "dr", "eb", "fs", "gt", "yq", "ya", "yz", "yb", "nz", "na", "nm", "nfr", "fwt", "pe", "pf", "pb", "pla", "icm" };
	      
	       //이름
	      String[] name1 = { "김", "이", "박", "정", "최", "손", "유" };
	      String[] name2 = { "신", "석", "효", "정", "진", "현", "제", "형", "민", "돈", "수", "나", "연", "찬" };
	      
	      //생년월일
	      String birthDate;
	      
	      FileWriter writer = new FileWriter(".\\dat\\account.txt");
	      
	      HashSet<String> set = new HashSet<String>();
	      
	      while (set.size() < joinDayList.length) { //id값 중복 제거
	         set.add(id1[rnd.nextInt(id1.length)] + id2[rnd.nextInt(id2.length)] + rnd.nextInt(99));
	      }
	      
	      Iterator<String> iter = set.iterator(); //id[]에 중복제거한 id값 넣기
	      
	      int num = 0;
	      while (iter.hasNext()) {
	         id[num] = iter.next();
	         num++;
	      }
	      
	      Calendar c = Calendar.getInstance();
	      c.set(2020,1,1);
	      
	      for (int i=0; i<joinDayList.length; i++) {
	         
	         int year = rnd.nextInt(84) + 1920; //1920 ~ 2003년(20세 ~ 103세)
	         
	         int month = rnd.nextInt(12) + 1; //1~12월

	         int day; //평년,윤년에 따라 2월 28일/29일 나눔, 월 단위 31일, 30일, 28일, 29일
	         switch (month) {
	            case 4:
	            case 6:
	            case 9: 
	            case 11: 
	               day = rnd.nextInt(30) + 1;
	               break;
	               
	            case 2: 
	               if(year % 4 == 0 && year % 100 != 0 || year % 400 == 0) {
	                  day = rnd.nextInt(29) + 1;
	               } else {
	                  day = rnd.nextInt(28) + 1;
	               }
	               break;
	               
	            default: day = rnd.nextInt(31) + 1;
	         }
	         
	         String name = name1[rnd.nextInt(name1.length)] + name2[rnd.nextInt(name2.length)] + name2[rnd.nextInt(name2.length)];
	         
	         joinDayList[i] = String.format("%tF", c); //계좌 가입일 들어감
	         nameList[i] = name;
	   
	         birthDate = String.format("%d-%02d-%02d", year, month, day);
	         //계좌번호,가입일, PW, 이름, 생년월일, 전화번호, 주민등록번호, 정지유무(true, false), ID
	         String data = String.format("%d,%tF,%04d,%s,%s,010-%s,%s,%b,%s", accountNumber 
	                , c
	                , rnd.nextInt(10000) //0000 ~ 9999
	                , name
	                , birthDate
	                , rnd.nextInt(9000) + 1000 + "-" + (rnd.nextInt(9000) + 1000)
	                , birthDate.replace("-", "").substring(2) + "-" + (rnd.nextInt(9000000) + 1000000)
	                , true
	                , id[i]);
	         
	         accountNumber++;
	         c.add(Calendar.DATE, rnd.nextInt(10)); //가입일은 이전사람과 같거나 최대 10일 차이-> 가입일에 따라 순차적으로 계좌 번호 할당)
	         System.out.println(data);
	         
	         writer.write(data + "\r\n");
	         
	      }//for
	   
	      writer.close();
	      
	   }//accountDummy()
	   
	}