package Programmers.LV2;

import java.util.*;

public class 카카오프렌즈_컬러링북 {
    class Solution {
        int N;
        int M;

        int[][] board;
        boolean[][] visited;

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, 1, 0, -1};

        public int[] solution(int m, int n, int[][] picture) {
            int numberOfArea = 0;
            int maxSizeOfOneArea = 0;

            N = m;
            M = n;
            board = new int[N][M];
            visited = new boolean[N][M];
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    board[i][j] = picture[i][j];
                }
            }

            int[] answer = new int[2];
            answer = findArea();

            return answer;
        }

        int[] findArea() {
            int[] arr = new int[2];
            int numberOfArea = 0;
            int maxSizeOfOneArea = 0;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] != 0 && !visited[i][j]) {
                        maxSizeOfOneArea = Math.max(maxSizeOfOneArea, bfs(i, j));
                        numberOfArea++;
                    }
                }
            }

            arr[0] = numberOfArea;
            arr[1] = maxSizeOfOneArea;

            return arr;
        }

        int bfs(int x, int y) {
            Queue<Point> q = new LinkedList<>();

            q.add(new Point(x, y));
            visited[x][y] = true;

            int cnt = 1;

            while (!q.isEmpty()) {
                Point cur = q.poll();

                int cx = cur.x;
                int cy = cur.y;

                for (int d = 0; d < 4; d++) {
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (isNotBoundary(nx, ny) || visited[nx][ny]) continue;
                    if (board[nx][ny] == board[cx][cy]) {
                        q.add(new Point(nx, ny));
                        visited[nx][ny] = true;
                        cnt++;
                    }
                }
            }

            return cnt;
        }

        boolean isNotBoundary(int x, int y) {
            return !(0 <= x && x < N && 0 <= y && y < M);
        }

        class Point {
            int x, y;

            public Point(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }

        void printBoard(int[][] board) {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

/*
    영역의 수가 그림의 난이도 (상하좌우로 연결된 같은 색상의 공간)
    - 그림의 크기 m * n
    - 그림을 나타내는 m * n 크기의 2차원 배열
*/
}
