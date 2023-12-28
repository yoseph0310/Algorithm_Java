package SWEA.D3.SW_10726_이진수_표현;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 이진수표현 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int checkN = (int)Math.pow(2, N) - 1;

            String ans = (checkN & M) == checkN ? "ON" : "OFF";

            System.out.println("#" + t + " " + ans);
        }
    }
}
