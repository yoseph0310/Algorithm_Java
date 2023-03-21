package BackJoon.브론즈.B1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9506_약수들의_합 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while(true) {
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            boolean[] arr = new boolean[100001];
            int sum = 0;

            if (N == -1) {
                break;
            }

            for (int i = 1; i <= N; i++) {
                if (N % i == 0 && N != i) {
                    arr[i] = true;
                    sum += i;
                }
            }

            sb.append(N);

            if (N == sum) {
                sb.append(" = ");
                for (int i = 1; i <= N; i++) {
                    if (arr[i]) {
                        sb.append(i).append(" + ");
                    }
                }
                System.out.println(sb.substring(0, sb.length() - 3));
            } else {
                sb.append(" is NOT perfect.");
                System.out.println(sb);
            }

        }
    }
}
