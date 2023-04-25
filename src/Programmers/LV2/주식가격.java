package Programmers.LV2;

import java.util.LinkedList;
import java.util.Queue;

public class 주식가격 {
    public int[] solution(int[] prices) {
        int[] answer = new int[prices.length];

        Queue<Integer> q = new LinkedList<>();

        for (int price : prices) {
            q.add(price);
        }

        int idx = 0;
        while(!q.isEmpty()) {
            int curPrice = q.poll();
            int num = 0;

            for (int j = idx + 1; j < prices.length; j++) {
                if (curPrice <= prices[j]) {
                    num++;
                } else {
                    num++;
                    break;
                }
            }
            answer[idx] = num;
            idx++;
        }

        return answer;
    }
}
