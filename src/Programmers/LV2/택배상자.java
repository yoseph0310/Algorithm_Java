package Programmers.LV2;
import java.util.*;

public class 택배상자 {
    public int solution(int[] order) {
        int answer = 0;

        Queue<Integer> belt = new LinkedList<>();
        Stack<Integer> sub = new Stack<>();

        for (int i = 1; i <= order.length; i++) {
            belt.add(i);
        }

        int selected = 0;

        for (int n : order) {
            while (true) {
                if (n < selected) {
                    if (sub.peek() == n) {
                        selected = sub.pop();
                        answer++;
                        break;
                    } else {
                        return answer;
                    }
                } else if (!belt.isEmpty()) {
                    if (belt.peek() == n) {
                        selected = belt.poll();
                        answer++;
                        break;
                    } else {
                        sub.push(belt.poll());
                    }
                }
            }
        }
        return answer;
    }
}
