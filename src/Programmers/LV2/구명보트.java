package Programmers.LV2;

import java.util.Arrays;

public class 구명보트 {
    class Solution {
        public int solution(int[] people, int limit) {
            int answer = 0;

            Arrays.sort(people);

            int i = 0;
            int j = people.length - 1;

            while (i <= j) {
                answer++;
                if (people[i] + people[j] <= limit) {
                    i++;
                }
                j--;
            }

            return answer;
        }
    }
}
