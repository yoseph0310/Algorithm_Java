package CodeTree.Samsung;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class A_2023_상반기_1번_포탑부수기_정답 {

    static int N, M, K;
    static int[] ans;
    // 보드 정보
    static int[][] board;
    // 이번 턴에 공격과 관련이 있었는가?
    static boolean[][] isAttacked;
    // 마지막에 공격한 턴은 언제인가?
    static int[][] lastAttack;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        lastAttack = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                int power = Integer.parseInt(st.nextToken());

                board[i][j] = power;
            }
        }

        progress();

        System.out.println(board[ans[0]][ans[1]]);
    }

    static void progress() {
        for (int turn = 1; turn < K + 1; turn++) {
            if (isFinish()) {
                break;
            }

            int[] attacker = selectAttacker();                  // 가장 약한 포탑 선정
            int[] target = selectTarget();                      // 가장 강한 포탑 선정
            isAttacked = new boolean[N][M];                     // 공격당한 좌표를 위한 boolean 2차원 배열 생성

            board[attacker[0]][attacker[1]] += (N + M);         // 공격 포탑의 공격력을 N + M 만큼 증가
            lastAttack[attacker[0]][attacker[1]] = turn;        // 공격 포탑의 최근 공격 턴을 기록
            isAttacked[attacker[0]][attacker[1]] = true;        // 공격과 관련되었으므로 true 로 기록

            if (!possibleLaser(attacker, target)) {                             // 레이저 공격이 가능한지 판단하고 불가하다면 폭탄으로 공격
                bomb(attacker, target);
            }

            for (int i = 0; i < N; i++) {                       // 공격받지 않았고 0(무너짐) 이 아니면 1씩 증가
                for (int j = 0; j < M; j++) {
                    if (!isAttacked[i][j] && board[i][j] != 0) {
                        board[i][j]++;
                    }
                }
            }
        }

        ans = selectTarget();
    }

    static int[] selectAttacker() {
        int[] attacker = new int[2];

        int minValue = Integer.MAX_VALUE;
        int maxTurn = -1;
        int minX = 0;
        int minY = 0;

        for (int sum = N + M - 2; sum > -1; sum--) {
            for (int j = M - 1; j > -1; j--) {
                int i = sum - j;

                if (i < 0 || i >= N) continue;
                if (board[i][j] == 0) continue;

                if (minValue > board[i][j]) {
                    minValue = board[i][j];
                    maxTurn = lastAttack[i][j];
                    minX = i;
                    minY = j;
                } else if (minValue == board[i][j] && maxTurn < lastAttack[i][j]) {
                    minValue = board[i][j];
                    maxTurn = lastAttack[i][j];
                    minX = i;
                    minY = j;
                }
            }
        }

        attacker[0] = minX;
        attacker[1] = minY;

        return attacker;
    }

    static int[] selectTarget() {
        int[] target = new int[2];

        int maxValue = -1;
        int minTurn = Integer.MAX_VALUE;
        int maxX = 0;
        int maxY = 0;

        for (int sum = 0; sum < N + M - 1; sum++) {
            for (int j = 0; j < M; j++) {
                int i = sum - j;

                if (i < 0 || i >= N) continue;
                if (board[i][j] == 0) continue;

                if (maxValue < board[i][j]) {
                    maxValue = board[i][j];
                    minTurn = lastAttack[i][j];
                    maxX = i;
                    maxY = j;
                } else if (maxValue == board[i][j] && minTurn > lastAttack[i][j]) {
                    maxValue = board[i][j];
                    minTurn = lastAttack[i][j];
                    maxX = i;
                    maxY = j;
                }
            }
        }

        target[0] = maxX;
        target[1] = maxY;

        return target;
    }

    static boolean possibleLaser(int[] attacker, int[] target) {
        Queue<int[]> q = new LinkedList<>();
        boolean[][] visited = new boolean[N][M];
        int[][][] come = new int[N][M][2];          // x행 y열이 어디로부터 왔는가? 0 : x, 1: y

        // 우하좌상
        int[] dx = {0, 1, 0, -1};
        int[] dy = {1, 0, -1, 0};

        q.add(attacker);
        visited[attacker[0]][attacker[1]] = true;

        while (!q.isEmpty()) {
            int[] cur = q.poll();

            int x = cur[0];
            int y = cur[1];

            for (int d = 0; d < 4; d++) {
                int nx = (x + dx[d] + N) % N;
                int ny = (y + dy[d] + M) % M;

                if (visited[nx][ny]) continue;
                if (board[nx][ny] == 0) continue;

                come[nx][ny][0] = x;
                come[nx][ny][1] = y;
                visited[nx][ny] = true;
                q.add(new int[]{nx, ny});
            }
        }

        if (!visited[target[0]][target[1]]) return false;

        int[] back = target;

        while (back[0] != attacker[0] || back[1] != attacker[1]) {
            int power = board[attacker[0]][attacker[1]] / 2;

            if (back[0] == target[0] && back[1] == target[1]) {
                power = board[attacker[0]][attacker[1]];
            }

            board[back[0]][back[1]] = attack(back[0], back[1], power);
            back = come[back[0]][back[1]];
        }

        return true;
    }

    static int attack(int x, int y, int power) {
        isAttacked[x][y] = true;
        board[x][y] = Math.max(0, board[x][y] - power);
        return board[x][y];
    }

    static void bomb(int[] attacker, int[] target) {
        int[] dx = {0, -1, 0, 1, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, 1, 0, -1, -1, 1, -1, 1};

        for (int d = 0; d < 9; d++) {
            int nx = (target[0] + dx[d] + N) % N;
            int ny = (target[1] + dy[d] + M) % M;

            if (nx == attacker[0] && ny == attacker[1]) continue;

            int power = board[attacker[0]][attacker[1]] / 2;

            if (nx == target[0] && ny == target[1]) power = board[attacker[0]][attacker[1]];

            board[nx][ny] = attack(nx, ny, power);
        }
    }

    // 보드에서 포탑이 한개만 남으면 종료이므로 cnt == 1이 true 이도록 반환
    static boolean isFinish() {
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i][j] != 0) cnt++;
            }
        }

        return cnt == 1;
    }
}