package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class BJ_2417 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Long ans = 0L;
        Long n = Long.parseLong(br.readLine());

        if ((long)Math.sqrt(n) >= n){
            ans = (long)Math.sqrt(n);
        } else {
            ans = (long)Math.sqrt(n + 1);
        }
        System.out.println(ans);
    }
}
