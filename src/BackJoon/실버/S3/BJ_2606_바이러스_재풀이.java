package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_2606_바이러스_재풀이 {

    static int N, M, ans;
    static int[][] graph;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        graph = new int[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from][to] = graph[to][from] = 1;
        }

        DFS(1);

        System.out.println(ans);
    }

    static void DFS(int n) {
        visited[n] = true;

        for (int i = 1; i <= N; i++) {
            if (graph[n][i] == 1 && !visited[i]) {
                DFS(i);
                ans++;
            }
        }

    }
}
