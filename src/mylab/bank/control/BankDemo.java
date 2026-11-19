package mylab.bank.control;

import mylab.bank.entity.Account;
import mylab.bank.entity.Bank;
import mylab.bank.entity.SavingsAccount;
import mylab.bank.entity.CheckingAccount;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;
import mylab.bank.exception.WithdrawalLimitExceededException;

public class BankDemo {
	
	public static void main(String[] args) {
		
		Bank bank = new Bank();
		
		try {
			System.out.println("===계좌 생성===");
			
			SavingsAccount savings1 = bank.createSavingsAccount("홍길동", 10000, 0.03);
			CheckingAccount checking = bank.createCheckingAccount("김철수", 20000, 5000);
			SavingsAccount savings2 = bank.createSavingsAccount("이영희", 30000, 0.02);
					
			System.out.println("저축 계좌 생성: "+savings1);
			System.out.println("체킹 계좌 생성: "+checking);
			System.out.println("저축 계좌 생성: "+savings2);
			
			System.out.println();
			bank.displayAllAccounts();
			
			System.out.println("\n=== 계좌 검색 테스트 ===");

            Account found = bank.findAccount("AC1000");
            System.out.println("검색 결과: " + found);
            
            System.out.println("\n=== 입금/출금 테스트 ===");

            bank.deposit("AC1000", 5000);
            bank.withdraw("AC1001", 3000);
            
            System.out.println("\n=== 이자 적용 테스트 ===");

            savings1.applyInterest();
            
            System.out.println("\n=== 계좌 이체 테스트 ===");

            bank.transfer("AC1002", "AC1001", 5000);

            System.out.println();
            bank.displayAllAccounts();
            
            System.out.println("\n=== 예외 처리 테스트 ===");

            testWithdraw(bank, "AC1001", 7000);
            testWithdraw(bank, "AC1001", 6000);

            testFindAccount(bank, "AC9999");

            testWithdraw(bank, "AC1002", 30000);
		} catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
	}
	
	private static void testWithdraw(
            Bank bank, String accountNumber, double amount) {

        try {
            bank.withdraw(accountNumber, amount);

        } catch (WithdrawalLimitExceededException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (InsufficientBalanceException e) {
            System.out.println("예외 발생: " + e.getMessage());

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
	
	private static void testFindAccount(
            Bank bank, String accountNumber) {

        try {
            bank.findAccount(accountNumber);

        } catch (AccountNotFoundException e) {
            System.out.println("예외 발생: " + e.getMessage());
        }
    }
}
