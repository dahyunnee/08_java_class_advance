package step8_02.atm_v2.연습;

import java.util.Random;
import java.util.Scanner;

public class AccountManager {

	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	UserManager um = UserManager.getInstance();

	void createAcc(int identifier) {
		
		if (um.userList[identifier].accCnt == 3) {
			
			System.out.println("더 이상 계좌를 생성할 수 없습니다.");
			return;
		}
		
		Account[] temp = um.userList[identifier].acc;
		um.userList[identifier].accCnt ++;
		
		um.userList[identifier].acc = new Account[um.userList[identifier].accCnt];
		
		for (int i = 0; i < um.userList[identifier].accCnt; i++) {
			
			um.userList[identifier].acc[i] = temp[i];
		}
		
		um.userList[identifier].acc[um.userList[identifier].accCnt] = new Account();
		
		while(true) {
			
			int randomAcc = ran.nextInt(10000) + 1;
			if (!um.getCheckAcc(Integer.toString(randomAcc))) {
				
				um.userList[identifier].acc[um.userList[identifier].accCnt].accNumber = 
						Integer.toString(randomAcc);
				
				break;
			}
			
		}
		
		
	}
	
	void deleteAcc(int identifier) {
		
		User temp = um.userList[identifier];
		System.out.print("삭제할 계좌 : ");
		String getAccount = scan.next();
		
		int accountIndex = -1;
		
		for (int i = 0; i < temp.accCnt; i++) {
			if ( temp.acc[i].accNumber.equals(getAccount)) {
				accountIndex = i;
				break;
			}
		}

		if (temp.accCnt == 0 || accountIndex == -1) {
			System.out.println("삭제할 계좌가 존재하지 않습니다.");
			return;
		}
		
		um.userList[identifier].acc = new Account[um.userList[identifier].accCnt - 1];
		int j = 0;
		
		for (int i = 0; i < temp.accCnt; i++) {
			
			if ( accountIndex != i) {
				
				um.userList[identifier].acc[j++] = temp.acc[i];
			}
		}
		
		um.userList[identifier].accCnt --;
		
	}
	
	
	void printAcc(int identifier) {
		
		User temp = um.userList[identifier];
	
		for (int i = 0; i < temp.accCnt; i++) {
			
			System.out.println(i + " : " + temp.acc[i] + " / " + temp.acc[i].money );
		}
		
	}
	
}
