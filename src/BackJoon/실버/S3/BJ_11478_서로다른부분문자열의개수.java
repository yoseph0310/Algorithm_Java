package BackJoon.실버.S3;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class BJ_11478_서로다른부분문자열의개수 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String str = br.readLine();
        HashSet<String> hs = new HashSet<>();

        for (int i = 0; i < str.length(); i++) {
            String s = "";
            for (int j = i; j < str.length(); j++) {
                s += str.substring(j, j+1);
                hs.add(s);
            }
        }
        System.out.println(hs.size());

    }
}
