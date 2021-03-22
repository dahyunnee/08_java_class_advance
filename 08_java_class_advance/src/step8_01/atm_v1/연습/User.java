package step8_01.atm_v1.연습;

public class User {
	
	String id;
	int accCount;
	Account[] acc;
	
	void printAccount() {
		
		for (int i = 0; i < acc.length; i++) {
			acc[i].printOwnAccount();
		}
		
	}
	
}
