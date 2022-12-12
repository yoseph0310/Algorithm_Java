package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_15758_무한_문자열 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringBuilder sb_S = new StringBuilder();
            StringBuilder sb_T = new StringBuilder();
            String input = br.readLine();
            String[] input_arr = input.split(" ");
            String str_S = input_arr[0];
            String str_T = input_arr[1];

            int S_len = str_S.length();
            int T_len = str_T.length();

            for (int i = 0; i < S_len; i++) {
                sb_T.append(str_T);
            }
            for (int i = 0; i < T_len; i++) {
                sb_S.append(str_S);
            }

            String sS = sb_S.toString();
            String sT = sb_T.toString();

            if (sS.length() > sT.length()) {
                if (sS.contains(sT)) System.out.println("#" + t + " " + "yes");
                else System.out.println("#" + t + " " + "no");
            } else if (sT.length() > sS.length()){
                if (sT.contains(sS)) System.out.println("#" + t + " " + "yes");
                else System.out.println("#" + t + " " + "no");
            } else {
                if (sT.equals(sS)) System.out.println("#" + t + " " + "yes");
                else System.out.println("#" + t + " " + "no");
            }

        }
    }
}