package Z_OS_TestCode.프로세스동기화_Monitor;

/**
 * 모니터를 활용한 상호배제 (Mutual Exclusion) 문제 해결
 *
 * - 상호배제 (Mutual Exclusion) : 공유 자원에 대해 동시에 한 프로세스(스레드) 만 접근하도록 하는 것
 */
public class BankAccount_use_Monitor {
    static class Test {
        public static void main(String[] args) throws InterruptedException {
            BankAccount b = new BankAccount();

            Parent p = new Parent(b);
            Child c = new Child(b);

            p.start();      // start() : 쓰레드 실행 메소드
            c.start();

            p.join();       // join() : 쓰레드가 끝나기를 기다리는 메소드
            c.join();

            System.out.println("\n balance = " + b.getBalance());
        }
    }

    static class BankAccount {
        int balance;

        synchronized void deposit(int amt) {
            int tmp = balance + amt;
            System.out.print("+");
            balance = tmp;
        }

        synchronized void withdraw(int amt) {
            int tmp = balance - amt;
            System.out.print("-");
            balance = tmp;
        }

        int getBalance() {
            return balance;
        }
    }

    static class Parent extends Thread {
        BankAccount b;

        Parent(BankAccount b) {
            this.b = b;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                b.deposit(1000);
            }
        }
    }

    static class Child extends Thread {
        BankAccount b;

        Child(BankAccount b) {
            this.b = b;
        }

        public void run() {
            for (int i = 0; i < 100; i++) {
                b.withdraw(1000);
            }
        }
    }
}
