package linkedList.leetcode;


import java.util.HashMap;

public class Leetcode142 {

    // solution one
//    public static int findLengthOfCycle(ListNode head){
//        ListNode slow = head;
//        ListNode fast = head;
//        while (true){
//            slow = slow.next;
//            fast = fast.next.next;
//            if(fast == null || fast.next == null || fast.next.next == null){
//                return 0;
//            }else if (fast == slow){
//                ListNode tempNode = slow;
//                int length= 0;
//                do {
//                    tempNode = tempNode.next;
//                    length++;
//                }while (tempNode != slow);
//                return length;
//            }
//        }
//    }
//    public static ListNode detectCycle(ListNode head) {
//        int length = 0;
//        ListNode slow = head;
//        ListNode fast = head;
//        while (true){
//            slow = slow.next;
//            fast = fast.next.next;
//            if(fast == null || fast.next == null || fast.next.next == null){
//                return  null;
//            }else if (fast == slow){
//                length = findLengthOfCycle(slow);
//                break;
//            }
//        }
//        if (length == 0){
//            return null;
//        }
//        ListNode first = head;
//        ListNode second = head;
//
//        while (length > 0){
//            second = second.next;
//            length--;
//        }
//
//        while (first != second){
//            first = first.next;
//            second = second.next;
//        }
//        return first;
//    }


    //solution second, it is slow but does job;

    public ListNode detectCycle(ListNode head) {
        ListNode pointer = head;
        HashMap<ListNode, Integer> nodeMap = new HashMap<>();
        int i = 0;
        while (pointer != null){
            if (nodeMap.containsKey(pointer)){
                return pointer;
            }
            nodeMap.put(pointer, i);
            pointer = pointer.next;
            i++;
        }
        return null;
    }
}
