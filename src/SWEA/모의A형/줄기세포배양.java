package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class 줄기세포배양 {

    static int N, M, K, nx, ny, ans;
    static int[][] board;
    static boolean[][] visited;
    static Queue<Cell> q;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    static final short DEATH = 0, ACTIVE = 1, INACTIVE = 2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());

            board = new int[N + K + 2][M + K + 2];
            visited = new boolean[N + K + 2][M + K + 2];
            q = new LinkedList<>();

            int temp;
            for (int i = K / 2 + 1; i < N + K / 2 + 1; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = K / 2 + 1; j < M + K / 2 + 1; j++) {
                    temp = Integer.parseInt(st.nextToken());
                    if (temp != 0){
                        board[i][j] = temp;
                        visited[i][j] = true;
                        q.add(new Cell(i, j, temp));
                    }
                }
            }

            ans = solution();
            System.out.println("#"+t+" "+ans);
        }
    }

    static int solution(){
        int cnt = K;
        Cell cell;
        while (cnt > 0){
            int len = q.size();
            for (Cell c: q) {
                if (c.status == ACTIVE) check(c);
            }

            for (int i = 0; i < len; i++) {
                cell = q.poll();
                if (cell.status == ACTIVE){
                    for (int d = 0; d < 4; d++) {
                        nx = cell.x + dx[d];
                        ny = cell.y + dy[d];

                        if (visited[nx][ny]) continue;

                        q.add(new Cell(nx, ny, board[nx][ny]));
                        visited[nx][ny] = true;
                    }
                }

                cell.next();

                if(cell.status == DEATH) continue;
                q.add(cell);
            }
            cnt--;
        }
        return q.size();
    }

    static void check(Cell cell) {
        for (int d = 0; d < 4; d++) {
            nx = cell.x + dx[d];
            ny = cell.y + dy[d];

            if (visited[nx][ny]) continue;

            if (board[nx][ny] < cell.value) board[nx][ny] = cell.value;
        }
    }

    static class Cell{
        int x, y;
        int value, temp;
        short status;

        public Cell(int x, int y, int value){
            this.x = x;
            this.y = y;
            this.value = value;
            this.temp = value;
            this.status = INACTIVE;
        }

        public void next(){
            switch (status){
                case INACTIVE:
                    if (--temp == 0) status = ACTIVE;
                    break;
                case ACTIVE:
                    if (++temp == value) status = DEATH;
                    break;
            }
        }
    }
}
