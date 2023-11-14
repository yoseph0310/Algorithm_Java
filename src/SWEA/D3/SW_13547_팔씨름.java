package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class SW_13547_팔씨름 {

    static final int MAX_GAME_CNT = 15;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String ans = "";

            String input = br.readLine();

            int remainGameCnt = MAX_GAME_CNT;

            int p1Cnt = 0;
            int p2Cnt = 0;

            for (int i = 0; i < input.length(); i++) {
                if (input.charAt(i) == 'o') p1Cnt++;
                else p2Cnt++;

                remainGameCnt--;
            }

            // 남은 게임 횟수에 p1Cnt 를 더해서 8 이상이면 YES.
            ans = (isAvailable(p1Cnt, remainGameCnt)) ? "YES" : "NO";

            System.out.println("#" + t + " " + ans);
        }
    }

    static boolean isAvailable(int pC, int rC) {
        return pC + rC >= 8;
    }
}
