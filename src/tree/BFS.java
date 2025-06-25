package tree;

import java.util.*;

public class BFS {

    public static List<List<Integer>> doBfs(TreeNode root){
        List<List<Integer>> result = new ArrayList<>();
        Queue<TreeNode> queue = new LinkedList<>();
        if (root == null){
            return result;
        }
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> subList = new LinkedList<>();
            int level = queue.size();
            for (int i =0; i < level; i++){
                assert queue.peek() != null;
                if (queue.peek().left != null){
                    queue.offer(queue.peek().left);
                }
                assert queue.peek() != null;
                if (queue.peek().right != null){
                    queue.offer(queue.peek().right);
                }
                assert queue.peek() != null;
                subList.add(queue.poll().val);
            }
            result.add(subList);
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(doBfs(TreeNode.getTree1()));
    }
}
