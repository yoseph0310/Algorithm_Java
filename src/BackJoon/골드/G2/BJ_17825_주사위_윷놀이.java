package BackJoon.골드.G2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_17825_주사위_윷놀이 {

    static int ans;
    static int[] dice;      // 주사위 입력 배열
    static int[] gamePiece; // cnt 번째 주사위를 돌렸을 때 i 번의 말이라는 정보를 담는 gamePiece

    static int[] map = {
            // 가장 외곽
            0, 2, 4, 6, 8, 10, 12, 14, 16, 18, 20, 22, 24, 26, 28, 30, 32, 34, 36, 38, 40, 0,   // 0 ~ 21
            // 0 -> 10 -> 25 -> 40 -> 도착
            10, 13, 16, 19, 25, 30, 35, 40, 0,                                                  // 22 ~ 30
            // 0 -> 20 -> 25 -> 40 -> 도착
            20, 22, 24, 25, 30, 35, 40, 0,                                                      // 31 ~ 38
            // 0 -> 30 -> 25 -> 40 -> 도착
            30, 28, 27, 26, 25, 30, 35, 40, 0                                                   // 39 ~ 47
    };

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        dice = new int[10];
        gamePiece = new int[10];
        ans = 0;

        for (int i = 0; i < 10; i++) {
            dice[i] = Integer.parseInt(st.nextToken());
        }

        perm(0);
        System.out.println(ans);
    }

    static void perm(int cnt) {
        if (cnt == 10) {
            playGame();
            return;
        }

        for (int i = 0; i < 4; i++) {
            gamePiece[cnt] = i;
            perm(cnt + 1);
        }
    }

    static void playGame() {
        boolean[] visited = new boolean[map.length];
        int score = 0;
        int[] p = new int[4];

        for (int i = 0; i < 10; i++) {
            int nowDice = dice[i];
            int nowPiece = gamePiece[i];

            if (isFinished(p[nowPiece])) return;

            int next = nextPoint(p[nowPiece], nowDice);
            if (isFinished(next)) {
                setVisited(visited, p[nowPiece], false);
                p[nowPiece] = next;
                continue;
            }

            if (visited[next]) return;
            setVisited(visited, p[nowPiece], false);
            setVisited(visited, next, true);

            p[nowPiece] = next;
            score += map[p[nowPiece]];
        }

        ans = Math.max(ans, score);
    }

    static void setVisited(boolean[] visited, int idx, boolean check){
        visited[idx] = check;
    }

    static int nextPoint(int nowIdx, int dice) {
        int nextIdx = nowIdx + dice;

        if (nowIdx < 21) {
            if (nextIdx >= 21) nextIdx = 21;
        } else if (nowIdx < 30) {
            if (nextIdx >= 30) nextIdx = 30;
        } else if (nowIdx < 38) {
            if (nextIdx >= 38) nextIdx = 38;
        } else if (nowIdx < 47) {
            if (nextIdx >= 47) nextIdx = 47;
        }

        if (nextIdx == 5) return 22;
        if (nextIdx == 10) return 31;
        if (nextIdx == 15) return 29;

        return nextIdx;
    }

    static boolean isFinished(int idx) {
        return idx == 21 || idx == 30 || idx == 38 || idx == 47;
    }
}
