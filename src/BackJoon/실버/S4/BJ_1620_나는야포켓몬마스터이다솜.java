package BackJoon.실버.S4;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.StringTokenizer;

public class BJ_1620_나는야포켓몬마스터이다솜 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        HashMap<String, Integer> hm = new HashMap<>();
        String[] nameArr  = new String[N + 1];

        for (int i = 1; i < N + 1; i++) {
            String name = br.readLine();
            hm.put(name, i);
            nameArr[i] = name;
        }

        for (int i = 0; i < M; i++) {
            String s = br.readLine();
            if (isStringNumber(s)) {
                int idx = Integer.parseInt(s);
                System.out.println(nameArr[idx]);
            } else {
                System.out.println(hm.get(s));
            }
        }
    }

    static boolean isStringNumber(String s){
        try {
            Double.parseDouble(s);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
}
