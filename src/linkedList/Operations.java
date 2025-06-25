package linkedList;

public class Operations {

    public static void main(String[] args) {
        LinkedListByKunal list = new LinkedListByKunal();
        list.add(2);
        list.add(4);
        list.add(6);
        list.add(7);
        list.add(5);

        list.printList();
        System.out.println( "Head = " + list.getHead());
        System.out.println("Tail = " + list.getTail());
        System.out.println("Size = " + list.getSize());
    }
}
