package BackJoon.실버.S5;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BJ_10867_Counting {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int [] plusNum = new int[1001];
        int [] minusNum = new int[1001];
        int [] arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            if(arr[i] >= 0){
                plusNum[arr[i]] = 1;
            } else {
                minusNum[arr[i] * (-1)] = 1;
            }
        }

        for (int i = 1000; i >= 0; i--) {
            if (minusNum[i] > 0){
                bw.write(-i + " ");
            }
        }
        for (int i = 0; i < 1001; i++) {
            if (plusNum[i] > 0){
                bw.write(i + " ");
            }
        }
        bw.flush();
        bw.close();

    }
}
