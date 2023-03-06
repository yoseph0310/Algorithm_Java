package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_3190_뱀 {

    static int N, K, L, time;
    static int[][] board;
    static List<Point> snake;

    static int idx = 0;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static Map<Integer, String> dir;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());        // 보드 크기

        board = new int[N + 1][N + 1];

        K = Integer.parseInt(br.readLine());        // 사과 개수
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            board[x][y] = 1;
        }

        dir = new HashMap<>();
        L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());       // 시간
            String command = st.nextToken();

            dir.put(time, command);
        }

        snake = new LinkedList<>();
        snake.add(new Point(1, 1));

        System.out.println(solve(1, 1));
    }

    static int solve(int cx, int cy) {
        time = 0;

        int nx, ny;

        while (true) {
            time++;

            nx = cx + dx[idx];
            ny = cy + dy[idx];

            if (isFinish(nx, ny)) break;

            if (board[nx][ny] == 1) {
                board[nx][ny] = 0;
                snake.add(new Point(nx, ny));
            } else {
                snake.add(new Point(nx, ny));
                snake.remove(0);
            }

            cx = nx;
            cy = ny;

            if (dir.containsKey(time)) {
                if (dir.get(time).equals("D")) {
                    idx++;
                    if (idx == 4) idx = 0;
                }
                if (dir.get(time).equals("L")) {
                    idx--;
                    if (idx == -1) idx = 3;
                }
            }
        }
        return time;
    }

    static boolean isFinish(int x, int y) {
        if (x < 1 || x >= N + 1 || y < 1 || y >= N + 1) {
            return true;
        }
        for (Point s: snake) {
            if (x == s.x && y == s.y) return true;
        }
        return false;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}

/*
   1. 사과를 먹으면 뱀 길이가 늘어남.
   2. 벽 or 자기자신과 부딪히면 게임 끝 ---> 게임 종료 조건
   3. 보드 상하좌우 벽
   4. 게임 시작시 뱀 좌표(0, 0) && 뱀 길이 1 && 뱀 방향 오른쪽

   이동 규칙
   1. 뱀은 몸길이를 늘려 머리를 다음칸에 위치시킨다.
   2. 이동한 칸에 사과가 있다면 그 칸에 사과는 없어지고 꼬리는 움직이지 않음
   3. 이동한 칸에 사과가 없다면, 몸길이를 줄여 꼬리가 위치한 칸을 비운다. 몸길이는 변하지 않는다.
 */