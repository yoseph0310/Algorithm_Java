package Programmers.LV2;

public class 피로도 {
    int answer = 0;
    boolean[] visited;

    public int solution(int k, int[][] dungeons) {
        visited = new boolean[dungeons.length];

        adventure(k, 0, dungeons);

        return answer;
    }

    void adventure(int k, int depth, int[][] dungeons) {
        for (int i = 0; i < dungeons.length; i++) {
            if (!visited[i] && dungeons[i][0] <= k) {
                visited[i] = true;
                adventure(k - dungeons[i][1], depth+1, dungeons);
                visited[i] = false;
            }
        }

        answer = Math.max(answer, depth);
    }
}
