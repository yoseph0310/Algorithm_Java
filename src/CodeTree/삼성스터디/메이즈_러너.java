package CodeTree.삼성스터디;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 메이즈_러너 {

    static int N, M, K, moveCnt;
    static int[][] maze;        // 0 : 빈칸, -1 ~ -9 : 벽 내구도를 나타낸다, -10 : 출구,  1 이상의 자연수 해당 칸에 존재하는 참가자 수

    // 참가자 이동 가능 우선 순위 델타 배열 - 상 하 좌 우
    static int[] sDx = {-1, 1, 0, 0};
    static int[] sDy = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        maze = new int[N + 1][N + 1];

        // 미로 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                maze[i][j] = -Integer.parseInt(st.nextToken());
            }
        }

        // 참가자 입력
        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            maze[x][y]++;
        }

        // 출구 입력
        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        maze[x][y] = -10;

        // 게임 진행
        solve();

        // 정답 출력
        int[] exit = findExit();
        System.out.println(moveCnt);
        System.out.println(exit[0] + " " + exit[1]);

    }

    static void solve() {
        while (K-- > 0) {
            // 참가자 이동
            moveParticipants();

            // 게임 종료 여부
            if (isFinished()) break;

            // 회전 시키기
            round();
        }
    }

    // 모든 참가자 한칸씩 이동
    static void moveParticipants() {
        int[][] newMaze = new int[N+1][N+1];
        int[] exit = findExit();
        int ex = exit[0];
        int ey = exit[1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                // 해당 좌표에 벽이나 출구가 있다면 그대로 복사
                if (maze[i][j] < 0) {
                    newMaze[i][j] = maze[i][j];
                    continue;
                }

                // 빈 공간이면 continue
                if (maze[i][j] == 0) continue;

                // 현재 참가자 - 출구 거리
                int curDist = Math.abs(i - ex) + Math.abs(j - ey);
                int minDist = curDist;
                int minX = 0;
                int minY = 0;

                for (int d = 0; d < 4; d++) {
                    int nx = i + sDx[d];
                    int ny = j + sDy[d];

                    if (nx < 1 || ny < 1 || nx > N || ny > N) continue;         // 격자 벗어나면 continue
                    if (-9 <= maze[nx][ny] && maze[nx][ny] <= -1) continue;     // 벽이면 continue

                    int dist = Math.abs(nx - ex) + Math.abs(ny - ey);
                    if (minDist > dist) {
                        minDist = dist;
                        minX = nx;
                        minY = ny;
                    }
                }

                // 이동 불가라면
                if (minDist == curDist) {
                    newMaze[i][j] += maze[i][j];        // 그대로 유지
                    continue;
                }

                moveCnt += maze[i][j];      // 참가자 이동 횟수 증가

                // 출구에 도달했다면
                if (maze[minX][minY] == -10) continue;

                newMaze[minX][minY] += maze[i][j];
            }
        }

        // 미로에 복사
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                maze[i][j] = newMaze[i][j];
            }
        }

    }

    // 선택된 정사각형 시계방향 회전
    static void round() {
        // 정사각형 크기 결정
        // 정사각형 크기는 출구와 가장 가까운 참가자 간의 거리가 된다.
        int minDist = Integer.MAX_VALUE;
        int[] exit = findExit();
        int ex = exit[0];
        int ey = exit[1];

        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] <= 0) continue;          // 참가자가 없다면 continue

                int dist = Math.max(Math.abs(i - ex), Math.abs(j - ey));
                minDist = Math.min(minDist, dist);
            }
        }

        // 정사각형 위치 결정
        int minX = 0;               // 정사각형의 좌상단 좌표가 될 minX, minY
        int minY = 0;
        for (int i = 1; i <= N - minDist; i++) {
            for (int j = 1; j <= N - minDist; j++) {
                boolean flagExit = false;
                boolean flagPerson = false;

                for (int k = i; k <= i + minDist; k++) {
                    for (int l = j; l <= j + minDist; l++) {
                        if (maze[k][l] == -10) flagExit = true;
                        if (maze[k][l] > 0) flagPerson = true;
                    }
                }

                if (flagExit && flagPerson) {
                    minX = i;
                    minY = j;
                    break;
                }
            }
            // 반복문 탈출조건
            if (minX != 0) break;
        }

        // 선택된 정사각형 회전 시키기
        roundSquare(minX, minY, minDist);
    }

    static void roundSquare(int x, int y, int d) {
        // 격자가 1 부터 시작하고 좌상단이 맨 끝줄일 경우를 대비하여 최소 12 사이즈는 필요
        int[][] a = new int[12][12];
        int[][] b = new int[12][12];

        for (int i = x; i <= x + d; i++) {
            for (int j = y; j <= y + d; j++) {
                a[i - x + 1][j - y + 1] = maze[i][j];
            }
        }

        int n = d + 1;
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                // 벽 내구도
                if (-9 <= a[i][j] && a[i][j] <= -1) {
                    a[i][j]++;
                }
                b[j][n + 1 - i] = a[i][j];
            }
        }

        for (int i = x; i <= x + d; i++) {
            for (int j = y; j <= y + d; j++) {
                maze[i][j] = b[i - x + 1][j - y + 1];
            }
        }
    }

    // 게임 종료 여부 확인
    static boolean isFinished() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] > 0) return false;
            }
        }

        return true;
    }

    // 출구 찾기
    static int[] findExit() {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (maze[i][j] == -10) {
                    return new int[]{i, j};
                }
            }
        }
        return null;
    }

    static void printBoard(int[][] b) {
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}