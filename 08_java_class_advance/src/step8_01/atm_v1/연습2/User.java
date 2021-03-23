package step8_01.atm_v1.연습2;

public class User {
	
	String id;
	int accCount;
	Account[] acc;
	
	void printAccount() {
		
		for (int i = 0; i < accCount; i++) {
			
			System.out.println(acc[i].number + " : " + acc[i].money);
		}
		
	}
	
	public String toString() {
		
		return id;
	}
	
}
