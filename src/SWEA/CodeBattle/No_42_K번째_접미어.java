package SWEA.CodeBattle;

import Z_DataStructure.Trie.Trie_Test;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class No_42_K번째_접미어 {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            int K = Integer.parseInt(br.readLine());
            String word = br.readLine();


        }
    }

    static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean endOfWord;
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
