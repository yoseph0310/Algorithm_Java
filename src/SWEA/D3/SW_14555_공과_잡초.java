package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_14555_공과_잡초 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String s = br.readLine();
            int ans = 0;

            for (int i = 0; i < s.length() - 1; i++) {
                char c = s.charAt(i);

                if (c == '|') {
                    if (s.charAt(i + 1) == ')') ans++;
                }
                else if (c == '(') {
                    if (s.charAt(i + 1) == '|') ans++;
                    else if (s.charAt(i + 1) == ')') ans++;
                }
            }
            System.out.println("#" + t + " " + ans);
        }
    }
}
