package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Hunter {

    static int N, ans, placeNum;
    static boolean[] check;
    static ArrayList<Point> infos;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            ans = Integer.MAX_VALUE;
            N = Integer.parseInt(br.readLine());
            infos = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int tmp = Integer.parseInt(st.nextToken());
                    if (tmp != 0){
                        infos.add(new Point(i, j, tmp));
                    }
                }
            }
            Collections.sort(infos);
            placeNum = infos.size();
            check = new boolean[placeNum];
            dfs(0, 0, 0, 0);
            System.out.println("#"+t+" "+ans);
        }
    }

    public static void dfs(int depth, int sumDist, int hx, int hy){
        if (depth == placeNum){
            ans = Math.min(ans, sumDist);
            return;
        }

        for (int i = 0; i < placeNum; i++) {
            if(check[i] || (i % 2 == 0 && !check[i+1]))
                continue;

            Point p = infos.get(i);
            check[i] = true;
            dfs(depth+1, sumDist + callDist(p, hx, hy), p.x, p.y);
            check[i] = false;
        }
    }

    public static int callDist(Point p, int hx, int hy){
        return Math.abs(hx - p.x) + Math.abs(hy- p.y);
    }

    static class Point implements Comparable<Point>{
        int x;
        int y;
        int type;

        public Point(int x, int y, int type){
            this.x = x;
            this.y = y;
            this.type = type;
        }

        public int compareTo(Point p){
            return Math.abs(this.type) == Math.abs(p.type) ? Integer.compare(this.type, p.type) : Integer.compare(Math.abs(this.type), Math.abs(p.type));
        }
    }
}

