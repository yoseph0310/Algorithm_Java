package Programmers.LV2;

public class 숫자_변환하기 {
    public int solution(int x, int y, int n) {
        int[] dp = new int[y+1];

        for (int i = 0; i <= y; i++) {
            dp[i] = Integer.MAX_VALUE;
        }

        dp[x] = 0;
        for (int i = x; i <= y; i++) {
            if (dp[i] == Integer.MAX_VALUE) continue;

            if (i+n <= y) {
                dp[i+n] = Math.min(dp[i+n], dp[i] + 1);
            }
            if (i*2 <= y) {
                dp[i*2] = Math.min(dp[i*2], dp[i] + 1);
            }
            if (i*3 <= y) {
                dp[i*3] = Math.min(dp[i*3], dp[i] + 1);
            }
        }

        if (dp[y] == Integer.MAX_VALUE) return -1;
        return dp[y];
    }
}
