package Z_OS_TestCode.프로세스동기화_Monitor.Producer_Consumer;

public class Producer_Consumer_use_Monitor {
    static class Test {
        public static void main(String[] args) {
            Buffer b = new Buffer(100);
            Producer p = new Producer(b, 1000);
            Consumer c = new Consumer(b, 1000);

            p.start();
            c.start();

            try {
                p.join();
                c.join();
            } catch (InterruptedException e) {}

            System.out.println("Number of items in the buf is : " + b.count);
        }
    }

    // 버퍼 : 공유자원
    static class Buffer {
        int[] buf;
        int size, count, in, out;

        Buffer(int size) {
            buf = new int[size];
            this.size = size;
            count = in = out = 0;
        }

        synchronized void insert(int item) {
            while (count == size) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

            buf[in] = item;
            in = (in + 1) % size;
            notify();
            count++;
        }

        synchronized int remove() {
            while (count == 0) {
                try {
                    wait();
                } catch (InterruptedException e) {}
            }

            int item = buf[out];
            out = (out + 1) % size;
            count--;
            notify();
            return item;
        }
    }

    static class Producer extends Thread {
        Buffer b;
        int N;

        Producer(Buffer b, int N) {
            this.b = b;
            this.N = N;
        }

        public void run() {
            for (int i = 0; i < N; i++) {
                b.insert(i);
            }
        }
    }

    static class Consumer extends Thread {
        Buffer b;
        int N;

        Consumer(Buffer b, int N) {
            this.b = b;
            this.N = N;
        }

        public void run() {
            int item;
            for (int i = 0; i < N; i++) {
                item = b.remove();
            }
        }
    }
}
