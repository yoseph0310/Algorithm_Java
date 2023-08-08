package SWEA.CodeBattle;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class No_42_K번째_접미어 {

    static int K, total;
    static char[] ans;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            K = Integer.parseInt(br.readLine());
            String word = br.readLine();


        }
    }

    static class Node {
        Map<Character, Node> childNodes = new HashMap<>();
        boolean isLastChar;
    }

    static class Trie {
        Node rootNode = new Node();

        void insert(String str) {

        }

        boolean search(String str) {
            return false;
        }
    }
}
