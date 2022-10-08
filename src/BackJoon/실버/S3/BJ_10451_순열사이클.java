package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_10451_순열사이클 {

    static int N, ans;
    static int[] graph;
    static boolean[] check;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            N = Integer.parseInt(br.readLine());
            graph = new int[N+1];
            ans = 0;

            st = new StringTokenizer(br.readLine());
            for (int i = 1; i < N+1; i++) {
                graph[i] = Integer.parseInt(st.nextToken());
            }

            check = new boolean[N+1];
            for (int i = 1; i < N+1; i++) {
                if (!check[i]){
                    dfs(i);
                    ans++;
                }
            }
            System.out.println(ans);
        }

    }
    static void dfs(int start){
        check[start] = true;

        int next = graph[start];
        if (!check[next]){
            dfs(next);
        }
    }
}
