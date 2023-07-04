package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_1206_View {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            st = new StringTokenizer(br.readLine());

            int[] arr = new int[N];
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            int cnt = 0;
            for (int i = 2; i < N - 2; i++) {
                int max = Math.max(arr[i-2], Math.max(arr[i-1], Math.max(arr[i+1], arr[i+2])));
                if (arr[i] - max > 0) {
                    cnt += arr[i] - max;
                }
            }

            System.out.println("#" + t + " " + cnt);
        }
    }

}
