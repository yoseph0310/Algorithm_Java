package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_9328_열쇠 {

    static int H, W, cnt;
    static char[][] board;
    static boolean[][] visited;
    static ArrayList<Point>[] doorList;

    static boolean[] hasKey;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            H = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            board = new char[H + 2][W + 2];
            visited = new boolean[H + 2][W + 2];
            doorList = new ArrayList[26];
            hasKey = new boolean[26];

            cnt = 0;

            for (int i = 0; i < 26; i++) {
                doorList[i] = new ArrayList<>();
            }

            for (int h = 0; h < H+2; h++) {
                for (int w = 0; w < W+2; w++) {
                    board[h][w] = '.';
                }
            }

            for (int i = 1; i <= H; i++) {
                String input = br.readLine();
                for (int j = 1; j <= W; j++) {
                    board[i][j] = input.charAt(j - 1);
                }
            }
            String keyInput = br.readLine();
            if (!keyInput.equals("0")) {
                for (int i = 0; i < keyInput.length(); i++) {
                    int keyIdx = keyInput.charAt(i) - 'a';
                    hasKey[keyIdx] = true;
                }
            }

            bfs();
            System.out.println(cnt);
        }

    }
    static void bfs() {
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(0, 0));
        visited[0][0] = true;

        while(!q.isEmpty()) {
            Point cur = q.poll();

            int cx = cur.x;
            int cy = cur.y;

            for (int d = 0; d < 4; d++) {
                int nx = cx + dx[d];
                int ny = cy + dy[d];

                if (isBoundary(nx, ny)) {
                    if (!visited[nx][ny] && board[nx][ny] != '*') {
                        if (board[nx][ny] - 'A' >= 0 && board[nx][ny] - 'A' <= 25) {
                            if (hasKey[board[nx][ny] - 'A']) {
                                board[nx][ny] = '.';
                                visited[nx][ny] = true;
                                q.add(new Point(nx, ny));
                            } else {
                                doorList[board[nx][ny] - 'A'].add(new Point(nx, ny));
                            }
                        } else if (board[nx][ny] - 'a' >= 0 && board[nx][ny] - 'a' <= 25) {
                            hasKey[board[nx][ny] - 'a'] = true;
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny));

                            for (int i = 0; i <= 25; i++) {
                                if (doorList[i].size() != 0 && hasKey[i]) {
                                    for (int j = 0; j < doorList[i].size(); j++) {
                                        Point doorPoint = doorList[i].get(j);
                                        board[doorPoint.x][doorPoint.y] = '.';
                                        visited[doorPoint.x][doorPoint.y] = true;
                                        q.add(new Point(doorPoint.x, doorPoint.y));
                                    }
                                }
                            }
                        } else if (board[nx][ny] == '$') {
                            System.out.println(cx + " ," + cy);
                            cnt++;
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny));
                        } else {
                            visited[nx][ny] = true;
                            q.add(new Point(nx, ny));
                        }

                    }

                }
            }
        }
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < H + 2 && 0 <= y && y < W + 2;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static void printBoard(char[][] board) {
        System.out.println();
        for (int i = 0; i <= H + 1; i++) {
            for (int j = 0; j <= W + 1; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

}
