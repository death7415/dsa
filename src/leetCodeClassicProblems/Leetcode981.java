package leetCodeClassicProblems;

import java.util.*;

class TimeMap {
    Map<String , TreeMap<Integer, String>> map;
    public TimeMap() {
        map = new HashMap<>();
    }

    public void set(String key, String value, int timestamp) {
        map.computeIfAbsent(key, k -> new TreeMap<>()).put(timestamp, value);
    }

    public String get(String key, int timestamp) {
        if (map.containsKey(key)){
            TreeMap<Integer, String> timeStampAndValueMap = map.get(key);
            Integer smallestToTimestamp = timeStampAndValueMap.floorKey(timestamp);
            if (smallestToTimestamp == null){
                return "";
            }
            return timeStampAndValueMap.get(smallestToTimestamp);
        }
        return "";
    }
}

public class Leetcode981 {


    public static void main(String[] args) {

    }
}
