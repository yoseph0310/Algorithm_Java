package Programmers.LV2;

import java.util.Deque;
import java.util.LinkedList;

public class 캐시_1차 {
    public int solution(int cacheSize, String[] cities) {
        if (cacheSize == 0) return cities.length * 5;

        int answer = 0;

        Deque<String> deque = new LinkedList<>();

        for (int i = 0; i < cities.length; i++) {
            String city = cities[i].toUpperCase();

            if (deque.remove(city)) {

                deque.offer(city);

                answer += 1;
            }
            // 덱에 도시가 안들어있으면 --> cache miss
            else {
                // 덱의 사이즈를 체크. 만약 cacheSize 만큼 차있다면
                if (deque.size() >= cacheSize) {
                    // 첫번째 도시를 덱 첫번째에서 빼고 입력된 도시를 덱 마지막에 집어넣는다.
                    deque.poll();
                }
                deque.offer(city);
                answer += 5;
            }
        }

        return answer;
    }
}
