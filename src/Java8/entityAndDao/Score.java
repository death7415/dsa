package Java8.entityAndDao;

import lombok.Getter;
import lombok.Setter;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Score {
    // Getters and setters
    private String studentId;
    private String subject;
    private int marks;

    public Score(String studentId, String subject, int marks) {
        this.studentId = studentId;
        this.subject = subject;
        this.marks = marks;
    }

}


class ScoreDAO {
    public List<Score> getAllScores() {
        return Arrays.asList(
                new Score("S001", "Math", 85),
                new Score("S001", "Science", 78),
                new Score("S002", "Math", 90),
                new Score("S002", "Science", 88),
                new Score("S003", "Math", 95),
                new Score("S003", "Science", 80),
                new Score("S004", "Math", 75),
                new Score("S004", "Science", 70),
                new Score("S005", "Math", 65),
                new Score("S005", "Science", 60)
        );
    }
}
