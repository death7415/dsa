package sashCode.trees;

import tree.TreeNode;

import java.util.ArrayList;
import java.util.Stack;

public class InOrderTraversal {
    public static ArrayList<Integer> inOrder(TreeNode root){
        ArrayList<Integer> res= new ArrayList<>();
        if (root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node !=  null){
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            res.add(node.val);
            node = node.right;
        }
        return res;
    }
    public static void main(String[] args) {
        System.out.println(inOrder(TreeNode.getTree1()));
    }
}
