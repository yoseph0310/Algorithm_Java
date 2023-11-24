package Z_OS_TestCode.BankAccount;

import java.util.concurrent.Semaphore;

public class Test_use_Semaphore_Ordering_2 {
    public static void main(String[] args) throws InterruptedException {
        BankAccount3 b = new BankAccount3();

        Parent3 p = new Parent3(b);
        Child3 c = new Child3(b);

        p.start();      // start() : 쓰레드 실행 메소드
        c.start();

        p.join();       // join() : 쓰레드가 끝나기를 기다리는 메소드
        c.join();

        System.out.println();
        System.out.println("balance = " + b.getBalance());
    }
}

// 계좌
class BankAccount3 {
    int balance;

    Semaphore sem, semDeposit, semWithdraw;

    BankAccount3() {
        sem = new Semaphore(1);
        semDeposit = new Semaphore(0);
        semWithdraw = new Semaphore(0);
    }

    void deposit(int amount) {
        try {
            sem.acquire();      // 임계구역 진입 요청
            int temp = balance + amount;
            System.out.print("+");;
            balance = temp;
            sem.release();
            semWithdraw.release();
            semDeposit.acquire();      // 입금 후에는 반드시 출금해야 하므로 자신을 block.
        } catch (InterruptedException e) {}
    }

    void withdraw(int amount) {
        try {
            semWithdraw.acquire();     // 입금보다 먼저 수행하는 것을 막는다.
            sem.acquire();
        } catch (InterruptedException e) {}

        int temp = balance - amount;
        System.out.print("-");
        balance = temp;
        sem.release();
        semDeposit.release();       // 출금 수행이 완료되면 block 된 입금 프로세스를 깨운다.
    }

    int getBalance() {
        return balance;
    }

}

// 입금 프로세스
class Parent3 extends Thread {
    BankAccount3 b;

    Parent3(BankAccount3 b) {
        this.b = b;
    }

    public void run() {     // run() : 쓰레드가 실제로 동작하는 부분 (치환)
        for (int i = 0; i < 100; i++) {
            b.deposit(1000);
        }
    }
}

// 출금 프로세스
class Child3 extends Thread {
    BankAccount3 b;

    Child3(BankAccount3 b) {
        this.b = b;
    }

    public void run() {
        for (int i = 0; i < 100; i++) {
            b.withdraw(1000);
        }
    }
}
