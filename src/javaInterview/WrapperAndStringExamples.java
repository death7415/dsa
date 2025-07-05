package javaInterview;

public class WrapperAndStringExamples {
    public static void main(String[] args) {

        // 1. Auto-boxing example
        Integer a = 100;
        System.out.println(a);
        /*
         * Output: 100
         * Auto-boxing converts primitive int to Integer object automatically.
         */

        // 2. Auto-unboxing example
        int b = a;
        System.out.println(b);
        /*
         * Output: 100
         * Auto-unboxing extracts primitive value from wrapper class.
         */

        // 3. Comparing Integer objects using == and equals
        Integer x = 127;
        Integer y = 127;
        System.out.println(x == y);     // true
        System.out.println(x.equals(y)); // true
        /*
         * Output: true true
         * Integers between -128 and 127 are cached, so == compares reference and returns true.
         */

        // 4. Comparing Integer objects outside cache range
        Integer p = 128;
        Integer q = 128;
        System.out.println(p == q);     // false
        System.out.println(p.equals(q)); // true
        /*
         * Output: false true
         * == compares reference which is different; equals compares value.
         */

        // 5. Converting String to int
        String s1 = "123";
        int sVal = Integer.parseInt(s1);
        System.out.println(sVal);
        /*
         * Output: 123
         * parseInt converts String to primitive int.
         */

        // 6. Converting int to String
        int n = 45;
        String s2 = String.valueOf(n);
        System.out.println(s2);
        /*
         * Output: "45"
         * valueOf converts any type to String.
         */

        // 7. String immutability
        String str1 = "Hello";
        String str2 = str1;
        str1 = str1 + " World";
        System.out.println(str2);
        /*
         * Output: Hello
         * Strings are immutable; str1 reassignment doesn't change str2.
         */

        // 8. String equals vs ==
        String strA = "Java";
        String strB = "Java";
        String strC = new String("Java");
        System.out.println(strA == strB); // true
        System.out.println(strA == strC); // false
        System.out.println(strA.equals(strC)); // true
        /*
         * strA and strB point to same string literal; == true
         * strC is new object; == false
         * equals() checks content; returns true
         */

        // 9. StringBuilder vs String
        StringBuilder sb = new StringBuilder("Start");
        sb.append(" End");
        System.out.println(sb);
        /*
         * Output: Start End
         * StringBuilder is mutable and more efficient in concatenations.
         */

        // 10. Converting char to Character
        char ch = 'A';
        Character chObj = ch;
        System.out.println(chObj);
        /*
         * Output: A
         * Auto-boxing converts primitive char to Character object.
         */

        // Remaining 10 examples to be added here

        // 11. Wrapper class null unboxing exception
        Integer nullInt = null;
        try {
            int val = nullInt; // Auto-unboxing null
        } catch (NullPointerException e) {
            System.out.println("NullPointerException caught during unboxing.");
        }
        /*
         * Output: NullPointerException caught during unboxing.
         * Unboxing a null wrapper throws NullPointerException.
         */

        // 12. String intern()
        String s3 = new String("Hello").intern();
        String s4 = "Hello";
        System.out.println(s3 == s4);
        /*
         * Output: true
         * intern() moves string to String pool if not already present.
         */

        // 13. Wrapper valueOf() vs constructor
        Integer int1 = Integer.valueOf(10);
        //Integer int2 = new Integer(10);
        Integer int2 = Integer.valueOf(10);
        System.out.println(int1 == int2);
        /*
         * Output: false if not using value of for object int2 else true if using value of
         * valueOf may return cached object; new always returns new instance.
         */

        // 14. String isEmpty() and length()
        String empty = "";
        System.out.println(empty.isEmpty());  // true
        System.out.println(empty.length());   // 0
        /*
         * isEmpty() checks for zero length; both are equivalent for empty strings.
         */

        // 15. String trim()
        String raw = "  space  ";
        System.out.println("-" + raw.trim() + "-");
        /*
         * Output: -space-
         * trim() removes leading and trailing whitespaces.
         */

        // 16. String split()
        String sentence = "a,b,c";
        String[] parts = sentence.split(",");
        for (String part : parts) System.out.print(part + " ");
        System.out.println();
        /*
         * Output: a b c
         * split() divides the string based on delimiter.
         */

        // 17. Wrapper class toString()
        Double d = 12.34;
        System.out.println(d.toString());
        /*
         * Output: "12.34"
         * Wrapper classes override toString to return readable string values.
         */

        // 18. String replace()
        String orig = "hello world";
        String updated = orig.replace("l", "*");
        System.out.println(updated);
        /*
         * Output: he**o wor*d
         * replace() replaces all occurrences of the target character or substring.
         */

        // 19. Character methods
        char x1 = '9';
        System.out.println(Character.isDigit(x1)); // true
        System.out.println(Character.isLetter(x1)); // false
        /*
         * Character class provides static utility methods to test characters.
         */

        // 20. Converting String to char array and back
        String original = "data";
        char[] chars = original.toCharArray();
        String rebuilt = new String(chars);
        System.out.println(rebuilt);
        /*
         * Output: data
         * toCharArray() and String constructor work together to break and rebuild strings.
         */
    }
}

