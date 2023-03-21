package BackJoon.브론즈.B2;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_24267_알고리즘의_수행_시간_6 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long N = Long.parseLong(br.readLine());

        System.out.println((N - 2) * (N - 1) * N / 6);
        System.out.println(3);
    }
}
