package BackJoon.실버.S2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_15663_N과M_9 {

    static int N, M;
    static int[] arr;
    static int[] selected;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();


    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];
        selected = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);

        dfs(0);
        System.out.println(sb);
    }

    static void dfs(int depth) {
        if (depth == M) {
            for (int v: selected) {
                sb.append(v).append(' ');
            }
            sb.append('\n');
            return;
        }

        int past = -1;
        for (int i = 0; i < N; i++) {
            int now = arr[i];
            if (!visited[i] && past != now) {
                past = now;
                visited[i] = true;
                selected[depth] = arr[i];
                dfs(depth + 1);
                visited[i] = false;
            }
        }
    }
}
