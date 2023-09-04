package Programmers.LV3;
import java.util.*;

public class 이중우선순위큐 {
    class Solution {
        public int[] solution(String[] operations) {
            int[] answer = new int[2];

            PriorityQueue<Num> min_pq = new PriorityQueue<>((o1, o2) -> (o1.value - o2.value));
            PriorityQueue<Num> max_pq = new PriorityQueue<>((o1, o2) -> (o2.value - o1.value));

            for (int i = 0; i < operations.length; i++) {
                String[] OP = operations[i].split(" ");

                String command = OP[0];
                int num = Integer.parseInt(OP[1]);

                // 숫자 삽입
                if (command.equals("I")) {
                    Num newNum = new Num(i, num);

                    min_pq.add(newNum);
                    max_pq.add(newNum);
                }
                // 최솟값 제거
                else if (command.equals("D") && num == -1) {
                    if (min_pq.isEmpty()) continue;

                    // min_pq 에서는 poll() 로 삭제하고 max_pq 에서는 해당 객체를 바로 remove 하는 식으로 삭제한다.
                    Num deleteNum = min_pq.poll();

                    if (max_pq.isEmpty()) continue;
                    max_pq.remove(deleteNum);
                }
                // 최댓값 제거
                else if (command.equals("D") && num == 1) {
                    if (max_pq.isEmpty()) continue;
                    // max_pq 에서는 poll() 로 삭제하고 min_pq 에서는 해당 객체를 바로 remove 하는 식으로 삭제한다.
                    Num deleteNum = max_pq.poll();

                    if (min_pq.isEmpty()) continue;
                    min_pq.remove(deleteNum);
                }
            }

           if (min_pq.isEmpty()) {
                answer[0] = max_pq.peek().value;
            }
            // 최대큐만 비어있는 경우
            else if (max_pq.isEmpty()) {
                answer[1] = min_pq.peek().value;
            }
            // 모두 비어있지 않는 경우
            else {
                answer[0] = max_pq.peek().value;
                answer[1] = min_pq.peek().value;
            }


            return answer;
        }

        class Num {
            int idx, value;

            public Num(int idx, int value) {
                this.idx = idx;
                this.value = value;
            }
        }
    }
}
