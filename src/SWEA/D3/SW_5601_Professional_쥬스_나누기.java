package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_5601_Professional_쥬스_나누기 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int N = Integer.parseInt(br.readLine());

            StringBuilder sb = new StringBuilder();

            int p = 1;

            sb.append("#").append(t).append(" ");
            for (int i = 0; i < N-1; i++) {
                sb.append(p).append("/").append(N).append(" ");
            }
            sb.append(p).append("/").append(N);

            System.out.println(sb);
        }
    }
}
