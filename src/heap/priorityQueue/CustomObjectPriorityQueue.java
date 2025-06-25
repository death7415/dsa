package heap.priorityQueue;

import java.util.PriorityQueue;

public class CustomObjectPriorityQueue {

    public static void main(String[] args) {
//        PriorityQueue<Student> minPq = new PriorityQueue<>(Comparator.comparingInt(nameLen -> nameLen.getName().length()));
        PriorityQueue<Student> minPq = new PriorityQueue<>((a,b) -> b.getName().length() - a.getName().length());

        minPq.offer(new Student(1, "Alice Thomas", 10));
        minPq.offer(new Student(2, "Bob Nob", 10));
        minPq.offer(new Student(3, "Carol Seth", 11));
        minPq.offer(new Student(4, "David Gupta Timber-lake", 11));
        minPq.offer(new Student(5, "Eve Sharma", 12));
        minPq.offer(new Student(6, "Frank Hash-Brownie", 12));

        System.out.println(minPq.peek());
        System.out.println(minPq.contains(new Student(1, "Alice Thomas", 10)));

    }
}
