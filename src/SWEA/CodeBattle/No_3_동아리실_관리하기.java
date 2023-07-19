package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class No_3_동아리실_관리하기 {

    static int V = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String input = br.readLine();
            int dayLen = input.length();
            long[][] record = new long[dayLen][1<<4];       // 4명의 참여 여부에 따른 모든 경우의 수 2^4 - 1

            // 최대 10,000 일
            for (int i = 0; i < dayLen; i++) {
                int turn = 1 << (input.charAt(i) - 'A');

                // 어제 참석한 부원들 탐색
                for (int j = 0; j < (1<<4); j++) {
                    // 첫날
                    if (i == 0) {
                        if ((j & turn) > 0 && (j & 1) > 0) {
                            record[0][j] = 1;
                        }
                    } else {
                        if (record[i - 1][j] == 0) continue;

                        for (int k = 0; k < (1<<4); k++) {
                            if ((j & k) > 0 && (k & turn) > 0) {
                                record[i][k] += record[i-1][j];
                                record[i][k] %= V;
                            }
                        }
                    }
                }
            }

            long ans = 0;
            for (int i = 0; i < (1<<4); i++) {
                ans += record[dayLen-1][i];
                ans %= V;
            }

            System.out.println("#" + t + " " + ans);
        }
    }
}