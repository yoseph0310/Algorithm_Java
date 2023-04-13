package Programmers.LV2;

public class 타일링_3xn {
    public long solution(int n) {
        long answer = 0;

        long[] DP = new long[n + 1];
        int mod = 1000000007;

        DP[0] = 1;
        DP[2] = 3;

        for (int i = 4; i <= n; i += 2) {
            long num = (DP[i-2] * 4 % mod - DP[i-4] % mod + mod) % mod;
            DP[i] = num;
        }

        answer = DP[n];

        return answer;
    }
}
