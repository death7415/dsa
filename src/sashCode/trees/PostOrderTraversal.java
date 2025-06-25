package sashCode.trees;

import tree.TreeNode;

import java.util.*;

public class PostOrderTraversal {
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> res = new ArrayList<>();
        if (root == null){
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        while (current != null || !stack.isEmpty()){
            while (current != null){ //going till extreme left
                stack.push(current);
                current = current.left;
            }

            if (!stack.isEmpty() && stack.peek().right != null){
                current = stack.peek().right; // we are visiting right
            }else {
                TreeNode temp = stack.pop(); //this is are root now
                res.add(temp.val);
                // now you are checking what ever is popped,
                // is it right side node of the top element present in stack, if yes
                // than we know, left is already processed because we would not be inside this else block.
                while (!stack.isEmpty() && temp == stack.peek().right){
                    temp = stack.pop();
                    res.add(temp.val);
                }
            }
        }
        return res;

        /*

        ArrayList<Integer> res = new ArrayList<>();
        if(root == null){
            return res;
        }
        Stack<Node> st = new Stack<>();
        Node curr = root;
        while(curr != null || !st.isEmpty()){
            while(curr != null){
                st.push(curr);
                curr = curr.left;
            }

            if(st.peek().right != null){
                curr = st.peek().right;
            }else{
                Node temp = st.pop();
                res.add(temp.data);
                while(!st.empty() && temp == st.peek().right){
                    temp = st.pop();
                    res.add(temp.data);
                }
            }
        }
        return res;



         */
    }

    public static void main(String[] args) {

    }
}
