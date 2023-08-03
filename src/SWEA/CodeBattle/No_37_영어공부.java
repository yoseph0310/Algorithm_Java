package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class No_37_영어공부 {

    static int N, P, max;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());           // 영어 공부를 한 날의 수  (1 <= N <= 200,000)
            P = Integer.parseInt(st.nextToken());           // 체크할 수 있는 날의 수  (1 <= P <= 200,000)

            visited = new boolean[1000001];
            int last = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {                   // 번호의 크기 (0 <= num <= 10 ^ 6 (백만) )
                int day = Integer.parseInt(st.nextToken());
                last = Math.max(last, day);

                visited[day] = true;
            }

            max = P + 1;

            findMax(last);
            System.out.println("#" + t + " " + max);
        }
    }

    static void findMax(int last) {
        int s = 1;
        int e = 1;
        int cnt = 0;

        while (e < last + 1) {
            // 이미 체크한 곳이라면
            if (visited[e]) {
                cnt++;
                e++;
                max = Math.max(max, cnt);
            } else {
                if (P == 0) {
                    max = Math.max(max, cnt);
                    if (!visited[s]) P++;

                    s++;
                    cnt--;

                } else {
                    P--;
                    cnt++;
                    e++;
                }
            }
        }

    }
}
