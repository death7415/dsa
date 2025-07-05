package javaInterview;

// Tricky Java Questions and Explanations

public class TrickyExamples {
    public static void main(String[] args) {

        // 1. Integer cache range comparison
        Integer a = 1000, b = 1000;
        System.out.println("1: " + (a == b)); // false

        // 2. String literal vs new object
        String s1 = "abc";
        String s2 = "abc";
        String s3 = new String("abc");
        System.out.println("2a: " + (s1 == s2)); // true
        System.out.println("2b: " + (s1 == s3)); // false

        // 3. Using final variable in array size
        final int x = 10;
        int[] arr = new int[x];
        System.out.println("3: array length = " + arr.length); // 10

        // 4. Complex increment expression
        int aa = 5;
        System.out.println("4: " + (aa++ + ++aa)); // 5 + 7 = 12

        // 5. null instanceof check
        String s = null;
        if (s instanceof String) {
            System.out.println("5: Yes");
        } else {
            System.out.println("5: No"); // No
        }

        // 6. String concatenation order
        System.out.println("6: " + (10 + 20 + "30" + 40 + 50)); // 30304050

        // 7. Byte overflow
        int i = 128;
        byte b1 = (byte) i;
        System.out.println("7: " + b1); // -128

        // 8. List insert at index
        java.util.List<String> list = new java.util.ArrayList<>();
        list.add("A");
        list.add(1, "B"); // Valid insert at index 1
        System.out.println("8: " + list); // [A, B]

        // 9. Compile-time string optimization
        String str1 = "abc";
        String str2 = "ab" + "c";
        System.out.println("9: " + (str1 == str2)); // true

        // 10. String immutability in concat
        String str = "abc";
        str.concat("def");
        System.out.println("10: " + str); // abc

        // 11. StringBuffer vs StringBuilder
        StringBuffer sb1 = new StringBuffer("Hello");
        sb1.append(" World");
        System.out.println("11: " + sb1); // Hello World
        // StringBuffer is synchronized; StringBuilder is not

        // 12. Substring memory reference (pre-Java 7 vs Java 7+)
        String longStr = "HelloWorld";
        String sub = longStr.substring(5);
        System.out.println("12: " + sub); // World
        // In Java 6, substring shared char array; Java 7+ creates new array

        // 13. String equalsIgnoreCase
        String aStr = "TeSt";
        String bStr = "test";
        System.out.println("13: " + aStr.equalsIgnoreCase(bStr)); // true

        // 14. Replacing characters with regex
        String input = "abc123xyz";
        String onlyLetters = input.replaceAll("\\d", "");
        System.out.println("14: " + onlyLetters); // abcxyz

        // 15. Difference between replace and replaceAll
        String text = "1+1=2";
        System.out.println("15a: " + text.replace("+", "plus"));      // 1plus1=2
        System.out.println("15b: " + text.replaceAll("\\+", "plus")); // 1plus1=2

        // 16. Joining strings using String.join
        String joined = String.join("-", "2025", "07", "05");
        System.out.println("16: " + joined); // 2025-07-05

        // 17. String format usage
        String formatted = String.format("Name: %s, Age: %d", "Alice", 30);
        System.out.println("17: " + formatted); // Name: Alice, Age: 30

        // 18. Using matches with regex
        String regexStr = "abc123";
        boolean isMatch = regexStr.matches("[a-z]+\\d+");
        System.out.println("18: " + isMatch); // true

        // 19. Empty vs null check
        String nullStr = null;
        String emptyStr = "";
        System.out.println("19a: " + (nullStr == null));      // true
        System.out.println("19b: " + emptyStr.isEmpty());     // true

        // 20. Trim vs strip (Java 11+)
        String ws = " hello "; // unicode whitespace
        System.out.println("20a: trim=" + ws.trim());         // does not remove  
        System.out.println("20b: strip=" + ws.strip());       // removes all unicode whitespaces


        // 21. CompareTo vs equals
        String strA = "apple";
        String strB = "banana";
        System.out.println("21a: " + strA.equals(strB)); // false
        System.out.println("21b: " + strA.compareTo(strB)); // negative (alphabetically before)
        /*
         * equals() checks value equality.
         * compareTo() compares lexicographically: returns <0 if strA < strB
         */

        // 22. String.split() edge case
        String test = "one,,two,,";
        String[] splitArr = test.split(",");
        System.out.println("22: length=" + splitArr.length); // 3
        for (String part : splitArr) {
            System.out.print("[" + part + "] ");
        }
        /*
         * split() drops trailing empty strings by default.
         * Use split(",", -1) to preserve them.
         */

        // 23. toUpperCase() vs equals
        String low = "java";
        String up = "JAVA";
        System.out.println("23: " + low.toUpperCase().equals(up)); // true
        /*
         * toUpperCase() creates a new String in uppercase.
         * equals() still checks content.
         */

        // 24. StringBuilder capacity expansion
        StringBuilder sb = new StringBuilder(2);
        sb.append("abc");
        System.out.println("24: " + sb); // abc
        /*
         * StringBuilder automatically expands capacity when exceeded.
         */

        // 25. Interning dynamic string
        String dyn = new String("java");
        String lit = "java";
        System.out.println("25a: " + (dyn == lit)); // false
        System.out.println("25b: " + (dyn.intern() == lit)); // true
        /*
         * intern() returns reference from string pool.
         */

        // 26. Null-safe equals using Objects.equals
        String one = null;
        String two = "text";
        System.out.println("26: " + java.util.Objects.equals(one, two)); // false
        /*
         * Prevents NullPointerException when comparing strings
         */

        // 27. Reversing a string
        String rev = new StringBuilder("racecar").reverse().toString();
        System.out.println("27: " + rev); // racecar
        /*
         * StringBuilder provides in-place reverse
         */

        // 28. contains() is case-sensitive
        String msg = "Hello World";
        System.out.println("28a: " + msg.contains("world")); // false
        System.out.println("28b: " + msg.toLowerCase().contains("world")); // true
        /*
         * contains() is case-sensitive; convert to lower or upper case for insensitive match
         */

        // 29. StringJoiner
        java.util.StringJoiner sj = new java.util.StringJoiner(", ", "[", "]");
        sj.add("red").add("green").add("blue");
        System.out.println("29: " + sj); // [red, green, blue]
        /*
         * StringJoiner is useful for formatting delimited strings with prefix/suffix
         */

        // 30. String.repeat() (Java 11+)
        String repeat = "-".repeat(5);
        System.out.println("30: " + repeat); // -----
        /*
         * repeat(n) returns a new string repeated n times
         */       // removes all unicode whitespaces


        // 31. HashMap insertion and retrieval
        java.util.Map<String, Integer> map = new java.util.HashMap<>();
        map.put("apple", 1);
        map.put("banana", 2);
        System.out.println("31: " + map.get("apple")); // 1
        /*
         * HashMap stores key-value pairs with average O(1) lookup.
         * Keys must implement equals() and hashCode() properly.
         */

        // 32. TreeMap ordering
        java.util.Map<String, Integer> treeMap = new java.util.TreeMap<>();
        treeMap.put("b", 2);
        treeMap.put("a", 1);
        System.out.println("32: " + treeMap); // {a=1, b=2}
        /*
         * TreeMap stores entries in sorted key order using natural/comparator ordering.
         */

        // 33. LinkedHashMap access order
        java.util.LinkedHashMap<Integer, String> lru = new java.util.LinkedHashMap<>(16, 0.75f, true);
        lru.put(1, "one");
        lru.put(2, "two");
        lru.get(1);
        System.out.println("33: " + lru); // 2 before 1
        /*
         * LinkedHashMap with accessOrder=true maintains order of access, useful for LRU caches.
         */

        // 34. Set prevents duplicates
        java.util.Set<String> set = new java.util.HashSet<>();
        set.add("java");
        set.add("java");
        System.out.println("34: " + set.size()); // 1
        /*
         * HashSet stores unique elements; duplicates are ignored based on equals/hashCode.
         */

        // 35. ConcurrentModificationException
        java.util.List<String> myList = new java.util.ArrayList<>();
        myList.add("A");
        myList.add("B");
        try {
            for (String item : myList) {
                if (item.equals("A")) myList.remove(item); // throws exception
            }
        } catch (java.util.ConcurrentModificationException e) {
            System.out.println("35: Caught CME");
        }
        /*
         * Modifying a list during iteration using enhanced-for causes ConcurrentModificationException.
         */

        // 36. Iterator safe removal
        java.util.Iterator<String> it = myList.iterator();
        while (it.hasNext()) {
            if ("B".equals(it.next())) it.remove();
        }
        System.out.println("36: " + myList); // []
        /*
         * Always use iterator's remove() method to avoid CME while looping.
         */

        // 37. PriorityQueue ordering
        java.util.PriorityQueue<Integer> pq = new java.util.PriorityQueue<>();
        pq.add(30);
        pq.add(10);
        pq.add(20);
        System.out.println("37: " + pq.poll()); // 10
        /*
         * PriorityQueue gives natural order (min-heap by default).
         */

        // 38. Stack operations (LIFO)
        java.util.Stack<String> stack = new java.util.Stack<>();
        stack.push("first");
        stack.push("second");
        System.out.println("38: " + stack.pop()); // second
        /*
         * Stack follows Last-In-First-Out; pop removes the last added element.
         */

        // 39. Queue operations (FIFO)
        java.util.Queue<String> queue = new java.util.LinkedList<>();
        queue.offer("A");
        queue.offer("B");
        System.out.println("39: " + queue.poll()); // A
        /*
         * Queue (LinkedList) gives FIFO access; poll removes the head.
         */

        // 40. Collections.sort on List
        java.util.List<Integer> nums = java.util.Arrays.asList(3, 1, 2);
        java.util.Collections.sort(nums);
        System.out.println("40: " + nums); // [1, 2, 3]
        /*
         * Collections.sort performs in-place sort on lists using natural ordering.
         */
    }
}

