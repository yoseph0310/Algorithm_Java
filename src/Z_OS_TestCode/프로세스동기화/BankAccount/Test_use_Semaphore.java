package Z_OS_TestCode.프로세스동기화.BankAccount;

import java.util.concurrent.Semaphore;

public class Test_use_Semaphore {
    public static void main(String[] args) throws InterruptedException {
        BankAccount b = new BankAccount();

        Parent p = new Parent(b);
        Child c = new Child(b);

        p.start();      // start() : 쓰레드 실행 메소드
        c.start();

        p.join();       // join() : 쓰레드가 끝나기를 기다리는 메소드
        c.join();

        System.out.println();
        System.out.println("balance = " + b.getBalance());
    }
}

// 계좌
class BankAccount {
    int balance;

    Semaphore sem;

    BankAccount() {     // BankAccount 클래스가 호출되면 세마포를 만든다.
        sem = new Semaphore(1);     // value 값을 1로 초기화한다.
    }

    void deposit(int amount) {
        try {
            sem.acquire();      // 임계구역 진입 요청
        } catch (InterruptedException e) {}
        /* 임계 구역 */
        int temp = balance + amount;
        System.out.print("+");
        balance = temp;

        sem.release();          // 임계구역 나가기.
    }

    void withdraw(int amount) {
        try {
            sem.acquire();
        } catch (InterruptedException e) {}
        /* 임계 구역 */
        int temp = balance - amount;
        System.out.print("-");
        balance = temp;

        sem.release();
    }

    int getBalance() {
        return balance;
    }

}

// 입금 프로세스
class Parent extends Thread {
    BankAccount b;

    Parent(BankAccount b) {
        this.b = b;
    }

    public void run() {     // run() : 쓰레드가 실제로 동작하는 부분 (치환)
        for (int i = 0; i < 1000; i++) {
            b.deposit(1000);
        }
    }
}

// 출금 프로세스
class Child extends Thread {
    BankAccount b;

    Child(BankAccount b) {
        this.b = b;
    }

    public void run() {
        for (int i = 0; i < 1000; i++) {
            b.withdraw(1000);
        }
    }
}
