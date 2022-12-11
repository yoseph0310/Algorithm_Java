package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_15230_알파벳_공부 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String s = br.readLine();
            int ans = 0;

            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);

                if (i != (c - 'a')) break;
                else ans++;
            }
            System.out.println("#"+t+ " " + ans);
        }


    }
}
