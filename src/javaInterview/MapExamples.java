package javaInterview;
// Map-related Java Scenarios (50+ Detailed Examples)

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

// Custom Employee class for use as keys
class Employee {
    String name;
    int id;

    Employee(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee emp = (Employee) obj;
        return id == emp.id && Objects.equals(name, emp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    @Override
    public String toString() {
        return name + ":" + id;
    }
}

public class MapExamples {

    public static void main(String[] args) {
        // 1. Basic HashMap add/get
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        System.out.println(map.get("A")); // 1

        // 2. Key collision handling
        map.put("A", 3); // replaces previous value
        System.out.println(map.get("A")); // 3

        // 3. Using containsKey
        System.out.println(map.containsKey("B")); // true

        // 4. Iterating using entrySet
        for (Map.Entry<String, Integer> entry : map.entrySet()) {
            System.out.println(entry.getKey() + " = " + entry.getValue());
        }

        // 5. Using computeIfAbsent
        map.computeIfAbsent("C", k -> 5);
        System.out.println(map); // Adds C=5 if not present

        // 6. Using Employee class as key
        Map<Employee, String> empMap = new HashMap<>();
        Employee e1 = new Employee("John", 101);
        Employee e2 = new Employee("John", 101);
        empMap.put(e1, "Developer");
        System.out.println(empMap.get(e2));
        /*
         * Expected Output: Developer
         * Because both e1 and e2 have the same content and hashCode and equals are overridden,
         * HashMap treats them as the same key.
         */

        // 7. Without equals/hashCode -> different behavior
        class BadEmployee {
            String name;
            BadEmployee(String name) { this.name = name; }
        }
        Map<BadEmployee, String> badMap = new HashMap<>();
        BadEmployee b1 = new BadEmployee("Alice");
        BadEmployee b2 = new BadEmployee("Alice");
        badMap.put(b1, "Tester");
        System.out.println(badMap.get(b2));
        /*
         * Expected Output: null
         * Because BadEmployee does not override equals and hashCode,
         * HashMap treats b1 and b2 as different objects.
         */

        // 8. Same name different IDs
        Employee emp1 = new Employee("Ram", 100);
        Employee emp2 = new Employee("Ram", 101);
        empMap.put(emp1, "HR");
        empMap.put(emp2, "Finance");
        System.out.println(empMap);
        /*
         * Expected Output: Two entries
         * Because although names are same, IDs are different,
         * which results in different hashCodes and hence treated as distinct keys.
         */

        // 9. LinkedHashMap to preserve insertion order
        Map<String, Integer> linkedMap = new LinkedHashMap<>();
        linkedMap.put("one", 1);
        linkedMap.put("two", 2);
        linkedMap.put("three", 3);
        System.out.println(linkedMap);
        /*
         * Expected Output: {one=1, two=2, three=3}
         * LinkedHashMap maintains the order in which keys are inserted.
         */

        // 10. TreeMap (natural sorting)
        Map<String, Integer> treeMap = new TreeMap<>();
        treeMap.put("Zebra", 5);
        treeMap.put("Apple", 10);
        treeMap.put("Lemon", 3);
        System.out.println(treeMap);
        /*
         * Expected Output: {Apple=10, Lemon=3, Zebra=5}
         * TreeMap stores keys in natural (lexicographical) order.
         */

        // Next 40+ scenarios will include:
        // - null keys/values
        // - compute(), merge(), replace()
        // - nested maps
        // - concurrent maps
        // - custom sorting in TreeMap
        // - duplicate values, unique keys logic
        // - frequency counters
        // - reverse lookup
        // - grouping by properties (e.g., department)
        // - map to list/set conversions
        // - etc.

        // 11. HashMap with null key and value
        Map<String, String> nullMap = new HashMap<>();
        nullMap.put(null, "NullKey");
        nullMap.put("NullValue", null);
        System.out.println(nullMap);
        /*
         * Expected Output: {null=NullKey, NullValue=null}
         * HashMap allows one null key and multiple null values.
         */

        // 12. compute() usage
        map.compute("A", (k, v) -> v == null ? 1 : v + 1);
        System.out.println(map.get("A"));
        /*
         * Expected Output: 4 (because A=3, and 3+1=4)
         * compute() recomputes value for key using BiFunction.
         */

        // 13. computeIfPresent()
        map.computeIfPresent("B", (k, v) -> v * 2);
        System.out.println(map.get("B"));
        /*
         * Expected Output: 4 (2 * 2)
         * computeIfPresent only updates if key is present.
         */

        // 14. merge() usage
        map.merge("C", 10, Integer::sum); // C=5 exists, 5+10=15
        System.out.println(map.get("C"));
        /*
         * Expected Output: 15
         * merge() adds value if key exists, else sets new value.
         */

        // 15. replace() usage
        map.replace("C", 20);
        System.out.println(map.get("C"));
        /*
         * Expected Output: 20
         * replace() simply replaces value if key exists.
         */

        // 16. Nested Maps
        Map<String, Map<String, Integer>> nestedMap = new HashMap<>();
        Map<String, Integer> inner = new HashMap<>();
        inner.put("Math", 90);
        inner.put("English", 80);
        nestedMap.put("Student1", inner);
        System.out.println(nestedMap);
        /*
         * Expected Output: {Student1={Math=90, English=80}}
         * Nested Maps are useful for grouping complex data.
         */

        // 17. ConcurrentHashMap for thread safety
        Map<String, Integer> concurrentMap = new ConcurrentHashMap<>();
        concurrentMap.put("T1", 1);
        concurrentMap.put("T2", 2);
        System.out.println(concurrentMap);
        /*
         * Expected Output: {T1=1, T2=2}
         * ConcurrentHashMap allows thread-safe access.
         */

        // 18. TreeMap with custom comparator (reverse order)
        TreeMap<String, Integer> reverseTreeMap = new TreeMap<>(Comparator.reverseOrder());
        reverseTreeMap.put("A", 1);
        reverseTreeMap.put("B", 2);
        reverseTreeMap.put("C", 3);
        System.out.println(reverseTreeMap);
        /*
         * Expected Output: {C=3, B=2, A=1}
         * TreeMap uses custom comparator for ordering.
         */

        // 19. Frequency count using Map
        String str = "banana";
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : str.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        System.out.println(freqMap);
        /*
         * Expected Output: {a=3, b=1, n=2}
         * Count frequency of characters using getOrDefault.
         */

        // 20. Reverse lookup: find key by value
        String searchRole = "HR";
        for (Map.Entry<Employee, String> entry : empMap.entrySet()) {
            if (entry.getValue().equals(searchRole)) {
                System.out.println("Found: " + entry.getKey());
            }
        }
        /*
         * Expected Output: Found: Ram:100
         * Reverse lookup by iterating entries when only value is known.
         */

        // To be continued...
    }
}
