package BackJoon.골드.G4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2661_좋은수열 {

    static int N;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        backTracking("");
    }
    static void backTracking(String str){
        if (str.length() == N){
            System.out.println(str);
            System.exit(0);
        } else {
            for (int i = 1; i <= 3; i++) {
                if (isGoodSequence(str + i)){
                    backTracking(str + i);
                }
            }
        }
    }

    static boolean isGoodSequence(String s){
        int length = s.length() / 2;

        for (int i = 1; i <= length; i++) {
            if (s.substring(s.length() - i).equals(s.substring(s.length() - 2 * i, s.length() - i))){
                return false;
            }
        }
        return true;
    }
}
