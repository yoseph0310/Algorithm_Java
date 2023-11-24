package Z_OS_TestCode.BankAccount;

import java.util.concurrent.Semaphore;

public class Test_use_Semaphore_Ordering {
    public static void main(String[] args) throws InterruptedException {
        BankAccount2 b = new BankAccount2();

        Parent2 p = new Parent2(b);
        Child2 c = new Child2(b);

        p.start();      // start() : 쓰레드 실행 메소드
        c.start();

        p.join();       // join() : 쓰레드가 끝나기를 기다리는 메소드
        c.join();

        System.out.println();
        System.out.println("balance = " + b.getBalance());
    }
}

// 계좌
class BankAccount2 {
    int balance;

    Semaphore sem, semOrder;

    BankAccount2() {
        sem = new Semaphore(1);
        semOrder = new Semaphore(0);        // Ordering 을 위한 세마포
    }

    void deposit(int amount) {
        try {
            sem.acquire();      // 임계구역 진입 요청
        } catch (InterruptedException e) {}

        int temp = balance + amount;
        System.out.print("+");
        balance = temp;
        sem.release();
        semOrder.release();     // block 된 출금 프로세스가 있다면 깨워준다.
    }

    void withdraw(int amount) {
        try {
            semOrder.acquire();     // 출금을 먼저하려고 하면 block 한다.
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
class Parent2 extends Thread {
    BankAccount2 b;

    Parent2(BankAccount2 b) {
        this.b = b;
    }

    public void run() {     // run() : 쓰레드가 실제로 동작하는 부분 (치환)
        for (int i = 0; i < 100; i++) {
            b.deposit(1000);
        }
    }
}

// 출금 프로세스
class Child2 extends Thread {
    BankAccount2 b;

    Child2(BankAccount2 b) {
        this.b = b;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            b.withdraw(1000);
        }
    }
}
