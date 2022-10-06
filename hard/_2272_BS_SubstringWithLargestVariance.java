package leetcode.hard;

import java.util.*;

/**
 * 2272. Substring With Largest Variance
 * Hard
 *
 * 498
 *
 * 55
 *
 * Add to List
 *
 * Share
 * The variance of a string is defined as the largest difference between the number of occurrences of any 2 characters present in the string. Note the two characters may or may not be the same.
 *
 * Given a string s consisting of lowercase English letters only, return the largest variance possible among all substrings of s.
 *
 * A substring is a contiguous sequence of characters within a string.
 *
 *
 *
 * Example 1:
 *
 * Input: s = "aababbb"
 * Output: 3
 * Explanation:
 * All possible variances along with their respective substrings are listed below:
 * - Variance 0 for substrings "a", "aa", "ab", "abab", "aababb", "ba", "b", "bb", and "bbb".
 * - Variance 1 for substrings "aab", "aba", "abb", "aabab", "ababb", "aababbb", and "bab".
 * - Variance 2 for substrings "aaba", "ababbb", "abbb", and "babb".
 * - Variance 3 for substring "babbb".
 * Since the largest possible variance is 3, we return it.
 * Example 2:
 *
 * Input: s = "abcde"
 * Output: 0
 * Explanation:
 * No letter occurs more than once in s, so the variance of every substring is 0.
 *
 *
 * Constraints:
 *
 * 1 <= s.length <= 104
 * s consists of lowercase English letters.
 */
public class _2272_BS_SubstringWithLargestVariance {
    static class Count {
        private int count;
        private Set<Character> set;

        public Count(int count, char code) {
            this.count = count;
            this.set = new TreeSet<Character>();
            set.add(code);
        }

        public int getCount() {
            return count;
        }

        public void add(Count another) {
            if (this.count == another.count) {
                this.set.addAll(another.set);
            }
        }

        public void remove(Count another) {
            if (this.count == another.count && this.count > 0) {
                this.set.removeAll(another.set);
            }
        }

        public boolean isEmpty() {
            return this.set.isEmpty();
        }

        @Override
        public String toString() {
            return String.format("{%s,%s}", count, String.valueOf(set));
        }
    }

    static class Solution {

        public int largestVariance(String s) {
            if (s == null || s.length() < 1) {
                return 0;
            }

            int n = s.length();
            var map = new TreeMap<Character, Integer>();
            var counts = new LinkedList<Count>();

            int max = 0;

            for (int i = 0; i < n / 2; i++) {
                map.clear();
                counts.clear();
                for (int j = i; j < n; j++) {
                    int variance = varianceAt(map, counts, s.charAt(j));
                    if (variance > max) {
                        max = variance;
                        //System.out.printf("at: %s, char: %s, max: %s\n", j, s.charAt(j), max);
                    }
                }
            }
            return max;
        }

        private int varianceAt(Map<Character, Integer> charMap, LinkedList<Count> counts, char charAt) {
            Integer incidence = charMap.getOrDefault(charAt, 0);
            incidence++;
            charMap.put(charAt, incidence);
            //System.out.printf("c: %s, map: %s\n", charAt, String.valueOf(charMap));
            /**
             * first add, or same character
             */
            Count countAt = new Count(incidence, charAt);

            /**
             More than one character, so do count
             */
            int index = Collections.binarySearch(counts, countAt, Comparator.comparing(Count::getCount));

            Count prev = null;
            Count before = countAt.count > 1 ? new Count(countAt.count - 1, charAt) : null;
            int beforeIndex = before != null ?
                    Collections.binarySearch(counts, before, Comparator.comparing(Count::getCount)) :
                    -1;

            if (index >= 0) {
                counts.get(index).add(countAt);
                if (index > 0 && before != null) {
                    prev = counts.get(index - 1);
                    prev.remove(before);
                }
            } else {
                index = -index - 1;
                if (index == counts.size() && beforeIndex >= 0) {
                    prev = counts.get(beforeIndex);
                    prev.remove(before);
                    counts.addLast(countAt);
                } else if (index == 0) {
                    counts.addFirst(countAt);
                } else {
                    //prev and before must exist
                    prev = counts.get(beforeIndex);
                    prev.remove(before);
                    counts.add(index, countAt);
                }
            }

            /**
             * remove by reference, if the prev has only character in set
             */
            if (prev != null && prev.isEmpty()) {
                counts.remove(prev);
            }

            return counts.getLast().count - counts.getFirst().count;
        }
    }

    public static final void main(String[] args) {
        String s = "lucmdweawziyvixyfesksmkxkbzzzqdmrmvdxeghlrlyteuhvumwppwltssrlboozoiudqegobjvnuinwoaaxbiqivtwabunqkzvjnczasvghsvrckpzelcqeloppxwmnbeocoiximllpvhahesjxznfphohoycaqsaghpoligtghoejodmhwuzjmpwkrpehheuubiespninbzfbqtiimtzbymdrxxbjzhqanmoocicqfhdrtfwjbxkgehjdqhmmjnrrgilsvyhonfmvywaejhxxgabogdqgttfiufrgpgpduwhzgmgoecwagwdvmnobiukuigphrkupqkeaphjsqhmetkgmcramydkosqqmayrdgfiokpanxznuknqpcqsbumyrxfsmmcxvherbjykpbwdzeqjgdhysauxflcdhkmlflmygylnxubaimtmsbbapfsrqdwwihubmemmhumzhmvalwkneehsxjofrcubyscgmlwfuzepmlyvpthqlvxrzcekmbemneozbtfajwkaizheoexbtdicgzmgnbytwyruexhigheujnolqafjmvtfgeduwtkisjovklsazfoslylmqjkgafbcfsawdjlyyobskeywidozxbmmapjrhqjjtoknpujwibccdotmnfxqcmhbelrireqfxmqoitciszlhecacxrpdbxeqravhrgwylhzpamvjjmghrzywpfpxjogidkkuolqscxuqgxzfmkuiagndjfhcmuwysojjmwtdrmicpnjpxonymsuwvrodwmfbtpwyxmesmkpuctrlabbknyoyueumfitkpdzsnkurzzyexeutmbqcdbmirqndghaksbpukszbkgvgswjrixuwvzsoymjuiungsnpytstwjbekzudtjxqkwkhgyophfllqvmdwvdlywtnsvlfwkesxdhdfwytgtwkgprlocjlcjqezcwpiwldnrqwyqxrgyyrkdotjhtsppwjkpecnpyarjftdbvzhdnqkqpbkwtkcfsomzwgxnwtsoslvxbwdkfvaeyxzkadctnngewqbwftphtfcdhjbwzytmrlolbgouoluyfyngtkijgwvxmjzqcapymvdssiirusnrnmuextfeosrdsudwixozufmwatfmjjumqmnprsqdrrerjkivjlnohkgckhuzbajvfjezbsivnhnexfryxghcxvetlwnjlutskdguwlsqhcuravxvfmzeycxifyjjqvbdmlmzfsuekrszqvdtmlfcytznjkplqpveqybkdmggrnyuoabxkepgbenzaufxwrmqufmnlgndjakvhbkkkzhgdoutdphnrqhtogbrpgdifgcqzheognwlgoqszqjsshaiciiwjqoxlznfgjtytrmkmypspmmsyencfxdjtzzlzgjzzoqwkzriqhvfqigezstcwcflbhyalipesdxddoyzcdskthyiasfdkgxgigirbixeaneynxedrbvfybpxxonssjylrahpkklrjgvbzllsfinxtcdkejynhxekkehqlizormtlmglsakfrketakpgsziogdgtfpwzeufejryluxjjuwfcgvbipmkrgtnfupqshysughfgnxtfgazdybvdtiqiimxibxlxhzsorqgshaauhgjszlfhaoxzfhnnfsdnsxqjmhaliuhavzqielpcqjzbzelrnruhqzxrynexubqpkhsoqrearfdxmliaiamfaorysjpuldzvuqnddmskegfmrxdgeonfhyuzhpgmghsvkvolhvrdyqvgqxshjjzrozkhkrsoktmvpkllizosqdsmybnwmybkyfqxyaeumgcubtdwt" +
                "lbxuhcowgqvvrraazmeoamazjbljfzfvjmjhiifpskinydncsbcoefknvjzqinbfvgyyfjzqewxwdzivzeemqvxmjrsuxavjeqtbklezsqeas";
        Solution solution = new Solution();
        int result = solution.largestVariance(s);

        System.out.printf("result: %s\n", String.valueOf(result));
    }

}
