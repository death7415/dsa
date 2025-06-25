package tree;

import java.util.*;

public class DFS {

    //Root, Left, Right
    public static void preOrder(TreeNode root){
        if (root == null){
            return;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()){
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if (node.right != null){
                stack.push(node.right);
            }
            if (node.left != null){
                stack.push(node.left);
            }
        }
    }

    //Left, Right, Root
    //this would be done with 2 stacks
    public static List<Integer> postOrder(TreeNode root){
        List<Integer> postOrder = new ArrayList<>();
        if (root == null){
            return postOrder;
        }
        Stack<TreeNode> stack1 =  new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()){
            root = stack1.pop();
            stack2.push(root);
            if (root.left != null){
                stack1.push(root.left);
            }
            if (root.right != null){
                stack1.push(root.right);
            }
        }

        while (!stack2.isEmpty()){
            postOrder.add(stack2.pop().val);
        }
        return postOrder;
    }

    public static List<Integer> inOrderTraversal(TreeNode root){
        List<Integer> inorder =  new LinkedList<>();
        if (root == null){
            return inorder;
        }
        Stack<TreeNode> stack = new Stack<>();
        TreeNode node = root;
        while (true){
            if (node != null){
                stack.push(node);
                node = node.left;
            }else {
                if (stack.isEmpty()){
                    break;
                }
                node = stack.pop();
                inorder.add(node.val);
                node = node.right;
            }
        }
        return inorder;
    }


    public static void main(String[] args) {
        System.out.println(postOrder(TreeNode.getTree1()));
    }
}
