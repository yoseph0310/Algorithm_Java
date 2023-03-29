package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class BJ_24479_알고리즘_수업_깊이_우선_탐색_1 {

    static int N, M, R, idx = 0;
    static int[] answer;
    static ArrayList<Integer>[] edges;
    static boolean[] visited;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        edges = new ArrayList[N + 1];

        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }

        answer = new int[N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            edges[from].add(to);
            edges[to].add(from);
        }

        for (int i = 1; i <= N; i++) {
            Collections.sort(edges[i]);
        }

        dfs(R);

        for (int i = 1; i <= N; i++) {
            sb.append(answer[i]).append('\n');
        }

        System.out.println(sb);
    }

    static void dfs(int node) {
        visited[node] = true;
        answer[node] = node;

        for (int i = 0; i < edges[node].size(); i++) {
            if (!visited[edges[node].get(i)]) {
                visited[edges[node].get(i)] = true;
                dfs(edges[node].get(i));
            }
        }
    }
}
