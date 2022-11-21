package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_3079 {

    static int N, M;
    static int [] times;
    static long max = 0;
    static long min = Long.MAX_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        times = new int[N];

        for (int i = 0; i < N; i++) {
            times[i] = Integer.parseInt(br.readLine());
            max = Math.max(max, times[i]);
        }
        getMinTime(M);
        System.out.println(min);
    }
    public static void getMinTime(int m){
        long low = 0;
        long high = max * m;

        while (low <= high){
            long mid = (low + high) / 2;
            long sum = 0;

            for (int time: times) {
                long needPeople = mid / time;
                if (sum >= m) break;
                sum += needPeople;
            }

            if (sum >= m) {
                System.out.println(mid + ", "+ sum);
                min = Math.min(min, mid);
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }
}
// 2 3 3 4 6 8 9
