package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
    LIS (최장 증가 부분 수열: Longest Increasing SubSequence)알고리즘과 마찬가지로
    DP 바탕으로 한 LCS(최장 공통 부분 수열: Longest Common SubSequence) 로 풀이한다.
 */
public class No_20_최장_공통_부분_수열 {

    static char[] A, B;
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            String strA = st.nextToken();
            String strB = st.nextToken();

            int aLen = strA.length();
            int bLen = strB.length();

            A = new char[aLen + 1];
            B = new char[bLen + 1];
            dp = new int[aLen + 1][bLen + 1];

            for (int i = 1; i <= aLen; i++) {
                A[i] = strA.charAt(i - 1);
            }

            for (int i = 1; i <= bLen; i++) {
                B[i] = strB.charAt(i - 1);
            }

            for (int i = 1; i <= aLen; i++) {
                for (int j = 1; j <= bLen; j++) {
                    // 두 문자가 같다면
                    if (A[i] == B[j]) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    }
                    // 두 문자가 다르다면
                    else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }

            System.out.println("#" + t + " " + dp[aLen][bLen]);
        }
    }
}
