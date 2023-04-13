package Programmers.LV2;

public class 타일링_2xn {
    public int solution(int n) {
        int answer = 0;

        int[] DP = new int[n + 1];

        DP[0] = 0;
        DP[1] = 1;
        DP[2] = 2;

        for (int i = 3; i <= n; i++) {
            int num = DP[i-1] + DP[i-2];
            DP[i] = num  % 1000000007;
        }

        answer = DP[n];

        return answer;
    }
}
