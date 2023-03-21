package BackJoon.브론즈.B1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_11653_소인수_분해 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());

        int idx = 2;
        while (N != 1) {

            if (N % idx == 0) {
                System.out.println(idx);
                N /= idx;
            } else {
                idx++;
            }
        }

//        System.out.println(sb.substring(0, sb.length() - 1));
    }
}
