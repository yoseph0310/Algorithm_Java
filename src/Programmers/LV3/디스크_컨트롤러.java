package Programmers.LV3;
import java.util.*;

public class 디스크_컨트롤러 {
    class Solution {
        public int solution(int[][] jobs) {
            int answer = 0;
            int end = 0;
            int jobsIdx = 0;
            int cnt = 0;

            Arrays.sort(jobs, (o1, o2) -> o1[0] - o2[0]);

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o1[1] - o2[1]);

            while (cnt < jobs.length) {
                // 한 작업이 완료되는 시점(end) 까지 들어온 모든 요청을 pq에 넣는다.
                while (jobsIdx < jobs.length && jobs[jobsIdx][0] <= end) {
                    pq.add(jobs[jobsIdx++]);
                }

                // 큐가 비었다면 작업 완료 후에 다시 요청이 들어온다는 것을 의미함
                // end 를 요청의 가장 처음으로 맞춘다.
                if (pq.isEmpty()) {
                    end = jobs[jobsIdx][0];
                }

                // 작업이 끝나기 전(end 이전) 들어온 요청 중 가장 수행시간이 짧은 요청부터 수행한다.
                else {
                    int[] tmp = pq.poll();
                    answer += tmp[1] + end - tmp[0];
                    end += tmp[1];
                    cnt++;
                }
            }

            return answer / jobs.length;
        }
    }
}
