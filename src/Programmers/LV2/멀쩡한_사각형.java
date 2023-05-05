package Programmers.LV2;

public class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        long answer = 1;
        long width = w;
        long height = h;

        answer = (w * h) - (w + h - gcd(w,h));

        return answer;
    }

    long gcd(long w, long h) {
        if (h == 0) return w;
        return gcd(h, w % h);
    }
}

