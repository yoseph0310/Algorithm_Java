package Z_OS_TestCode.프로세스동기화.Producer_Consumer;

import java.util.concurrent.Semaphore;

public class Test {
    public static void main(String[] args) {
        Buffer b = new Buffer(100);

        Producer p = new Producer(b, 10000);
        Consumer c = new Consumer(b, 10000);

        p.start();
        c.start();

        try {
            p.join();
            c.join();
        } catch (InterruptedException e) {}

        System.out.println("Number of items in the buf is : " + b.count);
    }
}

class Buffer {
    int[] buf;
    int size, count, in, out;
    Semaphore mutex, full, empty;

    Buffer(int size) {
        buf = new int[size];
        this.size = size;
        count = in = out = 0;
        mutex = new Semaphore(1);
        full = new Semaphore(0);
        empty = new Semaphore(size);
    }

    void insert(int item) {
        try {
            empty.acquire();        // 버퍼의 비어있는 공간을 1 감소 시킨다. (비어있는 공간이 없다면 block)
            mutex.acquire();        // 임계구역 진입 요청
            buf[in] = item;
            in = (in + 1) % size;
            count++;
            mutex.release();        // 임계구역 나가기
            full.release();         // 버퍼의 찬 공간을 1 증가 시킨다.
        } catch (InterruptedException e) {}
    }

    int remove() {
        try {
            full.acquire();         // 버퍼의 찬 공간을 1 감소 시킨다. (버퍼가 모두 비어있으면 block)
            mutex.acquire();
            int item = buf[out];
            out = (out + 1) % size;
            count--;
            mutex.release();
            empty.release();        // 버퍼의 비어있는 공간을 1 증가시킨다.
            return item;
        } catch (InterruptedException e) {}
        return -1;
    }
}

// 생산자
class Producer extends Thread {
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
// 소비자
class Consumer extends Thread {
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
