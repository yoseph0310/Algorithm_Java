package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_14178_1차원_정원 {

    static int N, D, ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            N = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            int available = 2 * D + 1;
            int mod = N % available;

            ans = (mod > 0) ?  N / available + 1 : N / available;

            System.out.println("#" + t + " " + ans);
        }
    }
}
