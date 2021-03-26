package step8_02.atm_v2.연습;

import java.util.Random;
import java.util.Scanner;

public class UserManager {
	
	private UserManager() {}
	static private UserManager instance = new UserManager();
	static public UserManager getInstance() {
		return instance;
	}
	
	Scanner scan = new Scanner(System.in);
	Random ran = new Random();
	
	final int ACC_MAX_CNT = 3;			// 최대 개설 가능한 계좌 수
	User[] userList = null;				// 전체 회원정보
	int userCnt = 0;					// 전체 회원 수
	
	void printAllUser() {
		
		for (int i=0; i<userCnt; i++) {
			System.out.print((i+1) + ".ID(" + userList[i].id + ")\tPW(" + userList[i].pw + ")\t");
			if (userList[i].accCnt != 0) {
				for (int j=0; j<userList[i].accCnt; j++) {
					System.out.print("(" + userList[i].acc[j].accNumber + ":" + userList[i].acc[j].money + ")");
				}
			}
			System.out.println();
		}
	}
	
	
	boolean getCheckAcc(String account) {
		
		boolean isExisted = false;
		
		for (int i = 0; i < userCnt; i++) {
			
			for (int j = 0; j < userList[i].accCnt; j++) {
				
				if ( userList[i].acc[j].accNumber.equals(account)) {
					
					isExisted = true;
					break;
				}
			}
		}
		
		return isExisted;
		
	}
	
	
	int logUser() {
		
		int identifier = -1;
		
		System.out.println("로그인절차");
		System.out.print("[ID] : ");
		String getId = scan.next();
		
		if (this.checkId(getId) == true ) {
			
			for (int i = 0; i < userCnt; i++) {
				
				if (userList[i].id.equals(getId)) identifier = i;
			}
		}
		else {
			
			System.out.println("아이디나 비밀번호를 확인해주세요.");
		}
		
	
		
		return identifier;
	}
	
	
	boolean checkId(String getid) {
		
		boolean isTrue = false;
		
		if (userCnt == 0) return false;
	
		
		for (int i = 0; i < userCnt; i++) {
			
			if (userList[i].id.equals(getid)) {
				
				isTrue = true;
				break;
			}
		}
		
		return isTrue;
	
	}
	
	
	void joinMember() {
		
		
		if (userCnt == 0) {
			
			userList = new User[1];
			
		}
		else {
			
			User[] temp = userList;
			userList = new User[userCnt + 1];
		}
		
		userList[userCnt] = new User();
		
		System.out.println("회원가입");
		System.out.print("[ID] : ");
		String getId = scan.next();
		boolean isExist = checkId(getId);
		
		if (!isExist) userList[userCnt].id = getId;
		else {
			System.out.println("이미 존재하는 아이디입니다.");
			return;
		}
		System.out.print("[PW] : ");
		userList[userCnt].pw = scan.next();
		
		System.out.println(userList[userCnt].id + "님 회원가입 완료");
		userCnt ++;
		
		FileManager.getInstance().save();

	}

	
	int deleteMember(int identifier) {
		
		User[] temp = userList;
		
		userList = new User[userCnt - 1];
		int indexofUserList = 0;
		
		for (int i = 0; i < userCnt; i++) {
			
			if ( i != identifier) userList[indexofUserList ++] = temp[i];
		}
		
		userCnt --;
		FileManager.getInstance().save();
		return -1;
		
	}
	
	// (테스트생성용 메서드)  : 테스트 데이타 > 더미
	void setDummy() {
		
		String[] ids  = {"user1"  ,  "user2",     "user3",    "user4",    "user5"};
		String[] pws  = {"1111"   ,   "2222",      "3333",     "4444",    "5555"};
		String[] accs = {"1234567",  "2345692",  "1078912",   "2489123",  "7391234"};
		int[] moneys  = { 87000   ,     12000,    49000,        34000,     128000};
		
		userCnt = 5;
		userList = new User[userCnt];
		
		for (int i=0; i<userCnt; i++) {
			userList[i] = new User();
			userList[i].id = ids[i];
			userList[i].pw = pws[i];
			userList[i].acc[0] = new Account();
			userList[i].acc[0].accNumber = accs[i];
			userList[i].acc[0].money = moneys[i];
			userList[i].accCnt++;
		}
		
	}
	
}
