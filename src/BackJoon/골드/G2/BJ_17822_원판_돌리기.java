package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class BJ_17822_원판_돌리기 {

    static int N, M, T, ans;
    static int[][] circle;
    static HashSet<Point> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());

        circle = new int[N + 1][M];    // xi 의 배수 번쩨 원판을 돌려하기 때문에 입력값 그대로 인덱스를 쓴다.

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                circle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int x, d, k;
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());

            x = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());

            // 회전
            rotate(x, d, k);

            // 인접수 찾아 삭제하기
            findSameNumber();

            if (set.size() != 0) {
                for (Point p: set) {
                    circle[p.x][p.y] = 0;
                }
            } else {
                calculate();
            }

        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                ans += circle[i][j];
            }
        }

        System.out.println(ans);
    }

    static void calculate() {
        double sum = 0;
        double num = 0;
        double avg = 0;

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] != 0){
                    sum += circle[i][j];
                    num++;
                }
            }
        }

        if (num != 0) {
            avg = sum / num;
        }

        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0) continue;

                if (circle[i][j] > avg) circle[i][j]--;
                else if (circle[i][j] < avg) circle[i][j]++;
            }
        }
    }

    static void findSameNumber() {
        set.clear();

        // 같은 안 원안에서의 인접수 찾기
        for (int i = 1; i <= N; i++) {
            for (int j = 0; j < M - 1; j++) {
                if (circle[i][j] == 0) continue;

                if (j == 0) {
                    if (circle[i][0] == circle[i][M - 1]) {
                        set.add(new Point(i, 0));
                        set.add(new Point(i, M - 1));
                    }
                }

                if (circle[i][j] == circle[i][j + 1]) {
                    set.add(new Point(i, j));
                    set.add(new Point(i, j + 1));
                }
            }
        }

        // 다른 두 원의 접점에서 인접수 찾기
        for (int i = 1; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (circle[i][j] == 0) continue;

                if (circle[i][j] == circle[i + 1][j]) {
                    set.add(new Point(i, j));
                    set.add(new Point(i + 1, j));

                }
            }
        }



    }

    static void rotate(int x, int d, int k) {
        // 원판을 회전시킨다.
        for (int i = 1; i <= N; i++) {
            // x의 배수인 원판을
            if (i % x == 0) {

                // d 방향(0 - 시계, 1 - 반시계)으로 k 번 회전
                for (int j = 0; j < k; j++) {
                    if (d == 0) {

                        int temp = circle[i][M - 1];

                        for (int l = M - 1; l > 0; l--) {
                            circle[i][l] = circle[i][l - 1];
                        }
                        circle[i][0] = temp;
                    } else {

                        int temp = circle[i][0];

                        for (int l = 0; l < M - 1; l++) {
                            circle[i][l] = circle[i][l + 1];
                        }
                        circle[i][M - 1] = temp;
                    }
                }

            }
        }

    }

    static class Point {
        int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
