package javaInterview;

// Java LinkedHashMap Deep Dive - 20 Detailed Examples

import java.util.*;

public class LinkedHashMapExamples {
    public static void main(String[] args) {

        // 1. Basic LinkedHashMap insertion
        LinkedHashMap<String, Integer> map = new LinkedHashMap<>();
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);
        System.out.println("1: " + map);
        /*
         * LinkedHashMap stores entries in insertion order.
         * Internally uses a hash table + doubly-linked list.
         * This gives predictable iteration order unlike HashMap.
         * Great for order-sensitive use cases.
         */

        // 2. Access order mode for LRU
        LinkedHashMap<Integer, String> lru = new LinkedHashMap<>(16, 0.75f, true);
        lru.put(1, "A");
        lru.put(2, "B");
        lru.put(3, "C");
        lru.get(1);
        lru.get(2);
        System.out.println("2: " + lru);
        /*
         * By setting accessOrder=true, order changes on get/put.
         * Most recently accessed entries move to the end.
         * Ideal for building LRU caches.
         * Still maintains O(1) get and put operations.
         */

        // 3. Null keys and values
        map.put(null, 99);
        map.put("date", null);
        System.out.println("3: " + map);
        /*
         * LinkedHashMap allows one null key and multiple null values.
         * Same behavior as HashMap regarding nulls.
         * Useful for nullable data mapping.
         * Key lookup for null works like any other key.
         */

        // 4. Iteration order confirmation
        for (String key : map.keySet()) {
            System.out.print(key + " ");
        }
        System.out.println("\n4: Values=" + map.values());
        /*
         * keySet() and values() reflect insertion/access order.
         * Under the hood, a linked list is used for order tracking.
         * Order is stable across inserts and iteration.
         * Perfect for deterministic traversals.
         */

        // 5. Removing eldest entry
        LinkedHashMap<Integer, String> limited = new LinkedHashMap<>(5, 0.75f, true) {
            protected boolean removeEldestEntry(Map.Entry<Integer, String> eldest) {
                return size() > 3; // Keep size ≤ 3
            }
        };
        limited.put(1, "One");
        limited.put(2, "Two");
        limited.put(3, "Three");
        limited.put(4, "Four");
        System.out.println("5: " + limited);
        /*
         * Overriding removeEldestEntry() lets you implement LRU eviction.
         * Each time put() is called, this method checks whether to remove first entry.
         * Useful in memory-bound caching mechanisms.
         * The eldest refers to the head of the linked list.
         */

        // 6. Checking order stability after overwriting
        map.put("banana", 200); // overwrite
        System.out.println("6: " + map);
        /*
         * Overwriting a value does not affect insertion order.
         * Key remains in its original position.
         * This is crucial when updates should not impact iteration order.
         * Demonstrates that only accessOrder=true can alter order.
         */

        // 7. containsKey and containsValue usage
        System.out.println("7a: containsKey='apple'? " + map.containsKey("apple"));
        System.out.println("7b: containsValue=20? " + map.containsValue(20));
        /*
         * containsKey() checks for presence of key (O(1)).
         * containsValue() scans values (O(n)).
         * Often used in validations or defaulting logic.
         * Reliable for membership checks before update/remove.
         */

        // 8. clone() creates a shallow copy
        LinkedHashMap<String, Integer> cloned = (LinkedHashMap<String, Integer>) map.clone();
        System.out.println("8: " + cloned);
        /*
         * clone() creates a shallow copy of the map.
         * References to objects are copied, not deep objects themselves.
         * Modifying mutable values inside still affects original.
         * Useful for snapshotting map state quickly.
         */

        // 9. Iterating using entrySet()
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println("9: " + entry.getKey() + " -> " + entry.getValue());
        }
        /*
         * entrySet() gives access to full key-value pairs.
         * Ideal for detailed processing of each entry.
         * Order remains insertion-based unless access order is enabled.
         * Avoid structural modifications during iteration.
         */

        // 10. putAll() bulk insert
        LinkedHashMap<String, Integer> copy = new LinkedHashMap<>();
        copy.putAll(map);
        System.out.println("10: " + copy);
        /*
         * putAll() copies all entries from another map.
         * Insertion order is preserved in LinkedHashMap.
         * Useful when merging configs, caches, or templates.
         * Keys are shallow copied, not deep cloned.
         */

        // 11. equals() vs == on map comparisons
        System.out.println("11a: equals = " + map.equals(copy)); // true
        System.out.println("11b: == = " + (map == copy));       // false
        /*
         * equals() compares content: same keys and values.
         * == compares object references (identity).
         * Use equals() for logical comparison in testing and validation.
         * Very common confusion point in beginner interviews.
         */

        // 12. forEach with lambda (Java 8+)
        map.forEach((k, v) -> System.out.println("12: " + k + " = " + v));
        /*
         * forEach is a functional way to iterate in Java 8+.
         * It avoids explicit loops and boilerplate.
         * Useful for compact and readable traversal.
         * Works on all Map implementations.
         */

        // 13. compute() for dynamic update
        map.compute("apple", (k, v) -> v == null ? 0 : v + 5);
        System.out.println("13: " + map);
        /*
         * compute() recalculates a value for a given key.
         * It handles nulls and missing keys flexibly.
         * Used for value accumulation or transformations.
         * Returns null to remove key from map.
         */

        // 14. Access order vs insertion order
        LinkedHashMap<String, String> accessMap = new LinkedHashMap<>(16, 0.75f, true);
        accessMap.put("A", "1"); accessMap.put("B", "2"); accessMap.put("C", "3");
        accessMap.get("A"); accessMap.get("B");
        System.out.println("14: " + accessMap);
        /*
         * With accessOrder=true, recent get()/put() operations update order.
         * Keys move to the end upon access.
         * Used for building LRU and MRU structures.
         * Still supports all normal Map operations.
         */

        // 15. KeySet view behavior
        Set<String> keys = map.keySet();
        System.out.println("15: Keys = " + keys);
        /*
         * keySet() provides a view backed by the original map.
         * Changes to keySet reflect in the map and vice versa.
         * Order is preserved as in the original LinkedHashMap.
         * Don't modify during iteration without iterator.remove().
         */

        // 16. Value collection view
        Collection<Integer> values = map.values();
        System.out.println("16: Values = " + values);
        /*
         * values() returns a live view of the value list.
         * Order matches the key insertion order.
         * Changes to map affect this collection instantly.
         * Not designed for key-based updates—read-only use ideal.
         */

        // 17. replaceAll() bulk value update
//        map.replaceAll((k, v) -> v + 1);
//        System.out.println("17: " + map);
        /*
         * replaceAll() applies a transformation to each value.
         * Works in-place and modifies map directly.
         * Great for revaluing entries in one pass.
         * Can be used with lambdas for concise logic.
         */

        // 18. size(), isEmpty(), and clear()
        System.out.println("18a: size = " + map.size());
        System.out.println("18b: isEmpty = " + map.isEmpty());
        map.clear();
        System.out.println("18c: cleared size = " + map.size());
        /*
         * Basic map queries include size() and isEmpty().
         * clear() removes all entries and resets internal structure.
         * After clear(), iteration yields no elements.
         * Useful for recycling map instances.
         */

        // 19. Preserving order for JSON or APIs
        LinkedHashMap<String, Object> apiData = new LinkedHashMap<>();
        apiData.put("id", 101);
        apiData.put("name", "Alice");
        apiData.put("active", true);
        System.out.println("19: API Ordered = " + apiData);
        /*
         * LinkedHashMap is ideal for API responses where key order matters.
         * JSON serializers often respect insertion order.
         * Improves readability and predictability of API output.
         * Useful for form submissions, config files, etc.
         */

        // 20. Performance vs HashMap
        Map<String, Integer> hashMap = new HashMap<>();
        Map<String, Integer> linkedHash = new LinkedHashMap<>();
        for (int i = 0; i < 1000; i++) {
            hashMap.put("key" + i, i);
            linkedHash.put("key" + i, i);
        }
        System.out.println("20a: HashMap size=" + hashMap.size());
        System.out.println("20b: LinkedHashMap size=" + linkedHash.size());
        /*
         * LinkedHashMap has a slight overhead due to linked list maintenance.
         * However, iteration is faster when predictable order is required.
         * For large data sets where order is not important, HashMap is more memory efficient.
         * Choose based on iteration and ordering needs.
         */
    }
}


