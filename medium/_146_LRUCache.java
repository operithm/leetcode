package leetcode.medium;

import java.util.HashMap;
import java.util.Map;

/**
 Success
 Details
 Runtime: 128 ms, faster than 18.40% of Java online submissions for LRU Cache.
 Memory Usage: 164.6 MB, less than 17.93% of Java online submissions for LRU Cache.
 Next challenges:
 Design In-Memory File System
 Design Compressed String Iterator
 Design Most Recently Used Queue

 146. LRU Cache
 Medium

 Design a data structure that follows the constraints of a Least Recently Used (LRU) cache.

 Implement the LRUCache class:

 LRUCache(int capacity) Initialize the LRU cache with positive size capacity.
 int get(int key) Return the value of the key if the key exists, otherwise return -1.
 void put(int key, int value) Update the value of the key if the key exists. Otherwise, add the key-value pair to the cache. If the number of keys exceeds the capacity from this operation, evict the least recently used key.
 The functions get and put must each run in O(1) average time complexity.



 Example 1:

 Input
 ["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
 [[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
 Output
 [null, null, null, 1, null, -1, null, -1, 3, 4]

 Explanation
 LRUCache lRUCache = new LRUCache(2);
 lRUCache.put(1, 1); // cache is {1=1}
 lRUCache.put(2, 2); // cache is {1=1, 2=2}
 lRUCache.get(1);    // return 1
 lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
 lRUCache.get(2);    // returns -1 (not found)
 lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
 lRUCache.get(1);    // return -1 (not found)
 lRUCache.get(3);    // return 3
 lRUCache.get(4);    // return 4


 Constraints:

 1 <= capacity <= 3000
 0 <= key <= 104
 0 <= value <= 105
 At most 2 * 105 calls will be made to get and put.
 */
public class _146_LRUCache {

    private static class Node {
        private int key;
        private int value;
        private Node prev;
        private Node next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("%s->%s", key, value);
        }
    }

    class LRUCache {
        private Node head;
        private Node tail;
        private final Map<Integer, Node> cache;
        private final int capacity;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();
            this.head = null;
            this.tail = null;
        }

        public int get(int key) {
            if (cache.keySet().contains(key)) {
                Node node = cache.get(key);
                update(node, node.value);
                return node.value;
            } else {
                return -1;
            }
        }

        public void put(int key, int value) {
            Node val = cache.get(key);
            if (val == null) {
                if (cache.size() == capacity) {
                    evict();
                }
                val = new Node(key, value);
                addFirst(val);
            } else {
                update(val, value);
            }
            cache.put(key, val);
        }

        private void addFirst(Node node) {
            //System.out.printf(" add: %s, head: %s, tail: %s\n", node, head, tail);
            if (head == null) {
                head = node;
                tail = node;
            } else {
                node.next = head;
                head.prev = node;
                if (tail == head) {
                    tail.prev = node;
                }
                head = node;
            }
        }

        private void update(Node node, int value) {
            node.value = value;
            /**
             * only update value, if the update one is head
             */
            if (node.key == head.key) {
                return;
            }

            Node prev = node.prev;
            /*
             * Remove node
             */
            if (node.key == tail.key) {
                tail = prev;
            }

            if (node.next != null) {
                node.next.prev = prev;
            }
            if (prev != null) {
                prev.next = node.next;
            }

            /**
             * Move node to head
             *
             */
            Node next = head;
            node.next = next;
            if (next != null) {
                next.prev = node;
            }

            head = node;
            head.prev = null;

        }

        private void evict() {
            /**
             * One element cache
             */
            int evict = tail.key;
            if (head == tail) {
                head = null;
                tail = null;
            } else {
                Node prev = tail.prev;
                tail = prev;
                tail.next = null;
            }
            cache.remove(evict);
        }
    }

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
