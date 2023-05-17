package Programmers.LV2;
import java.util.*;

public class 전력망을_둘로_나누기 {
    int[][] board;
    boolean[] visited;
    int[] cnt;
    int cntIdx;
    int answer = Integer.MAX_VALUE;

    public int solution(int n, int[][] wires) {

        // 1. wires의 요소에서 하나씩 제거 하면서 로직을 진행해본다.
        for (int i = 0; i < wires.length; i++) {
            board = new int[n+1][n+1];          // 연결 정보
            cnt = new int[2];       // 두 전력망의 송전탑 개수를 기록해둘 cnt 배열
            cntIdx = 0;

            for (int j = 0; j < wires.length; j++) {
                if (i == j) continue;

                int x = wires[j][0];
                int y = wires[j][1];

                board[x][y] = 1;
                board[y][x] = 1;

            }

            // 하나를 자른 상태인 board에서 좌표값이 1이면 bfs탐색을 시작한다.
            // bfs안에서는 방문하지 않고 1인 값들을 찾아가며 cnt를 값을 기록한다.
            // 두 값을 뺀 절대값을 min에 갱신해나간다.
            start(n, wires);
        }

        return answer;
    }

    void start(int n, int[][] wires) {
        visited = new boolean[n+1];         // 송전탑 방문 정보

        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                bfs(i, n);
            }
        }

        int res = Math.abs(cnt[0] - cnt[1]);

        answer = Math.min(answer, res);
    }

    void bfs(int t, int n) {
        Queue<Integer> q = new LinkedList<>();
        q.add(t);
        visited[t] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (int i = 1; i <= n; i++) {
                if (board[cur][i] == 1 && !visited[i]) {
                    q.add(i);
                    visited[i] = true;
                    cnt[cntIdx]++;
                }
            }
        }

        cntIdx++;
    }
}
