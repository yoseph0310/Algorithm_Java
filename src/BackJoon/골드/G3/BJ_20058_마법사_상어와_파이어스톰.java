package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_20058_마법사_상어와_파이어스톰 {

    static int N, Q, totalIce, land;
    static int[][] map;
    static int[] L;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());

        N = (int) Math.pow(2, N);
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        L = new int[Q];
        for (int i = 0; i < Q; i++) {
            L[i] = Integer.parseInt(st.nextToken());
        }

        play();
    }

    static void play() {
        // Q 만큼 반복
        for (int i = 0; i < Q; i++) {
            map = divide(L[i]);
            map = meltIce();
        }

        biggest();
        System.out.println(totalIce);
        System.out.println(land);
    }

    static int[][] divide(int L) {
        int[][] tmp = new int[N][N];
        L = (int) Math.pow(2, L);       // 2의 L 승 만큼 범위가 증가한다.

        for (int i = 0; i < N; i += L) {
            for (int j = 0; j < N; j += L) {
                rotate(i, j, L, tmp);
            }
        }

        return tmp;
    }

    static void rotate(int x, int y, int L, int[][] tmp) {
        for (int i = 0; i < L; i++) {
            for (int j = 0; j < L; j++) {
                tmp[x + i][y + j] = map[x + L - 1 - j][y + i];
            }
        }
    }

    // 원래 map 으로 하면 결과가 달라질 수 있으므로 조건 확인 시에는 map 의 상황으로 판단하고 동시에 tmp 를 사용하여 상황을 반영한다.
    static int[][] meltIce() {
        int[][] tmp = new int[N][N];
        for (int i = 0; i < N; i++) {
            tmp[i] = Arrays.copyOf(map[i], N);
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j] == 0) continue;

                int cnt = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (isBoundary(nx, ny) && map[nx][ny] > 0) {
                        cnt++;
                    }
                }

                if (cnt < 3) tmp[i][j]--;
            }
        }

        return tmp;
    }

    static void biggest() {
        Queue<Point> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                totalIce += map[i][j];

                if (map[i][j] == 0) continue;

                if (!visited[i][j] && map[i][j] > 0) {
                    q.add(new Point(i, j));
                    visited[i][j] = true;
                    int cnt = 1;

                    while (!q.isEmpty()) {
                        Point cur = q.poll();

                        for (int d = 0; d < 4; d++) {
                            int nx = cur.x + dx[d];
                            int ny = cur.y + dy[d];

                            if (isBoundary(nx, ny) && !visited[nx][ny] && map[nx][ny] > 0) {
                                q.add(new Point(nx, ny));
                                visited[nx][ny] = true;
                                cnt++;
                            }
                        }
                    }

                    land = Math.max(land, cnt);
                }
            }
        }

    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < N;
    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
