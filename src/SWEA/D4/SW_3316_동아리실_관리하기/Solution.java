package SWEA.D4.SW_3316_동아리실_관리하기;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Solution {

    static final int MOD = 1_000_000_007;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            String input = br.readLine();
            int dayLen = input.length();
            long[][] record = new long[dayLen][1 << 4];

            // 최대 10,000
            for (int i = 0; i < dayLen; i++) {
                int turn = 1 << (input.charAt(i) - 'A');

                for (int j = 0; j < (1 << 4); j++) {
                    // 첫날
                    if (i == 0) {
                        // 당번 참석, A가 키를 들고 참석
                        if ((j & turn) > 0 && (j & 1) > 0) {
                            record[i][j] = 1;
                        }
                    }
                    else {
                        if (record[i - 1][j] == 0) continue;

                        for (int k = 0; k < (1 << 4); k++) {
                            // 당번
                            if ((j & k) > 0 && (k & turn) > 0) {
                                record[i][k] += record[i - 1][j];
                                record[i][k] %= MOD;
                            }
                        }
                    }
                }
            }

            long ans = 0;
            for (int i = 0; i < (1 << 4); i++) {
                ans += record[dayLen - 1][i];
                ans %= MOD;
            }

            System.out.println("#" + t + " " + ans);
        }
    }
}
