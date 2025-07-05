package javaInterview;

// Java TreeMap Deep Dive - 30 Detailed Examples
import java.util.*;

public class TreeMapExamples {
    public static void main(String[] args) {

        // 1. Basic TreeMap insertion
        TreeMap<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("apple", 10);
        treeMap.put("banana", 20);
        treeMap.put("cherry", 30);
        System.out.println("1: " + treeMap);
        /*
         * TreeMap stores keys in natural (ascending) order by default.
         * Internally uses a Red-Black Tree (self-balancing binary search tree).
         * Ensures O(log n) time complexity for get, put, remove operations.
         * Useful when sorted order is important in retrieval.
         */

        // 2. Custom Comparator (reverse order)
        TreeMap<String, Integer> reverseMap = new TreeMap<>(Comparator.reverseOrder());
        reverseMap.put("apple", 10);
        reverseMap.put("banana", 20);
        reverseMap.put("cherry", 30);
        System.out.println("2: " + reverseMap);
        /*
         * TreeMap can take a Comparator in constructor to define custom ordering.
         * reverseOrder() is commonly used to sort keys in descending order.
         * Comparator must be consistent with equals() to avoid unexpected behavior.
         * Allows flexibility in sorted maps without needing to sort later manually.
         */

        // 3. Null key not allowed
        try {
            treeMap.put(null, 100);
        } catch (NullPointerException e) {
            System.out.println("3: NullPointerException thrown (TreeMap doesn't allow null keys)");
        }
        /*
         * Unlike HashMap, TreeMap does NOT allow null keys.
         * Comparison with null throws NullPointerException.
         * Keys must be non-null and mutually comparable.
         * However, null values are permitted.
         */

        // 4. Checking first and last key
        System.out.println("4a: First Key = " + treeMap.firstKey());
        System.out.println("4b: Last Key = " + treeMap.lastKey());
        /*
         * firstKey() and lastKey() provide quick access to bounds of the map.
         * These methods are constant time as they're directly tracked.
         * Useful in range-based or limit-based scenarios (e.g. leaderboard).
         * Throws NoSuchElementException if map is empty.
         */

        // 5. Using headMap, tailMap, and subMap
        System.out.println("5a: headMap('cherry') = " + treeMap.headMap("cherry"));
        System.out.println("5b: tailMap('banana') = " + treeMap.tailMap("banana"));
        System.out.println("5c: subMap('apple', 'cherry') = " + treeMap.subMap("apple", "cherry"));
        /*
         * headMap returns a view of keys less than given key.
         * tailMap returns view of keys greater than or equal to given key.
         * subMap returns keys between a given range (fromKey inclusive, toKey exclusive).
         * All return live views — changes affect the original map.
         */

        // 6. floorKey() and ceilingKey()
        System.out.println("6a: floorKey('banana') = " + treeMap.floorKey("banana")); // banana
        System.out.println("6b: ceilingKey('banana') = " + treeMap.ceilingKey("banana")); // banana
        System.out.println("6c: floorKey('blueberry') = " + treeMap.floorKey("blueberry")); // banana
        System.out.println("6d: ceilingKey('blueberry') = " + treeMap.ceilingKey("blueberry")); // cherry
        /*
         * floorKey gives the greatest key <= given key.
         * ceilingKey gives the smallest key >= given key.
         * These are useful in range scanning, text search, and pagination.
         * Time complexity is O(log n) as they use tree traversal.
         */

        // 7. pollFirstEntry and pollLastEntry
        Map.Entry<String, Integer> first = treeMap.pollFirstEntry();
        Map.Entry<String, Integer> last = treeMap.pollLastEntry();
        System.out.println("7a: polled first = " + first);
        System.out.println("7b: polled last = " + last);
        System.out.println("7c: current map = " + treeMap);
        /*
         * pollFirstEntry and pollLastEntry remove and return first/last entries.
         * These are destructive and useful in bounded queues or sliding windows.
         * Helps efficiently trim from both ends.
         * O(log n) as internal rebalancing may occur.
         */

        // 8. Using keySet and descendingKeySet
        treeMap.put("grape", 50);
        treeMap.put("fig", 60);
        System.out.println("8a: keySet = " + treeMap.keySet());
        System.out.println("8b: descendingKeySet = " + treeMap.descendingKeySet());
        /*
         * keySet() returns keys in sorted order.
         * descendingKeySet() returns keys in reverse sorted order.
         * Views are backed by the original map and reflect updates.
         * Great for navigational or display-oriented operations.
         */

        // 9. Using values() and descendingMap()
        System.out.println("9a: values = " + treeMap.values());
        System.out.println("9b: descendingMap = " + treeMap.descendingMap());
        /*
         * values() returns Collection of all values in key-sorted order.
         * descendingMap() flips the internal comparator for traversal.
         * descendingMap is a view, not a separate copy.
         * Common in range-bound or reverse ranking scenarios.
         */

        // 10. replace() and putIfAbsent
        treeMap.replace("grape", 55);
        treeMap.putIfAbsent("kiwi", 70);
        System.out.println("10: " + treeMap);
        /*
         * replace updates value only if key exists.
         * putIfAbsent inserts value only if key is missing.
         * Avoids unnecessary overwriting or creation.
         * Common in conditional or multithreaded updates.
         */

        // 11. compute and computeIfAbsent
        treeMap.compute("kiwi", (k, v) -> v == null ? 0 : v + 1);
        treeMap.computeIfAbsent("lemon", k -> 100);
        System.out.println("11: " + treeMap);
        /*
         * compute allows recalculating values based on key.
         * computeIfAbsent initializes value lazily when missing.
         * Preferred for atomic-style value creation.
         * Helps in frequency counters, caching, and safe updates.
         */

        // 12. remove(key) and remove(key, value)
        treeMap.remove("fig");
        boolean removed = treeMap.remove("lemon", 999); // false
        System.out.println("12a: after removal = " + treeMap);
        System.out.println("12b: value match removed? = " + removed);
        /*
         * remove(key) deletes mapping if key exists.
         * remove(key, value) deletes only if both match.
         * This prevents race-condition bugs in concurrent updates.
         * Important in cache invalidation logic.
         */

        // 13. Using forEach loop
        treeMap.forEach((k, v) -> System.out.println("13: " + k + " -> " + v));
        /*
         * forEach with lambda provides functional way to iterate.
         * Works consistently across all map types.
         * Clean and concise for filtering and processing logic.
         * Internally optimized for performance.
         */

        // 14. equals and clone
        TreeMap<String, Integer> treeMapCopy = (TreeMap<String, Integer>) treeMap.clone();
        System.out.println("14a: equals = " + treeMap.equals(treeMapCopy));
        System.out.println("14b: same reference? = " + (treeMap == treeMapCopy));
        /*
         * equals compares contents, not references.
         * clone creates a shallow copy with same structure and values.
         * References to mutable values are not cloned.
         * Useful in testing or rollback scenarios.
         */

        // 15. Performance observation
        TreeMap<Integer, String> perfTest = new TreeMap<>();
        for (int i = 0; i < 100000; i++) perfTest.put(i, "V" + i);
        System.out.println("15: Last key in large TreeMap = " + perfTest.lastKey());
        /*
         * TreeMap has predictable O(log n) time for operations.
         * Slower than HashMap but maintains ordering.
         * Excellent for range queries or prefix-based lookups.
         * Avoid for unordered bulk inserts where HashMap is better.
         */

        // 16. TreeMap with custom objects
        class Employee implements Comparable<Employee> {
            String name;
            int age;
            Employee(String name, int age) {
                this.name = name; this.age = age;
            }
            public int compareTo(Employee other) {
                return this.age - other.age; // sort by age
            }
            public String toString() {
                return name + "(" + age + ")";
            }
        }
        TreeMap<Employee, String> empMap = new TreeMap<>();
        empMap.put(new Employee("Alice", 30), "HR");
        empMap.put(new Employee("Bob", 25), "IT");
        empMap.put(new Employee("Carol", 35), "Finance");
        System.out.println("16: Employee TreeMap = " + empMap);
        /*
         * TreeMap works with custom keys using Comparable or Comparator.
         * compareTo defines natural ordering logic.
         * Keys must be mutually comparable.
         * Very useful in sorted directory, indexing, and stats.
         */

        // 17. Using TreeMap for frequency counting
        String text = "apple banana apple orange banana apple";
        TreeMap<String, Integer> freqMap = new TreeMap<>();
        for (String word : text.split(" ")) {
            freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
        }
        System.out.println("17: Frequency map = " + freqMap);
        /*
         * TreeMap is excellent for building frequency maps with sorted keys.
         * Keys (words) appear alphabetically.
         * Output is easy to read and search.
         * Often used in word counting and aggregation problems.
         */

        // 18. Creating a TreeMap from another Map
        Map<String, Integer> baseMap = Map.of("x", 1, "y", 2, "z", 3);
        TreeMap<String, Integer> fromMap = new TreeMap<>(baseMap);
        System.out.println("18: TreeMap from Map = " + fromMap);
        /*
         * You can create a TreeMap from any existing Map.
         * Keys will be sorted in natural order.
         * Useful to convert unordered data to ordered views.
         * This constructor ensures all entries are copied.
         */

        // 19. TreeMap with duplicate values
        TreeMap<String, String> duplicateVals = new TreeMap<>();
        duplicateVals.put("id1", "User");
        duplicateVals.put("id2", "User");
        System.out.println("19: " + duplicateVals);
        /*
         * TreeMap enforces unique keys, but allows duplicate values.
         * Keys must be comparable or have a comparator.
         * Duplicate values do not affect the sorting.
         * Only keys participate in ordering.
         */

        // 20. NavigableMap - lowerKey and higherKey
        TreeMap<Integer, String> numMap = new TreeMap<>();
        numMap.put(10, "Ten");
        numMap.put(20, "Twenty");
        numMap.put(30, "Thirty");
        System.out.println("20a: lowerKey(25) = " + numMap.lowerKey(25));
        System.out.println("20b: higherKey(25) = " + numMap.higherKey(25));
        /*
         * lowerKey returns greatest key < given key.
         * higherKey returns smallest key > given key.
         * Very helpful in pagination and neighbor lookups.
         * Part of NavigableMap extension to SortedMap.
         */

        // 21. TreeMap with null values
        TreeMap<String, String> mapWithNullVals = new TreeMap<>();
        mapWithNullVals.put("one", null);
        mapWithNullVals.put("two", "TWO");
        System.out.println("21: TreeMap with null values = " + mapWithNullVals);
        /*
         * TreeMap allows null values, but not null keys.
         * Sorting works fine as long as keys are valid.
         * Helps when optional data is represented as null.
         * Avoids NullPointerExceptions on key comparison.
         */

        // 22. TreeMap size and empty check
        System.out.println("22a: size = " + mapWithNullVals.size());
        System.out.println("22b: isEmpty = " + mapWithNullVals.isEmpty());
        /*
         * size() returns number of entries.
         * isEmpty() returns true if map has no entries.
         * These are O(1) operations.
         * Frequently used in control flow logic.
         */

        // 23. TreeMap entrySet iteration with sorting guarantee
        for (Map.Entry<String, String> entry : mapWithNullVals.entrySet()) {
            System.out.println("23: " + entry.getKey() + " => " + entry.getValue());
        }
        /*
         * TreeMap entrySet guarantees ascending key order during iteration.
         * Order is consistent with the comparator or natural ordering.
         * Great for ordered output or logs.
         * Useful over HashMap where order is undefined.
         */

        // 24. TreeMap with StringBuilder as key using custom Comparator
        TreeMap<StringBuilder, String> sbMap = new TreeMap<>(Comparator.comparing(StringBuilder::toString));
        sbMap.put(new StringBuilder("key1"), "value1");
        sbMap.put(new StringBuilder("key2"), "value2");
        System.out.println("24: TreeMap with StringBuilder keys = " + sbMap);
        /*
         * TreeMap keys need a comparison logic.
         * Here we use Comparator.comparing with toString.
         * Avoids ClassCastException with non-Comparable keys.
         * Enables TreeMap to support more flexible key types.
         */

        // 25. TreeMap clear method
        sbMap.clear();
        System.out.println("25: after clear = " + sbMap);
        /*
         * clear() removes all entries from TreeMap.
         * It resets internal Red-Black tree structure.
         * Very fast and O(1) per entry deleted.
         * Great for reuse without creating new map instance.
         */

        // 26. TreeMap key collision handling
        TreeMap<String, String> collisionMap = new TreeMap<>();
        collisionMap.put("k", "val1");
        collisionMap.put("k", "val2");
        System.out.println("26: key overwritten = " + collisionMap);
        /*
         * TreeMap does not allow duplicate keys.
         * Inserting same key again overwrites the value.
         * Hash collision not applicable; keys are compared.
         * You’ll never get duplicate keys in TreeMap.
         */

        // 27. Using getOrDefault with TreeMap
        String val = collisionMap.getOrDefault("missing", "default");
        System.out.println("27: getOrDefault = " + val);
        /*
         * getOrDefault is a defensive read operation.
         * Helps avoid null checks on missing keys.
         * Returns provided fallback if key is absent.
         * Great for simplifying code and preventing NPEs.
         */

        // 28. Using TreeMap as priority queue (min-first)
        TreeMap<Integer, String> pqMap = new TreeMap<>();
        pqMap.put(3, "C");
        pqMap.put(1, "A");
        pqMap.put(2, "B");
        while (!pqMap.isEmpty()) {
            System.out.println("28: next min = " + pqMap.pollFirstEntry());
        }
        /*
         * TreeMap can simulate a PriorityQueue with custom order.
         * pollFirstEntry removes and returns min key item.
         * Order is maintained without explicit sorting.
         * Efficient alternative for structured priorities.
         */

        // 29. TreeMap key collisions with Comparator that ignores case
        TreeMap<String, String> caseInsensitiveMap = new TreeMap<>(String.CASE_INSENSITIVE_ORDER);
        caseInsensitiveMap.put("One", "1");
        caseInsensitiveMap.put("ONE", "ONE");
        System.out.println("29: case-insensitive = " + caseInsensitiveMap);
        /*
         * Comparator determines uniqueness in TreeMap.
         * Here, "One" and "ONE" are treated as same.
         * Later entry overrides earlier one.
         * Case-insensitive maps are useful in input validation.
         */

        // 30. TreeMap for prefix-based search simulation
        TreeMap<String, String> prefixMap = new TreeMap<>();
        prefixMap.put("apple", "fruit");
        prefixMap.put("application", "software");
        prefixMap.put("banana", "fruit");
        SortedMap<String, String> prefixResults = prefixMap.subMap("app", "apq");
        System.out.println("30: Prefix search = " + prefixResults);
        /*
         * TreeMap allows range-based lookups like prefix searching.
         * subMap returns all entries within given start/end bounds.
         * You can simulate prefix matching using char ranges.
         * Much faster than scanning all entries manually.
         */
    }
}
