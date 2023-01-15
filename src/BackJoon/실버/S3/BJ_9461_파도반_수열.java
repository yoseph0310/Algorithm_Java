package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9461_파도반_수열 {

    public static Long[] seq = new Long[101];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        seq[0] = 0L;
        seq[1] = 1L;
        seq[2] = 1L;
        seq[3] = 1L;

        int T = Integer.parseInt(br.readLine());

        while(T-- > 0) {
            int N = Integer.parseInt(br.readLine());

            sb.append(solution(N)).append('\n');
        }
        System.out.println(sb);
    }

    public static long solution(int N) {
        if (seq[N] == null) {
            seq[N] = solution(N - 2) + solution(N - 3);
        }

        return seq[N];
    }
}
