package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_17319_문자열문자열 {

    static int N;
    static String ans;
    static char[] arr1;
    static char[] arr2;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            ans = "Yes";
            N = Integer.parseInt(br.readLine());
            String input = br.readLine();

            if (N % 2 == 1) {
                ans = "No";
                System.out.println("#"+ t + " " + ans);
                continue;
            }

            arr1 = new char[N / 2];
            arr2 = new char[N / 2];

            for (int i = 0; i < N / 2; i++) {
                arr1[i] = input.charAt(i);
                arr2[i] = input.charAt(i + N / 2);
            }

            for (int i = 0; i < N / 2; i++) {
                if (arr1[i] != arr2[i]) {
                    ans = "No";
                    break;
                }
            }

            System.out.println("#"+ t + " " + ans);
        }
    }

    static void print_arr(char[] arr) {
        for (char c: arr) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
}