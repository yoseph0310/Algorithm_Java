package Programmers.LV2;

public class 유사_칸토어_비트 {
    public int solution(int n, long l, long r) {
        return countOne(n, l, r, 1);
    }

    int countOne(int n, long s, long e, long idx) {
        if (n == 0) return 1;

        int num = 0;
        long part = (long)Math.pow(5, n-1);

        for (int i = 0; i < 5; i++) {
            if (i == 2 || s > idx + part * (i+1) - 1 || e < idx + part * i) continue;
            num += countOne(n - 1, s, e, idx + part * i);
        }

        return num;
    }
}
