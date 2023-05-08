package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_9663_N_Queen {

    static int N;
    static int answer = 0;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        arr = new int[N];

        nQueen(0);

        System.out.println(answer);
    }

    static void nQueen(int depth) {
        if (depth == N) {
            answer++;
            return;
        }

        for (int i = 0; i < N; i++) {
            arr[depth] = i;
            if (isPossible(depth)) {
                nQueen(depth + 1);
            }
        }
    }

    static boolean isPossible(int col) {
        for (int i = 0; i < col; i++) {
            if (arr[col] == arr[i]) {
                return false;
            }
            else if (Math.abs(col - i) == Math.abs(arr[col] - arr[i])) {
                return false;
            }
        }
        return true;
    }

}
