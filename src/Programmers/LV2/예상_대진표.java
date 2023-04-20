package Programmers.LV2;

public class 예상_대진표 {
    public int solution(int n, int a, int b) {
        int answer = 0;

        while(true) {
            answer++;

            a = (a % 2 != 0) ? (a + 1) / 2 : (a / 2);
            b = (b % 2 != 0) ? (b + 1) / 2 : (b / 2);

            if (a == b) break;
        }
        return answer;
    }
}
