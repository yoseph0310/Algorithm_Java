package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class _123더하기 {

    static int[] dp = new int[11];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;

        for (int i = 4; i < 11; i++) {
            dp[i] = dp[i-3] + dp[i-2] + dp[i-1];
        }

        for (int i = 0; i < T; i++) {
            int N = Integer.parseInt(br.readLine());

//            System.out.println(dp[N]);
            bw.write(dp[N] + "\n");
        }
        bw.flush();
        bw.close();
    }

}
