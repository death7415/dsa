import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
/*

Employee
   name
   salary

select e.name from Employee e order by desc limit 1 offset 1


 */

class Employee{
    public String name;

    public Employee(String name){
        this.name = name;
    }
}
public class Solution{
    public static void main(String[] args) {
        Map<Employee, Integer> map = new HashMap<>();
        map.put(new Employee("abc"), 1000);
        map.put(new Employee("xyz"), 2000);
        map.put(new Employee("tuv"), 1500);

        var res = map.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .toList();

        System.out.println(res);
    }
}