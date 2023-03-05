package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_13460_구슬_탈출_2 {

    static int N, M;
    static char [][] board;
    static boolean [][][][] visited;
    static int holeX, holeY;
    static Point red, blue;
    
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        board = new char[N][M];
        visited = new boolean[N][M][N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = s.charAt(j);
                
                if (board[i][j] == 'O'){
                    holeX = i;
                    holeY = j;
                }
                else if (board[i][j] == 'R'){
                    red = new Point(i, j, 0, 0, 0);
                }
                else if (board[i][j] == 'B'){
                    blue = new Point(0, 0, i, j, 0);
                }
            }
        }

        System.out.println(bfs());
        
        br.close();
    }
    
    public static int bfs(){
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(red.rx, red.ry, blue.bx, blue.by, 1));
        visited[red.rx][red.ry][blue.bx][blue.by] = true;
        
        while(!q.isEmpty()){
            Point point = q.poll();
            
            int curRx = point.rx;
            int curRy = point.ry;
            int curBx = point.bx;
            int curBy = point.by;
            int curCnt = point.cnt;
            
            if (curCnt > 10) return -1;

            for (int d = 0; d < 4; d++) {
                int newRx = curRx;
                int newRy = curRy;
                int newBx = curBx;
                int newBy = curBy;

                boolean isRedHole = false;
                boolean isBlueHole = false;

                // 빨간 구슬이 벽을 만날때 까지
                while(board[newRx + dx[d]][newRy + dy[d]] != '#'){
                    newRx += dx[d];
                    newRy += dy[d];

                    if (newRx == holeX && newRy == holeY){
                        isRedHole = true;
                        break;
                    }
                }

                // 파란 구슬이 벽을 만날때 까지
                while(board[newBx + dx[d]][newBy + dy[d]] != '#'){
                    newBx += dx[d];
                    newBy += dy[d];

                    if (newBx == holeX && newBy == holeY){
                        isBlueHole = true;
                        break;
                    }
                }

                if (isBlueHole){
                    continue;
                }
                if (isRedHole && !isBlueHole){
                    return curCnt;
                }

                if (newRx == newBx && newRy == newBy){
                    if (d == 0){
                        if (curRx > curBx) newRx -= dx[d];
                        else newBx -= dx[d];
                    }
                    else if (d == 1){
                        if (curRy < curBy) newRy -= dy[d];
                        else newBy -= dy[d];
                    }
                    else if (d == 2){
                        if (curRx < curBx) newRx -= dx[d];
                        else newBx -= dx[d];
                    }
                    else {
                        if (curRy > curBy) newRy -= dy[d];
                        else newBy -= dy[d];
                    }
                }
                if (!visited[newRx][newRy][newBx][newBy]) {
                    visited[newRx][newRy][newBx][newBy] = true;
                    q.add(new Point(newRx, newRy, newBx, newBy, curCnt + 1));
                }
            }
        }
        return -1;
    }

    static class Point{
        int rx;
        int ry;
        int bx;
        int by;
        int cnt;

        public Point(int rx, int ry, int bx, int by, int cnt){
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.cnt = cnt;
        }
    }
}
