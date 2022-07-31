package Programmers.LV1;

public class 최대공약수와_최소공배수 {
    public int[] solution(int n, int m) {
        int[] answer = new int[2];
        int big = Math.max(n, m);
        int small = Math.min(n, m);

        answer[0] = GCD(n, m);
        answer[1] = (big * small) / answer[0];

        return answer;
    }

    public static int GCD(int n, int m){
        if (n % m == 0){
            return m;
        }

        return GCD(m, n%m);
    }
}
