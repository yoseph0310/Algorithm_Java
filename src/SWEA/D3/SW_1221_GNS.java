package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.StringTokenizer;

public class SW_1221_GNS {

    static int N;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            sb = new StringBuilder();
            st = new StringTokenizer(br.readLine());

            String testNum = st.nextToken();
            N = Integer.parseInt(st.nextToken());
            arr = new int[N];
            HashMap<Integer, String> map = new HashMap<>();

            String[] input = br.readLine().split(" ");
            for (int i = 0; i < input.length; i++) {
                switch (input[i]) {
                    case "ZRO":
                        arr[i] = 0;
                        break;
                    case "ONE":
                        arr[i] = 1;
                        break;
                    case "TWO":
                        arr[i] = 2;
                        break;
                    case "THR":
                        arr[i] = 3;
                        break;
                    case "FOR":
                        arr[i] = 4;
                        break;
                    case "FIV":
                        arr[i] = 5;
                        break;
                    case "SIX":
                        arr[i] = 6;
                        break;
                    case "SVN":
                        arr[i] = 7;
                        break;
                    case "EGT":
                        arr[i] = 8;
                        break;
                    case "NIN":
                        arr[i] = 9;
                        break;
                }
                map.put(arr[i], input[i]);
            }

            sb.append("#").append(t).append(" ");
            Arrays.sort(arr);
            for (int n: arr) {
                sb.append(map.get(n)).append(" ");
            }

            System.out.println(sb);
        }
    }
}
