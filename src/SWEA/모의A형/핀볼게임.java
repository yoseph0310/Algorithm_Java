package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class 핀볼게임 {
    static int N, ans;
    static int[][] board;
    static ArrayList<Point> departList;
    static ArrayList<Point>[] warmholeList;

    // 상 우 하 좌

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            ans = 0;
            N = Integer.parseInt(br.readLine());
            board = new int[N][N];
            departList = new ArrayList<>();
            warmholeList = new ArrayList[11];

            for (int i = 6; i <= 10; i++) {
                warmholeList[i] = new ArrayList<>();
            }


            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] == 0) departList.add(new Point(i, j));
                    if (board[i][j] >= 6) warmholeList[board[i][j]].add(new Point(i, j));
                }
            }

            for (Point p: departList) {
                for (int d = 0; d < 4; d++) {
                    int cnt = move(p.x, p.y, d);
                    ans = Math.max(ans, cnt);
                }
            }
            System.out.println("#"+t+" "+ans);
        }
    }

    static int move(int startX, int startY, int d){
        int cnt = 0;
        boolean flag = false;
        int x = startX;
        int y = startY;
        while (true){
            int nr = 0;
            int nc = 0;
            int block = board[x][y];

            if (1 <= block && block <= 5){
                cnt++;
                switch (block){
                    // 0 (상), 1 (우), 2 (하), 3 (좌)
                    case 1:         // 1 :  좌 -> 상, 하 -> 우, 이외 반대방향
                        if (d == 2 || d == 3) {
                            d = (d % 2 == 0) ? 1 : 0;
                        } else {
                            d = (d == 1) ? 3 : 2;
                        }
                        break;
                    case 2:         // 2 :  상 -> 우, 좌 -> 하, 이외 반대방향
                        if (d == 0 || d == 3){
                            d = (d % 2 == 0) ? 1 : 2;
                        } else {
                            d = (d == 1) ? 3 : 0;
                        }
                        break;
                    case 3:         // 3 :  우 -> 하, 상 -> 좌, 이외 반대방향
                        if (d == 1 || d == 0){
                            d = (d % 2 == 0) ? 3 : 2;
                        } else {
                            d = (d == 2) ? 0 : 1;
                        }
                        break;
                    case 4:         // 4 :  우 -> 상, 하 -> 좌, 이외 반대방향
                        if (d == 1 || d == 2){
                            d = (d % 2 == 0) ? 3 : 0;
                        } else {
                            d = (d == 0) ? 2 : 1;
                        }
                        break;
                    case 5:         // 5 :  모두 반대방향

                        break;
                }
            }
        }
    }


    static class Point {
        int x, y;

        public Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }

}
