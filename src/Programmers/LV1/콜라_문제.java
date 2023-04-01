package Programmers.LV1;

public class 콜라_문제 {

    public int solution(int a, int b, int n) {
        int answer = 0;

        while (true) {
            if (n < a) break;

            answer += (n / a) * b;
            int remain = n % b;

            n = (n / a) * b + remain;
        }
        return answer;
    }
}
