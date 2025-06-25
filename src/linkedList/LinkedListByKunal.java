package linkedList;

import lombok.Getter;

public class LinkedListByKunal {
    private static class Node{
        private int val;
        private Node next;

        public Node(int val, Node next){
            this.val = val;
            this.next = next;
        }

        public Node(int val){
            this.val = val;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    @Getter
    private int size;

    public LinkedListByKunal(){}

    public void add(int val){
        Node newNode = new Node(val);
        if (head == null){
            head = newNode;
            tail = newNode;
        }
        tail.next = newNode;
        tail = tail.next;
        this.size +=1;
    }

    public void printList(){
        Node pointer = head;
        while (pointer != null){
            System.out.println(pointer.val);
            pointer = pointer.next;
        }
    }

    public int getHead(){
       return this.head.val;
    }

    public int getTail(){
        return this.tail.val;
    }
}
