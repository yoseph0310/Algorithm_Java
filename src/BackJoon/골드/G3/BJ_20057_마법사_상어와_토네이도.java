package BackJoon.골드.G3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20057_마법사_상어와_토네이도 {

    static int N;
    static int[][] map;
    // 좌 하 우 상
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {-1, 0, 1, 0};
    static int[] dc = {1, 1, 2, 2};

    // 모래가 퍼지는 x 방향
    static int[][] dsx = {{-1, 1, -2, -1, 1, 2, -1, 1, 0}, {-1, -1, 0, 0, 0, 0, 1, 1, 2},
            {1, -1, 2, 1, -1, -2, 1, -1, 0}, {1, 1, 0, 0, 0, 0, -1, -1, -2}};

    // 모래가 퍼지는 y 방향
    static int[][] dsy = {{1, 1, 0, 0, 0, 0, -1, -1, -2}, {-1, 1, -2, -1, 1, 2, -1, 1, 0},
            {-1, -1, 0, 0, 0, 0, 1, 1, 2}, {1, -1, 2, 1, -1, -2, 1, -1, 0}};

    static int[] sandRatio = {1, 1, 2, 7, 7, 2, 10, 10, 5};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = calOutSand(N/2, N/2);
        System.out.println(ans);
    }

    static int calOutSand(int x, int y) {
        int totalOutSand = 0;

        int cx = x;
        int cy = y;

        while (true) {
            for (int d = 0; d < 4; d++) {
                for (int moveCnt = 0; moveCnt < dc[d]; moveCnt++) {
                    // 현재 위치에서 이동
                    int nx = cx + dx[d];
                    int ny = cy + dy[d];

                    if (isNotBoundary(nx, ny)) {
                        return totalOutSand;
                    }

                    // 이동한 위치의 모래 뿌리기
                    int sand = map[nx][ny];
                    map[nx][ny] = 0;
                    int spreadTotal = 0;

                    for (int spread = 0; spread < 9; spread++) {
                        int sandX = nx + dsx[d][spread];
                        int sandY = ny + dsy[d][spread];
                        int spreadAmount = (sand * sandRatio[spread]) / 100;

                        if (isNotBoundary(sandX, sandY)) totalOutSand += spreadAmount;
                        else map[sandX][sandY] += spreadAmount;

                        spreadTotal += spreadAmount;
                    }

                    // 알파
                    int alphaX = nx + dx[d];
                    int alphaY = ny + dy[d];
                    int alphaAmount = sand - spreadTotal;       // 흩뿌려진 모래를 제외한 나머지

                    if (isNotBoundary(alphaX, alphaY)) totalOutSand += alphaAmount;
                    else map[alphaX][alphaY] += alphaAmount;

                    // 이동한 위치를 현재 위치로 업데이트
                    cx = nx;
                    cy = ny;
                }
            }

            // 이동횟수 업데이트
            for (int i = 0; i < 4; i++) {
                dc[i] += 2;
            }

        }
    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }
}
