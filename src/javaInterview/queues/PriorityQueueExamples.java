package javaInterview.queues;

// Java PriorityQueue Deep Dive - 30 Detailed Examples

import java.util.*;

public class PriorityQueueExamples {
    public static void main(String[] args) {

        // 1. Basic min-heap behavior (natural order)
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(5);
        pq.offer(1);
        pq.offer(3);
        System.out.println("1: Natural order (min-heap) = " + pq);
        /*
         * PriorityQueue uses a binary heap internally.
         * By default, it behaves like a min-heap (smallest element has highest priority).
         * Maintains partial ordering — peek() always gives the smallest element.
         * Doesn't guarantee complete sorting when printed directly.
         */

        // 2. Accessing head element with peek()
        System.out.println("2: Peek = " + pq.peek());
        /*
         * peek() retrieves the element with the highest priority without removing it.
         * It operates in O(1) time.
         * Safe to call on an empty queue — returns null instead of throwing exception.
         * Useful for inspecting the next task/job.
         */

        // 3. Removing head element with poll()
        System.out.println("3: Poll = " + pq.poll());
        System.out.println("3: After poll = " + pq);
        /*
         * poll() removes and returns the head of the queue.
         * Internally swaps and heapifies the new root to maintain heap invariant.
         * Operates in O(log n) time.
         * Does not throw an exception if the queue is empty — returns null.
         */

        // 4. Using a max-heap with custom comparator
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());
        maxHeap.addAll(Arrays.asList(1, 4, 2, 6));
        System.out.println("4: Max-heap = " + maxHeap);
        /*
         * By passing Comparator.reverseOrder(), we can turn the min-heap into a max-heap.
         * Now highest element has highest priority.
         * Internally still uses a binary heap structure.
         * Great for top-k maximum elements.
         */

        // 5. Adding duplicate elements
        pq.offer(3);
        pq.offer(3);
        System.out.println("5: With duplicates = " + pq);
        /*
         * PriorityQueue allows duplicates.
         * Duplicates are handled like normal elements based on comparator.
         * All insertions maintain the heap structure.
         * Order of equal elements is not guaranteed.
         */

        // 6. Inserting custom objects (Comparable)
        class Task implements Comparable<Task> {
            int priority;
            String name;
            Task(String name, int priority) {
                this.name = name;
                this.priority = priority;
            }
            public int compareTo(Task o) {
                return Integer.compare(this.priority, o.priority);
            }
            public String toString() {
                return name + "(" + priority + ")";
            }
        }
        PriorityQueue<Task> taskQueue = new PriorityQueue<>();
        taskQueue.offer(new Task("Email", 2));
        taskQueue.offer(new Task("Code", 1));
        taskQueue.offer(new Task("Debug", 3));
        System.out.println("6: Custom tasks = " + taskQueue);
        /*
         * Custom objects must implement Comparable or be provided with Comparator.
         * Heap uses compareTo() to determine ordering.
         * Very useful for task schedulers, job queues, or resource prioritization.
         * Objects are heapified based on the return value of compareTo().
         */

        // 7. Using custom Comparator for objects
        Comparator<Task> reversePriority = (a, b) -> b.priority - a.priority;
        PriorityQueue<Task> maxTaskQueue = new PriorityQueue<>(reversePriority);
        maxTaskQueue.addAll(taskQueue);
        System.out.println("7: Max-priority tasks = " + maxTaskQueue);
        /*
         * You can reverse priority using a custom comparator.
         * Helps customize ordering logic beyond natural sort.
         * PriorityQueue allows dynamic ordering at initialization.
         * Doesn't allow changing comparator once constructed.
         */

        // 8. Traversing the priority queue
        for (Task task : taskQueue) {
            System.out.println("8: Task = " + task);
        }
        /*
         * Iteration does not return elements in priority order.
         * Only poll() and peek() respect the heap ordering.
         * Internally, the heap is partially ordered — not fully sorted.
         * Don’t rely on iterator order to reflect queue priorities.
         */

        // 9. Removing specific element
        taskQueue.removeIf(t -> t.name.equals("Email"));
        System.out.println("9: After removing Email = " + taskQueue);
        /*
         * PriorityQueue supports removal via remove() and removeIf().
         * Removing an arbitrary element is O(n) since it must scan the array.
         * After removal, it reheapifies to maintain the structure.
         * Useful for dynamic job cancellation.
         */

        // 10. Creating from Collection
        List<Integer> list = Arrays.asList(9, 5, 2, 7);
        PriorityQueue<Integer> pqFromList = new PriorityQueue<>(list);
        System.out.println("10: From list = " + pqFromList);
        /*
         * Constructor with Collection builds a heap using the elements.
         * Internally uses heapify algorithm (bottom-up) in O(n) time.
         * Faster than inserting elements one-by-one.
         * Efficient for bulk processing tasks.
         */

        // 11. offer vs add
        PriorityQueue<Integer> tempQueue = new PriorityQueue<>();
        tempQueue.offer(1);
        tempQueue.add(2);
        System.out.println("11: offer/add queue = " + tempQueue);
        /*
         * offer() and add() are functionally identical in PriorityQueue.
         * Both insert the element and reheapify.
         * offer() is preferred as it returns false if insertion fails, instead of throwing exception.
         * Especially important in bounded queues like BlockingQueue.
         */

        // 12. Using remove() for polling
        System.out.println("12: remove = " + tempQueue.remove());
        /*
         * remove() removes and returns the head of the queue.
         * Throws NoSuchElementException if queue is empty.
         * Slightly stricter than poll(), which returns null instead.
         * Useful when empty queues indicate a bug.
         */

        // 13. clear the queue
        pqFromList.clear();
        System.out.println("13: after clear = " + pqFromList);
        /*
         * clear() removes all elements.
         * Doesn’t affect capacity or internal structure.
         * Useful for resetting state without reallocating.
         * Efficient and safe under single-threaded context.
         */

        // 14. size and isEmpty
        System.out.println("14a: size = " + pq.size());
        System.out.println("14b: isEmpty = " + pq.isEmpty());
        /*
         * size() gives the current number of elements.
         * isEmpty() checks for absence of elements.
         * Both operate in O(1) time.
         * Good for termination checks or bounded processing.
         */

        // 15. contains check
        System.out.println("15: pq contains 3? = " + pq.contains(3));
        /*
         * contains checks if an element is present.
         * Requires scanning the heap array (O(n) time).
         * Not optimized for quick lookup like a HashSet.
         * Used primarily for validation or filtering.
         */

        // 16. Convert to array
        Object[] array = pq.toArray();
        System.out.println("16: toArray = " + Arrays.toString(array));
        /*
         * toArray() returns a copy of elements as array.
         * Array is not sorted — just reflects internal heap array.
         * Safe to use for inspection and iteration.
         * Does not affect the actual queue.
         */

        // 17. Bulk insertion from another queue
        PriorityQueue<Integer> bulkQueue = new PriorityQueue<>();
        bulkQueue.addAll(pq);
        System.out.println("17: Bulk added = " + bulkQueue);
        /*
         * addAll inserts all elements from another collection.
         * Internally each element is heapified on insertion.
         * Can result in higher time complexity if not batched.
         * Prefer constructor(Collection) for initialization.
         */

        // 18. Sorting elements by polling
        List<Integer> sorted = new ArrayList<>();
        while (!bulkQueue.isEmpty()) sorted.add(bulkQueue.poll());
        System.out.println("18: Sorted output = " + sorted);
        /*
         * Polling repeatedly from a PriorityQueue returns sorted order.
         * Min-heap yields ascending, max-heap yields descending.
         * This is known as heap sort behavior.
         * Efficient when sort + pop is needed together.
         */

        // 19. PriorityQueue with Strings
        PriorityQueue<String> stringPQ = new PriorityQueue<>();
        stringPQ.add("zebra"); stringPQ.add("apple"); stringPQ.add("mango");
        System.out.println("19: String PQ = " + stringPQ);
        /*
         * String natural ordering is alphabetical.
         * Useful in dictionary, name lookup, lexicographic problems.
         * Case-sensitive and uses compareTo internally.
         * Great for auto-complete engines and word games.
         */

        // 20. Custom ordering: String length
        PriorityQueue<String> lenPQ = new PriorityQueue<>(Comparator.comparingInt(String::length));
        lenPQ.add("banana"); lenPQ.add("apple"); lenPQ.add("kiwi");
        System.out.println("20: Length based PQ = " + lenPQ);
        /*
         * Custom comparator enables string-based rules (like length).
         * Powerful for custom sort needs: file sizes, response time, etc.
         * Always define comparator before adding elements.
         * Cannot change comparator later.
         */

        // 21. PriorityQueue with nulls throws exception
        try {
            pq.offer(null);
        } catch (NullPointerException e) {
            System.out.println("21: Null not allowed in PQ");
        }
        /*
         * PriorityQueue does NOT allow null elements.
         * Nulls break the comparison mechanism of heap.
         * Throws NullPointerException if attempted.
         * Always validate inputs when using external data sources.
         */

        // 22. Using initial capacity
        PriorityQueue<Integer> capQueue = new PriorityQueue<>(100);
        capQueue.addAll(Arrays.asList(3, 2, 1));
        System.out.println("22: With initial capacity = " + capQueue);
        /*
         * Initial capacity affects underlying array size.
         * Doesn’t limit the max size — auto grows.
         * Avoids early resizing if size is known ahead.
         * Useful for large queues or batch processing.
         */

        // 23. Heapifying large collection
        List<Integer> large = new ArrayList<>();
        for (int i = 1000; i > 0; i--) large.add(i);
        PriorityQueue<Integer> heapLarge = new PriorityQueue<>(large);
        System.out.println("23: Peek after heapify = " + heapLarge.peek());
        /*
         * Heapify builds heap from n elements in O(n) time.
         * Faster than n insertions (O(n log n)).
         * Uses Floyd’s algorithm (bottom-up heapify).
         * Efficient for building from pre-existing data.
         */

        // 24. Mixing types not allowed
        try {
            PriorityQueue raw = new PriorityQueue();
            raw.add(10);
            raw.add("Hello");
        } catch (ClassCastException e) {
            System.out.println("24: Mixing types throws ClassCastException");
        }
        /*
         * Mixed-type elements require common Comparator or Comparable.
         * Without it, JVM throws ClassCastException.
         * Always use generics to prevent this at compile time.
         * Helps maintain type safety and debugging ease.
         */

        // 25. Peek on empty queue
        PriorityQueue<Double> emptyPQ = new PriorityQueue<>();
        System.out.println("25: Peek on empty = " + emptyPQ.peek());
        /*
         * peek() returns null if queue is empty.
         * Safer than remove() which throws exception.
         * Use as readiness check before polling.
         * Avoids unnecessary crashes in workflows.
         */

        // 26. Poll on empty queue
        System.out.println("26: Poll on empty = " + emptyPQ.poll());
        /*
         * poll() also returns null if queue is empty.
         * Non-throwing variant of remove().
         * Ideal for safely draining queues.
         * Useful when queue might be empty frequently.
         */

        // 27. Re-prioritizing elements
        PriorityQueue<Integer> updatePQ = new PriorityQueue<>(Arrays.asList(5, 3, 7));
        updatePQ.remove(3);
        updatePQ.offer(1);  // lower priority inserted
        System.out.println("27: After re-prioritize = " + updatePQ);
        /*
         * PriorityQueue does not auto-reorder on value update.
         * Need to remove and reinsert to reflect new priority.
         * Simulates dynamic adjustment in priority.
         * No direct priority update method is available.
         */

        // 28. PriorityQueue with negative values
        PriorityQueue<Integer> negPQ = new PriorityQueue<>();
        negPQ.addAll(Arrays.asList(-5, -1, -10));
        System.out.println("28: Negative values = " + negPQ);
        /*
         * Negative values are treated just like positive ones.
         * Heap maintains relative ordering regardless of sign.
         * Useful for problems involving penalties, debts, or reverse gains.
         * Comparisons are consistent and predictable.
         */

        // 29. Using PriorityQueue for Top K
        PriorityQueue<Integer> topK = new PriorityQueue<>();
        int[] nums = {5, 1, 10, 3, 7};
        for (int n : nums) {
            topK.offer(n);
            if (topK.size() > 3) topK.poll();
        }
        System.out.println("29: Top 3 = " + topK);
        /*
         * Classic use-case: keeping top K elements in streaming data.
         * Keep heap size fixed; evict smallest when size exceeds.
         * Efficient O(n log k) solution for real-time ranking.
         * Common in ML, analytics, leaderboard systems.
         */

        // 30. Summary of PriorityQueue
        System.out.println("30: PriorityQueue is ideal for partial ordering with efficient insertion and removal");
        /*
         * PriorityQueue is a flexible, efficient data structure.
         * Offers O(log n) insertion/removal, O(1) peek.
         * Based on binary heap with comparator/Comparable ordering.
         * Used widely in scheduling, graphs, simulations, ranking, etc.
         */
    }
}

