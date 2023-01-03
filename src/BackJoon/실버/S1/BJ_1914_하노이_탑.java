package BackJoon.실버.S1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class BJ_1914_하노이_탑 {

    static int N;
    static int moveCnt = 0;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        BigInteger res = new BigInteger("1");

        if (N > 20) {
            for (int i = 0; i < N; i++) {
                res = res.multiply(new BigInteger("2"));
            }
            res = res.subtract(new BigInteger("1"));
            System.out.println(res);
        } else {
            hanoi(N, 1, 3, 2);
            System.out.println(moveCnt);
            System.out.println(sb.toString());
        }
    }

    static void hanoi(int n, int from, int to, int via) {
        if (n == 0) return;

        hanoi(n-1, from, via, to);
        moveCnt++;
        sb.append(from).append(" ").append(to).append("\n");
        hanoi(n-1, via, to, from);
    }

}
