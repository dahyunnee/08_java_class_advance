package step8_01.atm_v1.연습;

public class Account {
	
	String number = "";
	int money = 0;
	
	void printOwnAccount() {
		
		System.out.println("계좌번호 : " + this.number + ", 금액 : " + money);
	}
	
}
