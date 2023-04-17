package Programmers.LV2;

public class 배달 {

    static final int MAX = 500001;

    public static int solution(int N, int[][] road, int K) {
        int answer = 0;

        int[][] board = new int[N + 1][N + 1];

        // 최댓값으로 초기화
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                if (i == j) continue;
                board[i][j] = MAX;
            }
        }
        // 간선 weight 정보 저장
        for (int i = 0; i < road.length; i++) {
            int a = road[i][0];
            int b = road[i][1];
            int w = road[i][2];

            if (board[a][b] > w) {
                board[a][b] = w;
                board[b][a] = w;
            }
        }

        // 거리 배열 초기화 (1번 마을에서 갈 수 있는 마을들의 거리값을 넣는다.)
        int[] dist = new int[N + 1];
        for (int i = 2; i <= N; i++) {
            dist[i] = (dist[i] == 0) ? MAX : board[1][i];
        }

        boolean[] visited = new boolean[N + 1];
        visited[1] = true;

        for (int i = 1; i <= N - 1; i++) {
            int min_idx = 1;
            int min_value = MAX;

            for (int j = 2; j <= N; j++) {
                if (!visited[j] && dist[j] < min_value) {
                    min_value = dist[j];
                    min_idx = j;
                }
            }

            visited[min_idx] = true;

            for (int j = 2; j <= N; j++) {
                if (dist[j] > dist[min_idx] + board[min_idx][j]) {
                    dist[j] = dist[min_idx] + board[min_idx][j];
                }
            }
        }

        for (int i = 1; i <= N; i++) {
            if (dist[i] <= K) answer++;
        }

        return answer;
    }

    public static void main(String[] args) {
        int N1 = 5;
        int[][] road1 = {{1,2,1},{2,3,3},{5,2,2},{1,4,2},{5,3,1},{5,4,2}};
        int K1 = 3;

        System.out.println(solution(N1, road1, K1));
    }
}
