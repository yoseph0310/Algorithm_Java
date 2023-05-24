package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14499_주사위굴리기_2번째풀이 {

    static int N, M, r, c, K;
    static int[][] board;

    static int[] dice;

    // 동 서 북 남
    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {1, -1, 0, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        // 첫째줄 입력
        N = Integer.parseInt(st.nextToken());           // 세로
        M = Integer.parseInt(st.nextToken());           // 가로
        r = Integer.parseInt(st.nextToken());           // 주사위 좌표 x
        c = Integer.parseInt(st.nextToken());           // 주사위 좌표 y
        K = Integer.parseInt(st.nextToken());           // 명령의 개수

        board = new int[N][M];                          // 지도
        dice = new int[6];                              // 주사위 초기 상태
        // 지도 정보 입력
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 명령 대로 함수 실행
        // - 명령을 인자로 받아서 주사위 윗면을 출력.
        // - 바깥으로 이동하려고 하면 종료시키면서 출력이되지 않도록 해야한다.
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            // 함수
            int dir = Integer.parseInt(st.nextToken()) - 1;
            roll(r, c, dir);

        }

    }

    /**
     * 주사위를 전달 받은 방향에 따라 굴린다.
     *
     * 1. 주사위의 모든 면은 모두 0이다.
     * 2. 주사위를 굴린다.
     *      2-1. 이동한 칸에 쓰인 수가 0이면 주사위 바닥면(dice)의 수가 칸(board)에 복사된다.
     *      2-2. 이동한 칸에 쓰인 수가 0이 아니면 칸(board)의 수가 주사위 바닥면(dice)에 복사되고 0이 된다.
     * 3. 지도 바깥으로 이동하려는 경우 명령을 무시하고 출력되지 않게 한다.
     *
     * @param dir : 전달 받은 방향
     */
    static void roll(int x, int y, int dir) {
        // 현재 위치 기준으로 방향에 따라서 다음 좌표로 이동시킨다.
        int nx = x + dx[dir];
        int ny = y + dy[dir];

        // 바깥으로 나가게 되면 좌표값을 갱신하지 않고 함수를 종료 시킨다.
        if (isNotBoundary(nx, ny)) return;
        // x, y에 새로운 좌표값으로 갱신한다.
        r = nx; c = ny;

        // 방향에 따라서 주사위를 뒤집어준다.
        int temp = 0;
        switch (dir) {
            // 동
            case 0:
                temp = dice[4];
                dice[4] = dice[0];
                dice[0] = dice[5];
                dice[5] = dice[2];
                dice[2] = temp;
                break;
            // 서
            case 1:
                temp = dice[2];
                dice[2] = dice[5];
                dice[5] = dice[0];
                dice[0] = dice[4];
                dice[4] = temp;
                break;
            // 북
            case 2:
                temp = dice[2];
                dice[2] = dice[1];
                dice[1] = dice[0];
                dice[0] = dice[3];
                dice[3] = temp;
                break;
            // 남
            case 3:
                temp = dice[3];
                dice[3] = dice[0];
                dice[0] = dice[1];
                dice[1] = dice[2];
                dice[2] = temp;
                break;
        }
        // 바닥면과 지도 칸의 수를 비교하여 조건대로 수를 변경한다.
        // 이동한 칸에 쓰인 수가 0이면 주사위 바닥면(dice)의 수가 칸(board)에 복사된다.
        if (board[r][c] == 0) {
            board[r][c] = dice[0];
        }
        // 이동한 칸에 쓰인 수가 0이 아니면 칸(board)의 수가 주사위 바닥면(dice)에 복사되고 0이 된다.
        else {
            dice[0] = board[r][c];
            board[r][c] = 0;
        }

        // 주사위의 윗 칸을 출력한다.
        System.out.println(dice[2]);
    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < M);
    }

}
