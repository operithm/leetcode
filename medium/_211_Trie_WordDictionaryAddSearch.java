package leetcode.medium;

/**
 * Success
 * Details
 * Runtime: 798 ms, faster than 75.25% of Java online submissions for Design Add and Search Words Data Structure.
 * Memory Usage: 108.4 MB, less than 84.18% of Java online submissions for Design Add and Search Words Data Structure.
 * Next challenges:
 * Prefix and Suffix Search
 * Match Substring After Replacement
 *
 * 211. Design Add and Search Words Data Structure
 * Medium
 *
 * 5388
 *
 * 278
 *
 * Add to List
 *
 * Share
 * Design a data structure that supports adding new words and finding if a string matches any previously added string.
 *
 * Implement the WordDictionary class:
 *
 * WordDictionary() Initializes the object.
 * void addWord(word) Adds word to the data structure, it can be matched later.
 * bool search(word) Returns true if there is any string in the data structure that matches word or false otherwise. word may contain dots '.' where dots can be matched with any letter.
 *
 *
 * Example:
 *
 * Input
 * ["WordDictionary","addWord","addWord","addWord","search","search","search","search"]
 * [[],["bad"],["dad"],["mad"],["pad"],["bad"],[".ad"],["b.."]]
 * Output
 * [null,null,null,null,false,true,true,true]
 *
 * Explanation
 * WordDictionary wordDictionary = new WordDictionary();
 * wordDictionary.addWord("bad");
 * wordDictionary.addWord("dad");
 * wordDictionary.addWord("mad");
 * wordDictionary.search("pad"); // return False
 * wordDictionary.search("bad"); // return True
 * wordDictionary.search(".ad"); // return True
 * wordDictionary.search("b.."); // return True
 *
 *
 * Constraints:
 *
 * 1 <= word.length <= 25
 * word in addWord consists of lowercase English letters.
 * word in search consist of '.' or lowercase English letters.
 * There will be at most 3 dots in word for search queries.
 * At most 104 calls will be made to addWord and search.
 *
 */
public class _211_Trie_WordDictionaryAddSearch {

    class WordDictionary {
        private final WordDictionary[] suffix;
        private boolean isWord;
        private String word;

        public WordDictionary() {
            this.suffix = new WordDictionary[26];
        }

        public void addWord(String word) {
            if (word == null || word.length() < 1) {
                return;
            }
            WordDictionary current = this;
            for (char suf : word.toCharArray()) {
                int index = suf - 'a';
                if (current.suffix[index] == null) {
                    current.suffix[index] = new WordDictionary();
                }
                current = current.suffix[index];
            }
            current.isWord = true;
            current.word = word;

        }

        public boolean search(String word) {
            if (word == null || word.length() < 1) {
                return false;
            }
            return hasNext(word, this);
        }

        /**
         * Recursive level search
         * @param partial
         * @param current
         * @return
         */
        private boolean hasNext(String partial, WordDictionary current) {
            if (partial == null || partial.length() == 0) {
                return current.isWord;
            }
            char suf = partial.charAt(0);
            String next = partial.substring(1);
            if ( suf == '.') {
                boolean hasOne = false;
                for (WordDictionary child : current.suffix) {
                    if (child != null) {
                        hasOne |= hasNext(next, child);
                    }
                }
                return hasOne;
            } else {
                int index = suf - 'a';
                if (current.suffix[index] != null) {
                    return hasNext(next, current.suffix[index]);
                } else {
                    return false;
                }
            }
        }
    }

/**
 * Your WordDictionary object will be instantiated and called as such:
 * WordDictionary obj = new WordDictionary();
 * obj.addWord(word);
 * boolean param_2 = obj.search(word);
 */
}
