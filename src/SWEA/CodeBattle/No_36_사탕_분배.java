package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_36_사탕_분배 {

    static long sum;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            long A = Long.parseLong(st.nextToken());
            long B = Long.parseLong(st.nextToken());
            long K = Long.parseLong(st.nextToken());

            sum = A + B;
            long res = getRes(K);

            A = (A * res) % sum;
            long ans = Math.min(A, sum - A);
            System.out.println("#" + t + " " + ans);
        }
    }

    static long getRes(long k) {
        if (k == 1) return 2;

        long res = getRes(k/2);
        res = (res * res) % sum;

        if (k % 2 == 1) res = (res * 2) % sum;

        return res;
    }

}
