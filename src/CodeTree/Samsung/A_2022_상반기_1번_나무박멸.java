package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_2022_상반기_1번_나무박멸 {

    static int N, M, K, C;
    static int[][] tree;                // 나무 위치 및 그루 수 정보. -1은 벽, 0은 빈칸. 즉 0 초과는 나무가 있는 것
    static int[][] addTree;             // 번식할 나무 정보.
    static int[][] jecho;               // 제초제 정보. C 로 저장되며 1년이 지날때마다 1씩 줄어든다.

    static int[] tdx = {-1, 0, 1, 0};
    static int[] tdy = {0, 1, 0, -1};

    static int[] jdx = {-1, 1, -1, 1};
    static int[] jdy = {-1, -1, 1, 1};

    static int ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        tree = new int[N][N];
        addTree = new int[N][N];
        jecho = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                tree[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        solve();
    }

    static void solve() {
        // TODO M 년 동안 진행.
        for (int i = 0; i < M; i++) {

            growTree();
            spreadTree();
            deleteJecho();
            spreadJecho();

        }

        System.out.println(ans);
    }

    static void spreadJecho() {

        int max = 0;
        int maxX = 0;
        int maxY = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tree[i][j] <= 0) continue;

                int cnt = tree[i][j];        // 박멸 나무 수 세기

                for (int d = 0; d < 4; d++) {
                    int nx = i;
                    int ny = j;

                    for (int k = 0; k < K; k++) {
                        nx = nx + jdx[d];
                        ny = ny + jdy[d];

                        if (isNotBoundary(nx, ny)) break;
                        if (tree[nx][ny] <= 0) break;

                        cnt += tree[nx][ny];
                    }
                }

                if (max < cnt) {
                    max = cnt;
                    maxX = i;
                    maxY = j;
                }
            }
        }

        ans += max;

        if (tree[maxX][maxY] > 0) {
            jecho[maxX][maxY] = C;
            tree[maxX][maxY] = 0;

            for (int d = 0; d < 4; d++) {
                int nx = maxX;
                int ny = maxY;

                for (int k = 0; k < K; k++) {
                    nx = nx + jdx[d];
                    ny = ny + jdy[d];

                    if (isNotBoundary(nx, ny)) break;
                    if (tree[nx][ny] < 0) break;
                    if (tree[nx][ny] == 0) {
                        jecho[nx][ny] = C;
                        break;
                    }

                    jecho[nx][ny] = C;
                    tree[nx][ny] = 0;
                }
            }
        }

    }

    static void growTree() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tree[i][j] <= 0) continue;

                int cnt = 0;
                for (int d = 0; d < 4; d++) {
                    int nx = i + tdx[d];
                    int ny = j + tdy[d];

                    if (isNotBoundary(nx, ny)) continue;
                    if (tree[nx][ny] > 0) {
                        cnt++;
                    }
                }
                tree[i][j] += cnt;
            }
        }
    }

    static void spreadTree() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                addTree[i][j] = 0;
            }
        }


        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (tree[i][j] <= 0) continue;

                int cnt = 0;        // 주변에 번식 가능한 칸을 센다. (벽이 아니고, 나무 없어야하고 제초제 없어야함.)

                for (int d = 0; d < 4; d++) {
                    int nx = i + tdx[d];
                    int ny = j + tdy[d];

                    if (isNotBoundary(nx, ny)) continue;
                    if (jecho[nx][ny] > 0) continue;
                    if (tree[nx][ny] == 0) {
                        cnt++;
                    }
                }

                for (int d = 0; d < 4; d++) {
                    int nx = i + tdx[d];
                    int ny = j + tdy[d];

                    if (isNotBoundary(nx, ny)) continue;
                    if (jecho[nx][ny] > 0) continue;
                    if (tree[nx][ny] == 0) {
                        addTree[nx][ny] += tree[i][j] / cnt;
                    }
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                tree[i][j] += addTree[i][j];
            }
        }
    }

    static void deleteJecho() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (jecho[i][j] > 0) jecho[i][j] -= 1;
            }
        }

    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static void printBoard(int[][] board) {
        System.out.println();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
