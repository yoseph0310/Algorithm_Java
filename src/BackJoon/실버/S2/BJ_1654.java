package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1654 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int K = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());
        int[] arr = new int[K];

        long start = 0;
        long end = 0;

        for (int i = 0; i < K; i++) {
            arr[i] = Integer.parseInt(br.readLine());

            if (end < arr[i]) end = arr[i];
        }

        end++;

        while(start < end){
            long mid = (start + end) / 2;
            long ans = 0;
            for (int length: arr) {
                ans += length / mid;
            }

            if (ans < N) end = mid;
            else start = mid + 1;
        }

        System.out.println(start - 1);
    }
}
