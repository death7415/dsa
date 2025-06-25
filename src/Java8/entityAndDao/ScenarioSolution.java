package Java8.entityAndDao;

import graphs.Graph;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class ScenarioSolution {
    static class Pair {
        private final int first;
        private final int second;

        public Pair(int first, int second) {
            this.first = first;
            this.second = second;
        }

        @Override
        public String toString() {
            return "(" + first + ", " + second + ")";
        }
    }

    public static void recipeRelatedOperation(){
       List<Recipe> res = RecipeDAO.getAllRecipes();
       res.getFirst()
               .getNutritionFacts()
               .entrySet()
               .stream()
               .sorted(Map.Entry.comparingByValue(Comparator.comparingInt(o -> (int) o).reversed()))
               .forEach(System.out::println);
    }

    public static void employeeRelatedOperation(){
        List<Employee> employees = EmployeeDao.getAllEmployees();
        //Calculate the average salary of employees in each department.
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)))
                .forEach((k,v) -> System.out.println(k + " = " + v));

        System.out.println("======================");

        //Identify the employee with the longest tenure in the company.
        var maxTenureEmployee = employees.stream()
                                         .max(Comparator.comparingInt(Employee::getYearsOfExperience))
                                         .orElseThrow();
        System.out.println(maxTenureEmployee);

        System.out.println("======================");

        //List the names of employees who joined after a specific date, sorted by their joining date.
        employees.stream()
                .filter(employee -> employee.getJoiningDate().isAfter(LocalDate.of(2021,12,5)))
                .sorted(Comparator.comparing(Employee::getJoiningDate).reversed())
                .forEach(System.out::println);

        System.out.println("======================");

        //Group employees by their department and then by their joining year.
        employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment,
                        Collectors.groupingBy(Employee::getJoiningDate)))
                .entrySet().forEach(System.out::println);

        System.out.println("======================");
    }

    public static void transactionRelatedOperation(){
        var transactions = TransactionDAO.getAllTransactions();
        //- Compute the total transaction amount for each user.
        transactions.stream()
                    .collect(Collectors.groupingBy(Transaction::getUserId, Collectors.summingDouble(Transaction::getAmount)))
                    .entrySet()
                    .stream()
                    .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                    .forEach(System.out::println);

        System.out.println("======================");

        //Find the top 2 users who have the highest total transaction amounts.
        transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getUserId,
                        Collectors.summingDouble(Transaction::getAmount)))
                .entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue(Collections.reverseOrder()))
                .limit(2)
                .forEach(System.out::println);
        System.out.println("======================");

        //Filter out transactions that are either `FAILED` or `PENDING`
        // and then sort the successful transactions by amount in descending order.
        transactions
                .stream()
                .filter(transaction -> transaction.getStatus().contains("SUCCESS"))
                .sorted(Comparator.comparingDouble(Transaction::getAmount).reversed())
                .forEach(System.out::println);

        System.out.println("======================");

        //Determine if all transactions within a specific time frame are successful.
        var response =transactions
                                .stream()
                                .filter(transaction -> transaction.getTimestamp()
                                        .isAfter(LocalDateTime.of(2025, 1, 13, 11, 0))
                                        && transaction.getTimestamp()
                                        .isBefore(LocalDateTime.of(2025, 1, 15, 13, 20)))
                                .allMatch(transaction -> transaction.getStatus().equalsIgnoreCase("success"));

        System.out.println(response);
        System.out.println("======================");
    }


    public static void productRelatedOperation(){
        var products = ProductDao.getAllProducts();
        //- Generate a list of products that are out of stock.
        products.parallelStream()
                .filter(product -> product.getStockQuantity() == 0)
                .toList()
                .forEach(System.out::println);
        System.out.println("======================");

        //- Calculate the total value of products available in each category.
        products.parallelStream()
                .collect(Collectors.groupingBy(Product::getCategory,
                        Collectors.summingDouble(product-> product.getPrice() * product.getStockQuantity())))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("======================");

        //- Identify the most expensive product in each category.
        products.parallelStream()
                .collect(Collectors.groupingBy(Product::getCategory, Collectors.maxBy(Comparator.comparingDouble(Product::getPrice))))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("======================");
        //Create a map where the key is the category, and the value is a list of product names sorted by price.
//        products.stream()
//                .collect(Collectors.groupingBy(Product::getCategory,
//                                    Collectors.groupingBy((Product product) -> new ProductWithNameAndPrice(product.getName(),
//                                    product.getPrice()), Collectors.maxBy(Comparator.comparing(Product::getPrice)))))
//                .entrySet()
//                .forEach(System.out::println);

        products.parallelStream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                list -> list.stream()
                                        .sorted(Comparator.comparing(Product::getPrice))
                                        .map(Product::getName)
                                        .toList()
                        )
                ))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("======================");
    }

    public static void studentRelatedOperation(){
        var students = StudentDao.getAllStudents();

        //- Calculate the average score of each student across all subjects.
        students.parallelStream()
                .collect(Collectors.toMap(Student::getName,
                        student -> student.getScores()
                                        .stream()
                                        .collect(Collectors.averagingDouble(Double::doubleValue)))
                ).entrySet()
                .forEach(System.out::println);
        System.out.println("======================");

        //Determine the top-performing student in each grade level based on average score.
        students.parallelStream()
                .collect(Collectors.groupingBy(Student::getGradeLevel, Collectors.maxBy(Comparator.comparingDouble(student -> student.getScores()
                        .stream()
                        .mapToDouble(Double::doubleValue)
                        .average()
                        .orElse(0.0)))))
                .forEach((grade, optionalStudent) -> {
                    System.out.print("grade " + grade + " ");
                    if (optionalStudent.isPresent()){
                       var score =  optionalStudent.get()
                                .getScores()
                                .stream()
                                .mapToDouble(Double::doubleValue)
                                .average()
                                .orElse(0.0);

                        System.out.println("Top performing student " + optionalStudent.get().getName() + " with highest average score is "+ score);
                    }
                });

        System.out.println("======================");

        //List the names of students who have scored below a certain threshold in any subject.
        students.stream()
                .filter(student -> student
                        .getScores()
                        .stream()
                        .anyMatch(score-> score < 60.0))
                .map(Student::getName)
                .toList()
                .forEach(System.out::println);

        System.out.println("======================");

        //Group students by their grade level and then partition them
        // into pass and fail groups based on a passing score criteria.
        students.stream()
                .collect(Collectors.groupingBy(Student::getGradeLevel,
                        Collectors.partitioningBy(student ->
                                {
                                   var avg =  student.getScores()
                                            .stream()
                                            .mapToDouble(Double::doubleValue)
                                            .average()
                                            .orElse(0.0);
                                    return avg >= 65.0;
                                }
                        )))
                .forEach((mapKey,mapValue)  -> {
                    System.out.print("Grade " + mapKey + " student ");
                    mapValue.forEach((booleanMatch, studentList) -> {
                        if (booleanMatch){
                            System.out.print("qualified are ");
                            studentList.forEach(student -> System.out.print(student.getName() + " "));
                        }else {
                            System.out.print("failed are ");
                            studentList.forEach(student -> System.out.print(student.getName() + " "));
                        }
                        System.out.print("and ");
                    });
                    System.out.println();
                });

        System.out.println("======================");
    }

    public static void orderFulfillmentRelatedOperation(){
        //Calculate the total number of orders placed by each customer.
        List<Order> orders = OrderDao.getAllOrders();

        orders.stream()
                .collect(Collectors.groupingBy(Order::getCustomerName, Collectors.counting()))
                .entrySet()
                .forEach(System.out::println);

        System.out.println("======================");
    }

    public static void getIndexOf2Sum(List<Integer> nums, int target){
        var res = IntStream.range(0, nums.size())
                .boxed()
                .flatMap(integer -> IntStream.range(integer+1, nums.size())
                        .filter(j -> nums.get(integer) + nums.get(j) == target)
                        .mapToObj(j ->  new Pair(integer, j))
                )
                .findFirst();

        System.out.println(res.orElse(null));
    }

    public static void main(String[] args) {
        //orderFulfillmentRelatedOperation();
//        List<Integer> integers = List.of(2,6,1,3,8,9,10);
//        var max = integers.stream().mapToInt(i -> i).max().orElse(-1);
//        var min = integers.stream().mapToInt(i -> i).min().orElse(-1);
//
//        List<String> strings = List.of("Hello", "hi", "can be done", "work is important");
//        var result = strings.stream()
//                            .reduce((s1, s2) -> s1.length() > s2.length() ? s1 : s2)
//                            .get();

        //employeeRelatedOperation();
        //transactionRelatedOperation();
        //productRelatedOperation();
        //studentRelatedOperation();

//        String input = "hehe haho".replaceAll(" ", "");
//        var stringList = Arrays.asList(input.split(""));
//        stringList.stream()
//                .collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting()))
//                .entrySet()
//                //.filter(stringLongEntry -> stringLongEntry.getValue() == 1)
//                //.limit(1)
//                .forEach(System.out::println);

        //getIndexOf2Sum(List.of(2,7,20,12,23,44), 9);
        employeeRelatedOperation();
    }
}
