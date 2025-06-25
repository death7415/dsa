package tree;

import java.util.ArrayList;
import java.util.List;

public class PrePostInAllOrderTraversal {

    public static void prePostIn(TreeNode node, List<Integer> preOrder, List<Integer> inOrder, List<Integer> postOrder){
        if (node == null){
            return;
        }
        preOrder.add(node.val);
        prePostIn(node.left, preOrder, inOrder, postOrder);
        inOrder.add(node.val);
        prePostIn(node.right, preOrder, inOrder, postOrder);
        postOrder.add(node.val);
    }

    public static void main(String[] args) {

        List<Integer> pre = new ArrayList<>();
        List<Integer> inOrder = new ArrayList<>();
        List<Integer> postOrder = new ArrayList<>();

        prePostIn(TreeNode.getTree1(), pre, inOrder, postOrder);

        System.out.println("pre-order = " + pre);
        System.out.println("in-order = " + inOrder);
        System.out.println("post-order = " + postOrder);
    }
}
