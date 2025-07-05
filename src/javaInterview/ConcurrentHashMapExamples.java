package javaInterview;

// Java ConcurrentHashMap Deep Dive - 30 Detailed Examples

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentHashMapExamples {
    public static void main(String[] args) {

        // 1. Basic usage of ConcurrentHashMap
        ConcurrentHashMap<Integer, String> map = new ConcurrentHashMap<>();
        map.put(1, "Apple");
        map.put(2, "Banana");
        map.put(3, "Cherry");
        System.out.println("1: " + map);
        /*
         * ConcurrentHashMap is a thread-safe implementation of Map.
         * It allows concurrent read and write without locking the entire map.
         * Internally uses segmented buckets to reduce contention.
         * Ideal for high-concurrency scenarios.
         */

        // 2. putIfAbsent - atomic insert
        map.putIfAbsent(4, "Date");
        map.putIfAbsent(1, "Avocado"); // won't overwrite existing value
        System.out.println("2: After putIfAbsent = " + map);
        /*
         * putIfAbsent inserts a value only if the key is not already present.
         * This is atomic and avoids overwriting existing mappings.
         * Useful in caches and multithreaded inserts.
         * Prevents race conditions during initialization.
         */

        // 3. computeIfAbsent - value initialized lazily
        map.computeIfAbsent(5, k -> "Elderberry");
        System.out.println("3: After computeIfAbsent = " + map);
        /*
         * computeIfAbsent runs the mapping function only if key is missing.
         * The lambda function is evaluated lazily and atomically.
         * Prevents duplicate initialization in concurrent inserts.
         * Common in caching and memoization.
         */

        // 4. computeIfPresent - conditional update
        map.computeIfPresent(2, (k, v) -> v + " Pie");
        System.out.println("4: After computeIfPresent = " + map);
        /*
         * computeIfPresent updates value only if key exists.
         * Helpful in atomic appending or transformation.
         * Thread-safe and prevents inconsistent state.
         * Can return null to remove the key.
         */

        // 5. merge - atomic insert or update
        map.merge(1, " Jam", (oldVal, newVal) -> oldVal + newVal);
        System.out.println("5: After merge = " + map);
        /*
         * merge either inserts or updates key atomically.
         * Takes remapping function for value combination.
         * Safe for concurrent usage in accumulators.
         * Reduces boilerplate for put-or-update logic.
         */

        // 6. forEach with lambda
        map.forEach((k, v) -> System.out.println("6: Key = " + k + ", Value = " + v));
        /*
         * forEach provides parallel-safe iteration.
         * Internally locks buckets during access.
         * Can safely read while other threads modify the map.
         * Uses built-in concurrent traversal logic.
         */

        // 7. size() returns current count
        System.out.println("7: size = " + map.size());
        /*
         * Returns number of key-value mappings.
         * Accurate even during concurrent updates.
         * Internally iterates over segments.
         * May have minor performance cost on large maps.
         */

        // 8. containsKey and containsValue
        System.out.println("8a: containsKey(1) = " + map.containsKey(1));
        System.out.println("8b: containsValue('Banana Pie') = " + map.containsValue("Banana Pie"));
        /*
         * containsKey is fast and checks bucket directly.
         * containsValue may scan the entire map (slower).
         * Both are thread-safe and non-blocking.
         * Use containsKey for performant presence checks.
         */

        // 9. remove(key) and remove(key, value)
        map.remove(3);
        boolean wasRemoved = map.remove(2, "Banana Pie");
        System.out.println("9a: map after remove = " + map);
        System.out.println("9b: was conditional removed? = " + wasRemoved);
        /*
         * remove(key) deletes key regardless of value.
         * remove(key, value) only deletes if both match.
         * Helps avoid overwrites during concurrency.
         * Useful for compare-and-swap operations.
         */

        // 10. replace(key, value) vs replace(key, oldVal, newVal)
        map.replace(5, "Elderberry Jam");
        boolean replaced = map.replace(4, "Date", "Fig");
        System.out.println("10: replace operations = " + map + ", replaced? = " + replaced);
        /*
         * replace updates value atomically.
         * Optional overload matches old value for safe update.
         * Prevents blind overwrites.
         * Great for optimistic locking style updates.
         */

        // 11. keySet view is backed by the map
        Set<Integer> keys = map.keySet();
        keys.remove(4);
        System.out.println("11: After keySet.remove = " + map);
        /*
         * keySet returns a view backed by the original map.
         * Modifications to the set affect the map and vice versa.
         * Thread-safe and safe to use concurrently.
         * Reflects real-time changes.
         */

        // 12. values() view
        Collection<String> values = map.values();
        System.out.println("12: values = " + values);
        /*
         * values() returns a live view of the values.
         * Order is not guaranteed and reflects real-time updates.
         * Use cautiously in concurrent writes.
         * Still thread-safe for iteration.
         */

        // 13. entrySet view
        Set<Map.Entry<Integer, String>> entries = map.entrySet();
        for (Map.Entry<Integer, String> entry : entries) {
            System.out.println("13: entry = " + entry);
        }
        /*
         * entrySet is the most efficient way to traverse both key and value.
         * Changes via iterator or entry.setValue() reflect in the map.
         * Recommended for atomic iteration and modification.
         * Fully concurrent and avoids ConcurrentModificationException.
         */

        // 14. putAll() from another map
        Map<Integer, String> temp = Map.of(6, "Grape", 7, "Honeydew");
        map.putAll(temp);
        System.out.println("14: After putAll = " + map);
        /*
         * putAll copies all entries from source map.
         * Operates atomically per entry, not as a batch.
         * Useful for merging maps without locking external map.
         * All keys and values must be non-null.
         */

        // 15. parallelism support with forEach()
        map.forEach(1, (k, v) -> System.out.println("15: ForkJoin ForEach " + k + "=" + v));
        /*
         * forEach with parallelism threshold uses ForkJoinPool.
         * Allows parallel traversal of entries.
         * Improves throughput for large maps.
         * Threshold defines minimum size to fork.
         */

        // 16. search() with early exit
        String result = map.search(1, (k, v) -> v.startsWith("E") ? v : null);
        System.out.println("16: search result = " + result);
        /*
         * search allows early exit once non-null result is found.
         * Runs in parallel if size exceeds threshold.
         * Optimized for lookup over values or keys.
         * Efficient in large data sets.
         */

        // 17. reduce() operation
        String reduced = map.reduce(1, (k, v) -> v, (v1, v2) -> v1 + "," + v2);
        System.out.println("17: reduce result = " + reduced);
        /*
         * reduce performs map-reduce style aggregation.
         * KeyMapper + Reducer pattern applied in parallel.
         * Good for counting, summarizing, or transforming data.
         * Highly parallel and thread-safe.
         */

        // 18. Concurrent update via threads
        Runnable task = () -> map.merge(8, "Kiwi", (oldVal, newVal) -> oldVal + " + " + newVal);
        Thread t1 = new Thread(task);
        Thread t2 = new Thread(task);
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (InterruptedException e) {}
        System.out.println("18: After concurrent threads = " + map);
        /*
         * Concurrent threads updating same key is safe.
         * merge ensures atomicity under contention.
         * Avoids data corruption and locking issues.
         * Used widely in concurrent counting, maps, and queues.
         */

        // 19. null key/value not allowed
        try {
            map.put(null, "X");
        } catch (NullPointerException e) {
            System.out.println("19a: null key rejected");
        }
        try {
            map.put(10, null);
        } catch (NullPointerException e) {
            System.out.println("19b: null value rejected");
        }
        /*
         * ConcurrentHashMap disallows null keys and values.
         * This prevents ambiguity in concurrent operations.
         * Avoids accidental null dereferencing.
         * Safer than HashMap in multi-threaded context.
         */

        // 20. clone() creates shallow copy
        ConcurrentHashMap<Integer, String> clone = new ConcurrentHashMap<>(map);
        System.out.println("20: cloned map = " + clone);
        /*
         * clone() returns a shallow copy of the map.
         * Useful for snapshots or backups.
         * Modifying clone does not affect original.
         * Still thread-safe individually.
         */

        // 21. Using clear()
        ConcurrentHashMap<Integer, String> tempMap = new ConcurrentHashMap<>(map);
        tempMap.clear();
        System.out.println("21: after clear() = " + tempMap);
        /*
         * clear() removes all entries from the map.
         * The operation is atomic but can be costly.
         * Useful for reset or cache eviction.
         * Doesn't block other threads.
         */

        // 22. replaceAll with function
        map.replaceAll((k, v) -> v.toUpperCase());
        System.out.println("22: after replaceAll = " + map);
        /*
         * replaceAll updates each entry based on a function.
         * The operation is done atomically per entry.
         * Ideal for transformation or normalization.
         * Thread-safe across the map.
         */

        // 23. default value handling
        String defaultVal = map.getOrDefault(99, "Default");
        System.out.println("23: getOrDefault = " + defaultVal);
        /*
         * getOrDefault returns a fallback if key not found.
         * Avoids extra null checks or exception handling.
         * Does not insert the key.
         * Efficient and safe for reads.
         */

        // 24. snapshot using stream
        List<String> snapshot = new ArrayList<>(map.values());
        System.out.println("24: snapshot of values = " + snapshot);
        /*
         * Create a snapshot safely using a constructor.
         * Avoids concurrent modification issues during iteration.
         * Good for passing to external systems.
         * Snapshot is not live or reactive.
         */

        // 25. capacity and performance
        ConcurrentHashMap<String, String> bigMap = new ConcurrentHashMap<>(16, 0.75f, 4);
        System.out.println("25: bigMap created with custom concurrency level");
        /*
         * Constructor allows tuning concurrency level.
         * Higher levels allow more threads to write concurrently.
         * Affects number of internal segments.
         * Use defaults unless profiling shows need.
         */

        // 26. Weak consistency of iterators
        for (String val : map.values()) {
            System.out.println("26: iterated value = " + val);
        }
        /*
         * Iterators are weakly consistent.
         * They reflect state at the time of creation.
         * Safe from ConcurrentModificationException.
         * May or may not include concurrent updates.
         */

        // 27. removeIf on keySet
        map.keySet().removeIf(k -> k > 6);
        System.out.println("27: after removeIf = " + map);
        /*
         * removeIf removes matching entries based on predicate.
         * Backed view ensures safe deletion.
         * Thread-safe and atomic per key.
         * Cleaner alternative to explicit remove.
         */

        // 28. size computation under load
        System.out.println("28: size under concurrency = " + map.mappingCount());
        /*
         * mappingCount() is more scalable than size().
         * Accurate under concurrent access.
         * Uses LongAdder internally.
         * Ideal for high-contention environments.
         */

        // 29. fail-safe operations
        map.forEach(1, (k, v) -> {
            if (v.startsWith("A")) map.remove(k);
        });
        System.out.println("29: after conditional removal = " + map);
        /*
         * You can safely mutate map during forEach.
         * Only affects current bucket segment.
         * Safe due to fail-safe iteration design.
         * Recommended over external loops.
         */

        // 30. Summary and modern usage
        System.out.println("30: ConcurrentHashMap is preferred for thread-safe maps in Java");
        /*
         * CHM is ideal for multi-threaded access to maps.
         * Replaces synchronizedMap and Hashtable.
         * Offers lock-free reads and fine-grained writes.
         * Modern, scalable, and safe for concurrent use.
         */
    }
}
