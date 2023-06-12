package Programmers.LV2;
import java.util.*;

public class 요격_시스템 {
    public int solution(int[][] targets) {
        int answer = 0;

        Arrays.sort(targets, (o1, o2) -> (o1[1] - o2[1]));

        int last = -1;

        for (int[] target : targets) {
            if (last == -1) {
                answer++;
                last = target[1] - 1;
                continue;
            }

            if (last >= target[0] && last <= target[1]) continue;

            answer++;
            last = target[1] - 1;
        }

        return answer;
    }
}
