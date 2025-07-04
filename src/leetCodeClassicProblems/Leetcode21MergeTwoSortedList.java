package leetCodeClassicProblems;

import linkedList.leetcode.ListNode;

public class Leetcode21MergeTwoSortedList {
    public static ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode resultListNode = new ListNode(-1);
        ListNode current = resultListNode;
        helper(current, list1, list2);
        return resultListNode.next;
    }

    private static void helper(ListNode current, ListNode list1, ListNode list2){
        if (list1 == null){
            current.next = list2;
            return;
        }
        if (list2 == null){
            current.next = list1;
            return;
        }
        if (list1.val < list2.val){
            current.next = list1;
            helper(current.next, list1.next, list2);
        }else {
            current.next = list2;
            helper(current.next, list1, list2.next);
        }
    }

    public static void main(String[] args) {

    }
}
