package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14499_주사위굴리기 {

    static int N, M, x, y, K;               // 세로, 가로, 좌표 x, 좌표 y, 명령의 개수
    static int[][] board;
    static int[] dice = new int[7];

    static int[] dx = {0, 0, -1, 1};                   // 동 서 북 남
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        board = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            int d = Integer.parseInt(st.nextToken());
            move(d);
        }

    }
    public static void move(int d){
        int nx = x + dx[d-1];
        int ny = y + dy[d-1];
        if (0 <= nx && nx < N && 0 <= ny && ny < M){
            roll(d, nx, ny);
            x = nx; y = ny;
        }
    }

    public static void roll(int d, int x, int y){
        int tmp = 0;
        switch (d){
            // 1:동
            case 1:
                tmp = dice[5];
                dice[5] = dice[1];
                dice[1] = dice[4];
                dice[4] = dice[3];
                dice[3] = tmp;
                break;
            // 2:서
            case 2:
                tmp = dice[4];
                dice[4] = dice[1];
                dice[1] = dice[5];
                dice[5] = dice[3];
                dice[3] = tmp;
                break;
            // 3:남
            case 3:
                tmp = dice[3];
                dice[3] = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[0];
                dice[0] = tmp;
                break;
            // 4:북
            case 4:
                tmp = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[2];
                dice[2] = dice[3];
                dice[3] = tmp;
                break;
        }
        if (board[x][y] == 0){
            board[x][y] = dice[1];
        } else {
            dice[1] = board[x][y];
            board[x][y] = 0;
        }

        System.out.println(dice[3]);
    }
}
// 크기가 N * M 인 지도
// 지도의 오른쪽 - 동, 위쪽 - 북
