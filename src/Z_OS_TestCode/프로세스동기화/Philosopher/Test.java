package Z_OS_TestCode.프로세스동기화.Philosopher;

import java.util.concurrent.Semaphore;

public class Test {

    static final int num = 5;

    public static void main(String[] args) {
        Semaphore[] sticks = new Semaphore[num];

        for (int i = 0; i < num; i++) {
            sticks[i] = new Semaphore(1);
        }

        Philosopher[] philosophers = new Philosopher[num];
        for (int i = 0; i < num; i++) {
            philosophers[i] = new Philosopher(i, sticks[i], sticks[(i + 1) % num]);
        }

        for (int i = 0; i < num; i++) {
            philosophers[i].start();
        }
    }
}

class Philosopher extends Thread {
    int id;         // 철학자 ID
    Semaphore l_stick, r_stick;     // 왼쪽, 오른쪽 젓가락

    Philosopher(int id, Semaphore l_stick, Semaphore r_stick) {
        this.id = id;
        this.l_stick = l_stick;
        this.r_stick = r_stick;
    }

    public void run() {
        try {
            while (true) {
                if (id % 2 == 0) {
                    l_stick.acquire();
                    r_stick.acquire();
                }
                else {
                    r_stick.acquire();
                    l_stick.acquire();
                }

                eating();
                l_stick.release();
                r_stick.release();
                thinking();
            }
        } catch (InterruptedException e) {}
    }

    void eating() {
        System.out.println("[" + id + "] : eating");
    }

    void thinking() {
        System.out.println("[" + id + "] : thinking");
    }
}
