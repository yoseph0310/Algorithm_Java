package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BJ_1920 {

    static int [] N, M;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        N = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            N[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(N);

        int m = Integer.parseInt(br.readLine());
        M = new int[m];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < m; i++) {
            M[i] = Integer.parseInt(st.nextToken());
        }

        int start = 0;
        int end = N.length - 1;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            if (search(M[i], N, start, end) == 1){
                sb.append(1).append('\n');
            }
            else {
                sb.append(0).append('\n');
            }
        }
        System.out.println(sb);
    }

    public static int search(int num, int [] N, int start, int end){

        int mid = (start + end) / 2;
        if (start > end) {
            return 0;
        }
        if (num == N[mid]) {
            return 1;
        }
        else if (num < N[mid]) {
            return search(num, N, start, mid - 1);
        }
        else{
            return search(num, N, mid + 1, end);
        }
    }
}
