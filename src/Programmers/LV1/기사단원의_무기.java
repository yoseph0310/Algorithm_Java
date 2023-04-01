package Programmers.LV1;

public class 기사단원의_무기 {
    public int solution(int number, int limit, int power) {
        int answer = 0;
        int[] knight = new int[number + 1];

        for (int i = 1; i <= number; i++) {
            for (int j = 1; j * j <= i; j++) {
                if (j * j == i) knight[i]++;
                else if (i % j == 0) knight[i] += 2;
            }
        }

        for (int i = 1; i <= number; i++) {

            if (knight[i] > limit) {
                knight[i] = power;
            }
            answer += knight[i];
        }

        return answer;
    }
}
