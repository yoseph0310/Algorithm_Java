package Programmers.LV2;

public class 멀쩡한_사각형 {
    public long solution(int w, int h) {
        long answer = 1;
        long width = (long)w;
        long height = (long)h;

        answer = (width * height) - (width + height - gcd(width, height));

        return answer;
    }

    long gcd(long w, long h) {
        if (h == 0) return w;
        return gcd(h, w % h);
    }
}

