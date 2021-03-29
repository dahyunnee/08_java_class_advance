package step8_03.atm_v3.연습;

import java.util.Random;
import java.util.Scanner;

public class AccountManager {

	private AccountManager() {}
	private static AccountManager instance = new AccountManager();
	public static AccountManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	UserManager userManager = UserManager.getInstance();
	
	void createAccount() {
		
		Random rand = new Random();
		
		User prevUser = userManager.userList[userManager.identifier];
		Account[] temp = prevUser.accList;
		
		if (prevUser.accCount == 0) {
			
			prevUser.accList = new Account[1];
		}
		else {
			prevUser.accList = new Account[prevUser.accCount + 1];
			
			for (int i = 0; i < temp.length; i++) {
				prevUser.accList[i] = temp[i];
			}
			
		}
		
		prevUser.accList[prevUser.accCount] = new Account();
		
		while(true) {
			
			boolean isExist = true;
			String randomAccount = Integer.toString(rand.nextInt(100000) + 1);
			
			if (prevUser.accCount == 0) {
				
				prevUser.accList[0].number = randomAccount;
				
				break;
			}
			for (int i = 0; i < prevUser.accCount; i++) {
				
				if (temp[i].number.equals(randomAccount)) {
					continue;
				}
				else {
					prevUser.accList[prevUser.accCount].number = randomAccount;
					isExist = false;
					break;
				}
			}
			
			if ( isExist == false) break;
		}
		
		System.out.println(prevUser.accList[prevUser.accCount].number + "계좌 생성 완료");
		prevUser.accCount ++;

		
	}
	
	
	int showAccList(String msg) {
		
	
		User tempUser = userManager.userList[userManager.identifier];
		
		for (int i = 0; i < tempUser.accCount; i++) {
			
			System.out.println((i+1) + ": " + tempUser.accList[i].number );
		}
		
		while (true) {
			System.out.print(msg + "계좌선택 : ");
			
			int getChosenNumber = scan.nextInt() - 1;
			
			if (getChosenNumber < 0 || getChosenNumber >= tempUser.accCount) {
				
				System.out.println("다시 선택해주세요");
				continue;
			}
			else return getChosenNumber;
		}
		
	
	}
	
	
	void income() {
		
		int getAccountIndex = this.showAccList("입금");
		System.out.print("입금할 금액 : ");
		int getIncomeMoney = scan.nextInt();
		
		userManager.userList[userManager.identifier].accList[getAccountIndex].money += getIncomeMoney;
		System.out.println(userManager.userList[userManager.identifier].accList[getAccountIndex].number + "계좌, "
				+ getIncomeMoney + "원 입금");
		System.out.println("현재 잔액 : " + userManager.userList[userManager.identifier].accList[getAccountIndex].money + "원");
		
	}
	
	
	void outcome() {
		
		int getAccountIndex = this.showAccList("출금");
		
		System.out.print("출금할 금액 : ");
		int getOutcomeMoney = scan.nextInt();
		
		if (userManager.userList[userManager.identifier].accList[getAccountIndex].money < getOutcomeMoney) {
			System.out.println("잔액부족으로 출금하실 수 없습니다.");
			return;
		}
		
		userManager.userList[userManager.identifier].accList[getAccountIndex].money -= getOutcomeMoney;
		
		System.out.println("출금완료.");
		System.out.println("현재 잔액 : " + userManager.userList[userManager.identifier].accList[getAccountIndex].money + "원");
		
	}
	
	
	int checkAcc(String transAccount) {
		
		
		
		User temp = userManager.userList[userManager.identifier];
		
		for (int i = 0; i < temp.accCount; i++) {
			
			if (temp.accList[i].number.equals(transAccount)) {
				
				return i;
			}
		}
		
		return -1;
	}
	
	
	void getAccIndex(int transUserIndex, String transAccount) {
		
		User temp = userManager.userList[userManager.identifier];
		int transtoIndex = -1;
		
		for (int i = 0; i < temp.accCount; i++) {
			if ( temp.accList[i].number.equals(transAccount )) {
				
				transtoIndex = i;
				break;
			}
		}
		
		System.out.print("이체할 금액 : ");
		int transferMoney = scan.nextInt();
		
		if ( temp.accList[transUserIndex].money < transferMoney) {
			System.out.println("잔액부족");
			return;
			
		}
		else {
			temp.accList[transUserIndex].money -= transferMoney;
			temp.accList[transtoIndex].money += transferMoney;
			
		}
		
	}
	
	
	void transfer() {
		
		
		int transferfromIndex = this.showAccList("이체하실 ");
		
		System.out.print("이체받을 계좌의 번호를 입력해주세요 :  ");
		String transfertoAccount = scan.next();
		int toIndex = checkAcc(transfertoAccount);
		if (toIndex == -1) {
			
			System.out.println("존재하지 않는 계좌입니다.");
			return;
		}
		
		getAccIndex(transferfromIndex,transfertoAccount);
		
		return;
		
	}
	
	
	void lookupAcc() {
		userManager.userList[userManager.identifier].printOneUserAllAccounts();
	}

	
	void deleteAcc() {
		
		User tempUser = userManager.userList[userManager.identifier];
		Account[] tempAccount = tempUser.accList;
		
		
		int getIndex = this.showAccList("삭제");
		if (tempUser.accCount == 0) {
			System.out.println("계좌가 존재하지 않습니다.");
			return;
		}
		
		tempUser.accList = new Account[tempUser.accCount - 1];
		
		int accountIndex = 0;
	
		for (int i = 0; i < tempAccount.length; i++) {
			
			if ( i != getIndex) {
				tempUser.accList[accountIndex ++] = tempAccount[i];
			}
		}
		
		
		tempUser.accCount --;
		
		return;
	
	}
	
	
}
