package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_5642_Professional_합 {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];
            int max = Integer.MIN_VALUE;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int sum = 0;

            for (int i = 0; i < N; i++) {
                sum += arr[i];
                if (sum > max) max = sum;
                if (sum < 0) sum = 0;
            }

            System.out.println("#" + t + " " + max);
        }
    }
}
