package Z_Algorithm.KMP;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class KMP {

    static int res, pi[];
    static String origin, pattern;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        origin = br.readLine();
        pattern = br.readLine();

        pi = new int[pattern.length()];
        getPi();
        kmp();
    }

    static void getPi() {
        int idx = 0;
        for (int i = 1; i < pattern.length(); i++) {
            // 맞는 위치가 나올때까지 idx - 1 칸의 공통 부분 위치로 이동
            while (idx > 0 && pattern.charAt(i) != pattern.charAt(idx)) {
                idx = pi[idx-1];
            }

            // 맞다면
            if (pattern.charAt(i) == pattern.charAt(idx)) {
                // i 길이 문자열의 공통 길이는 idx 의 위치 + 1
                pi[i] = ++idx;
            }
        }
    }

    static void kmp() {
        int idx = 0;
        for (int i = 0; i < origin.length(); i++) {
            // 맞는 위치가 나올때까지 idx - 1 칸의 공통 부분 위치로 이동
            while (idx > 0 && origin.charAt(i) != pattern.charAt(idx)) {
                idx = pi[idx - 1];
            }

            if (origin.charAt(i) == pattern.charAt(idx)) {
                if (idx == pattern.length() - 1) {
                    res = 1;
                    break;
                }
                // 맞지만 패턴이 끝나지 않았다면 idx 증가
                else {
                    ++idx;
                }
            }
        }

        System.out.println(res);
    }
}
