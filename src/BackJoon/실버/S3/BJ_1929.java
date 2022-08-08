package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1929 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());



    }

    public static void make_prime(int n, int m){
        for (int i = n; i < m; i++) {
            if (i == 1){
                continue;
            }
            for (int j = 2; j <= Math.sqrt(i) ; j++) {
                if(i % j == 0)
            }
        }
    }
}
