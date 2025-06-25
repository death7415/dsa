package tree;

public class BinaryTreeWithKunal {
    public static void preOrderTraversal(TreeNode node){
        if (node == null){
            return;
        }
        System.out.print(node.val + " ");
        preOrderTraversal(node.left);
        preOrderTraversal(node.right);
    }

    public static void postOrderTraversal(TreeNode node){
        if (node == null){
            return;
        }
        postOrderTraversal(node.left);
        postOrderTraversal(node.right);
        System.out.print(node.val + " ");
    }

    public static void inOrderTraversal(TreeNode node){
        if (node == null){
            return;
        }
        inOrderTraversal(node.left);
        System.out.print(node.val + " ");
        inOrderTraversal(node.right);
    }


    public static void main(String[] args) {

        //      5
        //     / \
        //   12   13
        //   /  \    \
        //  7    14   2
        // /  \   /  \  / \
        //17 23 27  3  8  11

        inOrderTraversal(TreeNode.getTree1());
    }
}
