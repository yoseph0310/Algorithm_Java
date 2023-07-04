package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_5603_Professional_건초더미 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[] arr = new int[N];

            int sum = 0;
            int avg = 0;
            for (int i = 0; i < N; i++) {
                arr[i] = Integer.parseInt(br.readLine());
                sum += arr[i];
            }

            avg = sum / N;

            int diffSum = 0;
            for (int i = 0; i < N; i++) {
                int diff = Math.abs(arr[i] - avg);

                diffSum += diff;
            }

            int ans = diffSum / 2;

            System.out.println("#" + t + " " + ans);
        }
    }
}
/*
    10 7 2 1
    sum : 20 , avg : 5

    각 수들이 5가 되기 위해 필요한 수는
    10 : 5, 7 : 2, 2 : 3, 1 : 4
    총 14임. 그러나 한번 이동할때 값이 두개가 빠지므로 14 / 2 -> 7이됨
 */
