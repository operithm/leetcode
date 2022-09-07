package leetcode.medium;

/**
 *
 * Success
 * Details
 * Runtime: 48 ms, faster than 84.78% of Java online submissions for Implement Trie (Prefix Tree).
 * Memory Usage: 68.4 MB, less than 47.58% of Java online submissions for Implement Trie (Prefix Tree).
 * Next challenges:
 * Design Add and Search Words Data Structure
 * Design Search Autocomplete System
 * Replace Words
 * Implement Magic Dictionary
 * Encrypt and Decrypt Strings
 *
 *  208. Implement Trie (Prefix Tree)
 * Medium

 * A trie (pronounced as "try") or prefix tree is a tree data structure used to efficiently store and retrieve keys in a dataset of strings. There are various applications of this data structure, such as autocomplete and spellchecker.
 *
 * Implement the Trie class:
 *
 * Trie() Initializes the trie object.
 * void insert(String word) Inserts the string word into the trie.
 * boolean search(String word) Returns true if the string word is in the trie (i.e., was inserted before), and false otherwise.
 * boolean startsWith(String prefix) Returns true if there is a previously inserted string word that has the prefix prefix, and false otherwise.
 *
 *
 * Example 1:
 *
 * Input
 * ["Trie", "insert", "search", "search", "startsWith", "insert", "search"]
 * [[], ["apple"], ["apple"], ["app"], ["app"], ["app"], ["app"]]
 * Output
 * [null, null, true, false, true, null, true]
 *
 * Explanation
 * Trie trie = new Trie();
 * trie.insert("apple");
 * trie.search("apple");   // return True
 * trie.search("app");     // return False
 * trie.startsWith("app"); // return True
 * trie.insert("app");
 * trie.search("app");     // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length, prefix.length <= 2000
 * word and prefix consist only of lowercase English letters.
 * At most 3 * 104 calls in total will be made to insert, search, and startsWith.
 */
public class _208_Trie_ImplementTrie {
    class Trie {
        private final Trie[] children;
        private boolean isLeaf;
        private String word;

        public Trie() {
            this.children = new Trie[26];
        }

        /**
         *
         * @param word
         */
        public void insert(String word) {
            if (word == null || word.length() < 1) {
                return;
            }
            Trie current = this;
            for (char prefix : word.toCharArray()) {
                int index = prefix - 'a';
                if (current.children[index] == null) {
                    current.children[index] = new Trie();
                }
                current = current.children[index];
            }
            current.isLeaf = true;
            current.word = word;
        }

        /**
         *
         * @param word
         * @return
         */
        public boolean search(String word) {
            if (word == null || word.length() < 1) {
                return false;
            }
            Trie current = this;
            for (char prefix : word.toCharArray()) {
                int index = prefix - 'a';
                if (current.children[index] != null) {
                    current = current.children[index];
                } else {
                    return false;
                }
            }
            return current.isLeaf;
        }

        /**
         *
         * @param prefix
         * @return
         */
        public boolean startsWith(String prefix) {
            if (prefix == null || prefix.length() < 1) {
                return false;
            }
            Trie current = this;
            for (char next : prefix.toCharArray()) {
                int index = next - 'a';
                if (current.children[index] != null) {
                    current = current.children[index];
                } else {
                    return false;
                }
            }
            return true;
        }
    }

/**
 * Your Trie object will be instantiated and called as such:
 * Trie obj = new Trie();
 * obj.insert(word);
 * boolean param_2 = obj.search(word);
 * boolean param_3 = obj.startsWith(prefix);
 */

}
