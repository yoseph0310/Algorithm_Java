package SWEA.CodeBattle;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/*
    book 의 길이 : 1 <= book.length <= 500,000
    search 의 길이 : 1 <= search.length <= 100,000

 */
public class No_30_단어가_등장하는_횟수 {

    static int ans, dataLen, searchLen;
    static String data, search;
    static int[] pi;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            data = br.readLine();
            search = br.readLine();
            ans = 0;

            dataLen = data.length();
            searchLen = search.length();

            pi = new int[searchLen];

            // pi 배열 생성
            getPi();
            kmp();

            System.out.println("#" + t + " " + ans);
        }

    }

    static void getPi() {
        int idx = 0;
        for (int i = 1; i < searchLen; i++) {
            System.out.println(idx + " " + i + " 비교");
            while (idx > 0 && search.charAt(i) != search.charAt(idx)) {
                idx = pi[idx-1];
            }

            if (search.charAt(i) == search.charAt(idx)) {
                idx++;
                pi[i] = idx;
            }

        }
    }

    static void kmp() {
        int idx = 0;
        for (int i = 0; i < dataLen; i++) {
            while (idx > 0 && data.charAt(i) != search.charAt(idx)) {
                idx = pi[idx - 1];
            }

            if (data.charAt(i) == search.charAt(idx)) {
                if (idx == searchLen - 1) {
                    ans++;
                    idx = pi[idx];
                }
                else {
                    idx++;
                }
            }
        }
    }

}
