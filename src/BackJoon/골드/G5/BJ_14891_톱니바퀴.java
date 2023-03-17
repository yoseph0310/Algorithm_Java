package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_14891_톱니바퀴 {



    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int[][] gear = new int[5][8];

        for (int i = 1; i <= 4; i++) {
            String s = br.readLine();
            for (int j = 0; j < 8; j++) {
                int c = s.charAt(j) - '0';

                gear[i][j] = c;
            }
        }

        int K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());

            int num = Integer.parseInt(st.nextToken());
            int dir = Integer.parseInt(st.nextToken());

            int[] directions = getDirections(gear, num, dir);

            for (int j = 1; j <= 4; j++) {
                if (directions[j] != 0) {
                    roll(gear[j], directions[j]);
                }
            }
        }

        int ans = 0;

        if (gear[1][0] == 1) ans += 1;
        if (gear[2][0] == 1) ans += 2;
        if (gear[3][0] == 1) ans += 4;
        if (gear[4][0] == 1) ans += 8;

        System.out.println(ans);
    }

    static void roll(int[] gear, int dir) {
        if (dir == 1) {
            int temp = gear[7];
            for (int i = 7; i > 0; i--) {
                gear[i] = gear[i - 1];
            }
            gear[0] = temp;
        } else if (dir == -1) {
            int temp = gear[0];
            for (int i = 0; i < 7; i++) {
                gear[i] = gear[i + 1];
            }
            gear[7] = temp;
        }
    }

    static int[] getDirections(int[][] gear, int num, int dir) {
        int[] directions = new int[5];
        directions[num] = dir;

        for (int i = num; i > 1; i--) {
            if (gear[i][6] == gear[i - 1][2]) break;

            directions[i - 1] = directions[i] * -1;
        }

        for (int i = num; i < 4; i++) {
            if (gear[i][2] == gear[i + 1][6]) break;

            directions[i + 1] = directions[i] * -1;
        }


        return directions;
    }

}
