package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_9466_텀_프로젝트 {

    static int N, cnt;
    static int[] students;
    static boolean[] visited;
    static boolean[] finished;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            students = new int[N + 1];
            visited = new boolean[N + 1];
            finished = new boolean[N + 1];
            cnt = 0;

            st = new StringTokenizer(br.readLine());
            for (int from = 1; from <= N; from++) {
                int to = Integer.parseInt(st.nextToken());

                students[from] = to;
            }

            for (int i = 1; i <= N; i++) {
                if (!finished[i]) {
                    dfs(i);
                }
            }

            System.out.println(N - cnt);

        }
    }

    static void dfs(int n) {
        if (visited[n]) {
            finished[n] = true;
            cnt++;
        } else {
            visited[n] = true;
        }

        if (!finished[students[n]]) {
            dfs(students[n]);
        }

        visited[n] = false;
        finished[n] = true;
    }
}
