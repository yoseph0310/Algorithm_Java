package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 *  1. 가방 안에 사탕 종류 구성이 같아야함 -> 모든 사탕 종류를 쓸 필요는 없다.
 *  2. 모든 가방에는 정확히 M개가 들어가야함.
 *
 *  즉, 최대값에서 부터 진행해야함
 *
 *  출력 : 최대 몇개의 사탕 가방을 만들 수 있는가?
 */
public class No_39_사탕_가방 {



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            long M = Long.parseLong(st.nextToken());

            long[] candy = new long[N];
            long max = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                candy[i] = Long.parseLong(st.nextToken());

                max = Math.max(max, candy[i]);
            }

            long low = 1L;
            long high = max;

            while (low <= high) {
                long mid = (low + high) / 2;
                long sum = 0L;

                for (int i = 0; i < N; i++) {
                    sum += (candy[i] / mid);
                }

                if (sum < M) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }

            System.out.println("#" + t + " " + high);
        }
    }
}
