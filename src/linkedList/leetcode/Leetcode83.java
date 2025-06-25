package linkedList.leetcode;

public class Leetcode83 {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode pointer1 = head;
        ListNode pointer2 = head.next;

        while (pointer2 != null) {
            if (pointer1.val == pointer2.val){
                pointer1.next = pointer2.next;
            }else {
                pointer1 = pointer1.next;
            }
            pointer2 = pointer2.next;
        }
        return head;
    }
}
