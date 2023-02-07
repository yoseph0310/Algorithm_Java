package BackJoon.플래티넘.P5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_17071_숨바꼭질_5 {

    static int N, K;
    static boolean[][] visited = new boolean[500001][2];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        if (N == K) {
            System.out.println(0);
        } else {
            System.out.println(bfs(N));
        }
    }

    static int bfs(int start) {
        int time = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        visited[start][time] = true;

        while(!q.isEmpty()) {
            if (K > 500000) {
                return -1;
            }

            int newTime = time % 2;

            if (visited[K][newTime]) {
                return time;
            }

            for (int i = 0, size = q.size(); i < size; i++) {
                int cur = q.poll();

                int nextTime = (time + 1) % 2;
                int next;

                next = cur - 1;
                if (next >= 0 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    q.add(next);
                }

                next = cur + 1;
                if (next < 500001 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    q.add(next);
                }

                next = cur * 2;
                if (next < 500001 && !visited[next][nextTime]) {
                    visited[next][nextTime] = true;
                    q.add(next);
                }
            }
            time++;
            K += time;
        }
        return -1;
    }
}
