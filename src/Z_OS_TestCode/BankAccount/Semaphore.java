package Z_OS_TestCode.BankAccount;

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
