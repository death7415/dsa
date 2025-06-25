package tree.nary;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class Leetcode429 {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res =  new ArrayList<>();
        if (root == null){
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            List<Integer> subList = new ArrayList<>();
            int level = queue.size();
            for (int i = 0; i < level; i++){
                Node node = queue.poll();
                assert node != null;
                subList.add(node.val);
                for (Node child : node.children){
                    if (child != null){
                        queue.offer(child);
                    }
                }
            }
            res.add(subList);
        }
        return res;
    }

}
