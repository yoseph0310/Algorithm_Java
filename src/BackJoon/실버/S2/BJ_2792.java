package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_2792 {

    static int N, M, max;
    static int[] colors;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());           // 아이들의 수
        M = Integer.parseInt(st.nextToken());           // 색상의 수
        colors = new int[M];


        for (int i = 0; i < M; i++) {
            colors[i] = Integer.parseInt(br.readLine());           // 색상들 각각의 개수
            if (max < colors[i]) max = colors[i];
        }
        Arrays.sort(colors);
        int min = search(N, M);
        System.out.println(min);
    }

    public static int search(int n, int m){
        int min = Integer.MAX_VALUE;
        int low = 1;                // 무조건 1명에게는 나눠줘야 함. -> 모든 보석을 나눠야함
        int high = max;

        while (low <= high){
            int mid = (low + high) / 2;
            int sum = 0;
            for (int element: colors) {
                int people = element % mid == 0 ? element / mid : element / mid + 1;
                sum += people;
            }
            if (n >= sum){
                min = Math.min(min, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return min;
    }
}
