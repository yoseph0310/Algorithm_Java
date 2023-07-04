package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class SW_16800_구구단_걷기 {

    static long ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            long N = Long.parseLong(br.readLine());

            List<A> list = new ArrayList<>();

            for (int i = 1; i < Math.sqrt(N) + 1; i++) {
                if (N % i == 0) list.add(new A(i, N/i));
            }

            for (A a: list) {
                ans = (a.x - 1) + (a.y - 1);
            }

            System.out.println("#" + t + " " + ans);
        }
    }

    static class A {
        long x, y;

        public A(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }
}
