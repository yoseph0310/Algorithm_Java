package SWEA.CodeBattle;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class No_1_새로운_불면증_치료법 {

    static int N, ans;
    static HashSet<Integer> hs;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            N = Integer.parseInt(br.readLine());
            hs = new HashSet<>();
            // N 이 주어지면 cnt 를 1씩 늘려가면서 N 에 곱해간다.
            // 각 자리의 숫자들을 중복없이 체크하여 0 ~ 9까지 모두 세게되면 세기를 멈춘다.

            int num;
            int cnt = 0;
            while (!isDone()) {
                num = N * ++cnt;

                String[] strN = String.valueOf(num).split("");
                for (int i = 0; i < strN.length; i++) {
                    int n = Integer.parseInt(strN[i]);
                    hs.add(n);
                }
            }

            System.out.println("#" + t + " " + cnt * N);
        }
    }

    static boolean isDone() {
        for (int i = 0; i <= 9; i++) {
            if (!hs.contains(i)) return false;
        }

        return true;
    }
}
