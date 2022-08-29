package SWEA.모의A형;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class Treasure_Box_Password {

    static int N, K, ans;
    static char [] arr;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            String[] arr = br.readLine().split("");
            TreeSet<String> set = new TreeSet<>(Collections.reverseOrder());
            for (int i = 0; i < N/4; i++) {
                String tmp = arr[N-1];
                for (int j = N-1; j > 0; j--) {
                    arr[j] = arr[j-1];
                }
                arr[0] = tmp;
                for (int j = 0; j < arr.length; j+=N/4) {
                    StringBuilder sb = new StringBuilder();
                    for (int k = j; k < j+(N/4) ; k++) {
                        sb.append(arr[k]);
                    }
                    set.add(sb.toString());
                }
            }
            String[] ans = set.toArray(new String[set.size()]);
            System.out.println("#"+t+" "+Long.parseLong(ans[K - 1], 16));
        }
    }

}
