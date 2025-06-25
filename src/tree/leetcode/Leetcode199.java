package tree.leetcode;

import tree.TreeNode;

import java.util.*;

public class Leetcode199 {

    public static List<Integer> rightSideView(TreeNode root) {

        List<Integer> res = new LinkedList<>();
        if (root == null){
            return res;
        }
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        while (!queue.isEmpty()){
            int n = queue.size();
            TreeNode node = null;

            while (n > 0){
                node = queue.poll();
                assert node != null;
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer( node.right);
                }
                n--;
            }
            res.add(node.val);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
