package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5607_Professional_조합 {

    static long MOD = 1234567891;
    static long[] fact = new long[1000001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuffer sb = new StringBuffer();

        fact[0] = 1;
        for (int i = 1; i <= 1000000; i++) {
            fact[i] = fact[i-1] * i % MOD;
        }

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int R = Integer.parseInt(st.nextToken());

            long c1 = fact[N];;
            long c2 = (fact[N - R] * fact[R]) % MOD;
            long c3 = calPow(c2, MOD - 2);

            sb.append("#").append(t).append(" ");
            sb.append(c1 * c3 % MOD).append("\n");
        }

        System.out.println(sb);
    }

    static long calPow(long n, long k) {
        if (k==1) return n;

        long x = calPow(n, k/2) % MOD;

        if (k % 2 == 0) return x * x % MOD;
        else return ((x * x) % MOD * n) % MOD;
    }
}
