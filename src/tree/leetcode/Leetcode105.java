package tree.leetcode;

import tree.TreeNode;

public class Leetcode105 {

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int []idx = {0};
        return construct(preorder, inorder, 0, preorder.length - 1, idx);
    }

    public static TreeNode construct(int[] pre, int []in, int start, int end, int []idx){
        if (start > end){
            return null;
        }
        int rootVal = pre[idx[0]];
        int i = start;
        for (; i <= end; i++){
            if (in[i] == rootVal){
                break;
            }
        }
        idx[0]++;
        TreeNode root = new TreeNode(rootVal);
        root.left = construct(pre, in, start, i - 1, idx);
        root.right = construct(pre, in, i + 1, end, idx);
        return root;
    }

    private static int find(int ele, int[] in) {
        for (int i = 0; i < in.length; i++){
            if (in[i] == ele){
                return i;
            }
        }
        return -1;
    }

    public static void main(String[] args) {

    }
}
