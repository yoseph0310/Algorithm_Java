package Programmers.LV2;
import java.util.*;

public class 혼자_놀기의_달인 {
    boolean[] visited;
    int depth;

    public int solution(int[] cards) {
        int answer = 1;
        int len = cards.length;

        visited = new boolean[len+1];
        List<Integer> list = new ArrayList<>();

        for (int i = 0; i < len; i++) {
            if (!visited[i+1]) {
                depth = 1;
                visited[i+1] = true;
                bfs(cards[i], cards);
                list.add(depth);
            }
        }

        if (list.size() < 2) {
            return 0;
        }

        Collections.sort(list, Collections.reverseOrder());
        answer = list.get(0) * list.get(1);

        return answer;
    }

    void bfs(int x, int[] cards) {
        if (!visited[x]) {
            visited[x] = true;
            depth += 1;
            bfs(cards[x - 1], cards);
        }
    }
}
