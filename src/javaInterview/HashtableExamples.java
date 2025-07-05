package javaInterview;

// Java Hashtable Deep Dive - 30 Detailed Examples

import java.util.*;

public class HashtableExamples {
    public static void main(String[] args) {

        // 1. Basic Hashtable usage
        Hashtable<Integer, String> hashtable = new Hashtable<>();
        hashtable.put(1, "Apple");
        hashtable.put(2, "Banana");
        hashtable.put(3, "Cherry");
        System.out.println("1: " + hashtable);
        /*
         * Hashtable is a synchronized Map implementation.
         * It does not allow null keys or null values.
         * Provides thread safety through internal locking.
         * Typically slower than HashMap due to synchronization overhead.
         */

        // 2. Null keys or values not allowed
        try {
            hashtable.put(null, "Test");
        } catch (NullPointerException e) {
            System.out.println("2a: Null key not allowed");
        }
        try {
            hashtable.put(4, null);
        } catch (NullPointerException e) {
            System.out.println("2b: Null value not allowed");
        }
        /*
         * Hashtable is stricter than HashMap in this regard.
         * Prevents ambiguity in synchronization and internal handling.
         * Helps avoid null-related concurrency bugs.
         * Any attempt to insert null key or value throws NPE.
         */

        // 3. Updating value for existing key
        hashtable.put(2, "Blueberry");
        System.out.println("3: Updated value = " + hashtable.get(2));
        /*
         * Like all Maps, putting an existing key replaces the value.
         * Key identity is based on equals() and hashCode().
         * The operation is atomic due to built-in locking.
         * Useful in concurrent environments for safe updates.
         */

        // 4. containsKey and containsValue
        System.out.println("4a: containsKey(1) = " + hashtable.containsKey(1));
        System.out.println("4b: containsValue('Blueberry') = " + hashtable.containsValue("Blueberry"));
        /*
         * containsKey checks if key is present.
         * containsValue checks if any mapping has the given value.
         * Both are safe and synchronized operations.
         * Time complexity is O(n) for value and O(1) for key lookup.
         */

        // 5. remove a key
        hashtable.remove(3);
        System.out.println("5: After removal = " + hashtable);
        /*
         * Removes the mapping for the given key.
         * Returns the removed value or null.
         * The operation is thread-safe due to synchronization.
         * No effect if key is not found.
         */

        // 6. Iterating using Enumeration (legacy)
        Enumeration<Integer> keys = hashtable.keys();
        while (keys.hasMoreElements()) {
            Integer key = keys.nextElement();
            System.out.println("6: key = " + key + ", value = " + hashtable.get(key));
        }
        /*
         * Hashtable supports Enumeration, a legacy iterator.
         * Keys and elements() provide legacy traversal support.
         * Enumeration is not fail-fast.
         * Often replaced with modern for-each or Iterator.
         */

        // 7. Iterating using entrySet
        for (Map.Entry<Integer, String> entry : hashtable.entrySet()) {
            System.out.println("7: " + entry.getKey() + " = " + entry.getValue());
        }
        /*
         * entrySet() is the preferred modern way to iterate over maps.
         * Allows both key and value access in a single call.
         * Synchronization ensures consistent reads.
         * Safer in multi-threaded scenarios when wrapped properly.
         */

        // 8. Cloning Hashtable
        Hashtable<Integer, String> cloned = (Hashtable<Integer, String>) hashtable.clone();
        System.out.println("8: cloned table = " + cloned);
        /*
         * clone() performs a shallow copy of the table.
         * Keys and values are not deeply copied.
         * Useful for snapshotting or prototyping.
         * Thread-safe due to synchronized methods.
         */

        // 9. putIfAbsent behavior
        hashtable.putIfAbsent(1, "Avocado");
        hashtable.putIfAbsent(4, "Dragonfruit");
        System.out.println("9: After putIfAbsent = " + hashtable);
        /*
         * putIfAbsent avoids overwriting existing keys.
         * If key is not found, new value is inserted.
         * Common pattern in concurrency to avoid double writes.
         * Ensures atomic put-only-if-missing semantics.
         */

        // 10. clear() removes all entries
        cloned.clear();
        System.out.println("10: cleared clone = " + cloned);
        /*
         * clear() empties the entire table.
         * All mappings are removed.
         * Synchronized and safe under multi-threaded use.
         * Does not affect references held elsewhere.
         */

        // 11. Checking if Hashtable is empty
        System.out.println("11: isEmpty = " + hashtable.isEmpty());
        /*
         * isEmpty() checks whether the map contains any entries.
         * Returns true if size == 0.
         * Thread-safe due to internal synchronization.
         * Useful in loops and conditional logic.
         */

        // 12. Get value with default
        String value = hashtable.getOrDefault(5, "DefaultValue");
        System.out.println("12: getOrDefault(5) = " + value);
        /*
         * getOrDefault() returns a fallback value if key is missing.
         * Avoids null checks and conditional branching.
         * Does not modify the original map.
         * Improves code readability and robustness.
         */

        // 13. Using computeIfAbsent
        hashtable.computeIfAbsent(6, k -> "Guava");
        System.out.println("13: computeIfAbsent = " + hashtable);
        /*
         * computeIfAbsent evaluates mapping only if key is missing.
         * The lambda is evaluated lazily.
         * Prevents redundant calculations.
         * Safe and synchronized under the hood.
         */

        // 14. Using computeIfPresent
        hashtable.computeIfPresent(2, (k, v) -> v + " Juice");
        System.out.println("14: computeIfPresent = " + hashtable);
        /*
         * computeIfPresent only runs if the key is already present.
         * Helps in updating or appending data.
         * Can return null to remove the key.
         * Thread-safe and atomic.
         */

        // 15. Merging values
        hashtable.merge(1, " Pie", (oldVal, newVal) -> oldVal + newVal);
        System.out.println("15: merge() result = " + hashtable);
        /*
         * merge() is a flexible way to update or insert.
         * Takes a BiFunction to resolve value if key exists.
         * Adds key if it was missing.
         * Helps in counters, grouping, aggregation tasks.
         */

        // 16. Keys enumeration
        Enumeration<Integer> keysEnum = hashtable.keys();
        while (keysEnum.hasMoreElements()) {
            System.out.println("16: enum key = " + keysEnum.nextElement());
        }
        /*
         * Enumerations are older iteration style used in legacy APIs.
         * Still useful for backward compatibility.
         * Less preferred than for-each or streams today.
         * Not fail-fast — can be unsafe in concurrent modifications.
         */

        // 17. Values enumeration
        Enumeration<String> valuesEnum = hashtable.elements();
        while (valuesEnum.hasMoreElements()) {
            System.out.println("17: enum value = " + valuesEnum.nextElement());
        }
        /*
         * elements() returns an Enumeration of values.
         * Useful in legacy iteration.
         * Similar risks apply as with keys().
         * Suitable when working with older libraries.
         */

        // 18. KeySet view
        Set<Integer> keySet = hashtable.keySet();
        System.out.println("18: keySet = " + keySet);
        /*
         * keySet() provides a live view of the keys.
         * Modifications to the set reflect in the map.
         * Thread-safe and consistent with the backing map.
         * Good for key-based iteration or filtering.
         */

        // 19. Values view
        Collection<String> valueSet = hashtable.values();
        System.out.println("19: values view = " + valueSet);
        /*
         * values() gives a collection of all values.
         * Order is not guaranteed.
         * Reflects real-time updates to the map.
         * Safe under concurrency but must synchronize during bulk ops.
         */

        // 20. EntrySet view
        Set<Map.Entry<Integer, String>> entrySet = hashtable.entrySet();
        for (Map.Entry<Integer, String> e : entrySet) {
            System.out.println("20: entry = " + e);
        }
        /*
         * entrySet() is efficient for iterating both keys and values.
         * Safe for concurrent access if synchronized properly.
         * Supports modification via Iterator.remove().
         * Preferred over using separate key and value calls.
         */

        // 21. Synchronized wrapper over HashMap (alternative to Hashtable)
        Map<Integer, String> syncMap = Collections.synchronizedMap(new HashMap<>());
        syncMap.put(10, "X");
        System.out.println("21: synchronizedMap = " + syncMap);
        /*
         * Recommended approach for synchronization in modern code.
         * Uses HashMap under the hood, wrapped with synchronization.
         * More flexible and modern compared to Hashtable.
         * Allows null keys and values unlike Hashtable.
         */

        // 22. Comparison with HashMap
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(1, null);
        hashMap.put(null, "NullKey");
        System.out.println("22: HashMap with nulls = " + hashMap);
        /*
         * HashMap allows null keys and values.
         * Not synchronized by default.
         * Better for performance in single-threaded environments.
         * Contrasts with Hashtable’s stricter behavior.
         */

        // 23. Size and capacity discussion
        System.out.println("23: Hashtable size = " + hashtable.size());
        /*
         * Size returns number of key-value pairs.
         * Initial capacity and load factor are handled internally.
         * Performance depends on load factor and rehashing.
         * Capacity is not directly visible like in HashMap.
         */

        // 24. Thread-safe counting example
        Hashtable<String, Integer> wordCount = new Hashtable<>();
        String[] words = {"apple", "banana", "apple", "cherry", "banana"};
        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }
        System.out.println("24: wordCount = " + wordCount);
        /*
         * Thread-safe frequency counter.
         * getOrDefault avoids null handling.
         * Not atomic — use compute/merge for safer concurrency.
         * Suitable for basic aggregation tasks.
         */

        // 25. Hashtable with custom object keys
        class KeyObj {
            int id;
            KeyObj(int id) { this.id = id; }
            public boolean equals(Object o) { return o instanceof KeyObj && ((KeyObj)o).id == this.id; }
            public int hashCode() { return Objects.hash(id); }
        }
        Hashtable<KeyObj, String> customMap = new Hashtable<>();
        customMap.put(new KeyObj(1), "One");
        System.out.println("25: custom key = " + customMap);
        /*
         * Custom keys need proper equals and hashCode.
         * Hashtable relies on these methods to find buckets.
         * Internally distributes keys into synchronized buckets.
         * Common in caching and modeling domain objects.
         */

        // 26. Hashtable and null checks
        String check = hashtable.get(99);
        System.out.println("26: lookup non-existent = " + check);
        /*
         * get returns null if key is absent.
         * Always validate result to avoid NullPointerException.
         * Especially important in conditional branches.
         * Enhances fault tolerance.
         */

        // 27. Replacing values
        hashtable.replace(2, "Updated");
        System.out.println("27: replaced value = " + hashtable);
        /*
         * replace works only if key exists.
         * Thread-safe alternative to put + check.
         * Safer in concurrent modifications.
         * Doesn’t insert if key is missing.
         */

        // 28. remove(key, value) conditional remove
        boolean removed = hashtable.remove(2, "WrongVal");
        System.out.println("28: remove with value match = " + removed);
        /*
         * Deletes entry only if both key and value match.
         * Prevents accidental deletions in race conditions.
         * Used in CAS (compare-and-swap) logic.
         * Safe and synchronized.
         */

        // 29. Ensuring atomic updates with merge
        hashtable.merge(4, "Add", (oldVal, newVal) -> oldVal + " & " + newVal);
        System.out.println("29: merge for key 4 = " + hashtable);
        /*
         * merge supports atomic updates and insertions.
         * The remapping function gets current and new value.
         * Used in thread-safe accumulations.
         * Avoids race condition bugs.
         */

        // 30. Final note on Hashtable usage
        System.out.println("30: Hashtable is legacy — prefer ConcurrentHashMap or sync wrappers today");
        /*
         * Hashtable is thread-safe but outdated.
         * Lacks features and flexibility of modern alternatives.
         * Suitable only for legacy compatibility.
         * Use with caution in high-concurrency applications.
         */
    }
}

