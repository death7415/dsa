package dsa;

import java.util.*;

public class Leetcode {

    public static boolean containsDuplicate(int[] nums) {
//        return Arrays.stream(nums).parallel()
//                .boxed()
//                .distinct()
//                .toList().size() < nums.length;

//        if ( nums != null && nums.length < 2){
//            return false;
//        }
//        int pointer1 = 0, pointer2 = 1;
//        assert nums != null;
//        Arrays.sort(nums);
//        if (nums[pointer1] == nums[nums.length-1]){
//            return true;
//        }
//        while (pointer1 < nums.length && pointer2 < nums.length){
//            if(nums[pointer1] == nums[pointer2]){
//                return true;
//            }
//            ++pointer1;
//            ++pointer2;
//        }
//        return false;
//        List<Integer> list = new ArrayList<>(nums.length);
//        for (int num : nums) {
//            list.add(num);
//        }
//        Set<Integer> set = new TreeSet<>(list);
//        return set.size() != nums.length;

        Set<Integer> set = new HashSet<>();
        for (int n : nums){
            if (set.contains(n)){
                return true;
            }
              set.add(n);
        }
        return false;
    }

    public static boolean isAnagram(String s, String t) {
//        var map1 = s.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        var map2 = t.chars().boxed().collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
//        return  (map1.equals(map2));

        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return Arrays.equals(str1, str2);
    }

    public static int[] replaceElements(int[] arr) {
        int rightMax  = -1;
        for (int i = arr.length-1; i >= 0; i--){
            int newMAX = Math.max(rightMax, arr[i]);
            arr[i] = rightMax;
            rightMax = newMAX;
        }
        return arr;
    }

    public static boolean isSubsequence(String s, String t) {
        if (s.isEmpty() && t.isEmpty()){
            return true;
        }else {
            char[] charS = s.toCharArray();
            char[] charT = t.toCharArray();

            int pointer1 = 0;
            int pointer2 = 0;
            while(pointer1 < charS.length && pointer2 < charT.length){
                if (charS[pointer1] == charT[pointer2]){
                    pointer1++;
                }
                pointer2++;
            }

            return pointer1 == s.length();
        }
    }

    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < nums.length; i++){
            int res = target - nums[i];
            if (map.containsKey(res)){
                return new int[]{map.get(res), i};
            }
            map.put(nums[i], i);
        }
        return new int[]{-1, -1};
    }

    public boolean isHappy(int n) {
       return false;
    }

    public static void main(String[] args) {
//       String s = "aec";
//       String t = "ahbgdce";
//       System.out.println(isSubsequence(s, t));

        System.out.println(Arrays.toString(twoSum(new int[]{2,7,11,15}, 9)));
    }
}
