package javaInterview;

// Java ConcurrentSkipListMap Deep Dive - 30 Detailed Examples

import java.util.*;
import java.util.concurrent.*;

public class ConcurrentSkipListMapExamples {
    public static void main(String[] args) {

        // 1. Basic insertion
        ConcurrentSkipListMap<Integer, String> map = new ConcurrentSkipListMap<>();
        map.put(3, "Cherry");
        map.put(1, "Apple");
        map.put(2, "Banana");
        System.out.println("1: " + map);
        /*
         * ConcurrentSkipListMap is a thread-safe sorted map.
         * It maintains keys in ascending natural order.
         * Internally uses a Skip List (layered linked structure) for O(log n) operations.
         * Provides non-blocking concurrency and sorted map navigation.
         */

        // 2. Descending order using descendingMap()
        NavigableMap<Integer, String> descending = map.descendingMap();
        System.out.println("2: Descending map = " + descending);
        /*
         * descendingMap returns a view in reverse order.
         * Changes to this view reflect in the original map.
         * It's not a separate copy but backed by the original structure.
         * Useful for reverse sorting or rank-based access.
         */

        // 3. headMap view
        System.out.println("3: headMap(3) = " + map.headMap(3));
        /*
         * headMap returns keys strictly less than the specified key.
         * It's a view backed by the original map.
         * Modifications affect both views.
         * Great for bounded queries like ranges or upper thresholds.
         */

        // 4. tailMap view
        System.out.println("4: tailMap(2) = " + map.tailMap(2));
        /*
         * tailMap returns keys greater than or equal to the specified key.
         * Backed by the original map.
         * Efficient for lower-bound queries.
         * Maintains thread safety and consistency.
         */

        // 5. subMap view
        System.out.println("5: subMap(1, 3) = " + map.subMap(1, 3));
        /*
         * subMap gives a range view from 'fromKey' (inclusive) to 'toKey' (exclusive).
         * Very useful for paginated or windowed access.
         * Backed view allows updates and removals.
         * Time complexity remains O(log n).
         */

        // 6. pollFirstEntry
        Map.Entry<Integer, String> first = map.pollFirstEntry();
        System.out.println("6: polled first = " + first + ", map = " + map);
        /*
         * pollFirstEntry removes and returns the lowest entry.
         * Useful for priority-based access or queues.
         * Atomic and thread-safe operation.
         * Avoids contention by using non-blocking algorithms.
         */

        // 7. pollLastEntry
        map.put(0, "Apricot");
        Map.Entry<Integer, String> last = map.pollLastEntry();
        System.out.println("7: polled last = " + last + ", map = " + map);
        /*
         * pollLastEntry removes and returns highest entry.
         * Opposite of pollFirstEntry.
         * Both are used in bounded, ordered queue-like scenarios.
         * Maintains strict ordering guarantees.
         */

        // 8. firstKey and lastKey
        System.out.println("8a: firstKey = " + map.firstKey());
        System.out.println("8b: lastKey = " + map.lastKey());
        /*
         * firstKey returns the lowest key.
         * lastKey returns the highest key.
         * Constant time due to internal pointers.
         * Exception thrown if map is empty.
         */

        // 9. containsKey and containsValue
        System.out.println("9a: containsKey(2) = " + map.containsKey(2));
        System.out.println("9b: containsValue('Banana') = " + map.containsValue("Banana"));
        /*
         * containsKey uses log(n) time via skip list traversal.
         * containsValue is linear time — it scans values.
         * Thread-safe and consistent during concurrent read/write.
         * Useful for validation and safe lookups.
         */

        // 10. getOrDefault
        System.out.println("10: getOrDefault(99, 'Default') = " + map.getOrDefault(99, "Default"));
        /*
         * getOrDefault retrieves value or fallback if key missing.
         * Prevents null checks and simplifies access logic.
         * Does not insert the default value.
         * Thread-safe even under high concurrency.
         */

        // 11. putIfAbsent ensures atomic insert
        map.putIfAbsent(4, "Date");
        map.putIfAbsent(2, "Blueberry"); // Won't overwrite existing key
        System.out.println("11: After putIfAbsent = " + map);
        /*
         * putIfAbsent adds a key-value only if key not present.
         * Atomic operation ensuring thread-safe initialization.
         * Great for cache population without overwrites.
         * Internally uses compare-and-swap semantics.
         */

        // 12. replace method
        map.replace(2, "Blackberry");
        map.replace(4, "Date", "Dragonfruit");
        System.out.println("12: After replace = " + map);
        /*
         * replace updates values conditionally or unconditionally.
         * Allows atomic updates with optional old value check.
         * Helps maintain data integrity in concurrent environments.
         * Efficient for safe in-place modifications.
         */

        // 13. remove method
        map.remove(1);
        map.remove(3, "Cherry"); // No effect, not matching
        System.out.println("13: After remove = " + map);
        /*
         * remove deletes key-value mapping.
         * Can enforce match with value for safer deletion.
         * Atomic and non-blocking.
         * Ensures consistency in concurrent use cases.
         */

        // 14. keySet view
        Set<Integer> keys = map.keySet();
        System.out.println("14: Keys = " + keys);
        /*
         * keySet returns a navigable set of keys.
         * Reflects live updates to the map.
         * Thread-safe and consistent during iteration.
         * Ideal for custom iteration or subset operations.
         */

        // 15. values view
        Collection<String> values = map.values();
        System.out.println("15: Values = " + values);
        /*
         * values() returns a collection view of map values.
         * Maintains insertion order as per key sorting.
         * Use with caution for concurrent structural updates.
         * Not backed by a separate data structure.
         */

        // 16. entrySet view
        for (Map.Entry<Integer, String> entry : map.entrySet()) {
            System.out.println("16: Entry = " + entry);
        }
        /*
         * entrySet offers key-value pair traversal.
         * Safe from ConcurrentModificationException.
         * Works consistently during structural changes.
         * Most efficient way to iterate and modify simultaneously.
         */

        // 17. higherKey and lowerKey
        System.out.println("17a: higherKey(2) = " + map.higherKey(2));
        System.out.println("17b: lowerKey(4) = " + map.lowerKey(4));
        /*
         * higherKey returns the next greater key.
         * lowerKey returns the next smaller key.
         * Fast for range-based access or neighbor lookups.
         * O(log n) complexity with skip list traversal.
         */

        // 18. ceilingKey and floorKey
        System.out.println("18a: ceilingKey(2) = " + map.ceilingKey(2));
        System.out.println("18b: floorKey(3) = " + map.floorKey(3));
        /*
         * ceilingKey gives equal or next higher key.
         * floorKey gives equal or next lower key.
         * Helps implement boundary conditions.
         * Efficient under concurrent reads.
         */

        // 19. size method
        System.out.println("19: size = " + map.size());
        /*
         * size returns the number of key-value mappings.
         * Accurate even during concurrent modifications.
         * Linear-time cost as it may need to scan segments.
         * Prefer estimating size if absolute count not needed.
         */

        // 20. isEmpty method
        System.out.println("20: isEmpty = " + map.isEmpty());
        /*
         * Checks whether map has no entries.
         * Efficient and thread-safe.
         * Doesn’t block concurrent writers or readers.
         * Quick check before heavy computations.
         */

        // 21. concurrent iteration and modification
        for (Integer key : map.keySet()) {
            map.putIfAbsent(key + 10, "X" + key);
        }
        System.out.println("21: after concurrent putIfAbsent in loop = " + map);
        /*
         * ConcurrentSkipListMap allows safe iteration and modification.
         * No ConcurrentModificationException is thrown.
         * Useful in streaming updates or merging state.
         * Internally designed with lock-free traversal.
         */

        // 22. NavigableMap tail view
        NavigableMap<Integer, String> tail = map.tailMap(2, true);
        System.out.println("22: tailMap inclusive = " + tail);
        /*
         * tailMap with inclusivity allows precise range slicing.
         * The boolean parameter defines whether to include the key.
         * Extremely useful for precise boundary queries.
         * Thread-safe live view supports dynamic updates.
         */

        // 23. equals and hashCode
        ConcurrentSkipListMap<Integer, String> anotherMap = new ConcurrentSkipListMap<>(map);
        System.out.println("23a: maps equal? = " + map.equals(anotherMap));
        System.out.println("23b: hashCodes = " + map.hashCode() + " & " + anotherMap.hashCode());
        /*
         * equals checks if two maps contain the same mappings.
         * hashCode is consistent with equals.
         * Safe to use for comparison and caching purposes.
         * Not affected by thread contention.
         */

        // 24. comparator
        System.out.println("24: comparator = " + map.comparator());
        /*
         * If no custom comparator is given, returns null.
         * Meaning natural ordering is used.
         * Helps detect custom sorting logic.
         * Very useful for dynamically inspecting ordering rules.
         */

        // 25. Working with Strings as keys
        ConcurrentSkipListMap<String, Integer> nameMap = new ConcurrentSkipListMap<>();
        nameMap.put("Charlie", 30);
        nameMap.put("Alice", 10);
        nameMap.put("Bob", 20);
        System.out.println("25: Sorted nameMap = " + nameMap);
        /*
         * String keys sort alphabetically by default.
         * Allows easy indexing and prefix lookups.
         * Case-sensitive and based on natural Unicode order.
         * Excellent for building search indexes.
         */

        // 26. Custom comparator for descending sort
        ConcurrentSkipListMap<String, Integer> descNameMap = new ConcurrentSkipListMap<>(Comparator.reverseOrder());
        descNameMap.putAll(nameMap);
        System.out.println("26: Desc sorted = " + descNameMap);
        /*
         * Custom comparator allows flexible ordering logic.
         * Reversing sort is common in leaderboards or logs.
         * Ensures predictable sorted views.
         * Maintains all thread-safe guarantees.
         */

        // 27. removeIf using entrySet
        map.entrySet().removeIf(e -> e.getKey() > 10);
        System.out.println("27: After removeIf = " + map);
        /*
         * You can safely remove entries using functional filters.
         * entrySet() is live and reflects changes immediately.
         * Safer alternative to external iterator removal.
         * Highly efficient for conditional pruning.
         */

        // 28. clear()
        map.clear();
        System.out.println("28: after clear = " + map);
        /*
         * clear removes all entries atomically.
         * Useful for cache resets or re-initialization.
         * Safe for concurrent access.
         * Clears structure but keeps references alive.
         */

        // 29. Snapshot to another Map
        Map<Integer, String> snapshot = new TreeMap<>(map);
        System.out.println("29: snapshot = " + snapshot);
        /*
         * Snapshot creates a copy of current state.
         * Further mutations don’t reflect in the copy.
         * Useful for thread-unsafe consumers.
         * Maintains original sort order.
         */

        // 30. Summary
        System.out.println("30: ConcurrentSkipListMap is ideal for sorted, concurrent, non-blocking maps");
        /*
         * Provides O(log n) operations with high concurrency.
         * Suitable for real-time, ordered, scalable use cases.
         * Outperforms synchronized sorted maps under load.
         * Flexible, safe, and powerful for concurrent sorted collections.
         */
    }
}

