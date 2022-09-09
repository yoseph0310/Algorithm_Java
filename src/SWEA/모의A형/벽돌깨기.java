package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 벽돌깨기 {

    static int N, W, H, ans, burstCnt;

    static int [] dx = {-1, 0, 1, 0};
    static int [] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            H = Integer.parseInt(st.nextToken());

            int total = 0;
            int[][] board = new int[H][W];
            for (int i = 0; i < H; i++) {
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < W; j++) {
                    board[i][j] = Integer.parseInt(st.nextToken());
                    if (board[i][j] > 0) total++;
                }
            }

            ans = Integer.MAX_VALUE;
            process(0, total, board);
            System.out.println("#"+t+" "+ans);
        }
    }

    static boolean process(int cnt, int remain, int[][] board){
        if (remain == 0){
            ans = 0;
            return true;
        }
        if (cnt == N){
            ans = Math.min(ans, remain);
            return false;
        }

        int[][] copy_board = new int[H][W];

        // 구슬 떨구기
        for (int y = 0; y < W; y++) {
            int x = 0;

            // 해당 열에서 가장 위에 있는 벽돌 찾기
            while (x < H && board[x][y] == 0) ++x;
            // 벽돌이 없을 경우 continue
            if (x == H) continue;

            // 벽돌이 있을 경우
            // 이전 구슬 상태를 복사
            copy(board, copy_board);
            // 해당 좌표로 구슬을 떨어트려 벽돌 터뜨리기
            burstCnt = 0;
            burst(copy_board, x, y, copy_board[x][y]);
            // 벽돌 내리기
            down_block(copy_board);
            // 다음 구슬 처리 (더 이상 확인할 필요 없으면 true)
            if(process(cnt + 1, remain - burstCnt, copy_board)){
                return true;
            }
        }
        return false;
    }

    static void down_block(int[][] board){
        for (int y = 0; y < W; y++) {
            int x = H - 1;
            while (x > 0){
                // 빈 공간이라면
                if(board[x][y] == 0){
                    // 직전 행 탐색
                    int nx = x-1;
                    // 처음 만나는 벽돌 찾기
                    while(nx > 0 && board[nx][y] == 0) nx--;

                    board[x][y] = board[nx][y];
                    board[nx][y] = 0;
                }
                x--;
            }
        }
    }

    static void burst(int[][] board, int x, int y, int cnt){
        // 벽돌이 깨지면 0
        board[x][y] = 0;
        burstCnt++;
        // 벽돌 숫자가 1이면 return
        if (cnt == 1) return;

        for (int d = 0; d < 4; d++) {
            int nx = x;
            int ny = y;

            // 벽돌에 적힌 숫자 - 1 만큼 영향
            for (int i = 0; i < cnt - 1; i++) {
                nx += dx[d];
                ny += dy[d];
                // 범위 확인
                if (0<= nx && nx < H && 0 <= ny && ny < W && board[nx][ny] != 0){
                    burst(board, nx, ny, board[nx][ny]);
                }
            }
        }
    }

    static void copy(int[][] board, int[][] copy_board){
        for (int i = 0; i < H; i++) {
            for (int j = 0; j < W; j++) {
                copy_board[i][j] = board[i][j];
            }
        }
    }

}