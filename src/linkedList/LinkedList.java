package linkedList;


public class LinkedList {

    static class Node{
        private int value;
        private Node next;
        public Node(int value) {
            this.value = value;
            this.next = null;
        }
    }

    private Node head;
    private Node tail;
    private int length = 0;

    public int getLength() {
        return this.length != 0 ? this.length : 0 ;
    }

    public int getHead() {
        return head != null ? head.value : -1;
    }

    public int getTail() {
        return tail != null ? tail.value : -1;
    }

    public void add(int value) {
        var node = new Node(value);
        if(head == null){
            head = node;
        }else {
           tail.next = node;
        }
        tail = node;
        this.length ++;
    }

    public boolean removeLast() {
        if(head == null){
            return false;
        }else if(this.length == 1){
            this.head = null;
            this.tail = null;
            this.length = 0;
            return true;
        }else {
            var tempHead = head;
            var pre_tail = head;
            while(tempHead.next != null){
                pre_tail = tempHead;
                tempHead = tempHead.next;
                if(tempHead.next == null){
                    tail = pre_tail;
                    tail.next = null;
                    length -= 1;
                }
            }
            return true;
        }
    }

    public int addFirst(int value){
        var node  = new Node(value);
        if(head == null){
            head = node;
            tail = node;
            this.length = 1;
        }else{
            var tempHead = head;
            head = node;
            head.next = tempHead;
            tempHead = null;
            length += 1;
        }
        return head.value;
    }

    public boolean removeFirst(){
        if(head == null){
            this.length = 0;
            return false;
        } else if (this.length == 1) {
            head = null;
            tail = null;
            this.length = 0;
            return true;
        } else {
            var tempHead = head;
            head = tempHead.next;
            length -=1;
            return true;
        }
    }

    public int getValueAtIndex(int index){
        if(head == null){
            return -1;
        } else if (this.length == 1 || index == 0) {
            return head.value;
        } else if (index <= -1  || index > length-1){
            return -1;
        } else {
            int i  = 0;
            var temp = head;
            while(true){
                temp = temp.next;
                i++;
                if(i == index) {
                    return temp.value;
                }
            }
        }
    }

    public boolean setElement(int index,  int value){
        var node = new Node(value);
        if(head == null && index > 0 ){
            head = node;
            tail = node;
            length = 1;
            return true;
        } else {
            if(index > length -1){
                return false;
            }
            if(index == 0){
                assert head != null;
                head.value = value;
                return true;
            }else {
                var tempHead = head;
                int i = 0;
                while(true){
                    tempHead = tempHead.next;
                    i++;
                    if(i == index){
                        tempHead.value = value;
                        return true;
                    }
                }
            }
        }
    }

    public boolean insert(int index,  int value){
        var node = new Node(value);
        if(index >= length || index < 0){
            return false;
        }else if (index == 0){
            var response = addFirst(value);
            return response >= 0;
        }else {
            var tempHead = head;
            var preTail = head;
            int i = 0;
            while (true){
                tempHead = tempHead.next;
                i++;
                if(i == index){
                    preTail.next = node;
                    node.next = tempHead;
                    length += 1;
                    return true;
                }
                preTail = preTail.next;
            }
        }
    }

    public boolean remove(int index){
        if(head == null || index <= -1 || index > length-1){
            return false;
        }else if(length == 1 && index == 0){
            head = null;
            tail = null;
            length = 0;
        } else if (length > 1 && index == 0) {
            return removeFirst();
        }else if(index == length - 1){
            return removeLast();
        } else {
            var tempHead = head;
            var preTempHead = head;
            int i = 0;
            while (true){
                tempHead = tempHead.next;
                i++;
                if(i == index){
                    preTempHead.next = tempHead.next;
                    tempHead.next = null;
                    length -= 1;
                    return true;
                }
                preTempHead = preTempHead.next;
            }
        }
        return false;
    }

    public boolean reverse(){
        if(head == null){
            return false;
        }else if(length == 1){
            return true;
        }else {
            var tempHead = head;
            head = tail;
            tail = tempHead;

            var afterTempHead = tempHead.next;
            Node beforeTempHead = null;

            int i = 0;
            while(i < length){
                afterTempHead = tempHead.next;
                tempHead.next = beforeTempHead;
                beforeTempHead = tempHead;
                tempHead = afterTempHead;
                i++;
            }
            assert beforeTempHead != null;
            return head.value == beforeTempHead.value;
        }
    }

    public void printList(){
        if(head == null || tail == null){
            return;
        }
        var printNode = head;
        while (printNode != null){
            System.out.println(printNode.value);
            printNode = printNode.next;
        }
    }
    /*
     *
     * """ LeetCode Exercise """
     *
     *
     */

    public Object findMiddleNode(){
        if(head == null){
            return -1;
        } else if (length == 1) {
            return head.value;
        } else if (length == 2) {
            return tail.value;
        } else {
            var slow = head;
            var fast = head.next.next;
            while (true) {
                slow = slow.next;
                if (fast == null || fast.next == null || fast.next.next == null) {
                    if(length % 2 == 0){
                        return slow.next.value;
                    }else {
                        return slow.value;
                    }
                }
                fast = fast.next.next;
            }
        }
    }

    public boolean hasLoop(){
        if(head == null){
            return false;
        }else if (length == 1){
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

    public Object findKthFromEnd(int k){
        if(head ==  null){
            return null;
        }else if (k > length){
            return null;
        } else if (k == 1) {
            return tail;
        } else if (k == length ) {
            return head;
        }else {
            var pointer = head;
            int i = 0;
            int toReachIndex = length - k;
            while (true) {
                if(i == toReachIndex){
                    return pointer;
                }
                pointer = pointer.next;
                i++;
            }
        }
    }

    public void partitionList(int x){
        if(head == null){
            return;
        } else if (length == 1) {
            return;
        }else {
            var leftNode = new Node(Integer.MIN_VALUE);
            var rightNode = new Node(Integer.MAX_VALUE);

            var leftNodePointer = leftNode;
            var rightNodePointer = rightNode;

            var pointer = head;
            int i = 0;
            while (i < length){
                if(pointer.value < x){
                    leftNodePointer.next = new Node(pointer.value);
                    leftNodePointer = leftNodePointer.next;
                }else {
                    rightNodePointer.next = new Node(pointer.value);
                    rightNodePointer = rightNodePointer.next;
                }
                pointer = pointer.next;
                i++;
            }
            if(leftNode.next ==  null){
                head = rightNode.next;
            }
            else {
                head = leftNode.next;
                leftNodePointer.next = rightNode.next;
            }
                tail = rightNodePointer;
                leftNode.next = null;
                rightNode.next = null;
        }
    }

    public void removeDuplicates(){
        if (head == null){
            return;
        } else if (length == 1) {
            return;
        } else if (length == 2) {
            if(head.value == head.next.value){
                head.next = null;
                return;
            }
            return;
        } else {
            var current = head;
            while (current != null) {
                var runner = current;
                while (runner.next != null) {
                    if (runner.next.value == current.value) {
                        runner.next = runner.next.next;
                        length--;
                    } else {
                        runner = runner.next;
                    }
                }
                current = current.next;
            }
        }
    }

    public int convertBinaryListToDecimal(){
        if(head == null || length == 0){
            return -1;
        } else {
            var pointer = head;
            int num = 0;
            while (pointer != null){
                num = (num*2) + pointer.value;
                pointer = pointer.next;
            }
            return num;
        }
    }

    //TODO: Complete this post doublyLinkedList Classes
    public void reverse(int m, int n){
        if (head == null){
            return;
        }else if (length == 1 || m == n){
            return;
        } else if (m <=-1 || n >= length ) {
            return;
        } else if (m >= n) {
            return;
        } else {
            Node beforeForStartIndex = null;
            Node currentForStartIndex = head;
            Node afterForEndIndex =  null;
            int i =0;
            while (i < m){
                beforeForStartIndex = currentForStartIndex;
                currentForStartIndex = currentForStartIndex.next;
                i++;
            }
            int j = m;
            Node currentForEndIndex = currentForStartIndex;
            while (j < n){
                currentForEndIndex = currentForEndIndex.next;
                j++;
            }
            afterForEndIndex = currentForEndIndex.next;
            var current = currentForStartIndex;
            var prev = afterForEndIndex;

            while (current != afterForEndIndex){
                var node = current.next;
                current.next = prev;
                prev = current;
                current = node;
            }
            if(beforeForStartIndex != null){
                beforeForStartIndex.next = currentForEndIndex;
            }else {
                head = currentForEndIndex;
            }
        }
    }
}
