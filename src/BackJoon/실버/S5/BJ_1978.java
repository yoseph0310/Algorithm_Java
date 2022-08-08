package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_1978 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int cnt = 0;

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N+1; i++) {
            boolean isPrime = true;

            int num = Integer.parseInt(st.nextToken());

            if(num == 1){
                continue;
            }
            for (int j = 2; j <= Math.sqrt(num) ; j++) {
                if(num % i == 0){
                    isPrime = false;
                    break;
                }
            }
            if(isPrime){
                cnt++;
            }
        }

        System.out.println(cnt);
    }
}
