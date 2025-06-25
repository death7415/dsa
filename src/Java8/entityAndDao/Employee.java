package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class Employee {
    private String id;
    private String department;
    private double salary;
    private int yearsOfExperience;
    private LocalDate joiningDate;

    public Employee(String id, String department, double salary, int yearsOfExperience, LocalDate joiningDate) {
        this.id = id;
        this.department = department;
        this.salary = salary;
        this.yearsOfExperience = yearsOfExperience;
        this.joiningDate = joiningDate;
    }

}

class EmployeeDao {

    public static List<Employee> getAllEmployees() {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E001", "Sales",      55000, 2, LocalDate.of(2024, 4, 23)));
        employees.add(new Employee("E002", "Sales",      65000, 4, LocalDate.of(2023, 7, 11)));
        employees.add(new Employee("E003", "HR",         60000, 3, LocalDate.of(2021, 2, 14)));
        employees.add(new Employee("E004", "Engineering",120000,5, LocalDate.of(2022, 8, 5)));
        employees.add(new Employee("E005", "Engineering",130000,7, LocalDate.of(2021, 12, 2)));
        employees.add(new Employee("E006", "Marketing",  50000, 2, LocalDate.of(2022, 6, 1)));
        return employees;
    }
}

