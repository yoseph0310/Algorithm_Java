package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Hunter_2 {

    static int N, ans;
    static ArrayList<Point> customers;
    static ArrayList<Point> monsters;
    static boolean[] visitedCustomers;
    static boolean[] visitedMonsters;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            ans = Integer.MAX_VALUE;
            customers = new ArrayList<>();
            monsters = new ArrayList<>();

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int n = Integer.parseInt(st.nextToken());
                    if (n != 0) {
                        if (n > 0) monsters.add(new Point(i, j, n));
                        else customers.add(new Point(i, j, n));
                    }
                }
            }

            visitedCustomers = new boolean[customers.size() + 1];
            visitedMonsters = new boolean[monsters.size() + 1];

            dfs(0, 0, 0, 0);

            System.out.println("#"+t+" "+ans);
        }
    }
    static void dfs(int depth, int distance, int x, int y){
        if (distance >= ans) return;
        if (depth == monsters.size() * 2) {
            ans = Math.min(ans, distance);
            return;
        }

        for (Point p: monsters) {
            if(visitedMonsters[p.n]) continue;

            int d = getDistance(p, x, y);
            visitedMonsters[p.n] = true;
            dfs(depth + 1, distance + d, p.x, p.y);
            visitedMonsters[p.n] = false;
        }
        for (Point p: customers) {
            int n = Math.abs(p.n);
            if(visitedCustomers[n] || !visitedMonsters[n]) continue;

            int d = getDistance(p, x, y);
            visitedCustomers[n] = true;
            dfs(depth + 1, distance+d, p.x, p.y);
            visitedCustomers[n] = false;
        }
    }

    static int getDistance(Point p, int tx, int ty){
        return Math.abs(p.x - tx) + Math.abs(p.y - ty);
    }

    static class Point{
        int x, y, n;    // 행, 열, 고객 혹은 몬스터 번호

        public Point(int x, int y, int n){
            this.x = x;
            this.y = y;
            this.n = n;
        }
    }
}
