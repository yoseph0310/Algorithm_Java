package BackJoon.골드.G5;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BJ_20055_컨베이어_벨트_위의_로봇 {

    static int N, K;
    static int[] Conveyor;
    static boolean[] robot;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        Conveyor = new int[2 * N];
        robot = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < Conveyor.length; i++) {
            Conveyor[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(solve(0));
    }

    static int solve(int cnt) {
        while(isAvailable()) {
            int temp = Conveyor[Conveyor.length - 1];
            for (int i = Conveyor.length - 1; i > 0; i--) {
                Conveyor[i] = Conveyor[i - 1];
            }
            Conveyor[0] = temp;

            for (int i = robot.length - 1; i > 0; i--) {
                robot[i] = robot[i - 1];
            }
            robot[0] = false;

            robot[N - 1] = false;
            for (int i = N - 1; i > 0; i--) {
                if (robot[i - 1] && !robot[i] && Conveyor[i] >= 1) {
                    robot[i] = true;
                    robot[i - 1] = false;
                    Conveyor[i]--;
                }
            }

            if (Conveyor[0] > 0) {
                robot[0] = true;
                Conveyor[0]--;
            }

            cnt++;
        }

        return cnt;
    }

    static boolean isAvailable() {
        int cnt = 0;

        for (int i = 0; i < Conveyor.length; i++) {
            if (Conveyor[i] == 0) {
                cnt++;
            }
            if (cnt >= K) {
                return false;
            }
        }
        return true;
    }
}
