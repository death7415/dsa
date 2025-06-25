package queue;

public class Queue {

    class Node{
        int value;
        Node next;

        public Node(int value){
            this.value = value;
            this.next = null;
        }
    }
    private Node first;
    private Node last;
    private int length;

    public void enqueue(int value){
        var node = new Node(value);
        if (first == null){
            first = node;
        }else {
            last.next = node;
        }
        last = node;
        length++;
    }

    public Node dequeue(){
        if (first == null){
            return null;
        }else if (length == 1){
            first = last = null;
            length--;
        }else {
            Node current = first;
            Node before = null;
            while (true) {
                if (current == last){
                    last = before;
                    last.next = null;
                    length --;
                    return current;
                }
                    before = current;
                    current = current.next;
            }
        }
        return null;
    }
}
