package BackJoon.브론즈.B1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class BJ_1032_명령프롬프트 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        ArrayList<char[]> strList = new ArrayList<>();
        ArrayList<char[]> ans = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            char[] wordList = new char[50];
            for (int j = 0; j < str.length(); j++) {
                wordList[j] = str.charAt(j);
            }
            strList.add(wordList);
        }

        ans.add(strList.get(0));

        for (char[] cList: strList) {
            for (int i = 0; i < cList.length; i++) {
                if (ans.get(0)[i] != cList[i]) {
                    ans.get(0)[i] = '?';
                }
            }
        }

        for (int i = 0; i < ans.get(0).length; i++) {
            if (ans.get(0)[i] == '\0'){
                break;
            }
            System.out.print(ans.get(0)[i]);
        }
    }
}
