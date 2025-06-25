package linkedList.leetcode;

public class Leetcode141 {
    public boolean hasCycle(ListNode head) {
        if(head == null){
            return false;
        }else if (head.next == null){
            return false;
        }else {
            var slow = head;
            var fast = head;
            while (true){
                slow = slow.next;
                fast = fast.next.next;
                if(fast == null || fast.next == null || fast.next.next == null){
                    return false;
                }else if (fast == slow){
                    return true;
                }
            }
        }
    }
}
