package javaInterview.queues;

// Java ConcurrentLinkedQueue Deep Dive - 30 Detailed Examples

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class ConcurrentLinkedQueueExamples {
    public static void main(String[] args) {

        // 1. Creating and adding elements
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.add("Apple");
        queue.add("Banana");
        queue.add("Cherry");
        System.out.println("1: Initial queue = " + queue);
        /*
         * ConcurrentLinkedQueue is a lock-free, thread-safe queue based on linked nodes.
         * It follows FIFO (First-In-First-Out) ordering.
         * Internally uses Compare-And-Swap (CAS) for atomic operations.
         * Suitable for high-concurrency environments with multiple producers and consumers.
         */

        // 2. Offering elements (non-blocking)
        queue.offer("Date");
        System.out.println("2: After offer = " + queue);
        /*
         * offer() adds element at the tail of the queue.
         * Always returns true in ConcurrentLinkedQueue as it’s unbounded.
         * Lock-free implementation avoids contention and deadlocks.
         * Efficient for enqueue operations in concurrent scenarios.
         */

        // 3. Polling elements (removal from head)
        String removed = queue.poll();
        System.out.println("3: Polled = " + removed);
        System.out.println("3: After poll = " + queue);
        /*
         * poll() removes and returns the head element.
         * Returns null if the queue is empty.
         * Thread-safe and non-blocking.
         * Internally updates head pointer using atomic operations.
         */

        // 4. Peeking at the head
        System.out.println("4: Peek = " + queue.peek());
        /*
         * peek() retrieves the head without removing it.
         * Returns null if queue is empty.
         * Useful for checking availability before consuming.
         * Ensures visibility of updates across threads.
         */

        // 5. Checking if queue is empty
        System.out.println("5: isEmpty = " + queue.isEmpty());
        /*
         * isEmpty() checks if queue has no elements.
         * O(1) operation; evaluates head and tail pointers.
         * Useful for guarding against unnecessary processing.
         * Thread-safe and reliable across threads.
         */

        // 6. Size operation (not constant time)
        System.out.println("6: size = " + queue.size());
        /*
         * size() traverses the queue to count elements.
         * It’s not a constant-time operation and may not be accurate under heavy concurrency.
         * Use cautiously in performance-critical or multi-threaded logic.
         * Often replaced by custom counters or atomic variables.
         */

        // 7. Add null (not allowed)
        try {
            queue.add(null);
        } catch (NullPointerException e) {
            System.out.println("7: Cannot add null to ConcurrentLinkedQueue");
        }
        /*
         * null elements are not allowed in ConcurrentLinkedQueue.
         * This avoids ambiguity with poll() and peek() returning null.
         * Throws NullPointerException if attempted.
         * Helps maintain clarity and consistency in concurrent logic.
         */

        // 8. Iterating using for-each
        for (String item : queue) {
            System.out.println("8: Item = " + item);
        }
        /*
         * Iterator is weakly consistent — reflects some state during iteration.
         * Doesn’t throw ConcurrentModificationException.
         * Allows concurrent insertions/removals while iterating.
         * Best-effort view suitable for monitoring or snapshot-style reads.
         */

        // 9. Contains check
        System.out.println("9: Contains 'Banana'? = " + queue.contains("Banana"));
        /*
         * contains() scans the queue to find the element.
         * Not optimized for fast lookup like a Set.
         * Thread-safe even during modifications.
         * Common for validation and conditional processing.
         */

        // 10. Removing specific element
        queue.remove("Banana");
        System.out.println("10: After remove = " + queue);
        /*
         * remove(Object o) removes first occurrence of the element.
         * Returns true if removal succeeded.
         * May traverse entire list in worst case.
         * Thread-safe for single element removal.
         */

        // 11. Clearing the queue
        queue.clear();
        System.out.println("11: After clear = " + queue);
        /*
         * clear() removes all elements from the queue.
         * Not an atomic operation — concurrent threads may still access.
         * Safe for most non-transactional operations.
         * Consider using with care in highly concurrent environments.
         */

        // 12. Using multiple threads to add elements
        ConcurrentLinkedQueue<Integer> intQueue = new ConcurrentLinkedQueue<>();
        Runnable producer = () -> {
            for (int i = 0; i < 100; i++) intQueue.offer(i);
        };
        Thread t1 = new Thread(producer);
        Thread t2 = new Thread(producer);
        t1.start(); t2.start();
        try { t1.join(); t2.join(); } catch (Exception ignored) {}
        System.out.println("12: Size after multi-threaded add = " + intQueue.size());
        /*
         * ConcurrentLinkedQueue performs well under multi-threaded usage.
         * Threads add elements concurrently without locking.
         * Internally uses CAS operations on tail node.
         * Excellent for producer-consumer and queue-based pipelines.
         */

        // 13. Polling until empty
        int polledCount = 0;
        while (intQueue.poll() != null) polledCount++;
        System.out.println("13: Elements polled = " + polledCount);
        /*
         * Polling until null means we've emptied the queue.
         * A standard pattern for consumer threads.
         * Helps drain tasks or messages in worker threads.
         * Safe in concurrent environments.
         */

        // 14. Peek after empty
        System.out.println("14: Peek on empty = " + intQueue.peek());
        /*
         * Peek returns null when queue is empty.
         * Doesn't throw an exception like remove().
         * Useful for safe inspection.
         * Indicates readiness without consuming elements.
         */

        // 15. Remove on empty queue
        boolean removedEmpty = intQueue.remove(10);
        System.out.println("15: Remove on empty = " + removedEmpty);
        /*
         * Removing from an empty queue returns false.
         * Doesn’t throw an exception for non-existent elements.
         * Avoids try-catch patterns for removals.
         * Preferred in robust consumer designs.
         */

        // 16. Insert and read concurrently
        ConcurrentLinkedQueue<Integer> sharedQueue = new ConcurrentLinkedQueue<>();
        Runnable writer = () -> {
            for (int i = 0; i < 50; i++) sharedQueue.offer(i);
        };
        Runnable reader = () -> {
            for (int i = 0; i < 50; i++) sharedQueue.peek();
        };
        Thread writerThread = new Thread(writer);
        Thread readerThread = new Thread(reader);
        writerThread.start(); readerThread.start();
        try { writerThread.join(); readerThread.join(); } catch (Exception ignored) {}
        System.out.println("16: Concurrent write/read size = " + sharedQueue.size());
        /*
         * Read/write operations are safe concurrently.
         * No need for external synchronization.
         * Ideal for lock-free concurrent pipelines.
         * Internal consistency is maintained using atomic variables.
         */

        // 17. Retain custom order in FIFO
        ConcurrentLinkedQueue<String> fifoQueue = new ConcurrentLinkedQueue<>();
        fifoQueue.add("1"); fifoQueue.add("2"); fifoQueue.add("3");
        System.out.println("17: FIFO retained = " + fifoQueue);
        /*
         * Elements retain insertion order due to linked structure.
         * FIFO (first-in-first-out) behavior is guaranteed.
         * Essential for task sequencing, logs, queues.
         * Order integrity holds under multi-threaded insertions.
         */

        // 18. Add duplicate elements
        fifoQueue.add("2"); fifoQueue.add("3");
        System.out.println("18: With duplicates = " + fifoQueue);
        /*
         * Duplicates are allowed in ConcurrentLinkedQueue.
         * No hashing — elements are compared using equals.
         * Use Sets if uniqueness is required.
         * Good for message queues, logs, tasks.
         */

        // 19. Use with custom objects
        class Job {
            String name;
            Job(String name) { this.name = name; }
            public String toString() { return name; }
        }
        ConcurrentLinkedQueue<Job> jobQueue = new ConcurrentLinkedQueue<>();
        jobQueue.add(new Job("Print"));
        jobQueue.add(new Job("Scan"));
        System.out.println("19: Custom object queue = " + jobQueue);
        /*
         * Any object type can be stored — including user-defined types.
         * Queue behavior depends on equals and toString implementation.
         * Useful for work queues, job dispatchers, task runners.
         * Elements processed in object order.
         */

        // 20. Use in polling-based scheduler
        while (!jobQueue.isEmpty()) {
            Job j = jobQueue.poll();
            System.out.println("20: Processing job: " + j);
        }
        /*
         * Classic use-case in job scheduling systems.
         * Consumers poll jobs and process.
         * Efficient in thread pools or real-time processing.
         * Lock-free behavior helps avoid bottlenecks.
         */

        // 21. Using queue for pipeline communication
        ConcurrentLinkedQueue<String> messages = new ConcurrentLinkedQueue<>();
        messages.add("INIT"); messages.add("LOAD"); messages.add("FINISH");
        while (!messages.isEmpty()) {
            String msg = messages.poll();
            System.out.println("21: Pipeline step = " + msg);
        }
        /*
         * ConcurrentLinkedQueue is great for decoupled producer-consumer workflows.
         * Each stage polls messages and processes independently.
         * Used widely in reactive systems and stream processors.
         * Encourages loose coupling between system components.
         */

        // 22. Performance under load
        ConcurrentLinkedQueue<Integer> perfQ = new ConcurrentLinkedQueue<>();
        long start = System.nanoTime();
        for (int i = 0; i < 1000000; i++) perfQ.offer(i);
        long end = System.nanoTime();
        System.out.println("22: Time to insert 1M items = " + (end - start) / 1_000_000 + " ms");
        /*
         * Insertions are fast due to lock-free implementation.
         * CAS operations scale better under many threads.
         * Good choice for high-frequency message passing.
         * Lower contention compared to synchronized structures.
         */

        // 23. Memory footprint check
        System.out.println("23: Final queue size = " + perfQ.size());
        /*
         * Memory grows linearly with item count.
         * Internally uses linked nodes with head/tail references.
         * Monitor for memory leaks in long-lived queues.
         * Queue size helps indicate load trends.
         */

        // 24. Does not block on empty
        ConcurrentLinkedQueue<String> testQ = new ConcurrentLinkedQueue<>();
        String result = testQ.poll();
        System.out.println("24: Non-blocking poll result = " + result);
        /*
         * poll() and peek() return null if empty.
         * No thread blocking occurs.
         * Must be paired with polling loops or wait-notify in real systems.
         * Ensures responsiveness in non-blocking architectures.
         */

        // 25. Cannot limit capacity
        ConcurrentLinkedQueue<Integer> unbounded = new ConcurrentLinkedQueue<>();
        for (int i = 0; i < 5; i++) unbounded.offer(i);
        System.out.println("25: Size = " + unbounded.size());
        /*
         * There is no upper bound — grows until memory runs out.
         * Cannot use for bounded queues or resource-throttled systems.
         * Prefer LinkedBlockingQueue if size control is needed.
         * Simpler model with predictable throughput.
         */

        // 26. Iterator remains valid after concurrent modifications
        Iterator<Integer> it = unbounded.iterator();
        unbounded.offer(99);
        while (it.hasNext()) {
            System.out.print(it.next() + " ");
        }
        System.out.println();
        System.out.println(" 26: Iterator did not fail on concurrent change");
        /*
         * Weakly consistent iterators tolerate concurrent modifications.
         * Does not throw ConcurrentModificationException.
         * May or may not reflect newly added elements.
         * Ideal for non-critical iteration like monitoring or metrics.
         */

        // 27. Queue used for buffer
        ConcurrentLinkedQueue<Byte> buffer = new ConcurrentLinkedQueue<>();
        buffer.add((byte) 1); buffer.add((byte) 2); buffer.add((byte) 3);
        System.out.println("27: Buffered stream = " + buffer);
        /*
         * Suitable for building lightweight non-blocking buffers.
         * Byte queues for socket data, file chunks, etc.
         * Allows asynchronous reads/writes.
         * Reduces thread contention in I/O operations.
         */

        // 28. Handling large data types
        ConcurrentLinkedQueue<StringBuilder> builderQueue = new ConcurrentLinkedQueue<>();
        builderQueue.add(new StringBuilder("log1"));
        builderQueue.add(new StringBuilder("log2"));
        System.out.println("28: Large object = " + builderQueue);
        /*
         * Works with mutable, heavy objects as well.
         * Use with care to avoid aliasing bugs.
         * Common in ETL pipelines or batch file handling.
         * Memory usage should be monitored under pressure.
         */

        // 29. Custom class for logging events
        class LogEvent {
            String msg;
            LogEvent(String msg) { this.msg = msg; }
            public String toString() { return "[LOG: " + msg + "]"; }
        }
        ConcurrentLinkedQueue<LogEvent> logQ = new ConcurrentLinkedQueue<>();
        logQ.add(new LogEvent("User login"));
        logQ.add(new LogEvent("File upload"));
        System.out.println("29: Log Events = " + logQ);
        /*
         * Log and telemetry systems can use queues for event batching.
         * Separate ingestion from processing stages.
         * Non-blocking behavior avoids slowdowns during bursts.
         * Pattern scales well for backend processing.
         */

        // 30. Summary of ConcurrentLinkedQueue
        System.out.println("30: Completed 30 detailed examples of ConcurrentLinkedQueue");
        /*
         * ConcurrentLinkedQueue is a performant, lock-free, thread-safe structure.
         * Ideal for high-throughput, multi-threaded systems.
         * Weak consistency trades strict guarantees for speed.
         * Use it in message passing, work queues, and streaming architectures.
         */

    }
}

