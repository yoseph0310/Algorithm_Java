package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_10816_Counting {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [] counting = new int[20000001]; // 수의 범위 -10,000,000 ~ 10,000,000
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            counting[Integer.parseInt(st.nextToken()) + 10000000]++;
        }

        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            bw.write(counting[Integer.parseInt(st.nextToken()) + 10000000] + " ");
        }
        bw.flush();
        bw.close();

    }
}
