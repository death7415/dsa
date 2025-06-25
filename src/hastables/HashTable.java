package hastables;

import java.util.*;

@SuppressWarnings("unchecked")
public class HashTable<T extends Comparable<T>, K> {
    private final Node[] dataMap;
    int size = 9;

    static class Node<T, K>
    {
        T key;
        K value;
        Node<T, K> next;

        public Node(T key, K value){
            this.key = key;
            this.value = value;
        }
    }

    public HashTable(){
        dataMap = new Node[size];
    }

    private int getHashKey(T key){
        return Math.abs(key.hashCode() % dataMap.length);
    }

    public void put(T key, K value){
        int index = getHashKey(key);
        var node = new Node<>(key, value);
        if (dataMap[index] == null){
            dataMap[index] = node;
        }else {
            var temp = dataMap[index];
            while (temp.next != null){
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public void printTable(){
        for (int i = 0; i < dataMap.length; i++) {
            System.out.print("index " + i + ": ");
            var current = dataMap[i];
            if (current == null) {
                System.out.println("null");
                continue;
            }
            while (current != null) {
                System.out.print("{ " + current.key + ": " + current.value + " } -> ");
                current = current.next;
            }
            System.out.println("null");
        }
    }

    public ArrayList<K> get(T key){
        var res = new ArrayList<K>();
        if (key == null){
            return null;
        }else {
            int index = getHashKey(key);
            var pointer = dataMap[index];
            while (pointer != null){
                if(key.equals((pointer.key))){
                    res.add((K)pointer.value);
                }
                pointer = pointer.next;
            }
            return res;
        }
    }

    public ArrayList<T> getKeys(){
        var res = new ArrayList<T>();
        for (var pointer : dataMap) {
            while (pointer != null) {
                if (!res.contains((T)pointer.key)){
                    res.add((T)pointer.key);
                }
                pointer = pointer.next;
            }
        }
        return res;
    }

    /***
     *
     *
     * Interview Exercise
     *
     */

    public boolean itemInCommon(int []array1, int [] array2){
        var hashmap = new HashMap<Integer, Boolean>();
        for (int item : array1){
            hashmap.put(item, true);
        }

        for (int item : array2){
            if (hashmap.containsKey(item)){
                return true;
            }
        }
        return false;
    }

    public List<Integer> findDuplicates(int []nums){
        List<Integer> res = new ArrayList<>();
        HashMap<Integer, Integer> map = new HashMap<>();
        int counter = 1;
        for (int item : nums){
            //map.getOrDefault(item, 0);
            if (map.containsKey(item)){
                counter++;
                map.put(item, counter);
            }else {
                map.put(item, counter);
            }
            counter = 1;
        }

        map.forEach((key, value)-> {
            if (value > 1){
                res.add(key);
            }
        });
        return res;
    }

    public Character firstNonRepeatingChar(String str){
        List<Character> res = new LinkedList<>();
        HashMap<Character, Integer> map = new HashMap<>();
        for (char ch : str.toCharArray()){
            map.put(ch, map.getOrDefault(ch, 0)+1);
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (map.get(c) == 1) {
                return c;
            }
        }
        return null;
    }

    public List<List<String>> groupAnagrams(String[] strings){
        Map<String, ArrayList<String>> map = new HashMap<>();
        for (String s: strings){
            char[] chars = s.toCharArray();
            Arrays.sort(chars);
            String checkKey = new String(chars);

            if (map.containsKey(checkKey)){
                map.get(checkKey).add(s);
            }else {
                ArrayList<String> unit = new ArrayList<>();
                unit.add(s);
                map.put(checkKey, unit);
            }
        }
        return new ArrayList<>(map.values());
    }

    public int[] twoSum(int target, int []nums){
        HashMap<Integer, Integer> values = new HashMap<>();
        ArrayList<Integer> al = new ArrayList<>();
        for (int i=0; i < nums.length; i++){
            values.put(nums[i], i);
        }
        values.forEach((key, value)->{
            System.out.println(key + " : "+ value);
        });
        for (int i=0; i < nums.length; i++){
            int toFind = target- nums[i];
            if (values.containsKey(toFind) && i != values.get(toFind)){
                return new int[]{i, values.get(toFind)};
            }
        }
        return new int[0];
    }

    public int[] subarraySum(int []nums, int target){
        int sum = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0,-1);
        for(int i= 0; i < nums.length; i++){
            sum += nums[i];
            int check = sum - target;
            if (!map.containsKey(check)){
                map.put(sum, i);
            }else {
                return new int[]{map.get(check) +1, i};
            }
        }
        return new int[]{};
    }

    public static List<Integer> removeDuplicates(List<Integer> myList){
        HashSet<Integer> set = new HashSet<>(myList);
        return new ArrayList<>(set);
    }

    public static boolean hasUniqueChars(String string){
        Set<Character> set = new HashSet<>();
        for (Character c : string.toCharArray()){
            set.add(c);
        }
        return string.length() == set.size();
    }

    public static List<int[]> findPairs(int[] arr1, int[] arr2, int target){
        HashSet<Integer> firstArraySet = new HashSet<>();

        List<int[]> result = new ArrayList<>();
        for (int item: arr1){
            firstArraySet.add(item);
        }
        for (int item : arr2){
            int check= target - item;
            if (firstArraySet.contains(check)){
                result.add(new int[]{check, item});
            }
        }
        return result;
    }

    //TODO: Overcome time-complexity issue
    public static int longestConsecutiveSequence(int []nums){
        if (nums.length == 0){
            return 0;
        }
        Set<Integer> set = new TreeSet<>();
        for (int item : nums){
            set.add(item);
        }
        int counter = 1;
        ArrayList<Integer> res = new ArrayList<>();
        LinkedList<Integer> list = new LinkedList<>(set);
        for (int i=0; i < list.size(); i++){
            if((i+1) < list.size() && (list.get(i+1) - list.get(i) == 1)){
                counter ++;
                res.add(counter);
            }else {
                counter = 1;
                res.add(counter);
            }
        }
        res.sort(Collections.reverseOrder());
        return res.getFirst();
    }
}
