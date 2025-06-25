package Java8;

import Java8.entityAndDao.Recipe;
import lombok.Builder;
import lombok.Data;
import java.util.Comparator;
import java.util.List;

@Data
@Builder
class Employee{
    private String name;
    private int salary;
}

@Data
@Builder
class Department{
    private String name;
    private List<Employee> employees;
}

@Data
@Builder
class Book{
    private int id;
    private String name;
    private int pages;
}
class BookDAO{
    public List<Book> getBooks(){
        return List.of(Book.builder().name("Read").id(1).pages(234).build(),
                Book.builder().name("Wealth").id(2).pages(211).build(),
                Book.builder().name("Game").id(3).pages(444).build(),
                Book.builder().name("Money").id(4).pages(198).build()
                );
    }
}

public class JavaOperation {

    public static void getSalaryDescByDepartment(){
        var empList1 = List.of(Employee.builder().name("abc").salary(1).build(), Employee.builder().name("xyz").salary(2).build());
        var empList2 = List.of(Employee.builder().name("ffd").salary(1).build(), Employee.builder().name("rff").salary(4).build());
        var empList3 = List.of(Employee.builder().name("gch").salary(1).build(), Employee.builder().name("kpo").salary(7).build());
        var empList4 = List.of(Employee.builder().name("bbf").salary(1).build(), Employee.builder().name("mkf").salary(1).build());
        List<Department> departments = List.of(Department.builder().name("lab").employees(empList1).build(),
                Department.builder().employees(empList2).name("qa").build(),
                Department.builder().employees(empList3).name("uat").build(),
                Department.builder().employees(empList4).name("dev").build()
        );

        departments.stream()
                .sorted(Comparator.comparingInt(
                        (Department dept) -> dept.getEmployees()
                                .stream()
                                .mapToInt(Employee::getSalary)
                                .sum()
                ).reversed())
                .toList()
                .forEach(dept -> {
                    int totalSalary = dept.getEmployees()
                            .stream()
                            .mapToInt(Employee::getSalary)
                            .sum();
                    System.out.println("Department "+ dept.getName() +" Total Salary: " + totalSalary);
                });
    }

    public static void printSortedBooks(){
       var books =  new BookDAO().getBooks();
       books.stream()
               .sorted(Comparator.comparing((Book::getId)))
               .skip(1)
               .limit(1)
               .forEach(System.out::println);
    }

    public static void main(String[] args) {
        List<Integer> list = List.of(3,1,4,9,5,2);
        list.stream().sorted(Comparator.reverseOrder())
                .skip(1)
                .limit(1)
                .forEach(System.out::println);
    }
}
