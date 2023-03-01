package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
     순열 기본
 */

public class BJ_15649_N과M_1 {

    static int[] arr;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        visited = new boolean[N];

        dfs(N, M, 0);
        System.out.println(sb);
    }

    static void dfs(int n, int m, int depth) {
        if (depth == m){
            for (int value: arr) {
                sb.append(value).append(' ');
            }
            sb.append('\n');
            return;
        }
        for (int i = 0; i < n; i++) {
            if (!visited[i]){
                visited[i] = true;
                arr[depth] = i + 1;
                dfs(n, m, depth + 1);
                visited[i] = false;
            }
        }
    }
}
