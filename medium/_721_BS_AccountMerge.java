package leetcode.medium;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

/**
 *
 * 721. Accounts Merge
 * Medium
 *
 * 4462
 *
 * 801
 *
 * Add to List
 *
 * Share
 * Given a list of accounts where each element accounts[i] is a list of strings, where the first element accounts[i][0] is a name, and the rest of the elements are emails representing emails of the account.
 *
 * Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.
 *
 * After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails in sorted order. The accounts themselves can be returned in any order.
 *
 *
 *
 * Example 1:
 *
 * Input: accounts = [["John","johnsmith@mail.com","john_newyork@mail.com"],["John","johnsmith@mail.com","john00@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Output: [["John","john00@mail.com","john_newyork@mail.com","johnsmith@mail.com"],["Mary","mary@mail.com"],["John","johnnybravo@mail.com"]]
 * Explanation:
 * The first and second John's are the same person as they have the common email "johnsmith@mail.com".
 * The third John and Mary are different people as none of their email addresses are used by other accounts.
 * We could return these lists in any order, for example the answer [['Mary', 'mary@mail.com'], ['John', 'johnnybravo@mail.com'],
 * ['John', 'john00@mail.com', 'john_newyork@mail.com', 'johnsmith@mail.com']] would still be accepted.
 * Example 2:
 *
 * Input: accounts = [["Gabe","Gabe0@m.co","Gabe3@m.co","Gabe1@m.co"],["Kevin","Kevin3@m.co","Kevin5@m.co","Kevin0@m.co"],["Ethan","Ethan5@m.co","Ethan4@m.co","Ethan0@m.co"],["Hanzo","Hanzo3@m.co","Hanzo1@m.co","Hanzo0@m.co"],["Fern","Fern5@m.co","Fern1@m.co","Fern0@m.co"]]
 * Output: [["Ethan","Ethan0@m.co","Ethan4@m.co","Ethan5@m.co"],["Gabe","Gabe0@m.co","Gabe1@m.co","Gabe3@m.co"],["Hanzo","Hanzo0@m.co","Hanzo1@m.co","Hanzo3@m.co"],["Kevin","Kevin0@m.co","Kevin3@m.co","Kevin5@m.co"],["Fern","Fern0@m.co","Fern1@m.co","Fern5@m.co"]]
 *
 *
 * Constraints:
 *
 * 1 <= accounts.length <= 1000
 * 2 <= accounts[i].length <= 10
 * 1 <= accounts[i][j].length <= 30
 * accounts[i][0] consists of English letters.
 * accounts[i][j] (for j > 0) is a valid email.
 *
 * Success
 * Details
 * Runtime: 2142 ms, faster than 5.02% of Java online submissions for Accounts Merge.
 * Memory Usage: 151.3 MB, less than 5.05% of Java online submissions for Accounts Merge.
 * Next challenges:
 * Redundant Connection
 * Sentence Similarity
 * Sentence Similarity II
 */
public class _721_BS_AccountMerge {

    private static class Account implements Comparable<Account>{
        private String name;
        private List<String> emails;
        public Account(String name, List<String> emails) {
            this.name = name;
            this.emails = emails;
        }

        /**
         */
        @Override
        public int compareTo(Account other) {
            if (other == null) {
                return -1;
            }
            if (this.emails == null || emails.isEmpty() || emails.get(0) == null) {
                return -1;
            }
            if (other.emails == null || other.emails.isEmpty() || other.emails.get(0) == null) {
                return 1;
            }

            return this.emails.get(0).compareTo(other.emails.get(0));
        }

        public void addEmails(List<String> addItems) {
            var set = new TreeSet<String>(emails);
            set.addAll(addItems);
            this.emails = new LinkedList<String>(set);
        }

        public List<String> toAccountString() {
            var accountString = new LinkedList<String>();
            accountString.add(name);
            accountString.addAll(new TreeSet<String>(emails));
            return accountString;
        }

    }

    class Solution {

        public List<List<String>> accountsMerge(List<List<String>> accounts) {
            if (accounts == null || accounts.isEmpty()) {
                return List.of();
            }


            var accountList = new LinkedList<Account>();
            for (var account : accounts ) {
                List<String> emails = account;
                String name = emails.remove(0);
                Collections.sort(emails);
                accountList.add(new Account(name, emails));
            }


            //Collections.sort(accountList);
            List<List<String>> result = new LinkedList<>();
            var buffer = new LinkedList<Account>();

            /**
             * Three Queues, the account queue, buffer queue, and merged email queue
             */
            while(!accountList.isEmpty()) {
                var account = accountList.removeFirst();
                var emails = new LinkedList<String>(account.emails);

                while (!emails.isEmpty()) {

                    var email = emails.removeFirst();
                    while(!accountList.isEmpty()) {
                        var target = accountList.removeFirst();
                        int idx = Collections.binarySearch(target.emails, email);

                        if (idx >= 0) {
                            account.emails.addAll(target.emails);
                            target.emails.remove(email);
                            emails.addAll(target.emails);
                            //System.out.printf(" list search on: %s, email: %s, idx: %s\n", target.emails, email, idx);
                        } else {
                            buffer.add(target);
                        }
                    }

                    accountList.addAll(buffer);
                    buffer.clear();

                }
                result.add(account.toAccountString());
            }
            return result;
        }
    }
}
