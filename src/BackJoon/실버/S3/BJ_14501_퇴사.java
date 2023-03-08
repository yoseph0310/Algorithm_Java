package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14501_퇴사 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] T = new int[N+15];
        int[] P = new int[N+15];
        int[] DP = new int[N+15];
        int max = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            T[i] = Integer.parseInt(st.nextToken());
            P[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i <= N; i++) {
            DP[i] = Math.max(DP[i], max);

            DP[T[i] + i] = Math.max(DP[T[i] + i], P[i] + DP[i]);

            max = Math.max(max, DP[i]);
        }

        System.out.println(max);
    }
}
