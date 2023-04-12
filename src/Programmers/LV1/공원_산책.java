package Programmers.LV1;

import java.util.*;

class 공원_산책 {

    char[][] board;
    HashMap<String, Integer> map = new HashMap<>();

    int dogX = 0;
    int dogY = 0;

    int N = 0;
    int M = 0;

    // 북, 동, 남, 서
    int[] dx = {-1, 0, 1, 0};
    int[] dy = {0, 1, 0, -1};

    public int[] solution(String[] park, String[] routes) {

        N = park.length;
        M = park[0].length();

        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                char c = park[i].charAt(j);

                if (c == 'S') {
                    dogX = i;
                    dogY = j;
                }

                board[i][j] = c;
            }
        }

        map.put("N", 0);
        map.put("E", 1);
        map.put("S", 2);
        map.put("W", 3);

        for (int i = 0; i < routes.length; i++) {
            String[] info = routes[i].split(" ");

            String dir = info[0];
            int moveCnt = Integer.parseInt(info[1]);

            System.out.println(dir + " " + moveCnt);
            move(dogX, dogY, map.get(dir), moveCnt);
        }

        int[] answer = {dogX, dogY};
        return answer;
    }

    void move(int x, int y, int d, int moveCnt) {
        int nx = x;
        int ny = y;

        for (int i = 0; i < moveCnt; i++) {
            board[nx][ny] = 'O';

            nx += dx[d];
            ny += dy[d];

            if (isNotBoundary(nx, ny) || board[nx][ny] == 'X') {
                dogX = x;
                dogY = y;
                return;
            }

            dogX = nx;
            dogY = ny;

            board[dogX][dogY] = 'S';

        }

    }

    boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < M);
    }
}

/*
    "방향 거리" 형식으로 명령이 주어짐

    공원 벗어나는지 확인, 장애물 만나는지 확인

    격자 (0~H)(0~W)

*/