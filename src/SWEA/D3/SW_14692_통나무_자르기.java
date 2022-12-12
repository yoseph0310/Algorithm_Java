package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_14692_통나무_자르기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            double N = Double.parseDouble(br.readLine());

            if (N % 2 == 0) System.out.println("#"+t+" "+"Alice");
            else System.out.println("#"+t+" "+"Bob");

        }
    }
}
