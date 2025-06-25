package geeksForGeeks;

import tree.TreeNode;
import java.util.*;

public class TopViewOfBinaryTree {

    static ArrayList<Integer> topView(TreeNode root) {
        // code here
        if (root == null){
            return new ArrayList<>();
        }
        Map<Integer, Integer> map = new TreeMap<>();
        Queue<AbstractMap.SimpleEntry<Integer, TreeNode>> queue = new LinkedList<>();
        queue.offer(new AbstractMap.SimpleEntry<>(0, root));

        while (!queue.isEmpty()){
            AbstractMap. SimpleEntry<Integer, TreeNode> data = queue.poll();
            TreeNode node = data.getValue();
            int column = data.getKey();
           if (!map.containsKey(column)){
               map.put(column, node.val);
           }
           if (node.left != null){
               queue.offer(new AbstractMap.SimpleEntry<>(column -1, node.left));
           }
           if (node.right != null){
               queue.offer(new AbstractMap.SimpleEntry<>(column + 1, node.right));
           }
        }
        return new ArrayList<>(map.values());
    }
    public static void main(String[] args) {

    }
}
