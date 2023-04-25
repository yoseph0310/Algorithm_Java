package Programmers.LV2;

import java.util.PriorityQueue;

public class 더_맵게 {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        PriorityQueue<Integer> heap = new PriorityQueue<>();

        for (int s : scoville) {
            heap.offer(s);
        }

        int min = heap.peek();

        while (K > min && heap.size() > 1) {
            answer++;
            int a = heap.poll();
            int b = heap.poll();

            int res = a + (b*2);

            heap.offer(res);
            min = heap.peek();
        }

        if (K > min) {
            return -1;
        }

        return answer;
    }
}

/**
 * K가 heap 의 최솟값과 같아도 성공입니다. 위 코드는 입력값이 [7], 7 인경우 섞을 필요없이 0으로 성공인데
 * while (heap.peek() <= K) <- 루프는 들어가나
 * if (heap.size() < 2) return -1; <- 이 조건에서 -1을 리턴 하게 되겠네요
 *
 * [0,1] 2 의 경우도 루프를 들어가서 계산 한번 하고 난뒤 [2]가 되어 1을 리턴 하며 성공하여야 하는데
 * if (heap.size() < 2) return -1; <- 이 조건으로 인해 성공이 아닌 -1을 리턴 하겠네요
 */
