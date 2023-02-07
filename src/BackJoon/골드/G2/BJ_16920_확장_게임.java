package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class BJ_16920_확장_게임 {

    static int N, M, P;
    static int[] castle;
    static int[] dist;
    static char[][] map;

    static Queue<Node>[] q;

    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        P = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        castle = new int[P + 1];
        dist = new int[P + 1];
        q = new LinkedList[P+1];

        for (int i = 1; i <= P; i++) {
            q[i] = new LinkedList<>();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= P; i++) {
            dist[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = input.charAt(j);

                if (c >= '0' && c <= '9') {
                    int player = c - '0';

                    castle[player]++;                   // 플레이어의 성 개수 증가
                    q[player].add(new Node(i, j));      // 플레이어의 확장을 해나갈 큐. 각 플레이어의 초기 위치를 큐에 넣는다.
                }
                map[i][j] = c;                          // 맵에 정보 입력
            }
        }

        int player = 1;
        int round = 0;

        while(true) {
            int maxDist = dist[player];                             // 플레이어가 갈 수 있는 최대 거리
            int cnt = expandCastle(q[player], maxDist, player);     // 성 개수

            castle[player] += cnt;
            round += cnt;

            player++;
            if (player == P + 1) {
                if (round == 0) break;
                player = 1;
                round = 0;
            }
        }

        for (int i = 1; i <= P; i++) {
            System.out.print(castle[i] + " ");
        }
    }

    static int expandCastle(Queue<Node> q, int maxDist, int player) {
        int cnt = 0;
        int dist = 1;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Node cur = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (isBoundary(nx, ny)) {
                        if (map[nx][ny] == '.') {
                            q.add(new Node(nx, ny));
                            map[nx][ny] = (char)(player + '0');
                            cnt++;
                        }
                    }
                }
            }
            dist++;
            if (dist > maxDist) break;
        }
        return cnt;
    }

    static boolean isBoundary(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static class Node {
        int x, y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
