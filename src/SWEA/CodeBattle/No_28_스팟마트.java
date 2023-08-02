package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

/**
 *  N 개의 과자 봉지 - A1 ~ AN
 *  M 개의 과자 봉지 - B1 ~ BN
 *
 *  1 <= N <= 3,000
 *  1 <= Ai <= 100,000  (들어가는 조각 수)
 *  1 <= M <= 100
 *  1 <= Bi <= 100,000  (들어가는 조각 수)
 */
public class No_28_스팟마트 {

    static int N, M;
    static ArrayList<Integer> A, B;
    static int[][][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            N = Integer.parseInt(br.readLine());
            A = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                A.add(Integer.parseInt(br.readLine()));
            }

            M = Integer.parseInt(br.readLine());
            B = new ArrayList<>();
            for (int i = 0; i < M; i++) {
                B.add(Integer.parseInt(br.readLine()));
            }

            /**
             *  dp[n][m][skip][get]
             *
             *  n : N 봉지 중 A[i] 를 보고 있는 상태 (0, n 은 왼쪽끝, 오른쪽끝을 의미)
             *  m : M 봉지 중 B[i] 를 고른 상태
             *  skip : M 봉지 중 스킵한 개수
             *  get : 고른 상태 (0 안고름, 1 고름)
             */
            dp = new int[3001][101][101][2];

            B.sort(Collections.reverseOrder());

            int ans = solve(0,0,0,0);

            System.out.println("#" + t + " " + ans);
        }
    }

    static int solve(int nIdx, int mIdx, int skip, int get) {
        // 만약 N 봉지 끝까지 고르고 M 봉지도 끝가지 간경우 (고른 과자 + 스킵 과자)
        if (nIdx == N && mIdx + skip == M) return 0;

        int ret = dp[nIdx][mIdx][skip][get];
        if (ret != 0) return ret;

        // 고르지 않은 경우
        if (get == 0) {
            // 만약 N 봉지를 다 고르지 않았을 때
            if (nIdx < N) {
                ret = Math.max(ret, solve(nIdx + 1, mIdx, skip, 1) + A.get(nIdx));  // 고르고
                ret = Math.max(ret, solve(nIdx + 1, mIdx, skip, 0));                // 안고르고
            }
            // 만약 M 봉지를 다 고르지 않았을 때
            if (mIdx + skip < M) {
                ret = Math.max(ret, solve(nIdx, mIdx + 1, skip, 1) + B.get(mIdx));  // 고르고
                ret = Math.max(ret, solve(nIdx, mIdx, skip + 1, 0));                // 안고르고
            }

        }
        // 이미 고른 경우 - 어디를 스킵할지 고른다
        else {
            // N 봉지 다 안골랐을 때
            if (nIdx < N) {
                ret = Math.max(ret, solve(nIdx + 1, mIdx, skip, 0));
            }
            // M 봉지 다 안골랐을 때
            if (mIdx + skip < M) {
                ret = Math.max(ret, solve(nIdx, mIdx, skip + 1, 0));
            }
        }

        return ret;
    }
}
