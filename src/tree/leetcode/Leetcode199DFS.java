package tree.leetcode;

import tree.TreeNode;

import java.util.*;

public class Leetcode199DFS {

    public static List<Integer> rightSideView(TreeNode root) {
        
        List<Integer> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        helper(root, res, 1);
       return res;
    }

    private static void helper(TreeNode node, List<Integer> res, int level){
        if (node == null){
            return;
        }
        if (res.size() < level){
            res.add(node.val);
        }
        helper(node.right, res, level + 1);
        helper(node.left, res, level + 1);
    }

    public static void main(String[] args) {

    }
}
