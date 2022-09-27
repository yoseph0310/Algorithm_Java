package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2417_BinarySearch {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long n = Long.parseLong(br.readLine());

        long start = 0;
        long end = n;
        long min = Long.MAX_VALUE;

        while (start <= end){
            long mid = (start + end) / 2;
            long res = (long)Math.pow(mid, 2);

            if (res >= n){
                min = mid;
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        System.out.println(min);
    }
}
