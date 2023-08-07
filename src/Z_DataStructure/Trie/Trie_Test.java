package Z_DataStructure.Trie;

import java.util.HashMap;
import java.util.Map;

public class Trie_Test {
    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("kakao");
        trie.insert("busy");
        trie.insert("card");
        trie.insert("cap");

        System.out.println(trie.search("kakao"));
        System.out.println(trie.search("bus"));
        System.out.println(trie.search("busy"));

    }

    static class Node {
        Map<Character, Node> childNode = new HashMap<>();
        boolean endOfWord;
    }

    static class Trie {
        Node rootNode = new Node();

        void insert(String str) {
            Node node = this.rootNode;

            for (int i = 0; i < str.length(); i++) {
                node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
            }

            node.endOfWord = true;
        }

        boolean search(String str) {
            Node node = this.rootNode;

            for (int i = 0; i < str.length(); i++) {
                node = node.childNode.getOrDefault(str.charAt(i), null);
                if (node == null) {
                    return false;
                }
            }

            return node.endOfWord;
        }
    }
}
