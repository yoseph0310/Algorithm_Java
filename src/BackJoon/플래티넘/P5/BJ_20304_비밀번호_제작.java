package BackJoon.플래티넘.P5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_20304_비밀번호_제작 {

    static int N, M, ans = 0;
    static int[] visit;
    static Queue<Point> q = new LinkedList<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());

        visit = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            visit[i] = -1;
        }

        int temp;
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            temp = Integer.parseInt(st.nextToken());
            q.add(new Point(temp, 0));
            visit[temp] = 0;
        }

        bfs();
        System.out.println(ans);
    }

    static void bfs() {
        while (!q.isEmpty()) {
            Point cur = q.poll();
            for (int temp = 1; temp <= N; temp <<= 1) {
                isPossible(cur, (cur.num & temp) > 0 ? (cur.num - temp) : (cur.num + temp));
            }
        }
    }

    static void isPossible(Point p, int num) {
        if (num <= N && visit[num] == -1) {
            visit[num] = p.cnt + 1;
            q.add(new Point(num, p.cnt + 1));
            ans = Math.max(ans, p.cnt + 1);
        }
    }

    static class Point {
        int num, cnt;

        public Point(int num, int cnt) {
            this.num = num;
            this.cnt = cnt;
        }
    }
}
