package heap.priorityQueue;

import lombok.Data;

import java.util.List;

@Data
public class Student{
    private int studentId;
    private String name;
    private int gradeLevel;

    public Student(int studentId, String name, int gradeLevel) {
        this.studentId = studentId;
        this.name = name;
        this.gradeLevel = gradeLevel;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentId=" + studentId +
                ", name='" + name + '\'' +
                ", gradeLevel=" + gradeLevel +
                "}\n";
    }

//    @Override
//    public boolean equals(Object object){
//        if (object instanceof Student check){
//            return this.getGradeLevel() == check.getGradeLevel() && (this.getName().compareTo(check.getName())) == 0;
//        }
//        return false;
//    }
}

