package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_38_촛불_이벤트 {

    static long N;       // 1 <= N <= 10^8 (10,000,000)

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Long.parseLong(br.readLine());

            long res = 0;
            long idx = -1;

            for (long n = (long)Math.sqrt(2*N); ; n++) {
                res = (n*(n+1)) / 2;
                if (res == N) {
                    idx = n;
                    break;
                } else if (res > N) {
                    break;
                }
            }

            System.out.println("#" + t + " " + idx);
        }
    }

}
