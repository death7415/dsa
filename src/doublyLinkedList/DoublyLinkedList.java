package doublyLinkedList;

public class DoublyLinkedList {

     class Node{
          int value;
          Node next;
          Node previous;

        public Node(int value){
            this.value = value;
        }
     }

    private Node head;
    private Node tail;
    private int length = 0;

    public int getLength() {
        return this.length;
    }

    public int getHead() {
        return this.head != null ? head.value : -1;
    }

    public int getTail() {
        return this.tail != null ? tail.value : -1;
    }

    public void printList(){
        if (head == null) return;
        var current = head;
        while (current != null){
            System.out.println(current.value);
            current = current.next;
        }
    }

    public void add(int value){
        var node = new Node(value);
        if (head == null){
            head = node;
        }else {
            node.previous = tail;
            tail.next = node;
        }
        tail = node;
        length++;
    }

    public Node removeLast(){
        if(head == null){
            return null;
        } else if (length == 1) {
            head = null;
            tail = null;
            length--;
        }else {
            var temp = tail;
            tail = tail.previous;
            tail.next = null;
            temp.previous = null;
            length--;
            return temp;
        }
        return null;
    }

    public Node prepend(int value){
        var node = new Node(value);
        if(head == null){
            head = node;
            tail = node;
        } else {
            node.next = head;
            head.previous = node;
            head = node;
        }
        length++;
        return node;
    }

    public Node removeFirst(){
        if (head == null){
            return null;
        }else if(length == 1) {
            var temp = head;
            head = null;
            tail = null;
            length --;
            return temp;
        }else {
            var current = head;
            head = head.next;
            head.previous = null;
            current.next = null;
            length--;
            return current;
        }
    }

    public Node get(int index){
        if(head == null || index >= length || index <= -1){
            return null;
        }else{
            var node = head;
            if (index < length/2){
                int i = 0;
                while (i < index){
                    node = node.next;
                    i++;
                }
            }else {
                int i = length-1;
                node = tail;
                while (i > index){
                    node = node.previous;
                    i--;
                }
            }
            return node;
        }
    }

    public boolean set(int index, int value){
        Node current = get(index);
        if(current != null){
            current.value = value;
            return true;
        }
        return false;
    }

    public boolean insert(int index, int value){
        var node = new Node(value);
        if (index == 0){
            return prepend(value) != null;
        } else if (index == length) {
            add(value);
            return true;
        }
        var current =  get(index);
        if (current != null){
           var previousNode = current.previous;
           previousNode.next = node;
           node.previous = previousNode;
           node.next = current;
           current.previous = node;
           length ++;
           return true;
        }
        return false;
    }

    public boolean remove(int index) {
        if (head == null || index >= length || index <= -1) {
            return false;
        } else if (index == 0 && length == 1) {
            head = null;
            tail = null;
            length--;
        } else if (index == length-1) {
            return removeLast() != null;
        }else {
            var current = get(index);
            current.previous.next = current.next;
            current.next.previous = current.previous;
            current.next = null;
            current.previous = null;
            length--;
            return true;
        }
        return false;
    }

    public void swapFirstLast(){
        if(head == null){
            return;
        }else if (length < 2){
            return;
        }else {
            int headValue = head.value;
            head.value = tail.value;
            tail.value = headValue;
        }
    }

    public void reverse(){
        if (head == null || tail == null){
            return;
        } else if (length == 1) {
            return;
        }else {
            var node = new Node(0);
            var pointer = tail;
            var nodePointer = node;
            var nodePointerPrevious = nodePointer;

            while(pointer != null){
                nodePointer.next = pointer;
                pointer = pointer.previous;
                nodePointer = nodePointer.next;
                nodePointer.previous = nodePointerPrevious;
                nodePointerPrevious = nodePointer;
            }
            head = node.next;
            tail = nodePointerPrevious;
            head.previous = null;
            tail.next = null;
        }
    }

    public boolean isPalindrome(){
        if (head == null || tail == null){
            return false;
        }else if (length == 1){
            return true;
        }else {
            var current = head;
            var last = tail;
            boolean isPalindrome = true;
            while (current != last){
                if (current.value != last.value){
                    isPalindrome = false;
                    break;
                }
                current = current.next;
                last = last.previous;
            }
            return isPalindrome;
        }
    }

    public void swapPairs(){
        if (head == null){
            return;
        }else if (length == 1){
            return;
        }else if (length == 2){
            var tempHead = head;
            head = head.next;
            head.previous = null;
            head.next = tempHead;
        }else {
            var current = head;
            var node = new Node(0);
            while (current != null){
                node.next = current.next;

                current=current.next;
            }
        }
    }
}
