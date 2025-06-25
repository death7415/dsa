package Java8.entityAndDao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@ToString
class UserData{
    private String _id;
    private String index;
    private String guid;
    private String isActive;
    private String balance;
    private int age;
    private String eyeColor;
    private String name;
    private String gender;
    private String company;
    private String email;
    private String phone;
    private String address;
    private String registered;
    private double latitude;
    private double longitude;
    private List<String> tags;
}
class RunAsyncDemo{

    public static void mapJsoFile(File file) throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(10);
        var voidCompletableFuture = CompletableFuture.runAsync(()-> {
            try {
                List<UserData> response = mapper.readValue(file, new TypeReference<>() {});
                System.out.println(Thread.currentThread().getName());
                System.out.println(response.size());
            }catch (Exception e){
                System.out.println(e.getMessage() + " " + e);
            }
        }, executor);
        voidCompletableFuture.get();
    }
}

class SupplyAsyncDemo{
    public static List<UserData> mapJsoFile() throws ExecutionException, InterruptedException {
        ObjectMapper mapper = new ObjectMapper();
        Executor executor = Executors.newFixedThreadPool(10);
        var supplyAsync = CompletableFuture.supplyAsync(()-> {
            try {
                return mapper.<List<UserData>>readValue(new File("user.json"), new TypeReference<>() {});
            }catch (Exception e){
                System.out.println(e.getMessage() + " " + e);
                return null;
            }
        }, executor);
        return supplyAsync.get();
    }

    public static CompletableFuture<Void> sendEmailNotification() throws ExecutionException, InterruptedException {
        Executor executor = Executors.newFixedThreadPool(5);
        return CompletableFuture
                .supplyAsync(NewEmployeeDAO::getAllNewEmployees, executor)
                .thenApplyAsync(newEmployees -> {
                    System.out.println("Thread used: "+ Thread.currentThread().getName());
                    return newEmployees.stream().filter(NewEmployee::isTrainingPending).toList();
                }, executor)
                .thenApplyAsync(trainingPendingEmployees -> {
                    System.out.println("Thread used: "+ Thread.currentThread().getName());
                    return trainingPendingEmployees.stream().collect(Collectors.toMap(NewEmployee::getEmail, emp ->
                        emp.getTrainingProgress()
                                .entrySet()
                                .stream()
                                .filter(entry -> "Not Started".equalsIgnoreCase(entry.getValue().get("status")))
                                .map(Map.Entry::getKey)
                                .toList()
                    )).entrySet()
                            .stream()
                            .filter(entry -> !entry.getValue().isEmpty())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
                }, executor)
                .thenAcceptAsync(mapOfEmailAndModulesAsNotStarted ->{
                    System.out.println("Thread used: "+ Thread.currentThread().getName());
                    mapOfEmailAndModulesAsNotStarted.forEach((key, val) -> {
                            System.out.println("email sent to " + key + " for pending courses " + val);
                    });
                    System.out.println("Size = " + mapOfEmailAndModulesAsNotStarted.size());
                }, executor);
    }
}

class PrintEvenOddUsing2Threads{

    public static void printEvenAndOdd(){
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        IntStream.rangeClosed(1, 10)
                .forEach( num -> {
            CompletableFuture<Integer> oddCompletableFuture = CompletableFuture.completedFuture(num);
            oddCompletableFuture.thenApplyAsync(x -> {
                if (x % 2 != 0){
                    System.out.println("Thread used " + Thread.currentThread().getName() + " for print odd value " + x);
                }
                return num;
            }, executorService);
            oddCompletableFuture.join();

            CompletableFuture<Integer> evenCompletableFuture = CompletableFuture.completedFuture(num);
            evenCompletableFuture.thenApplyAsync(x -> {
                if (x % 2 == 0){
                    System.out.println("Thread used " + Thread.currentThread().getName() + " for print odd value " + x);
                }
                return num;
            }, executorService);
            evenCompletableFuture.join();
        });
        executorService.shutdown();
    }

}

class PrintEvenOddUsing2ThreadsCF {

    private final Object lock = new Object();
    private int counter = 1;
    private final int MAX = 10; // upper limit

    public static void work() {
        PrintEvenOddUsing2ThreadsCF printer = new PrintEvenOddUsing2ThreadsCF();

        // Create an ExecutorService with two threads
        ExecutorService executor = Executors.newFixedThreadPool(2);

        // Create two CompletableFutures that run asynchronously using our executor
        CompletableFuture<Void> oddFuture = CompletableFuture.runAsync(printer::printOdd, executor);
        CompletableFuture<Void> evenFuture = CompletableFuture.runAsync(printer::printEven, executor);

        // Wait until both tasks are complete
        CompletableFuture.allOf(oddFuture, evenFuture).join();

        // Shut down executor
        executor.shutdown();
        System.out.println("Done printing up to " + printer.MAX);
    }

    // Thread that prints odd numbers
    private void printOdd() {
        while (counter <= MAX) {
            synchronized (lock) {
                // If the counter is even, wait until notified
                while (counter <= MAX && counter % 2 == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // Print odd
                if (counter <= MAX) {
                    System.out.println(Thread.currentThread().getName() + " (Odd)  -> " + counter);
                    counter++;
                    // Notify the other thread
                    lock.notify();
                }
            }
        }
    }

    // Thread that prints even numbers
    private void printEven() {
        while (counter <= MAX) {
            synchronized (lock) {
                // If the counter is odd, wait until notified
                while (counter <= MAX && counter % 2 == 1) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                        return;
                    }
                }
                // Print even
                if (counter <= MAX) {
                    System.out.println(Thread.currentThread().getName() + " (Even) -> " + counter);
                    counter++;
                    lock.notify();
                }
            }
        }
    }
}

class CompletableFutureExtensionDemo{
    private static final List<NewEmployee> newEmployees = NewEmployeeDAO.getAllNewEmployees();

    public static CompletableFuture<NewEmployee> getSpecificEmployeeById(){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("Thread used for getSpecificEmployeeById" + Thread.currentThread().getName());
           return newEmployees.stream()
                    .filter(newEmployee -> "E244".equalsIgnoreCase(newEmployee.getEmployeeId()))
                    .findFirst()
                    .orElse(null);
        });
    }

    public static CompletableFuture<String> getEmail(NewEmployee newEmployee){
        return CompletableFuture.supplyAsync(()->{
            System.out.println("Thread used for getEmail" + Thread.currentThread().getName());
            return newEmployee.getEmail();
        });
    }
}

class ProducerAndConsumer{
    private static int number = 0;
    private static final Object lock = new Object();
    private static final int MAX = 10;

    public static void interCommunicationOfThread(){

        Thread producer = new Thread(()-> {
            for (int i =1; i<= MAX; i++){
                synchronized (lock){
                    number = i;
                    try {
                        System.out.println(Thread.currentThread().getName()+" produced number : "+ number);
                        lock.notify();
                        lock.wait();
                    }catch (InterruptedException e){
                        System.out.println("Thread interrupted " + Thread.currentThread().getName());
                    }
                }
            }
        }, "Producer thread");

        Thread consumer = new Thread(()-> {
            for (int i =1; i<= MAX; i++){
                synchronized (lock){
                    number = i;
                    try {
                        System.out.println(Thread.currentThread().getName()+" consumed number : "+ number);
                        lock.notify();
                        lock.wait();
                    }catch (InterruptedException e){
                        System.out.println("Thread interrupted " + Thread.currentThread().getName());
                    }
                }
            }
        }, "Consumer thread");

        producer.start();
        consumer.start();
    }
}

class MichaelUdemyMultithreadding extends Thread{
    @Override
    public void run() {
        System.out.println("some operation by thread = " + this.getName());
    }
}


public class MultithreaddingAndAsyncProgramming {
    public static void main(String[] args) throws Exception{
        Thread multithreadding = new MichaelUdemyMultithreadding();
        multithreadding.setName("Worker-1");
        multithreadding.start();
    }
}
