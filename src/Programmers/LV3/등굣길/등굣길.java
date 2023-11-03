package Programmers.LV3.등굣길;

public class 등굣길 {
    /**
     * 직접 풀이
     */
    static class Solution_1 {

        static final int MOD = 1_000_000_007;

        static int N, M;
        static int[][] board;
        static int[][] availableCnt;

        // 상, 좌
        static int[] hdx = {-1, 0};
        static int[] hdy = {0, -1};

        public int solution(int m, int n, int[][] puddles) {
            int answer = 0;

            N = n;
            M = m;
            board = new int[N + 1][M + 1];
            availableCnt = new int[N + 1][M + 1];

            for (int i = 0; i < puddles.length; i++) {
                int x = puddles[i][1];
                int y = puddles[i][0];

                board[x][y] = 1;
            }

            // 격자 상태 Test Print
            // printBoard(board);

            // 학교 위, 왼쪽이 모두 물웅덩이인 경우 0
            if (isAllWater(puddles)) {
                // System.out.println("학교 위, 왼쪽이 모두 물웅덩이입니다.");
                return 0;
            }

            findPath();

            // 경로 상태 Test Print
            // printBoard(availableCnt);

            return availableCnt[N][M] % MOD;
        }

        static void findPath() {
            availableCnt[1][1] = 1 % MOD;

            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    // 물 웅덩이인 경우 continue
                    if (board[i][j] == 1) continue;

                    // 해당 좌표로 올 수 있는 방향만큼 ++
                    for (int d = 0; d < 2; d++) {
                        int nx = i + hdx[d];
                        int ny = j + hdy[d];

                        if (isNotBoundary(nx, ny)) continue;
                        if (board[nx][ny] == 1) continue;

                        availableCnt[i][j] += (availableCnt[nx][ny] % MOD);
                    }
                }
            }
        }

        static boolean isNotBoundary(int x, int y) {
            return !(1 <= x && x <= N && 1 <= y && y <= M);
        }

        static boolean isAllWater(int[][] puddles) {
            int cnt = 0;

            for (int i = 0; i < puddles.length; i++) {
                int x = puddles[i][0];
                int y = puddles[i][1];

                if (x == N && y == (M - 1)) cnt++;
                if (x == (N - 1) && y == M) cnt++;
            }

            return cnt == 2;
        }
    }

    /**
     * 불필요 코드 정리 코드
     */
    static class Solution_2 {
        public int solution(int m, int n, int[][] puddles) {
            int MOD = 1_000_000_007;

            int[][] board = new int[n + 1][m + 1];
            for (int i = 0; i < puddles.length; i++) {
                board[puddles[i][1]][puddles[i][0]] = -1;
            }

            board[1][1] = 1;

            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    if (board[i][j] == -1) continue;

                    if (board[i - 1][j] > 0) board[i][j] += board[i - 1][j] % MOD;
                    if (board[i][j - 1] > 0) board[i][j] += board[i][j - 1] % MOD;
                }
            }

            return board[n][m] % MOD;
        }
    }
}
