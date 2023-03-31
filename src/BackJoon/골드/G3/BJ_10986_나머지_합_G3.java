package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10986_나머지_합_G3 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        long[] S = new long[N+1];
        long[] C = new long[M];
        long ans = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            S[i] = Integer.parseInt(st.nextToken()) + S[i-1];
        }

        for (int i = 1; i <= N; i++) {
            int remainder = (int)(S[i] % M);
            if (remainder == 0) ans++;

            C[remainder]++;
        }

        for (int i = 0; i < M; i++) {

            if (C[i] > 1) {
                ans = ans + (C[i] * (C[i] - 1) / 2);
            }
        }

        System.out.println(ans);

    }
}
