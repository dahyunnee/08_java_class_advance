package step8_02.atm_v2;

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
		
		int accCntByUser = um.userList[identifier].accCnt;
		
		if (accCntByUser == 3) {
			System.out.println("[메세지]계좌개설은 3개까지만 가능합니다.");
			return;
		}
		
		um.userList[identifier].acc[accCntByUser] = new Account();
		
		String makeAccount = "";
		while (true) {
			makeAccount = ran.nextInt(9000000) + 1000001 + "";		
			if (!um.getCheckAcc(makeAccount)){
				break;
			}
		}
		um.userList[identifier].acc[accCntByUser].accNumber = makeAccount;
		um.userList[identifier].accCnt++;
		System.out.println("[메세지]'" + makeAccount + "'계좌가 생성되었습니다.\n");
	}
	
	void deleteAcc(int identifier) {
		
		System.out.print("삭제하실 계좌를 입력하세요 : ");
		int getAccount = scan.nextInt();
		
		int accountIndex = -1;
		
		for (int i = 0; i < um.userList[identifier].accCnt; i++) {
			
			if (um.userList[identifier].acc[i].accNumber.equals(Integer.toString(getAccount))) {
				accountIndex = i;
				break;
			}
			
		}
		
		int countAcc = um.userList[identifier].accCnt;
		
		if (countAcc == 0 || accountIndex == -1) {
			System.out.println("삭제할 계좌가 존재하지 않습니다.");
			return;
		}
		
		User temp = um.userList[identifier];
		Account[] tempAccount = new Account[temp.accCnt - 1];
		
		
		String deleteAccountName = temp.acc[accountIndex].accNumber;
		int tempAccountIndex = 0;
		
		for (int i = 0; i < temp.accCnt; i++) {
			
			if ( i != accountIndex ) {
				
				tempAccount[tempAccountIndex ++] = temp.acc[i];
			}
		}
		
		temp.acc = new Account[temp.accCnt - 1];
		temp.acc = tempAccount;
		temp.accCnt --;
		
		System.out.println(deleteAccountName + "계좌가 삭제되었습니다. ");
		
		
	}
	
	
	void printAcc(int identifier) {
		
		User temp = um.userList[identifier];
		System.out.println("====================");
		System.out.println("ID : " + temp.id);
		System.out.println("====================");
		for (int i=0; i<temp.accCnt; i++) {
			System.out.println("accNumber:" +temp.acc[i].accNumber + " / money: " + temp.acc[i].money);
		}
		System.out.println("=============================\n");
		
	}
	
}
