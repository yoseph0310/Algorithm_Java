package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class BJ_9663 {

    static int N;
    static int cnt = 0;
    static int [] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);
        bw.write(cnt+"");
        bw.flush();
        bw.close();
    }

    public static void nQueen(int depth){
        if(depth == N){
            cnt++;
            return;
        }
        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if(Possibility(depth)){
                nQueen(depth+1);
            }
        }
    }

    public static boolean Possibility(int col){
        for (int i = 0; i < col; i++) {
            if(arr[col] == arr[i]){
                return false;
            }
            else if(Math.abs(col - i) == Math.abs(arr[col] - arr[i])){
                return false;
            }
        }
        return true;
    }
}