package step8_01.atm_v1.연습2;
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
		
		if (identifier == -1 ) {
			System.out.println("존재하지 않는 ID입니다. ");
			return;
		}
		else {
			System.out.println("로그인 성공");
			this.printAccountMenu();
		}
		
	}
	
	
	void join() {	
		
		userManager.addUser();
		userManager.printAllUser();
		
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
				
				System.out.println("계좌 생성 시작");
				String randomAccount = Integer.toString(ran.nextInt(100001));
				
				int getNumberofAcc = userManager.getUser(identifier).accCount;
				User getUser = userManager.getUser(identifier);
				
				if (getNumberofAcc == 0) {
					
					getUser.acc = new Account[1];
					getUser.acc[0] = new Account();
				}
				
				else {
					
					Account[] temp = getUser.acc;
					getUser.acc = new Account[getNumberofAcc + 1];
					
					for (int i = 0; i < temp.length; i++) {
						
						getUser.acc[i] = temp[i];
					}
					getUser.acc[getNumberofAcc] = new Account();
				}
				
				
				getUser.acc[getNumberofAcc].number = randomAccount;
				getUser.accCount ++;
				System.out.println(randomAccount + " 생성 성공");
				
				break;
			}
			else if (getNum == 2) {
				
				int indexofDeleteAcc = -1;
				
				System.out.println("계좌 삭제 시작");
				System.out.print("삭제하실 계좌 : ");
				String getAcc = scan.next();
				
				int getNumberofAcc = userManager.getUser(identifier).accCount;
				User getUser = userManager.getUser(identifier);
				
				
				for (int i = 0; i < getUser.acc.length; i++) {
					
					if (getAcc.equals(getUser.acc[i].number)) {
						
						indexofDeleteAcc = i;
						break;
					}
				}
				
				if (indexofDeleteAcc == -1) {
					
					System.out.println("존재하지 않는 계좌입니다.");
					return;
				}
				else {
					
					int indexofUserArray = 0;
					Account[] temp = getUser.acc;
					getUser.acc = new Account[temp.length - 1];
					
					for (int i = 0; i < indexofDeleteAcc ; i++) {
						
						getUser.acc[indexofUserArray ++] = temp[i];
						
					}
					
					for (int i = indexofDeleteAcc + 1; i < temp.length; i++) {
						getUser.acc[indexofUserArray ++] = temp[i];
					}
					
					getUser.accCount --;
					System.out.println("계좌 삭제 성공");
				}
				break;
				
			}
			else if (getNum == 3) {
				
				System.out.println("계좌 조회");
				
				if (userManager.user[identifier].accCount == 0) {
					System.out.println("계좌가 존재하지 않습니다.");
					return;
				}
				for (int i = 0; i < userManager.user[identifier].accCount; i++) {
					
					System.out.println(userManager.user[identifier].acc[i].number + " : " + 
					userManager.user[identifier].acc[i].money);
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
