package Programmers.LV2;

public class 점_찍기 {
    public long solution(int k, int d) {
        long answer = 0;

        for (int i = 0; i <= d/k; i++) {
            long x = i * k;
            long y = (long) Math.sqrt((long)Math.pow(d,2) - (long)Math.pow(x,2)) / k;
            answer += y + 1;
        }

        return answer;
    }
}
