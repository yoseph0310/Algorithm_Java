package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2805 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        int start = 0;
        int end = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());

            if (end < arr[i]){
                end = arr[i];
            }
        }

        while (start < end){
            int mid = (start + end) / 2;
            long sum = 0;
            for (int height: arr) {
                if (height - mid > 0){
                    sum += (height - mid);
                }
            }

            if (sum < M){
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(start - 1);
    }
}
