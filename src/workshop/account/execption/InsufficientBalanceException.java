package workshop.account.execption;
//Account의 잔액이 부족할때 사용되는 사용자정의 Exception 클래스
public class InsufficientBalanceException extends Exception {
	//현재 잔액
	private int currentBalance;

	public InsufficientBalanceException(String message, int currentBalance) {
		// 부모 클래스의 생성자를 호출
		super(message);
		this.currentBalance = currentBalance;
		
	}
	public int getCurrentBalance() {
		return currentBalance;
	}
	
	
}
