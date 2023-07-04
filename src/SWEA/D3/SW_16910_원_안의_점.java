package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_16910_원_안의_점 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {

            int N = Integer.parseInt(br.readLine());
            int cnt = 0;

            for (int i = -N; i <= N; i++) {
                for (int j = -N; j <= N; j++) {
                    if (i * i + j * j <= N * N) cnt++;
                }
            }

            System.out.println("#" + t + " " + cnt);
        }
    }
}
