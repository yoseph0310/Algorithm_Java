package BackJoon.골드.G1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class BJ_19237_어른_상어 {

    static int N, M, K;
    static int[][] sharkBoard = new int[21][21];
    static int[][] smellBoard = new int[21][21];
    static int[][] period = new int[21][21];

    static Map<Integer, Shark> sharkMap = new HashMap<>();              // 번호의 맞는 상어 객체를 저장한 맵
    static int time = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                sharkBoard[i][j] = Integer.parseInt(st.nextToken());

                if (sharkBoard[i][j] > 0) {
                    Shark shark = new Shark();
                    shark.id = sharkBoard[i][j];
                    shark.x = i;
                    shark.y = j;

                    sharkMap.put(shark.id, shark);

                    smellBoard[i][j] = shark.id;
                    period[i][j] = K;
                }
            }
        }

        // 상어 초기 방향 저장
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            Shark shark = sharkMap.get(i + 1);
            shark.dir = Integer.parseInt(st.nextToken());
        }

        // 상어 방향 우선순위 저장
        for (int i = 0; i < M; i++) {
            Shark shark = sharkMap.get(i + 1);

            for (int j = 0; j < 4; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < 4; k++) {
                    shark.priority[j+1][k+1] = Integer.parseInt(st.nextToken());
                }
            }
        }

        play();
    }

    static void play() {
        while (time++ < 1000) {
            moveShark();
            decreaseSmell();
            createSmell();

            if (sharkMap.size() == 1) {
                System.out.println(time);
                return;
            }
        }

        System.out.println(-1);
    }

    static void moveShark() {
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        Queue<Integer> willRemove = new LinkedList<>();

        for (Integer id: sharkMap.keySet()) {
            Set<Integer> noSmellSet = new HashSet<>();
            Set<Integer> mySmellSet = new HashSet<>();

            Shark s = sharkMap.get(id);

            for (int d = 0; d < 4; d++) {
                int nx = s.x + dx[d];
                int ny = s.y + dy[d];

                if (isNotBoundary(nx, ny)) continue;

                // 냄새가 없으면 noSmellSet 에, 자신의 냄새면 mySmellSet 에 방향 저장
                if (smellBoard[nx][ny] == 0) noSmellSet.add(d + 1);
                else if (smellBoard[nx][ny] == s.id) mySmellSet.add(d + 1);
            }

            // 냄새 없는 곳 부터 스캔하여 다음 좌표를 찾는데 0이라면 없는 것이므로 자기 냄새 있는 곳에서 다시 탐색
            int nextDir = s.findNextDir(noSmellSet);

            if (nextDir == 0) nextDir = s.findNextDir(mySmellSet);

            // 상어 이동
            sharkBoard[s.x][s.y] = 0;
            if (nextDir == 1) s.x -= 1;
            else if (nextDir == 2) s.x += 1;
            else if (nextDir == 3) s.y -= 1;
            else if (nextDir == 4) s.y += 1;

            // 이동할 위치에 상어 있으면 작은 번호가 승리
            if (sharkBoard[s.x][s.y] == 0 || s.id < sharkBoard[s.x][s.y]) {
                sharkBoard[s.x][s.y] = s.id;
                s.dir = nextDir;
            } else {
                willRemove.add(s.id);
            }
        }

        while (!willRemove.isEmpty()) {
            sharkMap.remove(willRemove.poll());
        }
    }

    static boolean isNotBoundary(int x, int y) {
        return !(0 <= x && x < N && 0 <= y && y < N);
    }

    static void decreaseSmell() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (period[i][j] == 0) continue;

                period[i][j]--;

                if (period[i][j] == 0) {
                    smellBoard[i][j] = 0;
                }
            }
        }
    }

    static void createSmell() {
        for (Integer id: sharkMap.keySet()) {
            Shark shark = sharkMap.get(id);

            smellBoard[shark.x][shark.y] = shark.id;
            period[shark.x][shark.y] = K;
        }
    }

    static class Shark {
        int id, x, y, dir;
        int[][] priority = new int[5][5];

        Shark() {}

        int findNextDir(Set<Integer> candidates) {
            for (int i = 1; i < 5; i++) {
                if (candidates.contains(priority[dir][i])) {
                    return priority[dir][i];
                }
            }

            return 0;
        }

    }
}
