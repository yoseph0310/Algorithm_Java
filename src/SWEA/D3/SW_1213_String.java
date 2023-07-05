package SWEA.D3;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class SW_1213_String {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int t = 1; t <= 10; t++) {
            int N = Integer.parseInt(br.readLine());
            int cnt = 0;

            String searchWord = br.readLine();
            String sentence = br.readLine();

            int searchWordLen = searchWord.length();
            int sentenceLen = sentence.length();

            for (int i = 0; i <= sentenceLen - searchWordLen; i++) {
                if (searchWord.equals(sentence.substring(i, i+searchWordLen))) {
                    cnt++;
                }
            }

            System.out.println("#" + t + " " + cnt);
        }
    }
}
