package BackJoon.브론즈.B3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_24265_알고리즘의_수행_시간_4 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Long N = Long.parseLong(br.readLine());


        System.out.println(N * (N - 1) / 2);
        System.out.println(2);;
    }
}
