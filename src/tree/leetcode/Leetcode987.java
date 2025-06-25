package tree.leetcode;

import tree.TreeNode;

import java.util.*;

public class Leetcode987 {
    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root  == null){
            return res;
        }
        Map<Integer, TreeMap<Integer, List<Integer>>> map = new TreeMap<>();
        dfs(root, map, 0, 0);

        for (Map.Entry<Integer, TreeMap<Integer, List<Integer>>> colEntry : map.entrySet()) {
            TreeMap<Integer, List<Integer>> levelEntry = colEntry.getValue();
            List<Integer> list = new ArrayList<>();
            for (Map.Entry<Integer, List<Integer>> subEntry : levelEntry.entrySet()) {
                List<Integer> subList = subEntry.getValue();
                Collections.sort(subList);
                list.addAll(subList);
            }
            res.add(list);
        }

        return res;
    }

    private static void dfs(TreeNode root, Map<Integer, TreeMap<Integer, List<Integer>>> map, int col, int level){
        if (root == null){
            return;
        }
        if (!map.containsKey(col)){
            map.put(col, new TreeMap<>());
        }
        if (!map.get(col).containsKey(level)){
            map.get(col).put(level, new ArrayList<>());
        }
        map.get(col).get(level).add(root.val);
        dfs(root.left, map, col -1, level +1);
        dfs(root.right, map, col + 1, level +1);
    }

    public static void main(String[] args) {
        System.out.println(verticalTraversal(TreeNode.getTree1()));
    }
}
