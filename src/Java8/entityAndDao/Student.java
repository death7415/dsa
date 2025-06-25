package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@ToString
public class Student {
    private int studentId;
    private String name;
    private int gradeLevel;
    private List<Double> scores;

    public Student(int studentId, String name, int gradeLevel, List<Double> scores) {
        this.studentId = studentId;
        this.name = name;
        this.gradeLevel = gradeLevel;
        this.scores = scores;
    }
}

// DAO class
class StudentDao {
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "Alice", 10, Arrays.asList(85.5, 90.0, 78.5)));
        students.add(new Student(2, "Bob", 10, Arrays.asList(58.0, 62.5, 70.0)));
        students.add(new Student(3, "Carol", 11, Arrays.asList(74.0, 80.0, 68.5)));
        students.add(new Student(4, "David", 11, Arrays.asList(92.0, 88.5, 91.0)));
        students.add(new Student(5, "Eve", 12, Arrays.asList(59.5, 61.0, 55.0)));
        students.add(new Student(6, "Frank", 12, Arrays.asList(88.0, 84.5, 90.0)));
        students.add(new Student(7, "Grace", 10, Arrays.asList(95.0, 93.5, 97.0)));
        students.add(new Student(8, "Hank", 11, Arrays.asList(45.0, 50.5, 48.0)));
        students.add(new Student(9, "Irene", 12, Arrays.asList(78.0, 82.0, 80.5)));
        students.add(new Student(10, "Jack", 10, Arrays.asList(65.0, 70.0, 68.0)));
        return students;
    }
}

