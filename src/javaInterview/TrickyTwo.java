package javaInterview;

// Java Map Interface Deep Dive - 20 Detailed Examples

import java.util.*;
import java.util.stream.Collectors;

public class TrickyTwo {
    public static void main(String[] args) {

        // 1. Basic HashMap insertion and retrieval
        Map<String, Integer> map1 = new HashMap<>();
        map1.put("apple", 1);
        map1.put("banana", 2);
        System.out.println("1: " + map1.get("apple")); // 1
        /*
         * HashMap stores key-value pairs where keys must be unique.
         * Keys are hashed using hashCode() and then placed into buckets.
         * Retrieval uses equals() to resolve hash collisions.
         * Average time complexity for put/get is O(1).
         */

        // 2. Handling null keys and values in HashMap
        map1.put(null, 100);
        map1.put("cherry", null);
        System.out.println("2: " + map1); // supports one null key
        /*
         * HashMap allows one null key and multiple null values.
         * null key is stored in bucket 0.
         * This makes HashMap useful when nulls must be tolerated in keys/values.
         * TreeMap does NOT allow null keys as it uses natural ordering.
         */

        // 3. Duplicate key overwrite behavior
        map1.put("apple", 10); // overwrite
        System.out.println("3: " + map1.get("apple")); // 10
        /*
         * If a key already exists, put() replaces its value with the new one.
         * This does NOT create a duplicate key entry.
         * The key's hashCode remains the same, and the new value is set.
         * Useful when needing to update values without adding new entries.
         */

        // 4. TreeMap natural ordering
        Map<String, String> treeMap = new TreeMap<>();
        treeMap.put("zebra", "Z");
        treeMap.put("apple", "A");
        treeMap.put("mango", "M");
        System.out.println("4: " + treeMap);
        /*
         * TreeMap maintains sorted order of keys (natural order or comparator).
         * Internally implemented as a Red-Black Tree (balanced BST).
         * Retrieval is O(log n) due to tree traversal.
         * Does NOT allow null keys as they cannot be compared.
         */

        // 5. LinkedHashMap maintains insertion order
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("A", 1);
        linkedMap.put("C", 3);
        linkedMap.put("B", 2);
        System.out.println("5: " + linkedMap);
        /*
         * LinkedHashMap keeps insertion order using a doubly-linked list.
         * Great when iteration order must match insertion sequence.
         * Slightly more memory than HashMap due to linking structure.
         * Access time remains O(1), but deterministic order.
         */

        // More examples will continue...

        // 6. HashMap iteration using entrySet
        for (Map.Entry<String, Integer> entry : linkedMap.entrySet()) {
            System.out.println("6: Key=" + entry.getKey() + ", Value=" + entry.getValue());
        }
        /*
         * entrySet() returns a set of key-value mappings for iteration.
         * It's efficient compared to separate keySet() and get() calls.
         * Useful for reading both keys and values at once.
         * Avoid modifying the map during iteration unless using an iterator.
         */

        // 7. Using getOrDefault to provide fallback
        int val = linkedMap.getOrDefault("D", -1);
        System.out.println("7: " + val); // -1
        /*
         * getOrDefault avoids null checks by returning a fallback if key is missing.
         * It simplifies defensive code where defaults are needed.
         * Does not insert the default into the map.
         * Especially helpful in data summarization/counting logic.
         */

        // 8. Merging values using merge()
        linkedMap.merge("A", 10, Integer::sum);
        System.out.println("8: " + linkedMap); // A's value becomes 11
        /*
         * merge() combines existing value with new one using a BiFunction.
         * If key is missing, the value is inserted.
         * If key exists, merge logic (here sum) is applied.
         * Elegant for counting, accumulating or conditional updates.
         */

        // 9. putIfAbsent behavior
        linkedMap.putIfAbsent("C", 100);
        System.out.println("9: " + linkedMap); // C remains 3
        /*
         * putIfAbsent only inserts if the key is not already present.
         * Useful for lazy initialization of values.
         * Does not overwrite existing mappings.
         * Ensures thread-safe insertions without overwriting accidentally.
         */

        // 10. Removing entries safely
        linkedMap.remove("B");
        System.out.println("10: " + linkedMap); // B is removed
        /*
         * remove(key) deletes the mapping if it exists.
         * remove(key, value) deletes only if mapping matches.
         * Helps prevent accidental deletion due to race conditions.
         * Returns true if removal succeeded.
         */

        // 11. Replacing values conditionally
        linkedMap.replace("A", 999);
        System.out.println("11: " + linkedMap); // A replaced with 999
        /*
         * replace(key, value) updates only if key exists.
         * replace(key, oldVal, newVal) updates only if current value matches old.
         * Prevents unintentional overwrites.
         * Useful for atomic-style conditional updates.
         */

        // 12. Clearing the map
        linkedMap.clear();
        System.out.println("12: size = " + linkedMap.size()); // 0
        /*
         * clear() removes all entries in one shot.
         * After this, map is empty.
         * Useful for memory cleanup or resetting state.
         * Be cautious in concurrent environments.
         */

        // 13. Checking key and value existence
        System.out.println("13a: " + map1.containsKey("apple")); // true
        System.out.println("13b: " + map1.containsValue(10));   // true
        /*
         * containsKey and containsValue allow validation before actions.
         * containsKey is O(1) in HashMap, but containsValue is O(n).
         * Often used in filtering or defensive checks.
         * Useful in caching layers and lookup scenarios.
         */

        // 14. Using computeIfAbsent
        Map<String, List<String>> multiMap = new HashMap<>();
        multiMap.computeIfAbsent("fruits", k -> new ArrayList<>()).add("apple");
        System.out.println("14: " + multiMap);
        /*
         * computeIfAbsent simplifies lazy creation and initialization.
         * If key is absent, the mapping function is executed.
         * Avoids verbose checks like "if (!map.containsKey(...))".
         * Especially handy for multi-valued mappings or cache loaders.
         */

        // 15. Using unmodifiableMap
        Map<String, Integer> unmodifiable = Collections.unmodifiableMap(map1);
        System.out.println("15: " + unmodifiable);
        /*
         * unmodifiableMap creates a read-only view of the map.
         * Any attempt to modify it results in UnsupportedOperationException.
         * Useful for exposing collections safely to external APIs.
         * Ensures immutability where needed.
         */

        // 16. Custom sorting with TreeMap comparator
        TreeMap<String, Integer> customSorted = new TreeMap<>(Comparator.reverseOrder());
        customSorted.put("A", 1);
        customSorted.put("C", 3);
        customSorted.put("B", 2);
        System.out.println("16: " + customSorted);
        /*
         * TreeMap can take a Comparator to define custom sorting.
         * reverseOrder() gives descending order.
         * Internally still uses a Red-Black Tree.
         * Comparator must be consistent with equals.
         */

        // 17. Converting Map to Stream
//        map1.entrySet().stream()
//                .filter(e -> e.getValue() > 5)
//                .forEach(e -> System.out.println("17: " + e.getKey() + " -> " + e.getValue()));
        /*
         * entrySet().stream() lets you use Java 8 stream features.
         * You can filter, map, sort, etc. on map entries.
         * Powerful for data transformation pipelines.
         * Great alternative to nested loops and conditionals.
         */

        // 18. Creating map from stream
        List<String> items = Arrays.asList("a", "b", "c");
        Map<String, Integer> itemLengths = items.stream()
                .collect(Collectors.toMap(s -> s, String::length));
        System.out.println("18: " + itemLengths);
        /*
         * Collectors.toMap creates a new map from a stream.
         * Key mapper and value mapper functions define the entries.
         * Can throw exception on duplicate keys unless handled.
         * Great for compact conversion logic.
         */

        // 19. EnumMap for enum keys
        enum Day { MON, TUE, WED }
        EnumMap<Day, String> schedule = new EnumMap<>(Day.class);
        schedule.put(Day.MON, "Gym");
        schedule.put(Day.TUE, "Work");
        System.out.println("19: " + schedule);
        /*
         * EnumMap is optimized for enum keys using internal array.
         * Very fast and memory efficient.
         * Keys must be non-null enums.
         * Best choice when map keys are enum constants.
         */

        // 20. Synchronized map wrapper
        Map<String, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put("X", "1");
        System.out.println("20: " + syncMap);
        /*
         * synchronizedMap provides thread-safe access to a map.
         * All read/write operations are synchronized.
         * It wraps any map type, including HashMap.
         * Use ConcurrentHashMap for better concurrency support.
         */
    }
}

