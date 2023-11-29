package Z_OS_TestCode.프로세스동기화_Monitor;

public class Test {
    class C {
        private int value;              // 공유 변수
        synchronized void Foo() {       // 배제 동기
            // ...
        }
        synchronized void Goo() {
            // ...
        }
        void H() {
            // ...
        }
    }
}
