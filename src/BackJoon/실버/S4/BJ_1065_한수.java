package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_1065_한수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        System.out.println(arithmetic_sequence(N));
    }

    static int arithmetic_sequence(int num) {
        int cnt = 0;

        if (num < 100) {
            return num;
        }

        else {
            cnt = 99;

            for (int i = 100; i <= num; i++) {
                int hun = i / 100;
                int ten = (i / 10) % 10;
                int one = i % 10;

                if ((hun - ten) == (ten - one)) {
                    cnt++;
                }
            }
        }

        return cnt;
    }
}
