package Programmers.LV2;

public class k진수에서_소수_개수_구하기 {
    public int solution(int n, int k) {
        int answer = 0;
        String convert = "";

        // k진법 변환
        while (n != 0) {
            convert = n % k + convert;
            n /= k;
        }

        String[] arr = convert.split("0");

        for (String s : arr) {
            if (s.equals("")) continue;

            long num = Long.parseLong(s);
            if (isPrime(num)) answer++;
        }

        return answer;
    }

    boolean isPrime(long n) {
        if (n < 2) return false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) return false;
        }

        return true;
    }
}
