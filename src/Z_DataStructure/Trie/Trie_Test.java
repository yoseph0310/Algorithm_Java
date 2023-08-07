package Z_DataStructure.Trie;

import java.util.HashMap;
import java.util.Map;

public class Trie_Test {
    public static void main(String[] args) {

        Trie trie = new Trie();

        trie.insert("PI");
        trie.insert("PIE");

        System.out.println(trie.contains("APP"));

    }

    static class Node {
        private Map<Character, Node> childNodes = new HashMap<>();
        private boolean isLastChar;

        Map<Character, Node> getChildNodes() {
            return this.childNodes;
        }

        boolean isLastChar() {
            return this.isLastChar;
        }

        void setIsLastChar(boolean isLastChar) {
            this.isLastChar = isLastChar;
        }
    }

    static class Trie {
        Node rootNode;

        public Trie() {
            rootNode = new Node();
        }

        void insert(String word) {
            Node thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                thisNode = thisNode.childNodes.computeIfAbsent(word.charAt(i), c -> new Node());
            }
            thisNode.setIsLastChar(true);
        }

        boolean contains(String word) {
            Node thisNode = this.rootNode;

            for (int i = 0; i < word.length(); i++) {
                char character = word.charAt(i);
                Node node = thisNode.getChildNodes().get(character);

                if (node == null) return false;

                thisNode = node;
            }

            return thisNode.isLastChar();
        }

        void delete(String word) {
            delete(this.rootNode, word, 0);
        }

        private void delete(Node thisNode, String word, int idx) {
            char character = word.charAt(idx);

            if (!thisNode.getChildNodes().containsKey(character)) {
                throw new Error("There is no [" + word + "] in this Trie");
            }

            Node childNode = thisNode.getChildNodes().get(character);
            idx++;

            if (idx == word.length()) {
                if (!childNode.isLastChar()) throw new Error("There is no [" + word + "] in this Trie");

                childNode.setIsLastChar(false);

                if (childNode.getChildNodes().isEmpty()) thisNode.getChildNodes().remove(character);
                else {
                    delete(childNode, word, idx);
                    if (!childNode.isLastChar() && childNode.getChildNodes().isEmpty()) {
                        thisNode.getChildNodes().remove(character);
                    }
                }
            }
        }

        boolean isRootEmpty() {
            return this.rootNode.getChildNodes().isEmpty();
        }
    }
}
