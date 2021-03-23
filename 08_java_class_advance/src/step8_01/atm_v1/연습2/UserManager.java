package step8_01.atm_v1.연습2;

import java.util.Scanner;

public class UserManager {
	
	Scanner scan = new Scanner(System.in);
	User[] user = null;
	int userCount = 0;
	
	void printAllUser() {
		
		for (int i = 0; i < userCount; i++) {
			
			System.out.println(user[i]);
		}
		
	}
	
	
	
	void addUser() {
		
		if ( userCount == 0) {
			
			user = new User[1];
			user[0] = new User();
		}
		else {
			
			User[] temp = user;
			user = new User[userCount + 1];
			
			for (int i = 0; i < temp.length; i++) {
				
				user[i] = temp[i];
			}
			user[userCount] = new User();
		}
		
		while(true) {
			
			System.out.println("회원가입");
			System.out.print("[ID] : ");
			String getId = scan.next();
			
			boolean isExisted = false;
			
			for (int i = 0; i < userCount; i++) {
				
				if (user[i].id.equals(getId)) {
					
					System.out.println("이미 존재하는 ID입니다.");
					isExisted = true;
					break;
				}
			}
			
			if (isExisted) continue;
			else {
				user[userCount].id = getId;
				
				userCount ++;
				break;
			}
		}
		
	}
	
	
	
	User getUser(int idx) {
		
		return user[idx];
		
	}
	

	
	int logUser() {
	
		int indexofUser = -1;
		
		System.out.println("로그인 실행");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		for (int i = 0; i < userCount; i++) {
			
			if ( user[i].id.equals(getId)) {
				indexofUser = i;
				break;
			}
		}
		
		if (indexofUser == -1) return -1;
		else return indexofUser;
	
	}
	
	
	
	void leave() {
	
		int indexofUser = -1;
		
		System.out.println("탈퇴진행");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		for (int i = 0; i < userCount; i++) {
			if (user[i].id.equals(getId)) {
				
				indexofUser = i;
				break;
			}
		}
		
		if (indexofUser == -1) {
			System.out.println("존재하지 않는 ID입니다.");
			return;
		}
		else {
			
			int indexofArrayUser = 0;
			User[] temp = user;
			user = new User[userCount - 1];
			for (int i = 0; i < indexofUser; i++) {
				
				user[indexofArrayUser ++] = temp[i];
			}
			for (int i = indexofUser + 1; i < temp.length; i++) {
				
				user[indexofArrayUser ++] = temp[i];
			}
			
			userCount --;
			System.out.println("탈퇴성공");
		}
	}
	
}
