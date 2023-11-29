package Z_OS_TestCode.프로세스동기화_Monitor.Philosopher;

public class Philosopher_use_Monitor {
    static class Test {
        static final int num = 5;

        public static void main(String[] args) {
            int i;

            Chopstick[] stick = new Chopstick[num];
            for (i = 0; i < num; i++) {
                stick[i] = new Chopstick();
            }

            Philosopher[] phils = new Philosopher[num];
            for (i = 0; i < num; i++) {
                phils[i] = new Philosopher(i, stick[i], stick[(i + 1) % num]);
            }

            for (i = 0; i < num; i++) {
                phils[i].start();
            }

        }
    }

    static class Philosopher extends Thread {
        int id;     // philosopher id
        Chopstick l_stick, r_stick;

        Philosopher(int id, Chopstick l_stick, Chopstick r_stick) {
            this.id = id;
            this.l_stick = l_stick;
            this.r_stick = r_stick;
        }

        public void run() {
            try {
                while (true) {
                    l_stick.acquire();
                    r_stick.acquire();

                    eating();

                    l_stick.release();
                    r_stick.release();

                    thinking();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        void eating() {
            System.out.println("[" + id + "] : eating");
        }

        void thinking() {
            System.out.println("[" + id + "] : thinking");
        }
    }

    static class Chopstick {
        private boolean inUse = false;

        synchronized void acquire() throws InterruptedException {
            while (inUse) {
                wait();
            }
            inUse = true;
        }

        synchronized void release() {
            inUse = false;
            notify();
        }
    }
}
