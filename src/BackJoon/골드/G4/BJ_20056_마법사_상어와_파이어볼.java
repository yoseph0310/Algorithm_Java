package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_20056_마법사_상어와_파이어볼 {

    static int N, M, K;
    static Queue<FireBall>[][] map;
    static List<FireBall> fireballList;

    // 상 우상 우 우하 하 좌하 좌 좌상
    static int[] dx = {-1, -1, 0, 1, 1, 1, 0, -1};
    static int[] dy = {0, 1, 1, 1, 0, -1, -1, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());       // 격자의 크기
        M = Integer.parseInt(st.nextToken());       // 파이어볼 개수
        K = Integer.parseInt(st.nextToken());       // 이동명령 개수

        map = new Queue[N][N];
        fireballList = new ArrayList<>();

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()) - 1;
            int y = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());

            FireBall fireBall = new FireBall(x, y, m, s, d);
            fireballList.add(fireBall);      // 전체 파이어볼 정보를 담기 위해 리스트에 저장
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = new LinkedList<>();
            }
        }

        solve();
    }

    static void solve() {
        // K 번의 명령 수행
        for (int i = 0; i < K; i++) {
            moveFireBall();
            combineAndDivide();
        }
        // 3. 격자를 돌면서 질량의 합을 구한다.
        System.out.println(checkMassSum());
    }

    static void moveFireBall() {
        for (FireBall f: fireballList) {
            f.x = (N + f.x + dx[f.d] * (f.s % N)) % N;
            f.y = (N + f.y + dy[f.d] * (f.s % N)) % N;

            map[f.x][f.y].add(f);
        }
    }

    static void combineAndDivide() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (map[i][j].size() >= 2) {
                    int m_sum = 0, s_sum = 0;
                    int cnt_sum = map[i][j].size();
                    boolean odd = true, even = true;

                    while (!map[i][j].isEmpty()) {
                        FireBall f = map[i][j].poll();

                        m_sum += f.m;
                        s_sum += f.s;

                        if (f.d % 2 == 0) odd = false;
                        else even = false;

                        fireballList.remove(f);
                    }

                    // 질량 : (질량합 / 5)
                    int nm = m_sum / 5;
                    if (nm == 0) continue;

                    // 속력 : (속력합 / 파이어볼 개수)
                    int ns = s_sum / cnt_sum;

                    if (odd || even) {
                        for (int k = 0; k < 8; k+=2) {
                            fireballList.add(new FireBall(i, j, nm, ns, k));
                        }
                    } else {
                        for (int k = 1; k < 8; k+=2) {
                            fireballList.add(new FireBall(i, j, nm, ns, k));
                        }
                    }
                } else {
                    map[i][j].clear();
                }
            }
        }

    }

    static int checkMassSum() {
        int sum = 0;

        for (FireBall f: fireballList) {
            sum += f.m;
        }

        return sum;
    }

    static class FireBall {
        int x, y, m, s, d;

        public FireBall(int x, int y, int m, int s, int d) {
            this.x = x;
            this.y = y;
            this.m = m;
            this.s = s;
            this.d = d;
        }
    }
}
