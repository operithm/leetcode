package leetcode.medium;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 * Success
 * Details
 * Runtime: 71 ms, faster than 24.66% of Java online submissions for Kill Process.
 * Memory Usage: 50.4 MB, less than 95.64% of Java online submissions for Kill Process.
 * Next challenges:
 * Serialize and Deserialize BST
 * Number of Ways of Cutting a Pizza
 * Tree of Coprimes
 *
 * 582. Kill Process
 * Medium
 *
 * 974
 *
 * 19
 *
 * Add to List
 *
 * Share
 * You have n processes forming a rooted tree structure. You are given two integer arrays pid and ppid, where pid[i] is the ID of the ith process and ppid[i] is the ID of the ith process's parent process.
 *
 * Each process has only one parent process but may have multiple children processes. Only one process has ppid[i] = 0, which means this process has no parent process (the root of the tree).
 *
 * When a process is killed, all of its children processes will also be killed.
 *
 * Given an integer kill representing the ID of a process you want to kill, return a list of the IDs of the processes that will be killed. You may return the answer in any order.
 *
 *
 *
 * Example 1:
 *
 *
 * Input: pid = [1,3,10,5], ppid = [3,0,5,3], kill = 5
 * Output: [5,10]
 * Explanation: The processes colored in red are the processes that should be killed.
 * Example 2:
 *
 * Input: pid = [1], ppid = [0], kill = 1
 * Output: [1]
 *
 *
 * Constraints:
 *
 * n == pid.length
 * n == ppid.length
 * 1 <= n <= 5 * 104
 * 1 <= pid[i] <= 5 * 104
 * 0 <= ppid[i] <= 5 * 104
 * Only one process has no parent.
 * All the values of pid are unique.
 * kill is guaranteed to be in pid.
 */
public class _582_BFS_KillProcess {

    class Solution {
        public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
            if (pid == null || pid.isEmpty() || ppid == null || ppid.isEmpty()) {
                return List.of();
            }

            int n = pid.size();
            var graph = new TreeMap<Integer, List<Integer>>();
            graph.put(0, new LinkedList<>());
            for (int i = 0; i < n; i++) {
                int p = ppid.get(i);
                var list = graph.getOrDefault(p, new LinkedList<>());
                list.add(pid.get(i));
                graph.put(p, list);
            }

            return BFS(graph, kill);

        }

        private List<Integer> BFS (Map<Integer, List<Integer>> graph, int kill) {
            var killList = new LinkedList<Integer>();
            var queue = new LinkedList<List<Integer>>();
            queue.offer(List.of(kill));

            while(!queue.isEmpty()) {
                var list = queue.poll();
                var next = new LinkedList<Integer>();
                for (int v : list) {
                    killList.add(v);
                    var u = graph.getOrDefault(v, new LinkedList<>());
                    if (!u.isEmpty()) {
                        next.addAll(u);
                    }
                }

                if (!next.isEmpty()) {
                    queue.offer(next);
                }
            }

            return killList;
        }
    }
}
