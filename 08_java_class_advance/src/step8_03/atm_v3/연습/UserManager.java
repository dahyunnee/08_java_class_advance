package step8_03.atm_v3.연습;

import java.util.Scanner;

public class UserManager {
	
	private UserManager () {}
	private static UserManager instance = new UserManager();
	public static UserManager getInstance() {
		return instance;
	}
	
	User[] userList;
	int userCount;
	int identifier = -1;
	
	
	void printAllUserInfo() {
		
		System.out.println("아이디\t패스워드\t계좌정보");
		for (int i=0; i<userCount; i++) {
			userList[i].printOneUserAllAccounts();
		}
		System.out.println("--------------------------");
		
	}
	
	
	void setDummy() {
		
		userCount = 5;
		userList = new User[userCount];
		for (int i=0; i<userCount; i++) {
			userList[i] = new User();
		}
				
		String[] a = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k"};
		String[] b = {"l", "b", "c", "m", "e", "f", "g", "n", "i", "p", "k"};
		String[] c = {"s", "t", "u", "w", "v", "o", "x", "n", "q", "p", "r"};
		
		for (int i=0; i<userCount; i++) {
			String id = "";
			int rNum = ATM.ran.nextInt(a.length);
			id += a[rNum];
			rNum = ATM.ran.nextInt(b.length);
			id += b[rNum];
			rNum = ATM.ran.nextInt(c.length);
			id += c[rNum];
			
			userList[i].id = id;
		}
		
		String[] d = {"1", "8", "9", "4"};
		String[] e = {"2", "7", "0", "6"};
		String[] f = {"5", "3", "2", "7"};
		
		for (int i=0; i<userCount; i++) {
			String pw = "";
			int rNum = ATM.ran.nextInt(d.length);
			pw += d[rNum];
			rNum = ATM.ran.nextInt(e.length);
			pw += d[rNum];
			rNum = ATM.ran.nextInt(f.length);
			pw += d[rNum];
			
			userList[i].password = pw;
		}
		
		System.out.println("[메세지]더미 파일이 추가되었습니다.\n");
		
	}

	
	int checkId(String id) {
		
		int identifier = -1;
		
		for (int i = 0; i < userList.length; i++) {
		
			if (userList[i].id.equals(id)) {
				
				identifier = i;
				break;
			}
		}
	
		return identifier;
		
	}
	
	
	void joinUser() {
		
		
		Scanner scan = new Scanner(System.in);
		System.out.println("가입절차");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		for (int i = 0; i < userList.length; i++) {
			if (userList[i].id.equals(getId)) {
				System.out.println("이미 존재하는 ID입니다.");
				return;
			}
		}
		
		System.out.print("[PW] : ");
		String getPw = scan.next();
		
		if(userCount == 0) {
			
			userList = new User[1];
		}
		else {
			
			User[] temp = userList;
			userList = new User[userCount + 1];
			
			for (int i = 0; i < temp.length; i++) {
				
				userList[i] = temp[i];
			}
			
		}
		
		userList[userCount] = new User(getId, getPw);
		System.out.println(getId + "님, 가입을 축하드립니다.");
		userCount ++;
		
	}
	
	
	void leaveUser() {
	
		User[] temp = userList;
		int userListIndex = 0;
		
		if (userCount == 1) userList = null;
		else {
		
			userList = new User[userCount - 1];
			for (int i = 0; i < temp.length; i++) {
				
				if (i != identifier) {
					userList[userListIndex ++] = temp[i];
				}
			}
			
		}
		
		userCount --;

		
	}
	
	
	void loginUser() {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("로그인절차");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		for (int i = 0; i < userList.length; i++) {
			if (userList[i].id.equals(getId)) {
				
				identifier = i;
				break;
			}
		}
		if ( identifier == -1 ) {
			
			System.out.println("존재하지 않는 ID입니다.");
			return;	
		}
		
		System.out.print("[PW] : ");
		String getPw = scan.next();
		
		if ( userList[identifier].password.equals(getPw)) {
			
			System.out.println(userList[identifier].id + "님, 로그인 완료되었습니다.");
			this.afterloginMenu();
		}
		
		else {
			System.out.println("비밀번호가 틀렸습니다.");
			identifier = -1;
			return;
		}
		
		
	}
	
	
	void logoutUser() {
		identifier = -1;
		System.out.println("[메세지]로그아웃되었습니다.\n");
	}
	
	
	void afterloginMenu() {
		
		while (true) {
			
			System.out.println("[" + userList[identifier].id + "님, 로그인]");
			System.out.println("[1]계좌생성 [2]입금하기 [3]출금하기 [4]이체하기 [5]계좌조회 "
					+ "[6]계좌삭제 [7]회원탈퇴 [0]뒤로가기");
			System.out.print("메뉴를 선택하세요 : ");
			int choice = ATM.scan.nextInt();
			
			if (choice == 1)  {
				AccountManager.getInstance().createAccount(); 
			}
			else if (choice == 2) {
				AccountManager.getInstance().income(); 
			}
			else if (choice == 3) {
				AccountManager.getInstance().outcome();
			}
			else if (choice == 4) {
				AccountManager.getInstance().transfer(); 
			}
			else if (choice == 5) {
				AccountManager.getInstance().lookupAcc(); 
			}
			else if (choice == 6) {
				AccountManager.getInstance().deleteAcc(); 
			}
			else if (choice == 7) {
				leaveUser();
				break;
			}
			else if (choice == 0) {
				logoutUser();
				break; 
			}
			
		}
		
	}
}









