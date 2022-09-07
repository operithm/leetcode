package leetcode.hard;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class _460_LFUCache {

    static class LFUCache {

        private static class Data {
            private final int key;
            private int value;
            private int count;

            public Data(int key, int value) {
                this.key = key;
                this.value = value;
                count();
            }

            public void count() {
                count += 1;
            }

            public int getCount() {
                return count;
            }

            @Override
            public String toString() {
                return String.format(" ->%s (%s)", value, count);
            }

        }

        private final Map<Integer, Data> cache = new TreeMap<>();
        private final Map<Integer, List<Integer>> counts = new TreeMap<>();
        private int leastCount;
        private final int capacity;

        public LFUCache(int capacity) {
            if (capacity < 0) {
                throw new IllegalArgumentException(String.format("Capacity %s is negative.", capacity));
            }
            this.capacity = capacity;
            this.leastCount = 1;
        }

        public int get(int key) {
            Data data = cache.get(key);
            //System.out.printf("GET: %s, %s, %s\n", leastCount, key, data);
            if (data != null) {
                updateCount(data);
                return data.value;
            }
            return -1;
        }

        public void put(int key, int value) {
            //System.out.printf("PUT: %s, %s, %s\n", leastCount, key, value);
            if (capacity > 0) {
                Data cached = cache.get(key);
                if (cached != null) {
                    cached.value = value;
                    updateCount(cached);
                } else {
                    cached = new Data(key, value);

                    if(cache.size() == capacity) {
                        //System.out.printf("PUT: %s, %s, %s\n", leastCount, key, value);
                        invalidate();
                    }

                    leastCount = 1;
                    List<Integer> keys = counts.getOrDefault(leastCount, new LinkedList<>());
                    keys.add(key);
                    counts.put(leastCount, keys);

                }

                cache.put(key, cached);
                //System.out.printf("AFTER PUT: %s, %s, %s\n", leastCount, cache, counts);

            }

            //System.out.printf("q: %s, cache: %s\n", queue, cache);

        }


        private void updateCount(Data cached) {
            //System.out.printf("UPDATE: %s, counts: %s\n", cached, counts);
            int count = cached.count;
            int key = cached.key;

            List<Integer> keys = counts.get(count);
            keys.remove(Integer.valueOf(key));

            if (keys.isEmpty()) {
                counts.remove(count);
                if (leastCount == count) {
                    leastCount ++;
                }
            } else {
                counts.put(count, keys);
            }

            cached.count();

            keys = counts.getOrDefault(count + 1, new LinkedList<>());
            keys.add(cached.key);
            counts.put(count + 1, keys);
            cache.put(key, cached);

            //System.out.printf("AFTER UPDATE: cache: %s, counts: %s\n", cache, counts);

        }

        private void invalidate() {
            List<Integer> keys = counts.get(leastCount);
            int key = keys.get(0);
            keys.remove(Integer.valueOf(key));
            if (keys.isEmpty()) {
                counts.remove(leastCount);
                leastCount = 1;
            } else {
                counts.put(leastCount, keys);
            }
            //System.out.printf("removing: %s\n", key);
            cache.remove(key);
        }

    }

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
}
