package atm.loginService;

import java.util.Scanner;

import atm.account.Account;
import atm.data.Data;
import atm.managerService.Manager;

/**
 * 전체 로그인 메뉴를 보여주는 클래스입니다. 
 */
public class Login {
	
	/**
	 * 회원 리스트에 저장된 아이디, 비밀번호룰 입력했는지 검사하는 메소드입니다. 
	 */
	public static Account loginMain() {
	      boolean loop = true;

	      Scanner scan = new Scanner(System.in);
	      
	      while (loop) {
	         System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
	         System.out.println("┃                                        *로그인 ");
	         System.out.print("┃아이디: ");
	         String idInput = scan.nextLine();
	         System.out.print("┃비밀번호: ");
	         String passInput = scan.nextLine();
	         System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
	         
	         for (Account acc : Data.account) {
	            if(idInput.equals("admin")&&passInput.equals("0000")) {
	            	 System.out.println("*관리자로 로그인 되었습니다.");
	               Manager.managerMain();
	               loop=false;
	               return null;
	            }else if (idInput.equals(acc.getID()) && passInput.equals(acc.getPW())) {
	                System.out.println("*" + acc.getName() + "님 로그인 되었습니다.");
	                loop = false;
	                return acc; 
	             } 
	         }
	         System.out.println("*아이디 또는 비밀번호가 다릅니다.");
	         System.out.println("1.재시도, 2.나가기");
	         System.out.print("선택: ");
	         int input = scan.nextInt();
	         scan.nextLine();
	         if(input==1) {
	            
	         }else {
	            return null;
	         }
	                              
	      }
	      return null;
	   }
	}