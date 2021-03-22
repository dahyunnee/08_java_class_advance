package step8_01.atm_v1.연습;

import java.util.Scanner;

public class UserManager {
	
	Scanner scan = new Scanner(System.in);
	User[] user = null;
	int userCount = 0;
	
	void printAllUser() {
		
		for (int i = 0; i < user.length; i++) {
			System.out.println((i+1) + " : " + user[i].id);
		}
		
	}
	
	
	
	void addUser() {
		
		if (userCount == 0) {
			
			user = new User[1];
			
			
		}
		else {
			
			User[] temp = user;
			user = new User[userCount + 1];
			
			for (int i = 0; i < temp.length ; i++) {
				user[i] = temp[i];
			}
		
			temp = null;
	
		}
		String getId = "";
		
		while(true) {
			
			boolean isExisted = false;
			
			System.out.println(" [가입절차]");
			System.out.print("[ID] : ");
			getId = scan.next();
			
			System.out.println(userCount);
			for (int i = 0; i < userCount; i++) {
				if (user[i].id.equals(getId)) {
					
				
					isExisted = true;
					break;
				}
				
			}
			
			if (isExisted == true) {
				System.out.println("이미 가입된 ID입니다. ");
			}
			else break;
		}
		
		user[userCount] = new User();
		user[userCount].id = getId;
		userCount ++;
		this.printAllUser();
		
	}
	
	
	
	User getUser(int idx) {
		
		return user[idx];
		
	}
	

	
	int logUser() {
	
		int indexofUser = -1;
		
		System.out.println("로그인");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		for (int i = 0; i < userCount ; i++) {
			
			if ( user[i].id.equals(getId)) {
				
				indexofUser = i;
				break;
			}
		}
		
		return indexofUser;
	}
	
	
	
	void leave() {
	
		int indexofUser = -1;
		
		System.out.println("탈퇴과정");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		for (int i = 0; i < user.length; i++) {
			
			if ( user[i].id.equals(getId)) {
				indexofUser = i;
				break;
			}
		}
		
		if ( indexofUser == -1 ) {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		}
		
		else {
			
			User[] temp = user;
			user = new User[userCount - 1];
			
			for (int i = 0; i < temp.length; i++) {
				user[i] = temp[i];
			}
			
			userCount --;
		}
		
	}
	
}
