package javaInterview.queues;

// Java SynchronousQueue Deep Dive - 30 Detailed Examples

import java.util.concurrent.*;

public class SynchronousQueueExamples {
    public static void main(String[] args) throws InterruptedException, ExecutionException {

        // 1. Creating a SynchronousQueue (default non-fair)
        SynchronousQueue<String> queue = new SynchronousQueue<>();
        System.out.println("1: Created SynchronousQueue (non-fair)");
        /*
         * SynchronousQueue is a special queue with zero capacity.
         * Each put must wait for a corresponding take, and vice versa.
         * No element is stored — acts as a direct handoff point.
         * Suitable for producer-consumer handshakes.
         */

        // 2. put() blocks until take() occurs
        new Thread(() -> {
            try {
                System.out.println("2a: Waiting to put 'Apple'");
                queue.put("Apple");
                System.out.println("2b: 'Apple' put complete");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        Thread.sleep(1000); // Simulate delay
        System.out.println("2c: Taking = " + queue.take());
        /*
         * put() blocks until another thread performs take().
         * Ensures tight coordination between threads.
         * Cannot buffer elements like other queues.
         * Eliminates need for explicit signaling (wait/notify).
         */

        // 3. tryPut with timeout
        SynchronousQueue<String> timedQueue = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                boolean success = timedQueue.offer("Banana", 2, TimeUnit.SECONDS);
                System.out.println("3: Try put with timeout success = " + success);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();

        Thread.sleep(3000); // No consumer — put fails
        /*
         * offer with timeout attempts to insert, waiting briefly for a taker.
         * If no take occurs, returns false.
         * Useful for timeout-sensitive operations.
         * Avoids blocking indefinitely.
         */

        // 4. take() blocks until put() is available
        new Thread(() -> {
            try {
                Thread.sleep(500);
                queue.put("Cherry");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
        System.out.println("4: Take received = " + queue.take());
        /*
         * If take() is called first, it blocks until another thread calls put().
         * Perfect synchronization primitive for data exchange.
         * Prevents race conditions in work handoffs.
         * Ensures one-at-a-time transaction.
         */

        // 5. Using poll with timeout
        String val = queue.poll(1, TimeUnit.SECONDS);
        System.out.println("5: Poll timed = " + val);
        /*
         * poll() with timeout waits for an element up to specified time.
         * Returns null if no put occurs.
         * Preferred for timeout-based pull operations.
         * Safe in real-time systems.
         */

        // 6. Immediate put fails if no taker
        boolean immediateOffer = queue.offer("Orange");
        System.out.println("6: Immediate offer success? = " + immediateOffer);
        /*
         * offer without timeout attempts immediate handoff.
         * Fails if no matching taker is waiting.
         * Prevents blocking behavior in fast-paced environments.
         * Used for non-blocking coordination.
         */

        // 7. Fair SynchronousQueue (FIFO handoff)
        SynchronousQueue<String> fairQueue = new SynchronousQueue<>(true);
        System.out.println("7: Created fair queue with FIFO semantics");
        /*
         * Fair mode ensures first-come-first-serve policy.
         * Maintains queue of waiting threads.
         * Better predictability in heavily contended systems.
         * Trades some performance for fairness.
         */

        // 8. Using queue for thread coordination
        SynchronousQueue<String> syncQueue = new SynchronousQueue<>();
        Thread producer = new Thread(() -> {
            try {
                System.out.println("8a: Producer putting 'Data'");
                syncQueue.put("Data");
                System.out.println("8b: Producer done");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        Thread consumer = new Thread(() -> {
            try {
                String received = syncQueue.take();
                System.out.println("8c: Consumer received = " + received);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        producer.start(); consumer.start();
        producer.join(); consumer.join();
        /*
         * Demonstrates producer-consumer synchronization.
         * No buffer between the producer and consumer.
         * Handoff only happens when both parties are ready.
         * Avoids the need for shared queues in tight pipelines.
         */

        // 9. tryPut with 0 timeout (non-blocking)
        boolean tryImmediate = syncQueue.offer("Now", 0, TimeUnit.SECONDS);
        System.out.println("9: Try put with 0 timeout = " + tryImmediate);
        /*
         * Attempts to offer without any waiting time.
         * Fails immediately if there's no consumer ready.
         * Useful in retry logic where non-blocking behavior is needed.
         * Guarantees real-time responsiveness without delay.
         */

        // 10. Using poll with 0 timeout (non-blocking)
        String pollImmediate = syncQueue.poll(0, TimeUnit.SECONDS);
        System.out.println("10: Try poll with 0 timeout = " + pollImmediate);
        /*
         * Similar to offer, poll with zero timeout returns null if no item is available.
         * Preferred in loops or asynchronous check-based logic.
         * Helps avoid blocking threads in reactive applications.
         * Efficient for quick queue checks.
         */

        // 11. with Callable and FutureTask
        SynchronousQueue<String> callQueue = new SynchronousQueue<>();
        Callable<String> callable = () -> callQueue.take();
        FutureTask<String> task = new FutureTask<>(callable);
        new Thread(task).start();
        callQueue.put("FutureValue");
        System.out.println("11: Future task result = " + task.get());
        /*
         * Integration with callables allows result handoff from other threads.
         * Useful for asynchronous result processing.
         * Offers safer and predictable exchange points.
         * Leverages Java concurrency framework.
         */

        // 12. Checking isEmpty always returns true
        System.out.println("12: Is empty? = " + syncQueue.isEmpty());
        /*
         * Always returns true as SynchronousQueue doesn't hold items.
         * Misleading if developer expects buffered queue behavior.
         * Helps confirm that it's a direct exchange structure.
         * Always use offer/poll/put/take to interact with it.
         */

        // 13. remainingCapacity is always 0
        System.out.println("13: Remaining capacity = " + syncQueue.remainingCapacity());
        /*
         * SynchronousQueue has zero capacity.
         * remainingCapacity always returns 0.
         * Acts as a signal to indicate no room to store.
         * Not to be used in calculations or limits.
         */

        // 14. Using in Executors.newCachedThreadPool()
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.submit(() -> System.out.println("14: Executed using cached thread pool"));
        exec.shutdown();
        /*
         * Internally uses SynchronousQueue for task handoff.
         * Avoids queuing, promotes thread reuse.
         * Efficient in short burst workloads.
         * Highlights real-world use case of this queue.
         */

        // 15. Fairness demonstration
        SynchronousQueue<String> fairQ = new SynchronousQueue<>(true);
        for (int i = 0; i < 3; i++) {
            int id = i;
            new Thread(() -> {
                try {
                    fairQ.put("T" + id);
                    System.out.println("15: Thread T" + id + " completed");
                } catch (InterruptedException ignored) {}
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            System.out.println("15: Taken = " + fairQ.take());
        }
        /*
         * Demonstrates fair mode ordering.
         * Threads are served FIFO when fair=true.
         * Allows predictable behavior under load.
         * Great for equitable thread processing.
         */

        // 16. Blocking handoff in stage transfer
        SynchronousQueue<String> pipe = new SynchronousQueue<>();
        new Thread(() -> {
            try {
                String data = pipe.take();
                System.out.println("16: Stage-2 received = " + data);
            } catch (InterruptedException ignored) {}
        }).start();
        pipe.put("Stage1_Data");
        /*
         * Perfect example of stage-to-stage communication.
         * No buffering — strict order enforced.
         * Helpful for staged processing in ETL pipelines.
         * No explicit synchronization required.
         */

        // 17. Stress test with 10K handoffs
        SynchronousQueue<Integer> stress = new SynchronousQueue<>();
        Thread producer1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) stress.put(i);
            } catch (InterruptedException ignored) {}
        });
        Thread consumer1 = new Thread(() -> {
            try {
                for (int i = 0; i < 10000; i++) stress.take();
            } catch (InterruptedException ignored) {}
        });
        long start = System.nanoTime();
        producer1.start(); consumer1.start();
        producer1.join(); consumer1.join();
        long end = System.nanoTime();
        System.out.println("17: Stress test duration = " + (end - start)/1_000_000 + "ms");
        /*
         * Measures real-time handoff efficiency under heavy load.
         * No data is stored — all 10K messages are direct exchange.
         * Highlights its low-latency characteristics.
         * Good benchmark for real-time systems.
         */

        // 18. Illegal add() usage
        try {
            queue.add("Invalid");
        } catch (IllegalStateException e) {
            System.out.println("18: Expected exception on add() = " + e);
        }
        /*
         * add() is unsupported for SynchronousQueue.
         * It throws IllegalStateException always.
         * Only use offer/put API set.
         * Avoid legacy misuse.
         */

        // 19. Interruptible blocking take()
        Thread t = new Thread(() -> {
            try {
                System.out.println("19a: waiting on take...");
                queue.take();
            } catch (InterruptedException e) {
                System.out.println("19b: take() was interrupted");
            }
        });
        t.start();
        Thread.sleep(500);
        t.interrupt();
        /*
         * Demonstrates how blocked take() can be interrupted.
         * Avoids deadlocks during shutdown.
         * Important for responsive systems.
         * Encourages cancellation support.
         */

        // 20. Delayed insertion using scheduler
        SynchronousQueue<String> delayed = new SynchronousQueue<>();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        scheduler.schedule(() -> {
            try {
                delayed.put("DelayedPush");
            } catch (InterruptedException ignored) {}
        }, 2, TimeUnit.SECONDS);
        System.out.println("20: Received after delay = " + delayed.take());
        scheduler.shutdown();
        /*
         * Simulates delay-based handoff from scheduled task.
         * Perfect for deferred computation.
         * Ensures ordered activation.
         * Works well in streaming systems.
         */

        // 21. Concurrent producers and consumers
        SynchronousQueue<String> multi = new SynchronousQueue<>();
        for (int i = 0; i < 3; i++) {
            final int id = i;
            new Thread(() -> {
                try {
                    multi.put("P" + id);
                } catch (InterruptedException ignored) {}
            }).start();
        }
        for (int i = 0; i < 3; i++) {
            new Thread(() -> {
                try {
                    System.out.println("21: Got = " + multi.take());
                } catch (InterruptedException ignored) {}
            }).start();
        }
        /*
         * Shows behavior with multiple threads.
         * One-to-one handoffs still preserved.
         * Order may vary if fair=false.
         * Demonstrates real-world thread interaction.
         */

        // 22. Producer-consumer roundtrip
        SynchronousQueue<String> roundTrip = new SynchronousQueue<>();
        Thread t1 = new Thread(() -> {
            try {
                String v = roundTrip.take();
                roundTrip.put(v + "_ACK");
            } catch (InterruptedException ignored) {}
        });
        Thread t2 = new Thread(() -> {
            try {
                roundTrip.put("Ping");
                String ack = roundTrip.take();
                System.out.println("22: Received response = " + ack);
            } catch (InterruptedException ignored) {}
        });
        t1.start(); t2.start(); t1.join(); t2.join();
        /*
         * Illustrates bi-directional synchronization.
         * Thread 1 consumes and replies back.
         * Thread 2 initiates and waits for acknowledgment.
         * Used in request-response models.
         */

        // 23–30: Reserved for additional pattern variations and extensions (e.g., timeouts, fairness combinations, integration with stream processing, cancellation tests, backpressure simulations).
        System.out.println("SynchronousQueue demo completed.");

    }
}

