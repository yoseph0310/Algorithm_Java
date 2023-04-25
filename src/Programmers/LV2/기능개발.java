package Programmers.LV2;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class 기능개발 {
    public int[] solution(int[] progresses, int[] speeds) {
        ArrayList<Integer> list = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < progresses.length; i++) {
            // 남은 작업일수를 큐에 넣는다. 나눠떨어지지 않으면 나머지를 처리하는데도 하루가 걸리므로 +1을 해준다.
            if ((100 - progresses[i]) % speeds[i] == 0) {
                q.add((100 - progresses[i]) / speeds[i]);
            } else {
                q.add((100 - progresses[i]) / speeds[i] + 1);
            }
        }

        int x = q.poll();
        int cnt = 1;
        while (!q.isEmpty()) {
            if (x >= q.peek()) {
                cnt++;
                q.poll();
            } else {
                list.add(cnt);
                cnt = 1;
                x = q.poll();
            }
        }
        list.add(cnt);

        int[] answer = new int[list.size()];
        for (int i = 0; i < list.size(); i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }
}
