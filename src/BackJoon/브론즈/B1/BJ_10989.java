package BackJoon.브론즈.B1;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_10989 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int [] arr = new int[10001];

        for (int i = 0; i < N; i++) {
            arr[Integer.parseInt(br.readLine())] += 1;
        }

        for (int i = 0; i < arr.length; i++) {
            while(arr[i] > 0){
                sb.append(i).append('\n');
                arr[i]--;
            }
        }

        System.out.println(sb);
    }
}
