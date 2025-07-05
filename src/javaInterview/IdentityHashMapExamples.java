package javaInterview;

// Java IdentityHashMap Deep Dive - 30 Detailed Examples

import java.util.*;

public class IdentityHashMapExamples {
    public static void main(String[] args) {

        // 1. Basic IdentityHashMap usage
        IdentityHashMap<String, String> idMap = new IdentityHashMap<>();
        idMap.put("a", "apple");
        idMap.put("b", "banana");
        System.out.println("1: " + idMap);
        /*
         * IdentityHashMap uses reference equality (==) instead of equals().
         * Keys are compared using memory addresses, not content.
         * This makes it behave differently than HashMap.
         * Very useful when identity matters more than logical equality.
         */

        // 2. Same content, different objects as keys
        String key1 = new String("test");
        String key2 = new String("test");
        idMap.put(key1, "value1");
        idMap.put(key2, "value2");
        System.out.println("2: key1 == key2? " + (key1 == key2));
        System.out.println("2: IdentityHashMap contents = " + idMap);
        /*
         * Although key1.equals(key2) is true, key1 != key2.
         * IdentityHashMap treats them as separate keys.
         * This would NOT happen in a regular HashMap.
         * Shows how memory reference affects key uniqueness.
         */

        // 3. Replacing value for same reference key
        idMap.put(key1, "newValue1");
        System.out.println("3: Replaced value for key1 = " + idMap.get(key1));
        /*
         * When the same object reference is used as key,
         * its value will be updated instead of creating a new entry.
         * IdentityHashMap uses == check during put and get.
         * This allows safe replacements only if reference is reused.
         */

        // 4. Verifying equals() doesn't affect lookup
        String eqKey = new String("b");
        System.out.println("4: value for 'b' copy = " + idMap.get(eqKey));
        /*
         * Even though eqKey.equals("b") is true, it's a new object.
         * IdentityHashMap returns null because the references differ.
         * Only exact reference will return value.
         * Key sensitivity makes it suitable for certain cache use cases.
         */

        // 5. Size shows all reference-based keys
        System.out.println("5: IdentityHashMap size = " + idMap.size());
        /*
         * Even if keys are logically equal, IdentityHashMap will count them separately.
         * Size reflects number of distinct references used.
         * This helps detect duplicate reference issues.
         * Also demonstrates the difference from logical-based maps.
         */

        // 6. Iterating over entries
        for (Map.Entry<String, String> entry : idMap.entrySet()) {
            System.out.println("6: Key: " + entry.getKey() + ", Value: " + entry.getValue());
        }
        /*
         * IdentityHashMap supports normal Map iteration.
         * Entries are stored in internal array as alternating key-value pairs.
         * Iteration is efficient and direct.
         * However, order is not guaranteed and may differ.
         */

        // 7. Null keys and values
        idMap.put(null, null);
        System.out.println("7: null key/value allowed = " + idMap);
        /*
         * IdentityHashMap allows one null key.
         * This is handled like any other object reference.
         * null is a valid object reference in Java.
         * It works the same as in HashMap, but comparison is via ==.
         */

        // 8. Replace null key's value
        idMap.put(null, "replacedNull");
        System.out.println("8: updated null value = " + idMap);
        /*
         * You can update the null key’s value like any other key.
         * Only one null key allowed (like HashMap).
         * Replaces the existing entry.
         * Demonstrates consistent behavior with other map types.
         */

        // 9. Using identity with wrapper types
        Integer int1 = new Integer(100);
        Integer int2 = new Integer(100);
        idMap.clear();
        IdentityHashMap<Integer, String> intMap = new IdentityHashMap<>();
        intMap.put(int1, "one");
        intMap.put(int2, "two");
        System.out.println("9: wrapper key intMap = " + intMap);
        /*
         * Even with same boxed value (100), references differ.
         * So, both keys are treated as different entries.
         * Wrapper caching does not apply here due to `new`.
         * Good test for identity vs value understanding.
         */

        // 10. IdentityHashMap entry replacement
        intMap.put(int1, "replaced one");
        System.out.println("10: Replaced int1 = " + intMap.get(int1));
        /*
         * Reusing same reference allows replacing value.
         * No new entry is added.
         * Confirms identity comparison over value equality.
         * Useful for precise cache or memoization logic.
         */

        // 11. IdentityHashMap with custom objects
        class Person {
            String name;
            Person(String name) { this.name = name; }
            public boolean equals(Object o) { return true; } // Always true
            public int hashCode() { return 1; } // Same hash
        }
        IdentityHashMap<Person, String> personMap = new IdentityHashMap<>();
        Person p1 = new Person("Alice");
        Person p2 = new Person("Alice");
        personMap.put(p1, "HR");
        personMap.put(p2, "Finance");
        System.out.println("11: personMap = " + personMap);
        /*
         * Even though equals/hashCode indicate sameness, IdentityHashMap treats them differently.
         * Reference identity still separates p1 and p2.
         * Highlights difference from HashMap behavior.
         * Critical when overriding equals/hashCode poorly.
         */

        // 12. containsKey with identity
        System.out.println("12: containsKey(p1) = " + personMap.containsKey(p1));
        System.out.println("12: containsKey(new Person('Alice')) = " + personMap.containsKey(new Person("Alice")));
        /*
         * containsKey uses reference comparison.
         * Only exact reference objects will return true.
         * Even logically equal objects are rejected.
         * Great for identity-sensitive caches or pools.
         */

        // 13. IdentityHashMap behaves like a sparse identity table
        IdentityHashMap<Object, Object> objectTable = new IdentityHashMap<>();
        Object o1 = new Object();
        Object o2 = new Object();
        objectTable.put(o1, 1);
        objectTable.put(o2, 2);
        System.out.println("13: objectTable = " + objectTable);
        /*
         * Common use case is for identity tables — object metadata, dependency graphs.
         * Keeps unique identity mappings.
         * Prevents collisions with equals-based logic.
         * Often used in compiler internals.
         */

        // 14. Remove entry by key reference
        personMap.remove(p1);
        System.out.println("14: After removing p1 = " + personMap);
        /*
         * remove uses reference identity.
         * Only matching reference key can be removed.
         * Safe from unexpected deletions based on equals().
         * Predictable key matching behavior.
         */

        // 15. Cloning IdentityHashMap
        IdentityHashMap<String, String> cloneMap = new IdentityHashMap<>();
        cloneMap.put("x", "one");
        cloneMap.put("y", "two");
        IdentityHashMap<String, String> cloneCopy = (IdentityHashMap<String, String>) cloneMap.clone();
        System.out.println("15: cloned map = " + cloneCopy);
        /*
         * Cloning gives shallow copy of the identity map.
         * References are maintained (not deep cloned).
         * Useful for backup or snapshot of identity-based structures.
         * Same identity behavior preserved.
         */

        // 16. Handling identity of interned strings
        String s1 = "java";
        String s2 = "java";
        IdentityHashMap<String, String> internMap = new IdentityHashMap<>();
        internMap.put(s1, "lang");
        System.out.println("16: s1 == s2? " + (s1 == s2));
        System.out.println("16: internMap.get(s2) = " + internMap.get(s2));
        /*
         * Interned strings point to same memory reference.
         * So identity comparison works as expected.
         * Demonstrates correct value retrieval.
         * Java interns string literals automatically.
         */

        // 17. Copying entries to HashMap (equality-based)
        Map<String, String> normalMap = new HashMap<>(internMap);
        System.out.println("17: copied to HashMap = " + normalMap);
        /*
         * You can convert IdentityHashMap to a regular map.
         * The keys and values are preserved.
         * After conversion, equality-based logic is used.
         * Useful for integration or output.
         */

        // 18. IdentityHashMap accepts duplicate values
        idMap.clear();
        idMap.put("a", "x");
        idMap.put("b", "x");
        System.out.println("18: duplicate values allowed = " + idMap);
        /*
         * Values can be duplicated freely.
         * Only keys are reference-sensitive.
         * Identity constraints apply only on keys.
         * Same as other map implementations.
         */

        // 19. Use in object tracking
        IdentityHashMap<Object, Boolean> visited = new IdentityHashMap<>();
        Object objA = new Object();
        visited.put(objA, true);
        System.out.println("19: visited.containsKey(new Object()) = " + visited.containsKey(new Object()));
        /*
         * In graph traversal, you want to track object visits.
         * Identity-based comparison ensures no logical overlap.
         * Prevents revisiting distinct objects with equal state.
         * Used in garbage collection algorithms.
         */

        // 20. Map null to multiple values — not possible
        idMap.put(null, "nullVal1");
        idMap.put(null, "nullVal2");
        System.out.println("20: null key replaced = " + idMap);
        /*
         * Only one null key allowed.
         * New value replaces the previous null entry.
         * Same as HashMap behavior.
         * No identity check needed for null.
         */

        // 21. IdentityHashMap size after identical values
        System.out.println("21: size with repeated values = " + idMap.size());
        /*
         * IdentityHashMap size is based on unique references of keys only.
         * Repeated values do not affect map size.
         * Keys must be different references to count as unique.
         * Values can be same or null.
         */

        // 22. Replacing a key with same reference
        String sameKey = new String("x");
        idMap.put(sameKey, "1");
        idMap.put(sameKey, "2");
        System.out.println("22: after replacing sameKey = " + idMap);
        /*
         * Using the same object key multiple times replaces its value.
         * Reference is identical, so no new entry added.
         * Similar to how HashMap updates existing key.
         * Ensures memory usage stays bounded.
         */

        // 23. Demonstrating entrySet iteration behavior
        for (Map.Entry<String, String> entry : idMap.entrySet()) {
            System.out.println("23: Entry = " + entry);
        }
        /*
         * entrySet gives snapshot of entries in key-value format.
         * IdentityHashMap stores entries in array for speed.
         * Iteration order is not guaranteed.
         * Use cautiously if order-sensitive.
         */

        // 24. KeySet modification effects
        Set<String> keys = idMap.keySet();
        keys.remove("a");
        System.out.println("24: after removing from keySet = " + idMap);
        /*
         * keySet is backed by the map.
         * Removing from keySet removes entry from map.
         * Modifies underlying structure.
         * Safe for controlled pruning of entries.
         */

        // 25. IdentityHashMap with Enum keys (works, but not ideal)
        enum Status { ON, OFF }
        IdentityHashMap<Status, String> enumMap = new IdentityHashMap<>();
        enumMap.put(Status.ON, "Running");
        enumMap.put(Status.OFF, "Stopped");
        System.out.println("25: enum identity map = " + enumMap);
        /*
         * Enums have fixed identity, so reference comparison is safe.
         * But EnumMap is more efficient for this purpose.
         * IdentityHashMap adds unnecessary complexity.
         * Demonstrates compatibility but not best practice.
         */

        // 26. containsValue with duplicate check
        System.out.println("26: containsValue('x') = " + idMap.containsValue("x"));
        /*
         * containsValue() works like normal maps.
         * It searches using equals(), not identity.
         * Only keys use identity comparison.
         * Values are handled normally.
         */

        // 27. Impact of identity on memory usage
        for (int i = 0; i < 1000; i++) idMap.put(new String("k" + i), "v");
        System.out.println("27: size after bulk insert = " + idMap.size());
        /*
         * Each new String reference is stored independently.
         * IdentityHashMap grows rapidly with unique references.
         * Suitable for use cases where every object matters.
         * But beware of memory usage.
         */

        // 28. IdentityHashMap not thread-safe
        // Uncommenting the below in real multi-threaded context may cause issues
        // IdentityHashMap<Integer, String> unsafeMap = new IdentityHashMap<>();
        // Runnable task = () -> unsafeMap.put(new Integer(1), "v");
        // new Thread(task).start();
        /*
         * IdentityHashMap is NOT thread-safe.
         * Concurrent access can cause corruption.
         * Must wrap with synchronized block or use ConcurrentMap alternatives.
         * Avoid in multithreaded environments.
         */

        // 29. Useful in frameworks like JAXB, JPA
        /*
         * IdentityHashMap is used internally by JAXB for tracking object cycles.
         * Same applies in Hibernate/JPA to track visited proxies.
         * Object identity ensures exact detection of object graph traversal.
         * Prevents infinite loops during serialization or persistence.
         */

        // 30. Final tip on use cases
        System.out.println("30: IdentityHashMap is best for: object identity tracking, memoization, graph traversal");
        /*
         * Use IdentityHashMap when logical equality is insufficient.
         * Great for caches where same value objects must be treated separately.
         * Helps avoid logic bugs due to equals()/hashCode overrides.
         * But must be used with understanding of its identity behavior.
         */

    }
}
