package sashCode.trees;


import tree.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFS {
    public static ArrayList<Integer> BFS(TreeNode root){
        ArrayList<Integer> res = new ArrayList<>();
        Queue<TreeNode>queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            TreeNode node =  queue.poll();
            res.add(node.val);
            if (node.left != null){
                queue.offer(node.left);
            }
            if (node.right != null){
                queue.offer(node.right);
            }
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(BFS(TreeNode.getTree1()));
    }
}
