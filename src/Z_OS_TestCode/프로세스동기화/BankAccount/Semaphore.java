package Z_OS_TestCode.프로세스동기화.BankAccount;

public class Semaphore {
    int value;

    Semaphore(int value) {
        // ...
    }

    void acquire() {
        value--;

        if (value < 0) {
            // add this process/thread to list
            // block
        }
    }

    void release() {
        value++;
        if (value <= 0) {
            // remove a process P from list
            // wakeup P
        }
    }
}
