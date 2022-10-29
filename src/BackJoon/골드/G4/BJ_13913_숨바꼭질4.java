package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class BJ_13913_숨바꼭질4 {

    static int N, K;
    static int[] visited = new int[100001];
    static int[] parent = new int[100001];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs(N, K);
        System.out.println(visited[K] - 1);

        Stack<Integer> s = new Stack<>();
        int idx = K;
        while (idx != N) {
            s.push(idx);
            idx = parent[idx];
        }
        s.push(idx);

        while (!s.isEmpty()){
            System.out.print(s.pop() + " ");
        }
    }

    static void bfs(int start, int end){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start] = 1;

        while (!q.isEmpty()){
            int now = q.poll();

            if (now == K) return;

            for (int i = 0; i < 3; i++) {
                int next;

                if (i==0) next = now + 1;
                else if (i==1) next = now - 1;
                else next = now * 2;

                if (0 <= next && next <= 100000) {
                    if (visited[next] == 0) {
                        q.add(next);
                        visited[next] = visited[now] + 1;
                        parent[next] = now;
                    }
                }
            }
        }
    }
}
