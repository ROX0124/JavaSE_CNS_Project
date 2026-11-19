
package mylab.bank.entity;

import java.util.ArrayList;

import mylab.bank.exception.AccountNotFoundException;
import mylab.bank.exception.InsufficientBalanceException;

public class Bank {

    // 은행에서 관리하는 계좌 목록
    private ArrayList<Account> accounts;

    // 다음에 생성할 계좌번호
    private int nextAccountNumber;

    // 생성자
    public Bank() {
        accounts = new ArrayList<Account>();
        nextAccountNumber = 1000;
    }

    // 1. 저축 계좌 생성
    public SavingsAccount createSavingsAccount(
            String ownerName,
            double initialBalance,
            double interestRate) {

        if (initialBalance < 0 || interestRate < 0) {
            throw new IllegalArgumentException(
                "초기 잔액과 이자율은 음수일 수 없습니다."
            );
        }

        String accountNumber = "AC" + nextAccountNumber++;

        SavingsAccount account = new SavingsAccount(
                accountNumber,
                ownerName,
                initialBalance,
                interestRate
        );

        accounts.add(account);
        return account;
    }

    // 2. 체킹 계좌 생성
    public CheckingAccount createCheckingAccount(
            String ownerName,
            double initialBalance,
            double withdrawalLimit) {

        if (initialBalance < 0 || withdrawalLimit < 0) {
            throw new IllegalArgumentException(
                "초기 잔액과 출금 한도는 음수일 수 없습니다."
            );
        }

        String accountNumber = "AC" + nextAccountNumber++;

        CheckingAccount account = new CheckingAccount(
                accountNumber,
                ownerName,
                initialBalance,
                withdrawalLimit
        );

        accounts.add(account);
        return account;
    }

    // 3. 계좌번호로 계좌 검색
    public Account findAccount(String accountNumber)
            throws AccountNotFoundException {

        for (Account account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        throw new AccountNotFoundException(
            "계좌번호 " + accountNumber
            + "에 해당하는 계좌를 찾을 수 없습니다."
        );
    }

    // 4. 입금
    public void deposit(String accountNumber, double amount)
            throws AccountNotFoundException {

        Account account = findAccount(accountNumber);
        account.deposit(amount);
    }

    // 5. 출금
    public void withdraw(String accountNumber, double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        Account account = findAccount(accountNumber);
        account.withdraw(amount);
    }

    // 6. 계좌 간 송금
    public void transfer(String fromAccountNumber,
                         String toAccountNumber,
                         double amount)
            throws AccountNotFoundException,
                   InsufficientBalanceException {

        if (amount <= 0) {
            throw new IllegalArgumentException(
                "송금액은 0보다 커야 합니다."
            );
        }

        Account fromAccount = findAccount(fromAccountNumber);
        Account toAccount = findAccount(toAccountNumber);

        if (fromAccount == toAccount) {
            throw new IllegalArgumentException(
                "같은 계좌로 송금할 수 없습니다."
            );
        }

        fromAccount.withdraw(amount);
        toAccount.deposit(amount);

        System.out.println(
            amount + "원이 "
            + fromAccountNumber + "에서 "
            + toAccountNumber + "로 송금되었습니다."
        );
    }

    // 7. 모든 계좌 목록 출력
    public void displayAllAccounts() {

        System.out.println("=== 모든 계좌 목록 ===");

        for (Account account : accounts) {
            System.out.println(account);
        }

        System.out.println("===================");
    }
}
