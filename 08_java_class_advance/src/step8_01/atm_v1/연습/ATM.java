package step8_01.atm_v1.연습;
import java.util.Random;
import java.util.Scanner;

public class ATM {
	
	Scanner scan = new Scanner(System.in);
	Random ran   = new Random();
	UserManager userManager = new UserManager();
	int identifier = -1;
	
	void printMainMenu() {
				
		while (true) {
			
			System.out.println("\n[ MEGA ATM ]");
			System.out.print("[1.로그인] [2.로그아웃] [3.회원가입] [4.회원탈퇴] [0.종료] : ");
			int sel = scan.nextInt();
			
			if      (sel == 1) 	    login();
			else if (sel == 2) 		logout();
			else if (sel == 3) 		join();
			else if (sel == 4) 		leave();
			else if (sel == 0) 		break;
			
		}
		
		System.out.println("[메시지] 프로그램을 종료합니다.");
		
	}
	
	
	void login() {
		
		identifier = userManager.logUser();
		if ( -1 == identifier) {
			System.out.println("존재하지 않는 ID입니다.");
			return ;
		}
		
		else {
			
			System.out.println("로그인 성공");
			this.printAccountMenu();
		}
	}
	
	
	void join() {	
		
		userManager.addUser();
		
	}
	
	
	void logout() {
		
		if ( identifier == -1 ) {
			System.out.println("로그인을 먼저 실행해주세요");
		}
		else {
			System.out.println("로그아웃 완료");
			identifier = -1;
		}

	}
	
	
	void leave() {
		
		
		userManager.leave();
	}
	
	
	void printAccountMenu() {
		
	

		while (true) {
			
			System.out.print("[1.계좌생성] [2.계좌삭제] [3.조회] [0.로그아웃] : ");
			int getNum = scan.nextInt();
			
			if (getNum == 1) {
				
				String randomAcc = Integer.toString(ran.nextInt(9001) + 10000);
				Account[] temp = null;
				
				if (userManager.user[identifier].accCount == 0) {
					
					userManager.user[identifier].acc = new Account[1];
					userManager.user[identifier].acc[0] = new Account();
					userManager.user[identifier].acc[0].number = randomAcc;
				}
				else {
	
					int countUserAcc = userManager.getUser(identifier).accCount;
					Account[] userAccount = userManager.getUser(identifier).acc;
					
					Account[] tempAccount = userAccount;
					
					userAccount = new Account[countUserAcc + 1];
					for (int i = 0; i < countUserAcc; i++) {
						userAccount[i] = tempAccount[i];
					}
					
					userAccount[countUserAcc] = new Account();
					userAccount[countUserAcc].number = randomAcc;
			
				}
				
				System.out.println("계좌" + randomAcc +"생성 완료");
				break;
				
			}
			else if (getNum == 2) {
				if (userManager.user[identifier].accCount == 0) {
					System.out.println("삭제할 계좌가 존재하지 않습니다.");
					return;
				}
				
				int countUserAcc = userManager.getUser(identifier).accCount;
				Account[] userAccount = userManager.getUser(identifier).acc;
				
				Account[] deleteTemp = userAccount;
				
				userAccount = new Account[countUserAcc - 1];
				
				System.out.print("삭제할 계좌번호 : ");
				String getAcc = scan.next();
				int getAccIndex = -1;
				
				for (int i = 0; i < deleteTemp.length; i++) {
				
					if ( deleteTemp[i].number.equals(getAcc)) {
						getAccIndex = i;
						break;
					}
				}
				
				if (getAccIndex == -1) {
					
					System.out.println("존재하지 않는 계좌입니다.");
					return;
				}
				
				else {
					
					int userAccoundIndex = 0;
					
					for (int i = 0; i < getAccIndex; i++) {
						userAccount[userAccoundIndex ++] = deleteTemp[i];
					}
					for (int i = getAccIndex + 1; i < deleteTemp.length; i++) {
						userAccount[userAccoundIndex ++] = deleteTemp[i];
					}
					
					System.out.println("계좌 삭제 완료");
				}
				
				userManager.getUser(identifier).accCount --;
				break;
				
			}
			else if (getNum == 3) {
				
				if ( userManager.user[identifier].accCount == 0) {
					System.out.println("계좌가 존재하지 않습니다.");
					return;
				}
				
				else {
					userManager.user[identifier].printAccount();
					
				}
				break;
				
			}
			else {
				
				this.logout();
				break;
			}
			
			
			
			
		}
		
	}	
}
