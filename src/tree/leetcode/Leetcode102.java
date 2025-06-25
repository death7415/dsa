package tree.leetcode;

import com.sun.source.tree.Tree;
import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode102 {

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> subList = new ArrayList<>();
            int level = queue.size();
            for (int i = 0; i < level; i++){
                TreeNode node = queue.poll();
                subList.add(node.val);
                if (node.left != null){
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
            }
            res.add(subList);
        }
        return res;
    }

    public static void main(String[] args) {

    }
}
